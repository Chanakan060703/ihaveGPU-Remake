package com.ihaveGPU.remake.entity

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
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
    val firstName: String,
  @Column(name = "last_name")
    val lastName: String,
  @Column(name = "password")
    val password: String,
  @Column(name = "date_of_birth")
    val dateOfBirth: String,
  @Column(name = "image_url")
    val imageUrl: String? = null,

  @OneToMany(mappedBy = "user")
  val savedSpecGroups: MutableList<SavedSpecGroup> = mutableListOf(),

  @Column(name = "is_deleted")
  val isDeleted: Boolean = false,

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val createdDate: Date = Date(),

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
  @CreationTimestamp
  val updatedDate: Date = Date()
)
