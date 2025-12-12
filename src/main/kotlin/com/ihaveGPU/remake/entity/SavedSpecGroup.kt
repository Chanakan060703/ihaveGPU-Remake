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
@Table(name = "saved_spec_group")
data class SavedSpecGroup(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,

  @Column(name = "user_email")
  val userEmail: String,

  @Column(name = "receipt_detail_id")
  val receiptDetailId: Long,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_email", insertable = false, updatable = false)
  val user: User,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "receipt_detail_id", insertable = false, updatable = false)
  val receiptDetail: ReceiptDetail,

  @Column(name = "is_deleted")
  val isDeleted: Boolean = false,

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val createdDate: Date = Date(),

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val updatedDate: Date = Date()
)
