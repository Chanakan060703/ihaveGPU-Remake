package com.ihaveGPU.remake.type.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class UpdateTypeRequest {
  @field:NotNull(message = "Width is required")
  @Min(1, message = "Id must be greater than 0")
  var id: Long = 0

  @field:NotBlank(message = "Height is required")
  val typeName: String = ""

  val iconClass: String = ""
}