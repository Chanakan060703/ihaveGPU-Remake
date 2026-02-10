package com.ihaveGPU.remake.config

import com.ihaveGPU.remake.security.JwtAuthenticationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig @Autowired constructor(
  private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {

  @Bean
  fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
    http
      .csrf { it.disable() }
      .cors { it.disable() }
      .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
      .authorizeHttpRequests { auth ->
        auth
          // Public endpoints
          .requestMatchers("/api/auth/**").permitAll()
          .requestMatchers("/swagger-ui/**", "/api-docs/**", "/v3/api-docs/**").permitAll()
          // Admin only endpoints
          .requestMatchers("/api/users/**").hasRole("ADMIN")
          .requestMatchers("/api/types/**").hasRole("ADMIN")
          // Authenticated endpoints
          .requestMatchers("/api/cart-items/**").authenticated()
          .requestMatchers("/api/receipts/**").authenticated()
          .requestMatchers("/api/addresses/**").authenticated()
          // Public product browsing
          .requestMatchers("/api/products/**").permitAll()
          .anyRequest().authenticated()
      }
      .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

    return http.build()
  }
}

