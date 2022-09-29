package com.edernilson.calcorte.backend.exception

data class ErrorMessage(
    var status: Int? = null,
    var message: String? = null
)
