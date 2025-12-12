package com.ihaveGPU.remake.type.repository

import com.ihaveGPU.remake.type.dto.TypeDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TypeRepositoryCustom {
  fun findGetListTypePage(
    page: Pageable,
    search: String?,
    isAsc: Boolean
  ): Page<TypeDto>
}