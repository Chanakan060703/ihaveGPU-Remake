package com.ihaveGPU.remake.cartItem.service

import com.ihaveGPU.remake.cartItem.dto.CartItemDto
import com.ihaveGPU.remake.cartItem.request.CreateCartItemRequest
import com.ihaveGPU.remake.cartItem.request.UpdateCartItemRequest

interface CartItemService {
  fun getCartItemAll(): List<CartItemDto>
  fun getCartItemById(id: Long): CartItemDto
  fun createCartItem(request: CreateCartItemRequest): CartItemDto
  fun updateCartItem(request: UpdateCartItemRequest): CartItemDto
  fun deleteCartItem(id: Long): Boolean
}

