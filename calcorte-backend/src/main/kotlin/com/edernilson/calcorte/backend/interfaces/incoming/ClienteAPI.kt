package com.edernilson.calcorte.backend.client

import com.edernilson.calcorte.backend.domain.Client
import com.edernilson.calcorte.backend.domain.ClientService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/clients", produces = [MediaType.APPLICATION_JSON_VALUE])
class ClientAPIImpl: ClientAPI {

    @Autowired
    private lateinit var clientService: ClientService

    @GetMapping
    override fun list() = clientService.list()

    @PostMapping
    override fun create(@RequestBody client: Client) = clientService.save(client)

    @PutMapping("/{id}")
    override fun update(@PathVariable("id") id: Long, @Valid @RequestBody client: Client) = clientService.update(id, client)

    @DeleteMapping("/{id}")
    override fun delete(@PathVariable("id") id: Long) {
        clientService.delete(id)
    }
}

@Tag(name = "Client API", description = "Responsável pelos dados de clientes")
interface ClientAPI {

    fun list(): List<Client>

    fun create(@RequestBody client: Client): Client

    fun update(@PathVariable("id") id: Long, @Valid @RequestBody client: Client): Client

    fun delete(@PathVariable("id") id: Long)
}