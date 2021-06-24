package com.mazbah.springbootvalidation.exception

import org.springframework.http.HttpStatus

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import org.springframework.web.context.request.WebRequest
import java.lang.Exception
import java.util.*

@ControllerAdvice
class GlobalExceptionHandler {

    // handling specific exception
    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundHandling(exception: ResourceNotFoundException, request: WebRequest): ResponseEntity<*>? {
        val errorDetails = ErrorDetails(Date(), exception.message!!, request.getDescription(false))
        return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
    }

    // handling global exception
    @ExceptionHandler(Exception::class)
    fun globalExceptionHandling(exception: Exception, request: WebRequest): ResponseEntity<*>? {
        val errorDetails = ErrorDetails(Date(), exception.message!!, request.getDescription(false))
        return ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}