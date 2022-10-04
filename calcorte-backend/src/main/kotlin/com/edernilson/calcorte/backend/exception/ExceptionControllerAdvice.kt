package com.edernilson.calcorte.backend.exception

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.ConstraintViolationException


@ControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseBody
class ExceptionControllerAdvice {

    @ExceptionHandler(Exception::class)
    fun handleOtherException(e: Exception): ErrorMessage {
        return ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.message)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun processValidationError(ex: MethodArgumentNotValidException): ErrorMessage {
        val result: BindingResult = ex.bindingResult
        val sb = StringBuilder("Valor inválido para o(s) campo(s):")
        for (fieldError in result.fieldErrors) {
            sb.append(" ")
            sb.append(fieldError.field)
        }
        return ErrorMessage(HttpStatus.BAD_REQUEST.value(), sb.toString())
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun processValidationError(ex: ConstraintViolationException): ErrorMessage {
        val violations = ex.constraintViolations
        val sb = StringBuilder("Valor inválido para o(s) campo(s):")
        for (fieldError in violations) {
            sb.append(" ")
            sb.append(fieldError.propertyPath)
        }
        return ErrorMessage(HttpStatus.BAD_REQUEST.value(), sb.toString())
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun processValidationError(ex: DataIntegrityViolationException): ErrorMessage {
        val sb = StringBuilder("Erro ao tentar salvar dados:")
        if (ex.message!!.contains("_uk")) sb.append(" Chave duplicada")
        return ErrorMessage(HttpStatus.BAD_REQUEST.value(), sb.toString())
    }

    @ExceptionHandler(ProductNotFoundException::class)
    fun processValidationError(ex: ProductNotFoundException): ErrorMessage {
        return ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.message)
    }
}