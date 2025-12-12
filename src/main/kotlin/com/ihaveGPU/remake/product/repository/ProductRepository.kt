package com.ihaveGPU.remake.product.repository

import com.ihaveGPU.remake.entity.product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<product, Long> {
  fun findAllByIsDeletedFalse(): List<product>
  fun findByIdAndIsDeletedFalse(id: Long): product?
}