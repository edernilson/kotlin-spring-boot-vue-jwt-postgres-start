package com.edernilson.calcorte.backend.domain

import com.edernilson.calcorte.backend.exception.ProductNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {

    @Autowired
    private lateinit var productRepository: ProductRepository

    fun list(): MutableList<Product> = productRepository.findAll()

    fun findByCode(code: String) = productRepository.findByCode(code)

    fun save(product: Product) = productRepository.save(product)

    fun findById(id: Long) = productRepository.findById(id)

    fun update(id: Long, product: Product): Product {
        val foundProduct = findById(id)

        if (foundProduct.isPresent) {
            val newProduct = foundProduct.get().copy(
                code = product.code,
                description = product.description,
                materialType = product.materialType
            )
            return productRepository.save(newProduct)
        }
        throw ProductNotFoundException("Product not found!")
    }

    fun delete(id: Long) {
        val product = findById(id)
        if (product.isPresent) {
            productRepository.delete(product.get())
            return
        }
        throw ProductNotFoundException("Product not found!")
    }
}
