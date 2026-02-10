package com.ihaveGPU.remake.cartItem.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

class CreateCartItemRequest {
  @field:NotNull(message = "Quantity is required")
  @field:Min(1, message = "Quantity must be at least 1")
  val quantity: Int = 0

  @field:NotNull(message = "Product ID is required")
  @field:Min(1, message = "Product ID must be greater than 0")
  val productId: Long = 0
}

