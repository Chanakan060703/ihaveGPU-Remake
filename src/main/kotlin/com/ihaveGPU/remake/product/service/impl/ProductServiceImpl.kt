package com.ihaveGPU.remake.product.service.impl

import com.ihaveGPU.remake.common.exception.NotFoundException
import com.ihaveGPU.remake.entity.product
import com.ihaveGPU.remake.product.dto.ProductDto
import com.ihaveGPU.remake.product.repository.ProductRepository
import com.ihaveGPU.remake.product.request.CreateProductRequest
import com.ihaveGPU.remake.product.request.UpdateProductRequest
import com.ihaveGPU.remake.product.service.ProductService
import com.ihaveGPU.remake.type.repository.TypeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl  @Autowired constructor(
  private val productRepository: ProductRepository,
  private val typeRepository: TypeRepository
): ProductService {
  override fun getProductAll(): List<ProductDto> {
    return productRepository.findAllByIsDeletedFalse().map { it.toProductDto() }
  }

  override fun getProductById(id: Long): ProductDto {
    val product = productRepository.findByIdAndIsDeletedFalse(id)
        ?: throw NotFoundException("Product not found")
    return product.toProductDto()
  }

  override fun createProduct(request: CreateProductRequest): ProductDto {
    val product = product(
      productName = request.productName,
      price = request.price,
      detail = request.detail,
      productQty = request.productQty,
      brand = request.brand,
      picProduct = request.picProduct,
      typeId = request.typeId
    )
    productRepository.save(product)
    return product.toProductDto()
  }

  override fun updateProduct(request: UpdateProductRequest): ProductDto {
    val product = productRepository.findByIdAndIsDeletedFalse(request.id)
        ?: throw NotFoundException("Product not found")
    val type = typeRepository.findByIdAndIsDeletedFalse(request.typeId)
        ?: throw NotFoundException("Type not found")

    product.productName = request.productName
    product.price = request.price
    product.detail = request.detail
    product.productQty = request.productQty
    product.brand = request.brand
    product.picProduct = request.picProduct
    product.typeId = type.id
    productRepository.save(product)
    return product.toProductDto()
  }

  override fun deleteProduct(id: Long): Boolean {
    val product = productRepository.findById(id)
        .orElseThrow { NotFoundException("Product not found") }

    product.isDeleted = true
    productRepository.save(product)

    return true
  }

}