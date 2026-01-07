package com.ihaveGPU.remake.security

import com.ihaveGPU.remake.user.repository.UserRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter @Autowired constructor(
  private val jwtUtil: JwtUtil,
  private val userRepository: UserRepository
) : OncePerRequestFilter() {

  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    filterChain: FilterChain
  ) {
    try {
      val authHeader = request.getHeader("Authorization")

      if (authHeader != null && authHeader.startsWith("Bearer ")) {
        val token = authHeader.substring(7)
        val email = jwtUtil.extractEmail(token)

        if (email != null && SecurityContextHolder.getContext().authentication == null) {
          val user = userRepository.findByEmailAndIsDeletedFalse(email)

          if (user != null && jwtUtil.validateToken(token, email)) {
            val authorities = listOf(SimpleGrantedAuthority("ROLE_${user.role.name}"))
            val authentication = UsernamePasswordAuthenticationToken(
              email,
              null,
              authorities
            )
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
          }
        }
      }
    } catch (e: Exception) {
      logger.error("Cannot set user authentication: ${e.message}")
    }

    filterChain.doFilter(request, response)
  }
}

