package com.ihaveGPU.remake.product.dto

data class ProductDto (
  val id: Long = 0,
  val productName: String = "",
  val price: Double = 0.0,
  val detail: String = "",
  val productQty: Int = 0,
  val brand: String = "",
  val picProduct: String = "",
  val typeId: Long = 0
)