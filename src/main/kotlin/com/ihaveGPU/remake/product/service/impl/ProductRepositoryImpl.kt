package com.ihaveGPU.remake.product.service.impl

import com.ihaveGPU.remake.entity.Qproduct
import com.ihaveGPU.remake.product.dto.ProductDto
import com.ihaveGPU.remake.product.repository.ProductRepositoryCustom
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductRepositoryImpl: ProductRepositoryCustom {

  @PersistenceContext
  lateinit var entityManager: EntityManager
  lateinit var queryFactory: JPAQueryFactory

  @PostConstruct
  fun init() {
    queryFactory = JPAQueryFactory(entityManager)
  }

  private val qProduct = Qproduct.product
  override fun findGetListProductPage(
    page: Pageable,
    search: String?,
    isAsc: Boolean
  ): Page<ProductDto> {
    var criteria = qProduct.isDeleted.eq(false)
    if (search != null) {
      criteria = criteria.and(qProduct.productName.containsIgnoreCase(search))
    }

    val orderBy = if (isAsc) {
      qProduct.id.asc()
    } else {
      qProduct.id.desc()
    }

    val query = queryFactory
      .select(qProduct)
      .from(qProduct)
      .where(criteria)
      .orderBy(orderBy)
      .offset(page.offset)
      .limit(page.pageSize.toLong())
      .fetch().map { it.toProductDto() }

    val total = queryFactory
      .select(qProduct)
      .from(qProduct)
      .where(criteria)
      .fetch().size.toLong()

    return PageImpl(query, page, total)
  }


}