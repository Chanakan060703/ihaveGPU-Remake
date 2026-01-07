package com.ihaveGPU.remake.auth.controller

import com.ihaveGPU.remake.auth.request.LoginRequest
import com.ihaveGPU.remake.auth.request.RegisterRequest
import com.ihaveGPU.remake.auth.service.AuthService
import com.ihaveGPU.remake.common.exception.BadRequestException
import com.ihaveGPU.remake.common.exception.NotFoundException
import com.ihaveGPU.remake.common.response.HttpResponse
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController @Autowired constructor(
  private val authService: AuthService
) {
  private val logger = org.slf4j.LoggerFactory.getLogger(AuthController::class.java)

  @PostMapping("/login")
  fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "เข้าสู่ระบบสำเร็จ",
          authService.login(request)
        )
      )
    } catch (e: NotFoundException) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(
          HttpResponse(
            false,
            e.message ?: "ไม่พบข้อมูลผู้ใช้"
          )
        )
    } catch (e: BadRequestException) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(
          HttpResponse(
            false,
            e.message ?: "ข้อมูลเข้าสู่ระบบไม่ถูกต้อง"
          )
        )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "เข้าสู่ระบบเกิดข้อผิดพลาด"
          )
        )
    }
  }

  @PostMapping("/register")
  fun register(@Valid @RequestBody request: RegisterRequest): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ลงทะเบียนสำเร็จ",
          authService.register(request)
        )
      )
    } catch (e: BadRequestException) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
          HttpResponse(
            false,
            e.message ?: "ข้อมูลไม่ถูกต้อง"
          )
        )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ลงทะเบียนเกิดข้อผิดพลาด"
          )
        )
    }
  }
}

