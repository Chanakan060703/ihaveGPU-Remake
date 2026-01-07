package com.ihaveGPU.remake.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.ihaveGPU.remake.user.dto.UserDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.util.Date

@Entity
@Table(name = "user")
data class User(
  @Id
  @Column(name = "email")
  val email: String,

  @Column(name = "first_name")
  var firstName: String,

  @Column(name = "last_name")
  var lastName: String,

  @Column(name = "password")
  var password: String,

  @Column(name = "date_of_birth")
  var dateOfBirth: String,

  @Column(name = "image_url")
  var imageUrl: String? = null,

  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  var role: UserRole = UserRole.USER,

  @OneToMany(mappedBy = "user")
  val savedSpecGroups: MutableList<SavedSpecGroup> = mutableListOf(),

  @Column(name = "is_deleted")
  var isDeleted: Boolean = false,

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val createdDate: Date = Date(),

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val updatedDate: Date = Date()
) {
  fun toUserDto(): UserDto {
    return UserDto(
      email = email,
      firstName = firstName,
      lastName = lastName,
      dateOfBirth = dateOfBirth,
      imageUrl = imageUrl,
      role = role
    )
  }
}
