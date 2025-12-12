package com.ihaveGPU.remake.type.service.impl

import com.ihaveGPU.remake.common.exception.NotFoundException
import com.ihaveGPU.remake.entity.Type
import com.ihaveGPU.remake.type.dto.TypeDto
import com.ihaveGPU.remake.type.repository.TypeRepository
import com.ihaveGPU.remake.type.request.CreateTypesRequest
import com.ihaveGPU.remake.type.request.UpdateTypeRequest
import com.ihaveGPU.remake.type.service.TypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service

@Service
class TypeServiceImpl @Autowired constructor(
  private val typeRepository: TypeRepository
): TypeService {
  override fun getTypeAll(): List<TypeDto> {
    return typeRepository.findAllByIsDeletedFalse().map { TypeDto(it.id, it.typeName, it.iconClass) }
  }

  override fun getTypeById(id: Long): TypeDto {
    val type = typeRepository.findByIdAndIsDeletedFalse(id)
        ?: throw NotFoundException("Type not found")
    return TypeDto(type.id, type.typeName, type.iconClass)
  }

  override fun createType(request: CreateTypesRequest): TypeDto {
    val type = Type(
      typeName = request.typeName,
      iconClass = request.iconClass
    )
    typeRepository.save(type)
    return TypeDto(type.id, type.typeName, type.iconClass)

  }

  override fun updateType(request: UpdateTypeRequest): TypeDto {
    val type = typeRepository.findByIdAndIsDeletedFalse(request.id)
        ?: throw NotFoundException("Type not found")
    type.typeName = request.typeName
    type.iconClass = request.iconClass
    typeRepository.save(type)
    return TypeDto(type.id, type.typeName, type.iconClass)
  }

  override fun deleteType(id: Long): Boolean {
    val type = typeRepository.findById(id)
        .orElseThrow { NotFoundException("Type not found") }

    type.isDeleted = true
    typeRepository.save(type)

    return true
  }

  override fun getListTypePage(
    pageable: Pageable,
    search: String?,
    isAsc: Boolean
  ): Page<TypeDto> {
  return typeRepository.findGetListTypePage(
    pageable,
      search,
      isAsc
    )
  }
}