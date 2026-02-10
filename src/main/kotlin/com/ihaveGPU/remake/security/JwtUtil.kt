package com.ihaveGPU.remake.security

import com.ihaveGPU.remake.config.JwtConfig
import com.ihaveGPU.remake.entity.UserRole
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtUtil @Autowired constructor(
  private val jwtConfig: JwtConfig
) {
  private val secretKey: SecretKey by lazy {
    Keys.hmacShaKeyFor(jwtConfig.secret.toByteArray())
  }

  fun generateToken(email: String, role: UserRole): String {
    val now = Date()
    val expiryDate = Date(now.time + jwtConfig.expiration)

    return Jwts.builder()
      .subject(email)
      .claim("role", role.name)
      .issuedAt(now)
      .expiration(expiryDate)
      .signWith(secretKey)
      .compact()
  }

  fun extractEmail(token: String): String? {
    return try {
      extractClaims(token).subject
    } catch (e: Exception) {
      null
    }
  }

  fun extractRole(token: String): UserRole? {
    return try {
      val role = extractClaims(token).get("role", String::class.java)
      UserRole.valueOf(role)
    } catch (e: Exception) {
      null
    }
  }

  fun validateToken(token: String, email: String): Boolean {
    return try {
      val extractedEmail = extractEmail(token)
      extractedEmail == email && !isTokenExpired(token)
    } catch (e: Exception) {
      false
    }
  }

  private fun extractClaims(token: String): Claims {
    return Jwts.parser()
      .verifyWith(secretKey)
      .build()
      .parseSignedClaims(token)
      .payload
  }

  private fun isTokenExpired(token: String): Boolean {
    return try {
      val expiration = extractClaims(token).expiration
      expiration.before(Date())
    } catch (e: Exception) {
      true
    }
  }
}

