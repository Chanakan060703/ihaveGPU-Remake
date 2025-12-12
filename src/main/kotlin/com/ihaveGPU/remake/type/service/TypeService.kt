package com.ihaveGPU.type.service

import com.ihaveGPU.type.dto.TypeDto

interface TypeService {
  fun getTypeAll(): List<TypeDto>

  fun getTypeById(id: Long): TypeDto
  fun createType(request: TypeDto): TypeDto
  fun updateType(request: Long, typeDto: TypeDto): TypeDto
  fun deleteType(id: Long): Boolean
}