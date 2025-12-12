package com.ihaveGPU.type.repository

import com.ihaveGPU.remake.entity.Type
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TypeRepository: JpaRepository<Type, Long> {

}