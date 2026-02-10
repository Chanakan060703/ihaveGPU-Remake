package com.ihaveGPU.remake.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtConfig {
  var secret: String = "ihaveGPUremakeSecretKeyForJWTTokenGenerationAndValidation2024"
  var expiration: Long = 86400000 // 24 hours in milliseconds
}

