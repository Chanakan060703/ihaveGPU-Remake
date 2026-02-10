package com.ihaveGPU.remake.user.service

import com.ihaveGPU.remake.user.dto.UserDto
import com.ihaveGPU.remake.user.request.CreateUserRequest
import com.ihaveGPU.remake.user.request.UpdateUserRequest

interface UserService {
  fun getUserAll(): List<UserDto>
  fun getUserByEmail(email: String): UserDto
  fun createUser(request: CreateUserRequest): UserDto
  fun updateUser(request: UpdateUserRequest): UserDto
  fun deleteUser(email: String): Boolean
}

