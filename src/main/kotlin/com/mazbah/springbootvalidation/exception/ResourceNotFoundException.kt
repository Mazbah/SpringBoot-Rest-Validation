package com.mazbah.springbootvalidation.exception

import net.bytebuddy.implementation.bind.annotation.Super
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException(message: String) :RuntimeException(){
    private final val serialVersionUID = 1L

    fun resourceNotFoundException(message: String) {
        super.message
    }

}
