package com.edernilson.calcorte.backend.user

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

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
