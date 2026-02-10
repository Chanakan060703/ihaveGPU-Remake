package com.ihaveGPU.remake.auth.response

import com.ihaveGPU.remake.entity.UserRole

data class LoginResponse(
  val token: String,
  val email: String,
  val firstName: String,
  val lastName: String,
  val role: UserRole
)

