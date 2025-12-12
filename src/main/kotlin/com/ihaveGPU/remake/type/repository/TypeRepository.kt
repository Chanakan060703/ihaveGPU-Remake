package com.ihaveGPU.remake.type.repository

import com.ihaveGPU.remake.entity.Type
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TypeRepository: JpaRepository<Type, Long>, TypeRepositoryCustom {
  fun findAllByIsDeletedFalse(): List<Type>
  fun findByIdAndIsDeletedFalse(id: Long): Type?
}