package com.ihaveGPU.remake.user.repository

import com.ihaveGPU.remake.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String> {
  fun findAllByIsDeletedFalse(): List<User>
  fun findByEmailAndIsDeletedFalse(email: String): User?
}

