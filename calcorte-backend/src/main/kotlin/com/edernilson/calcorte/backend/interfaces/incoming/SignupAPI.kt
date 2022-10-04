package com.edernilson.calcorte.backend.security

import com.edernilson.calcorte.backend.domain.User
import com.edernilson.calcorte.backend.domain.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/signup", produces = [MediaType.APPLICATION_JSON_VALUE])
class SignupAPIImpl: SignupAPI {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping
    override fun signup(@RequestBody user: User): ResponseEntity<User> {
        val userCreated = userService.create(user)
        return ResponseEntity.created(URI("")).body(userCreated)
    }
}

@Tag(name = "Signup API", description = "Responsável pela criação de usuários")
interface SignupAPI {

    fun signup(@RequestBody user: User): ResponseEntity<User>
}