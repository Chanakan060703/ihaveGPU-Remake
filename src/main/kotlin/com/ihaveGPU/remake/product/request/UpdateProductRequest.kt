package com.ihaveGPU.remake.product.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class UpdateProductRequest {
  @field:NotNull(message = "Id is required")
  @field:Min(1, message = "Id must be greater than 0")
  var id: Long = 0

  @field:NotBlank(message = "Width is required")
  val productName: String = ""
  val price: Double = 0.0

  @field:NotBlank(message = "Height is required")
  val detail: String = ""

  val productQty: Int = 0

  @field:NotBlank(message = "Height is required")
  val brand: String = ""

  val picProduct: String = ""

  val typeId: Long = 0
}