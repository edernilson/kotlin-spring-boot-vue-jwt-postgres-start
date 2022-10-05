package com.edernilson.calcorte.backend.domain

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String?): User?
}

interface ProductRepository: JpaRepository<Product, Long> {
    fun findByCode(code: String): Product?
}
interface ClientRepository: JpaRepository<Client, Long> {}