package com.ihaveGPU.remake.entity

import com.fasterxml.jackson.annotation.JsonFormat
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
@Table(name = "receipt_detail")
data class ReceiptDetail(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,
  @Column(name = "amount")
  val amount: Int,
  @Column(name = "total_detail")
  val totalDetail: Double,
  @Column(name = "cart_item_id")
  val cartItemId: Long,
  @Column(name = "receipt_id")
  val receiptId: Long,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_item_id", insertable = false, updatable = false)
  val cartItem: CartItem,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "receipt_id", insertable = false, updatable = false)
  val receipt: Receipt,

  @Column(name = "is_deleted")
  val isDeleted: Boolean = false,

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val createdDate: Date = Date(),

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val updatedDate: Date = Date()
)
