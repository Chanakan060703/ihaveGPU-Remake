package com.ihaveGPU.remake.user.dto

import com.ihaveGPU.remake.entity.UserRole

data class UserDto(
  val email: String = "",
  val firstName: String = "",
  val lastName: String = "",
  val dateOfBirth: String = "",
  val imageUrl: String? = null,
  val role: UserRole = UserRole.USER
)

