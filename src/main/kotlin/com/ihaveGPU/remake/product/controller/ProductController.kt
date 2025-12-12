package com.ihaveGPU.remake.product.controller

import com.ihaveGPU.remake.common.exception.BadRequestException
import com.ihaveGPU.remake.common.exception.NotFoundException
import com.ihaveGPU.remake.common.response.HttpResponse
import com.ihaveGPU.remake.product.request.CreateProductRequest
import com.ihaveGPU.remake.product.request.UpdateProductRequest
import com.ihaveGPU.remake.product.service.ProductService
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
@RequestMapping("/api/products")
class ProductController @Autowired constructor(
  private val productService: ProductService
){
  private val logger = org.slf4j.LoggerFactory.getLogger(ProductController::class.java)

  @GetMapping
  fun getProductAll(
  ): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "รายการ product all สำเร็จ",
          productService.getProductAll()
        )
      )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ดึงรายการ product all เกิดข้อพลาด"
          )
        )
    }
  }

  @GetMapping("/{id}")
  fun getProductById(
    @PathVariable id: Long
  ): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ข้อมูล product สำเร็จ",
          productService.getProductById(id)
          )
        )
    } catch (e: NotFoundException) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(
          HttpResponse(
            false,
            e.message ?: "ไม่พบข้อมูล product"
          )
        )
    }
    catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ข้อมูล product เกิดข้อพลาด"
          )
        )
    }
  }

  @PostMapping
  fun createProduct(
    @RequestBody request: CreateProductRequest
  ): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "สร้าง product สำเร็จ",
          productService.createProduct(request)
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
    }catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "สร้าง product เกิดข้อพลาด"
          )
        )
    }
  }

  @PutMapping
  fun updateProduct(
    @Valid @RequestBody request: UpdateProductRequest
  ): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "อัปเดต product สำเร็จ",
          productService.updateProduct(request)
          )
        )
    } catch (e: NotFoundException) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(
          HttpResponse(
            false,
            e.message ?: "ไม่พบข้อมูล product"
          )
        )
    }catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "อัปเดท product เกิดข้อพลาด"
          )
        )
    }
  }

  @DeleteMapping("/{id}")
  fun deleteProduct(
    @PathVariable id: Long
  ): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ลบ product สำเร็จ",
          productService.deleteProduct(id)
          )
        )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ลบ product เกิดข้อพลาด"
          )
        )
    }
  }
}