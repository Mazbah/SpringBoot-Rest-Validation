package com.mazbah.springbootvalidation.exception

import org.apache.tomcat.websocket.AuthenticationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import org.springframework.web.context.request.WebRequest
import java.lang.Exception
import java.util.*
import java.time.LocalDateTime

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.client.HttpClientErrorException
import java.io.PrintWriter
import java.io.StringWriter
import javax.persistence.EntityNotFoundException
import javax.persistence.NoResultException
import javax.validation.ConstraintViolationException

@ControllerAdvice
class GlobalExceptionHandler {

    // handling specific exception
    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundHandling(exception: ResourceNotFoundException, request: WebRequest): ResponseEntity<Any> {
//        val errorDetails = ErrorDetails(Date(), exception.message!!, request.getDescription(false))
        val errorDetails = ErrorDetails(Date(), exception.message, request.getDescription(false))
        return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
    }

    // handling global exception
    @ExceptionHandler(Exception::class)
    fun globalExceptionHandling(exception: Exception, request: WebRequest): ResponseEntity<Any>? {
        val errorDetails = ErrorDetails(Date(), exception.message, request.getDescription(false))
        return ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    fun handleMethodArgumentNotValid(exception: MethodArgumentNotValidException, headers: HttpHeaders?,
                  status: HttpStatus?, request: WebRequest?): ResponseEntity<Any>? {

        var errorDetails = ErrorDetails(Date(), exception.message, exception.bindingResult.toString())
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }

//    @ExceptionHandler(ConstraintViolationException::class, HttpClientErrorException.BadRequest::class,
//        MethodArgumentNotValidException::class, MissingServletRequestParameterException::class,
//        IllegalArgumentException::class)
//    fun constraintViolationException(e: Exception): ResponseEntity<ErrorDetails> {
//        return generateErrorResponse(HttpStatus.BAD_REQUEST, "Bad request", e)
//    }
//
////    @ExceptionHandler(AuthorizationException::class)
////    fun unauthorizedException(e: Exception): ResponseEntity<ErrorDetails> {
////        return generateErrorResponse(HttpStatus.FORBIDDEN, "You are not authorized to do this operation", e)
////    }
//
//    @ExceptionHandler(AuthenticationException::class)
//    fun forbiddenException(e: Exception): ResponseEntity<ErrorDetails> {
//        return generateErrorResponse(HttpStatus.UNAUTHORIZED, "You are not allowed to do this operation", e)
//    }
//
//    @ExceptionHandler(EntityNotFoundException::class, NoSuchElementException::class,
//        NoResultException::class,
//        EmptyResultDataAccessException::class,
//        IndexOutOfBoundsException::class,
//        KotlinNullPointerException::class)
//    fun notFoundException(e: Exception): ResponseEntity<ErrorDetails> {
//        return generateErrorResponse(HttpStatus.NOT_FOUND, "Resource not found", e)
//    }
//
//    @ExceptionHandler(
//        Exception::class
//    )
//    fun internalServerErrorException(e: Exception): ResponseEntity<ErrorDetails> {
//        return generateErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Generic internal error", e)
//    }
//
//    fun generateErrorResponse(status: HttpStatus, message: String, e: Exception): ResponseEntity<ErrorDetails> {
//        // converting the exception stack trace to a string
//        val sw = StringWriter()
//        val pw = PrintWriter(sw)
//        e.printStackTrace(pw)
//        val stackTrace = sw.toString()
//
//        // example: logging the stack trace
//        // log.debug(stackTrace)
//
//        // environment-based logic
//        val stackTraceMessage =
//            when (System.getenv("ENV").toUpperCase()) {
//                "STAGING" -> stackTrace // returning the stack trace
//                "PRODUCTION" -> null // returning no stack trace
//                else -> stackTrace // default behavior
//            }
//
//        return ResponseEntity(ErrorDetails(status, message, stackTraceMessage), status)
//    }

}