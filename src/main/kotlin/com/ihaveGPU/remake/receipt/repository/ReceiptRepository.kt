package com.ihaveGPU.remake.receipt.repository

import com.ihaveGPU.remake.entity.Receipt
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReceiptRepository : JpaRepository<Receipt, Long> {
  fun findAllByIsDeletedFalse(): List<Receipt>
  fun findByIdAndIsDeletedFalse(id: Long): Receipt?
  fun findAllByEmailAndIsDeletedFalse(email: String): List<Receipt>
}

