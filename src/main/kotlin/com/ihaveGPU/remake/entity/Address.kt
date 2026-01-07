package com.ihaveGPU.remake.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.ihaveGPU.remake.address.dto.AddressDto
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
  val id: Long = 0,

  @Column(name = "house_number")
  var houseNumber: String,

  @Column(name = "province")
  var province: String,

  @Column(name = "building")
  var building: String,

  @Column(name = "district")
  var district: String,

  @Column(name = "sub_district")
  var subDistrict: String,

  @Column(name = "is_deleted")
  var isDeleted: Boolean = false,

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val createdDate: Date = Date(),

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val updatedDate: Date = Date()
) {
  fun toAddressDto(): AddressDto {
    return AddressDto(
      id = id,
      houseNumber = houseNumber,
      province = province,
      building = building,
      district = district,
      subDistrict = subDistrict
    )
  }
}
