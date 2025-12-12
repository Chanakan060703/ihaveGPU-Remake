package com.ihaveGPU.remake.type.service

import com.ihaveGPU.remake.type.dto.TypeDto
import com.ihaveGPU.remake.type.request.CreateTypesRequest
import com.ihaveGPU.remake.type.request.UpdateTypeRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TypeService {
  fun getTypeAll(): List<TypeDto>

  fun getTypeById(id: Long): TypeDto
  fun createType(request: CreateTypesRequest): TypeDto
  fun updateType(request: UpdateTypeRequest): TypeDto
  fun deleteType(id: Long): Boolean

  fun getListTypePage(
    pageable: Pageable,
    search: String?,
    isAsc: Boolean
  ): Page<TypeDto>
}