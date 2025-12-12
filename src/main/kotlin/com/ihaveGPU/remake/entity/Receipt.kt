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
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.util.Date

@Entity
@Table(name = "receipt")
data class Receipt(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,

  @Column(name = "date")
  val date: String,

  @Column(name = "total")
  val total: Double,

  @Column(name = "email")
  val email: String,

  @Column(name = "address_id")
  val addressId: Long,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "address_id", insertable = false, updatable = false)
  val address: Address,

  @OneToMany(mappedBy = "receipt")
  val receiptDetails: MutableList<ReceiptDetail> = mutableListOf(),

  @Column(name = "is_deleted")
  val isDeleted: Boolean = false,

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val createdDate: Date = Date(),

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val updatedDate: Date = Date()

)
