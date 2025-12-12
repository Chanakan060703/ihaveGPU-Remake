package com.ihaveGPU.type.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

class UpdateTypeRequest {
  @field:NotBlank(message = "Width is required")
  @Min(1, message = "Id must be greater than 0")
  val id: Long = 0

  @field:NotBlank(message = "Height is required")
  val typeName: String = ""

  @field:NotBlank(message = "Height is required")
  val iconClass: String = ""
}