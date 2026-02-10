package com.ihaveGPU.remake.auth.service.impl

import com.ihaveGPU.remake.auth.request.LoginRequest
import com.ihaveGPU.remake.auth.request.RegisterRequest
import com.ihaveGPU.remake.auth.response.LoginResponse
import com.ihaveGPU.remake.auth.service.AuthService
import com.ihaveGPU.remake.common.exception.BadRequestException
import com.ihaveGPU.remake.common.exception.NotFoundException
import com.ihaveGPU.remake.entity.PasswordUtil
import com.ihaveGPU.remake.entity.User
import com.ihaveGPU.remake.entity.UserRole
import com.ihaveGPU.remake.security.JwtUtil
import com.ihaveGPU.remake.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl @Autowired constructor(
  private val userRepository: UserRepository,
  private val jwtUtil: JwtUtil
): AuthService {

  override fun login(request: LoginRequest): LoginResponse {
    val user = userRepository.findByEmailAndIsDeletedFalse(request.email)
      ?: throw NotFoundException("User not found or invalid credentials")

    if (!PasswordUtil.verifyPassword(request.password, user.password)) {
      throw BadRequestException("Invalid credentials")
    }

    val token = jwtUtil.generateToken(user.email, user.role)

    return LoginResponse(
      token = token,
      email = user.email,
      firstName = user.firstName,
      lastName = user.lastName,
      role = user.role
    )
  }

  override fun register(request: RegisterRequest): LoginResponse {
    val existingUser = userRepository.findByEmailAndIsDeletedFalse(request.email)
    if (existingUser != null) {
      throw BadRequestException("User with email ${request.email} already exists")
    }

    val hashedPassword = PasswordUtil.hashPassword(request.password)

    val newUser = User(
      email = request.email,
      firstName = request.firstName,
      lastName = request.lastName,
      password = hashedPassword,
      dateOfBirth = request.dateOfBirth,
      imageUrl = request.imageUrl,
      role = UserRole.USER
    )

    userRepository.save(newUser)

    val token = jwtUtil.generateToken(newUser.email, newUser.role)

    return LoginResponse(
      token = token,
      email = newUser.email,
      firstName = newUser.firstName,
      lastName = newUser.lastName,
      role = newUser.role
    )
  }
}

