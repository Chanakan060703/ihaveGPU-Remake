package com.ihaveGPU.remake.auth.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate

class RegisterRequest {
  @field:NotBlank(message = "Email is required")
  @field:Email(message = "Email must be valid")
  val email: String = ""

  @field:NotBlank(message = "First name is required")
  val firstName: String = ""

  @field:NotBlank(message = "Last name is required")
  val lastName: String = ""

  @field:NotBlank(message = "Password is required")
  @field:Size(min = 8, message = "Password must be at least 8 characters")
  val password: String = ""

  @field:NotNull(message = "Date of birth is required")
  val dateOfBirth: LocalDate? = null

  val imageUrl: String? = null
}

