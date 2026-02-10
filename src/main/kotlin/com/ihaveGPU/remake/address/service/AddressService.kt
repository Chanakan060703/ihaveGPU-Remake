package com.ihaveGPU.remake.address.service

import com.ihaveGPU.remake.address.dto.AddressDto
import com.ihaveGPU.remake.address.request.CreateAddressRequest
import com.ihaveGPU.remake.address.request.UpdateAddressRequest

interface AddressService {
  fun getAddressAll(): List<AddressDto>
  fun getAddressById(id: Long): AddressDto
  fun createAddress(request: CreateAddressRequest): AddressDto
  fun updateAddress(request: UpdateAddressRequest): AddressDto
  fun deleteAddress(id: Long): Boolean
}

