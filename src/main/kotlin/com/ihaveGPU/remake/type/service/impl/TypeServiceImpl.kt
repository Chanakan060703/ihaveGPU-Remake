package com.ihaveGPU.remake.type.service.impl

import com.ihaveGPU.remake.entity.Type
import com.ihaveGPU.remake.type.dto.TypeDto
import com.ihaveGPU.remake.type.repository.TypeRepository
import com.ihaveGPU.remake.type.service.TypeService
import com.luca.intern.common.exception.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TypeServiceRepository @Autowired constructor(
  private val typeRepository: TypeRepository
): TypeService {
  override fun getTypeAll(): List<TypeDto> {
    return typeRepository.findAll().map { TypeDto(it.id, it.typeName, it.iconClass) }
  }

  override fun getTypeById(id: Long): TypeDto {
    val type = typeRepository.findById(id)
        .orElseThrow { NotFoundException("Type not found") }
    return TypeDto(type.id, type.typeName, type.iconClass)
  }

  override fun createType(typeDto: TypeDto): TypeDto {
    val type = Type(
      typeName = typeDto.typeName,
      iconClass = typeDto.iconClass
    )
    typeRepository.save(type)
    return TypeDto(type.id, type.typeName, type.iconClass)

  }

  override fun updateType(id: Long, typeDto: TypeDto): TypeDto {
    val type = typeRepository.findById(id)
        .orElseThrow { NotFoundException("Type not found") }

    typeRepository.save(type)
    return TypeDto(type.id, type.typeName, type.iconClass)


  }

  override fun deleteType(id: Long): Boolean {
    val type = typeRepository.findById(id)
        .orElseThrow { NotFoundException("Type not found") }

    type.isDeleted = true
    typeRepository.save(type)
    TODO("Not yet implemented")
  }

}