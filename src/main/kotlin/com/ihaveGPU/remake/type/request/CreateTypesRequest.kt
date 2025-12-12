package com.ihaveGPU.type.request

import jakarta.validation.constraints.NotBlank

class CreateTypesRequest {
  @field:NotBlank(message = "Width is required")
  val typeName: String = ""

  @field:NotBlank(message = "Height is required")
  val iconClass: String = ""
}