package com.ihaveGPU.remake.address.service.impl

import com.ihaveGPU.remake.address.dto.AddressDto
import com.ihaveGPU.remake.address.repository.AddressRepository
import com.ihaveGPU.remake.address.request.CreateAddressRequest
import com.ihaveGPU.remake.address.request.UpdateAddressRequest
import com.ihaveGPU.remake.address.service.AddressService
import com.ihaveGPU.remake.common.exception.NotFoundException
import com.ihaveGPU.remake.entity.Address
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl @Autowired constructor(
  private val addressRepository: AddressRepository
): AddressService {
  override fun getAddressAll(): List<AddressDto> {
    return addressRepository.findAllByIsDeletedFalse().map { it.toAddressDto() }
  }

  override fun getAddressById(id: Long): AddressDto {
    val address = addressRepository.findByIdAndIsDeletedFalse(id)
      ?: throw NotFoundException("Address not found")
    return address.toAddressDto()
  }

  override fun createAddress(request: CreateAddressRequest): AddressDto {
    val address = Address(
      houseNumber = request.houseNumber,
      province = request.province,
      building = request.building,
      district = request.district,
      subDistrict = request.subDistrict
    )
    addressRepository.save(address)
    return address.toAddressDto()
  }

  override fun updateAddress(request: UpdateAddressRequest): AddressDto {
    val address = addressRepository.findByIdAndIsDeletedFalse(request.id)
      ?: throw NotFoundException("Address not found")

    address.houseNumber = request.houseNumber
    address.province = request.province
    address.building = request.building
    address.district = request.district
    address.subDistrict = request.subDistrict
    addressRepository.save(address)
    return address.toAddressDto()
  }

  override fun deleteAddress(id: Long): Boolean {
    val address = addressRepository.findById(id)
      .orElseThrow { NotFoundException("Address not found") }

    address.isDeleted = true
    addressRepository.save(address)

    return true
  }
}

