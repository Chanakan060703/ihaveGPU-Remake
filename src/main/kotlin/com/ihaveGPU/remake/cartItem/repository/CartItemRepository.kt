package com.ihaveGPU.remake.cartItem.repository

import com.ihaveGPU.remake.entity.CartItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartItemRepository: JpaRepository<CartItem, Long> {
  fun findAllByIsDeletedFalse(): List<CartItem>
  fun findByIdAndIsDeletedFalse(id: Long): CartItem?
}