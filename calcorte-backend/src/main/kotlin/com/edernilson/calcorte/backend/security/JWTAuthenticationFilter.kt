package com.edernilson.calcorte.backend.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter: UsernamePasswordAuthenticationFilter {

    private var jwtUtil: JWTUtil

    constructor(authenticationManager: AuthenticationManager, jwtUtil: JWTUtil) : super() {
        this.authenticationManager = authenticationManager
        this.jwtUtil = jwtUtil
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication? {
        try {
            val (email, password) = ObjectMapper().readValue(request.inputStream, Credentials::class.java)

            val token = UsernamePasswordAuthenticationToken(email, password)

            return authenticationManager.authenticate(token)
        } catch (e: Exception) {
            throw UsernameNotFoundException("User not found!")
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        val username = (authResult.principal as UserDetailsImpl).username
        val token = jwtUtil.generateToken(username)
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"

        val out = response.writer
        val mapper = ObjectMapper()
        val rootNode = mapper.createObjectNode()
        rootNode.put("token", token)
        rootNode.put("user", mapper.writeValueAsString((authResult.principal as UserDetailsImpl)))
        val jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode)

        out.println(jsonString)
        out.flush()
//        response.addHeader(authorization, "$bearer $token")
    }

}
