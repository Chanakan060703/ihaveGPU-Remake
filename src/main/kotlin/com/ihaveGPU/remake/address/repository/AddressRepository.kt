package com.ihaveGPU.remake.address.repository

import com.ihaveGPU.remake.entity.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<Address, Long> {
  fun findAllByIsDeletedFalse(): List<Address>
  fun findByIdAndIsDeletedFalse(id: Long): Address?
}

