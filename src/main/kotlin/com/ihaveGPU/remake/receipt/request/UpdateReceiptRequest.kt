package com.ihaveGPU.remake.receipt.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class UpdateReceiptRequest {
  @field:NotNull(message = "Id is required")
  @field:Min(1, message = "Id must be greater than 0")
  val id: Long = 0

  @field:NotBlank(message = "Date is required")
  val date: String = ""

  @field:NotNull(message = "Total is required")
  @field:Min(0, message = "Total must be at least 0")
  val total: Double = 0.0

  @field:NotBlank(message = "Email is required")
  @field:Email(message = "Email must be valid")
  val email: String = ""

  @field:NotNull(message = "Address ID is required")
  @field:Min(1, message = "Address ID must be greater than 0")
  val addressId: Long = 0
}

