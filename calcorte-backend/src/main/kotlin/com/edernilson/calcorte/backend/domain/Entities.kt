package com.edernilson.calcorte.backend.domain

import com.edernilson.calcorte.backend.domain.enums.MaterialType
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    val id: Long = 0,
    val fullName: String = "",
    val email: String = "",
    @JsonIgnore
    var password: String = ""
)

@Entity
@Table(name = "products")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @field:NotBlank
    val code: String = "",
    @field:NotBlank
    val description: String = "",
    val materialType: MaterialType = MaterialType.OTHER
)
