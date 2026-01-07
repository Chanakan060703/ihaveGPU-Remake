package com.ihaveGPU.remake.address.dto

data class AddressDto(
  val id: Long = 0,
  val houseNumber: String = "",
  val province: String = "",
  val building: String = "",
  val district: String = "",
  val subDistrict: String = ""
)

