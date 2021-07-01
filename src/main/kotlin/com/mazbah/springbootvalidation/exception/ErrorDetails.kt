package com.mazbah.springbootvalidation.exception

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import java.util.*

class ErrorDetails(val date: Date, val message: String? = null, val description:String? = null)


//class ErrorDetails(status: HttpStatus, val message: String, var stackTrace: String? = null)
//{
//    val code: Int = status.value()
//    val status: String = status.name
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
//    val timestamp: Date = Date()
//
//}

