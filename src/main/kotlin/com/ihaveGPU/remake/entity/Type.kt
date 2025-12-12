package com.ihaveGPU.remake.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.ihaveGPU.remake.type.dto.TypeDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.util.Date

@Entity
@Table(name = "type")
data class Type(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,

  @Column(name = "type_name")
  var typeName: String,

  @Column(name = "icon_class")
  var iconClass: String,

  @Column(name = "is_deleted")
  var isDeleted: Boolean = false,

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val createdDate: Date = Date(),

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val updatedDate: Date = Date()
) {
  fun toServiceLayDto(): TypeDto {
    return TypeDto(
      id = this.id,
      typeName = this.typeName,
      iconClass = this.iconClass
    )
  }
}
