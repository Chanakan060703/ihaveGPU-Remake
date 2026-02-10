package com.ihaveGPU.remake.user.service.impl

import com.ihaveGPU.remake.common.exception.BadRequestException
import com.ihaveGPU.remake.common.exception.NotFoundException
import com.ihaveGPU.remake.entity.PasswordUtil
import com.ihaveGPU.remake.entity.User
import com.ihaveGPU.remake.user.dto.UserDto
import com.ihaveGPU.remake.user.repository.UserRepository
import com.ihaveGPU.remake.user.request.CreateUserRequest
import com.ihaveGPU.remake.user.request.UpdateUserRequest
import com.ihaveGPU.remake.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl @Autowired constructor(
  private val userRepository: UserRepository
): UserService {
  override fun getUserAll(): List<UserDto> {
    return userRepository.findAllByIsDeletedFalse().map { it.toUserDto() }
  }

  override fun getUserByEmail(email: String): UserDto {
    val user = userRepository.findByEmailAndIsDeletedFalse(email)
      ?: throw NotFoundException("User not found")
    return user.toUserDto()
  }

  override fun createUser(request: CreateUserRequest): UserDto {
    // Check if user already exists
    val existingUser = userRepository.findByEmailAndIsDeletedFalse(request.email)
    if (existingUser != null) {
      throw BadRequestException("User with email ${request.email} already exists")
    }

    val hashedPassword = PasswordUtil.hashPassword(request.password)
    val user = User(
      email = request.email,
      firstName = request.firstName,
      lastName = request.lastName,
      password = hashedPassword,
      dateOfBirth = request.dateOfBirth,
      imageUrl = request.imageUrl
    )
    userRepository.save(user)
    return user.toUserDto()
  }

  override fun updateUser(request: UpdateUserRequest): UserDto {
    val user = userRepository.findByEmailAndIsDeletedFalse(request.email)
      ?: throw NotFoundException("User not found")

    user.firstName = request.firstName
    user.lastName = request.lastName
    user.dateOfBirth = request.dateOfBirth
    user.imageUrl = request.imageUrl
    userRepository.save(user)
    return user.toUserDto()
  }

  override fun deleteUser(email: String): Boolean {
    val user = userRepository.findById(email)
      .orElseThrow { NotFoundException("User not found") }

    user.isDeleted = true
    userRepository.save(user)

    return true
  }
}

