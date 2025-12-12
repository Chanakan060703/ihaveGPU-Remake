package com.ihaveGPU.remake.type.controller

import com.ihaveGPU.remake.common.exception.BadRequestException
import com.ihaveGPU.remake.common.exception.NotFoundException
import com.ihaveGPU.remake.common.response.HttpResponse
import com.ihaveGPU.remake.type.request.CreateTypesRequest
import com.ihaveGPU.remake.type.request.UpdateTypeRequest
import com.ihaveGPU.remake.type.service.TypeService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
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
@RequestMapping("/api/types")
class TypeController @Autowired constructor(
  private val typeService: TypeService
){
  private val logger = org.slf4j.LoggerFactory.getLogger(TypeController::class.java)

  @GetMapping
  fun getTypeAll(
  ): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ดึงรายการ type all สำเร็จ",
          typeService.getTypeAll()
        )
      )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ดึงรายการ type all เกิดข้อพลาด"
          )
        )
    }
  }

  @GetMapping("/page")
  fun getListTypePage(
    @RequestParam("page", defaultValue = "0") page: Int,
    @RequestParam("size", defaultValue = "20") size: Int,
    @RequestParam("search", required = false) search: String?,
    @RequestParam("isAsc", defaultValue = "true") isAsc: Boolean
  ): ResponseEntity<Any> {
    val pageable: Pageable = PageRequest.of(page, size)
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ดึงรายการ type all สำเร็จ",
          typeService.getListTypePage(
            pageable,
            search,
            isAsc
          )
        )
      )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ดึงรายการ type all เกิดข้อพลาด"
          )
        )
    }
  }

  @GetMapping("/{id}")
  fun getTypeById(
    @PathVariable id: Long
  ): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ดึงข้อมูล Type สำเร็จ",
          typeService.getTypeById(id)
          )
        )
    } catch (e: NotFoundException) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(
          HttpResponse(
            false,
            e.message ?: "ไม่พบข้อมูล Type"
          )
        )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ดึงข้อมูล Type เกิดข้อพลาด"
          )
        )
    }
  }

  @PostMapping
  fun createType(
    @Valid @RequestBody request: CreateTypesRequest
  ): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "สร้าง Type สำเร็จ",
          typeService.createType(request)
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
            e.message ?: "สร้าง Type เกิดข้อพลาด"
          )
        )
    }
  }

  @PutMapping
  fun updateType(
    @Valid @RequestBody request: UpdateTypeRequest
  ): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "อัปเดต Master Example สำเร็จ",
          typeService.updateType(request)
          )
        )
    } catch (e: NotFoundException) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(
          HttpResponse(
            false,
            e.message ?: "ไม่พบข้อมูล Master Example"
          )
        )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "อัปเดท Master Example เกิดข้อพลาด"
          )
        )
    }
  }

  @DeleteMapping("/{id}")
  fun deleteType(
    @PathVariable id: Long
  ): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ลบ Master Example สำเร็จ",
          typeService.deleteType(id)
          )
        )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ลบ Master Example เกิดข้อพลาด"
          )
        )
    }
  }
}