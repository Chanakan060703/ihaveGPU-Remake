package com.ihaveGPU.remake.user.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

class UpdateUserRequest {
  @field:NotBlank(message = "Email is required")
  @field:Email(message = "Email must be valid")
  val email: String = ""

  @field:NotBlank(message = "First name is required")
  val firstName: String = ""

  @field:NotBlank(message = "Last name is required")
  val lastName: String = ""

  @field:NotBlank(message = "Date of birth is required")
  val dateOfBirth: String = ""

  val imageUrl: String? = null
}

