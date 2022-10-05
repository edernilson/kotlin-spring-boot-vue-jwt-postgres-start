package com.edernilson.calcorte.backend.domain

import com.edernilson.calcorte.backend.exception.RegisterNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientService {

    @Autowired
    private lateinit var clientRepository: ClientRepository

    fun list(): MutableList<Client> = clientRepository.findAll()

    fun save(produto: Client) = clientRepository.save(produto)

    fun findById(id: Long) = clientRepository.findById(id)

    fun update(id: Long, client: Client): Client {
        val foundClient = findById(id)

        if (foundClient.isPresent) {
            val newClient = foundClient.get().copy(
                name = client.name,
                phone = client.phone,
                email = client.email,
                contactName = client.contactName,
                contactPhone = client.contactPhone
            )
            return clientRepository.save(newClient)
        }
        throw RegisterNotFoundException("Client not found!")
    }

    fun delete(id: Long) {
        val client = findById(id)
        if (client.isPresent) {
            clientRepository.delete(client.get())
            return
        }
        throw RegisterNotFoundException("Client not found!")
    }
}
