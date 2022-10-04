package com.edernilson.ctr.interfaces.incoming.errorhandling

import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Error Data", description = "Representa o objeto de erro retornado pela API")
data class ErrorData(
    val message: String
)

@Tag(name = "Error Response", description = "Representa a lista de erros retornado pela API")
data class ErrorResponse(
    val errors: List<ErrorData>
)