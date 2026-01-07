package com.ihaveGPU.remake.address.request

import jakarta.validation.constraints.NotBlank

class CreateAddressRequest {
  @field:NotBlank(message = "House number is required")
  val houseNumber: String = ""

  @field:NotBlank(message = "Province is required")
  val province: String = ""

  @field:NotBlank(message = "Building is required")
  val building: String = ""

  @field:NotBlank(message = "District is required")
  val district: String = ""

  @field:NotBlank(message = "Sub district is required")
  val subDistrict: String = ""
}

