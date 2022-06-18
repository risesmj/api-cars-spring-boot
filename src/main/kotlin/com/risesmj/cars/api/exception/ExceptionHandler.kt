package com.risesmj.cars.api.exception

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(
        EmptyResultDataAccessException::class
    )
    fun errorNotFound(e: Exception): ResponseEntity<Any>{
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(
        IllegalArgumentException::class
    )
    fun errorBadRequest(e: Exception): ResponseEntity<Any>{
        return ResponseEntity.badRequest().build()
    }
}