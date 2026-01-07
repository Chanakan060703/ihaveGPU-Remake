package com.ihaveGPU.remake.auth.service

import com.ihaveGPU.remake.auth.request.LoginRequest
import com.ihaveGPU.remake.auth.request.RegisterRequest
import com.ihaveGPU.remake.auth.response.LoginResponse

interface AuthService {
  fun login(request: LoginRequest): LoginResponse
  fun register(request: RegisterRequest): LoginResponse
}

