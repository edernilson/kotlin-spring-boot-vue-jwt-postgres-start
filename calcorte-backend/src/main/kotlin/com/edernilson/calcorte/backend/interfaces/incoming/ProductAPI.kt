package com.edernilson.calcorte.backend.product

import com.edernilson.calcorte.backend.domain.Client
import com.edernilson.calcorte.backend.domain.Product
import com.edernilson.calcorte.backend.domain.ProductService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@RequestMapping("/products", produces = [MediaType.APPLICATION_JSON_VALUE])
class ProductAPIImpl: ProductAPI {

    @Autowired
    private lateinit var productService: ProductService

    @GetMapping
    override fun list() = productService.list()

    @GetMapping("/{code}")
    override fun getByCode(@PathVariable code: String) = productService.findByCode(code) ?: throw ResponseStatusException(
        HttpStatus.NOT_FOUND, "Product not found"
    )

    @PostMapping
    override fun create(@RequestBody product: Product) = productService.save(product)

    @PutMapping("/{id}")
    override fun update(@PathVariable("id") id: Long, @Valid @RequestBody product: Product) = productService.update(id, product)

    @DeleteMapping("/{id}")
    override fun delete(@PathVariable("id") id: Long) {
        productService.delete(id)
    }
}

@Tag(name = "Product API", description = "Responsável pelos dados de produtos")
interface ProductAPI {

    fun list(): List<Product>

    fun getByCode(@PathVariable code: String): Product

    fun create(@RequestBody product: Product): Product

    fun update(@PathVariable("id") id: Long, @Valid @RequestBody product: Product): Product

    fun delete(@PathVariable("id") id: Long)
}