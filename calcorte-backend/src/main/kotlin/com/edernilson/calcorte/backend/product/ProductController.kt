package com.edernilson.calcorte.backend.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@RequestMapping("/products")
class ProductController {

    @Autowired
    private lateinit var productService: ProductService

    @GetMapping
    fun list() = ResponseEntity.ok(productService.list())

    @GetMapping("/{code}")
    fun getByCode(@PathVariable code: String) = productService.findByCode(code) ?: throw ResponseStatusException(
        HttpStatus.NOT_FOUND, "Product not found"
    )

    @PostMapping
    fun create(@RequestBody product: Product) = productService.save(product)

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @Valid @RequestBody product: Product) = productService.update(id, product)

    @DeleteMapping("/{id}")
    fun deletePassenger(@PathVariable("id") id: Long) {
        productService.delete(id)
    }
}