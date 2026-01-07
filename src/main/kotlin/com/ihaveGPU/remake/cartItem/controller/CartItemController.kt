package com.ihaveGPU.remake.cartItem.controller

import com.ihaveGPU.remake.cartItem.request.CreateCartItemRequest
import com.ihaveGPU.remake.cartItem.request.UpdateCartItemRequest
import com.ihaveGPU.remake.cartItem.service.CartItemService
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
@RequestMapping("/api/cart-items")
class CartItemController @Autowired constructor(
  private val cartItemService: CartItemService
) {
  private val logger = org.slf4j.LoggerFactory.getLogger(CartItemController::class.java)

  @GetMapping
  fun getCartItemAll(): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ดึงรายการ cart item all สำเร็จ",
          cartItemService.getCartItemAll()
        )
      )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ดึงรายการ cart item all เกิดข้อพลาด"
          )
        )
    }
  }

  @GetMapping("/{id}")
  fun getCartItemById(@PathVariable id: Long): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ดึงข้อมูล cart item สำเร็จ",
          cartItemService.getCartItemById(id)
        )
      )
    } catch (e: NotFoundException) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(
          HttpResponse(
            false,
            e.message ?: "ไม่พบข้อมูล cart item"
          )
        )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ดึงข้อมูล cart item เกิดข้อพลาด"
          )
        )
    }
  }

  @PostMapping
  fun createCartItem(@Valid @RequestBody request: CreateCartItemRequest): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "สร้าง cart item สำเร็จ",
          cartItemService.createCartItem(request)
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
            e.message ?: "สร้าง cart item เกิดข้อพลาด"
          )
        )
    }
  }

  @PutMapping
  fun updateCartItem(@Valid @RequestBody request: UpdateCartItemRequest): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "อัปเดต cart item สำเร็จ",
          cartItemService.updateCartItem(request)
        )
      )
    } catch (e: NotFoundException) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(
          HttpResponse(
            false,
            e.message ?: "ไม่พบข้อมูล cart item"
          )
        )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "อัปเดท cart item เกิดข้อพลาด"
          )
        )
    }
  }

  @DeleteMapping("/{id}")
  fun deleteCartItem(@PathVariable id: Long): ResponseEntity<Any> {
    return try {
      ResponseEntity.ok(
        HttpResponse(
          true,
          "ลบ cart item สำเร็จ",
          cartItemService.deleteCartItem(id)
        )
      )
    } catch (e: Exception) {
      logger.error(e.message)
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          HttpResponse(
            false,
            e.message ?: "ลบ cart item เกิดข้อพลาด"
          )
        )
    }
  }
}

