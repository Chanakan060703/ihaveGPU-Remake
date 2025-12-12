package com.ihaveGPU.remake.type.service.impl

import com.ihaveGPU.remake.entity.QType
import com.ihaveGPU.remake.type.dto.TypeDto
import com.ihaveGPU.remake.type.repository.TypeRepositoryCustom
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TypeServiceRepositoryImpl: TypeRepositoryCustom {
  @PersistenceContext
  lateinit var entityManager: EntityManager
  lateinit var queryFactory: JPAQueryFactory

  @PostConstruct
  fun init() {
    queryFactory = JPAQueryFactory(entityManager)
  }

  private val qType = QType.type
  override fun findGetListTypePage(
    pageable: Pageable,
    search: String?,
    isAsc: Boolean
  ): Page<TypeDto> {
    var criteria = qType.isDeleted.eq(false)
    if (search != null) {
      criteria = criteria.and(qType.typeName.containsIgnoreCase(search))
    }

    val orderBy = if (isAsc) {
      qType.id.asc()
    } else {
      qType.id.desc()
    }

    val query = queryFactory
      .select(qType)
      .from(qType)
      .where(criteria)
      .orderBy(orderBy)
      .offset(pageable.offset)
      .limit(pageable.pageSize.toLong())
      .fetch().map { it.toServiceLayDto() }

    val total = queryFactory
      .select(qType)
      .from(qType)
      .where(criteria)
      .fetch().size.toLong()

    return PageImpl(query, pageable, total)
  }

}