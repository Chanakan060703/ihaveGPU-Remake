package com.ihaveGPU.remake.product.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class CreateProductRequest {
  @field:NotBlank(message = "Width is required")
  val productName: String = ""

  val price: Double = 0.0

  @field:NotBlank(message = "Height is required")
  val detail: String = ""

  val productQty: Int = 0
  @field:NotBlank(message = "Height is required")

  val brand: String = ""

  val picProduct: String = ""
  @field:NotNull(message = "Height is required")
  var typeId: Long = 0
}