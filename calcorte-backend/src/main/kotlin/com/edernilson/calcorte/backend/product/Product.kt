package com.edernilson.calcorte.backend.product

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotBlank

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
