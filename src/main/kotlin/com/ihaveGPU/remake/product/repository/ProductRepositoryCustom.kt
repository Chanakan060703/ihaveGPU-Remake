package com.ihaveGPU.remake.product.repository

import com.ihaveGPU.remake.product.dto.ProductDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductRepositoryCustom {
  fun findGetListProductPage(
    page: Pageable,
    search: String?,
    isAsc: Boolean
  ): Page<ProductDto>
}