package com.mazbah.springbootvalidation.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class ResourceNotFoundException(s: String) : Exception() {
    private val serialVersionUID = 1L

}