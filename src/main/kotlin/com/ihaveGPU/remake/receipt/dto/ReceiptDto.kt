package com.ihaveGPU.remake.receipt.dto

data class ReceiptDto(
  val id: Long = 0,
  val date: String = "",
  val total: Double = 0.0,
  val email: String = "",
  val addressId: Long = 0
)

