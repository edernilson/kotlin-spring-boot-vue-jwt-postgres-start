package com.edernilson.calcorte.backend.user

import com.edernilson.calcorte.backend.domain.User
import com.edernilson.calcorte.backend.domain.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users", produces = [MediaType.APPLICATION_JSON_VALUE])
class UserAPIImpl: UserAPI {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/")
    override fun list() = userService.list()
}

@Tag(name = "User API", description = "Responsável pelos dados de usuários")
interface UserAPI {

    fun list(): List<User>
}