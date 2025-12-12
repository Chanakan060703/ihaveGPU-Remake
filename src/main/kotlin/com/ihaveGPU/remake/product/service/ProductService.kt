package com.ihaveGPU.remake.product.service

import com.ihaveGPU.remake.product.dto.ProductDto
import com.ihaveGPU.remake.product.request.CreateProductRequest
import com.ihaveGPU.remake.product.request.UpdateProductRequest

interface ProductService {
  fun getProductAll(): List<ProductDto>
  fun getProductById(id: Long): ProductDto
  fun createProduct(request: CreateProductRequest): ProductDto
  fun updateProduct(request: UpdateProductRequest): ProductDto
  fun deleteProduct(id: Long): Boolean
}