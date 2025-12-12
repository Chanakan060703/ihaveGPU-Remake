package com.ihaveGPU.remake.entity

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.util.Date

@Entity
@Table(name = "address")
data class Address(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,

  @Column(name = "house_number")
  val houseNumber: String,

  @Column(name = "province")
  val province: String,

  @Column(name = "building")
  val building: String,

  @Column(name = "district")
  val district: String,

  @Column(name = "sub_district")
  val subDistrict: String,

  @Column(name = "is_deleted")
  val isDeleted: Boolean = false,

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val createdDate: Date = Date(),

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val updatedDate: Date = Date()
){

}
