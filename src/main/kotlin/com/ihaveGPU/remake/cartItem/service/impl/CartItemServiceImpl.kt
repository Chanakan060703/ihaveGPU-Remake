package com.ihaveGPU.remake.cartItem.service.impl

import com.ihaveGPU.remake.cartItem.dto.CartItemDto
import com.ihaveGPU.remake.cartItem.repository.CartItemRepository
import com.ihaveGPU.remake.cartItem.request.CreateCartItemRequest
import com.ihaveGPU.remake.cartItem.request.UpdateCartItemRequest
import com.ihaveGPU.remake.cartItem.service.CartItemService
import com.ihaveGPU.remake.common.exception.NotFoundException
import com.ihaveGPU.remake.entity.CartItem
import com.ihaveGPU.remake.product.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CartItemServiceImpl @Autowired constructor(
  private val cartItemRepository: CartItemRepository,
  private val productRepository: ProductRepository
): CartItemService {
  override fun getCartItemAll(): List<CartItemDto> {
    return cartItemRepository.findAllByIsDeletedFalse().map { it.toCartItemDto() }
  }

  override fun getCartItemById(id: Long): CartItemDto {
    val cartItem = cartItemRepository.findByIdAndIsDeletedFalse(id)
      ?: throw NotFoundException("CartItem not found")
    return cartItem.toCartItemDto()
  }

  override fun createCartItem(request: CreateCartItemRequest): CartItemDto {
    val product = productRepository.findByIdAndIsDeletedFalse(request.productId)
      ?: throw NotFoundException("Product not found")

    val cartItem = CartItem(
      quantity = request.quantity,
      productId = product.id
    )
    cartItemRepository.save(cartItem)
    return cartItem.toCartItemDto()
  }

  override fun updateCartItem(request: UpdateCartItemRequest): CartItemDto {
    val cartItem = cartItemRepository.findByIdAndIsDeletedFalse(request.id)
      ?: throw NotFoundException("CartItem not found")
    val product = productRepository.findByIdAndIsDeletedFalse(request.productId)
      ?: throw NotFoundException("Product not found")

    cartItem.quantity = request.quantity
    cartItem.productId = product.id
    cartItemRepository.save(cartItem)
    return cartItem.toCartItemDto()
  }

  override fun deleteCartItem(id: Long): Boolean {
    val cartItem = cartItemRepository.findById(id)
      .orElseThrow { NotFoundException("CartItem not found") }

    cartItem.isDeleted = true
    cartItemRepository.save(cartItem)

    return true
  }
}

