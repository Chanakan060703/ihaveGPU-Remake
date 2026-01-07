package com.ihaveGPU.remake.address.controller

import com.ihaveGPU.remake.address.request.CreateAddressRequest
import com.ihaveGPU.remake.address.request.UpdateAddressRequest
import com.ihaveGPU.remake.address.service.AddressService
import com.ihaveGPU.remake.common.exception.BadRequestException
import com.ihaveGPU.remake.common.exception.NotFoundException
import com.ihaveGPU.remake.common.response.HttpResponse
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/addresses")
class AddressController @Autowired constructor(
  private val addressService: AddressService
) {
  private val logger = org.slf4j.LoggerFactory.getLogger(AddressController::class.java)

  @GetMapping
  fun getAddressAll(): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ดึงรายการ address all สำเร็จ",
          addressService.getAddressAll()
        )
      )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ดึงรายการ address all เกิดข้อพลาด"
          )
        )
    }
  }

  @GetMapping("/{id}")
  fun getAddressById(@PathVariable id: Long): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ดึงข้อมูล address สำเร็จ",
          addressService.getAddressById(id)
        )
      )
    } catch (e: NotFoundException) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(
          HttpResponse(
            false,
            e.message ?: "ไม่พบข้อมูล address"
          )
        )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ดึงข้อมูล address เกิดข้อพลาด"
          )
        )
    }
  }

  @PostMapping
  fun createAddress(@Valid @RequestBody request: CreateAddressRequest): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "สร้าง address สำเร็จ",
          addressService.createAddress(request)
        )
      )
    } catch (e: BadRequestException) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
          HttpResponse(
            false,
            e.message ?: "คำร้องขอไม่ถูกต้อง"
          )
        )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "สร้าง address เกิดข้อพลาด"
          )
        )
    }
  }

  @PutMapping
  fun updateAddress(@Valid @RequestBody request: UpdateAddressRequest): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "อัปเดต address สำเร็จ",
          addressService.updateAddress(request)
        )
      )
    } catch (e: NotFoundException) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(
          HttpResponse(
            false,
            e.message ?: "ไม่พบข้อมูล address"
          )
        )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "อัปเดท address เกิดข้อพลาด"
          )
        )
    }
  }

  @DeleteMapping("/{id}")
  fun deleteAddress(@PathVariable id: Long): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ลบ address สำเร็จ",
          addressService.deleteAddress(id)
        )
      )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ลบ address เกิดข้อพลาด"
          )
        )
    }
  }
}

