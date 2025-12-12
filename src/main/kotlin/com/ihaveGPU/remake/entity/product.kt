package com.ihaveGPU.remake.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.ihaveGPU.remake.product.dto.ProductDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.util.Date

@Entity
@Table(name = "product")
data class product(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,

  @Column(name = "product_name")
  var productName: String,

  @Column(name = "price")
  var price: Double,

  @Column(name = "detail")
  var detail: String,

  @Column(name = "product_qty")
  var productQty: Int,

  @Column(name = "brand")
  var brand: String,

  @Column(name = "pic_product")
  var picProduct: String,

  @Column(name = "type_id")
  var typeId: Long,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_id", insertable = false, updatable = false)
  val type: Type? = null,

  @Column(name = "is_deleted")
  var isDeleted: Boolean = false,

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val createdDate: Date = Date(),

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val updatedDate: Date = Date()
){
  fun toProductDto(): ProductDto {
    return ProductDto(
      id = id,
      productName = productName,
      price = price,
      detail = detail,
      productQty = productQty,
      brand = brand,
      picProduct = picProduct,
      typeId = typeId
    )
  }
}
