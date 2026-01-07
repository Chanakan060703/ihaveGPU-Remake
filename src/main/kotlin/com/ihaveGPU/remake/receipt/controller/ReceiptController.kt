package com.ihaveGPU.remake.receipt.controller

import com.ihaveGPU.remake.common.exception.BadRequestException
import com.ihaveGPU.remake.common.exception.NotFoundException
import com.ihaveGPU.remake.common.response.HttpResponse
import com.ihaveGPU.remake.receipt.request.CreateReceiptRequest
import com.ihaveGPU.remake.receipt.request.UpdateReceiptRequest
import com.ihaveGPU.remake.receipt.service.ReceiptService
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/receipts")
class ReceiptController @Autowired constructor(
  private val receiptService: ReceiptService
) {
  private val logger = org.slf4j.LoggerFactory.getLogger(ReceiptController::class.java)

  @GetMapping
  fun getReceiptAll(): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ดึงรายการ receipt all สำเร็จ",
          receiptService.getReceiptAll()
        )
      )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ดึงรายการ receipt all เกิดข้อพลาด"
          )
        )
    }
  }

  @GetMapping("/{id}")
  fun getReceiptById(@PathVariable id: Long): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ดึงข้อมูล receipt สำเร็จ",
          receiptService.getReceiptById(id)
        )
      )
    } catch (e: NotFoundException) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(
          HttpResponse(
            false,
            e.message ?: "ไม่พบข้อมูล receipt"
          )
        )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ดึงข้อมูล receipt เกิดข้อพลาด"
          )
        )
    }
  }

  @GetMapping("/by-email")
  fun getReceiptByEmail(@RequestParam email: String): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ดึงรายการ receipt by email สำเร็จ",
          receiptService.getReceiptByEmail(email)
        )
      )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ดึงรายการ receipt by email เกิดข้อพลาด"
          )
        )
    }
  }

  @PostMapping
  fun createReceipt(@Valid @RequestBody request: CreateReceiptRequest): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "สร้าง receipt สำเร็จ",
          receiptService.createReceipt(request)
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
    } catch (e: NotFoundException) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(
          HttpResponse(
            false,
            e.message ?: "ไม่พบข้อมูล"
          )
        )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "สร้าง receipt เกิดข้อพลาด"
          )
        )
    }
  }

  @PutMapping
  fun updateReceipt(@Valid @RequestBody request: UpdateReceiptRequest): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "อัปเดต receipt สำเร็จ",
          receiptService.updateReceipt(request)
        )
      )
    } catch (e: NotFoundException) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(
          HttpResponse(
            false,
            e.message ?: "ไม่พบข้อมูล receipt"
          )
        )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "อัปเดท receipt เกิดข้อพลาด"
          )
        )
    }
  }

  @DeleteMapping("/{id}")
  fun deleteReceipt(@PathVariable id: Long): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ลบ receipt สำเร็จ",
          receiptService.deleteReceipt(id)
        )
      )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ลบ receipt เกิดข้อพลาด"
          )
        )
    }
  }
}

