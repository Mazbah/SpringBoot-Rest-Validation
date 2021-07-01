package com.mazbah.springbootvalidation.validation

import com.mazbah.springbootvalidation.model.User
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class CustomEmailValidator: ConstraintValidator<CustomEmail, String> {

    override fun isValid(email: String, context: ConstraintValidatorContext): Boolean {
        val result1 = email.contains("robi.com.bd")
        val result2 = email.contains("reddotdigitalit.com")
        val result:Boolean
        if(result1 == true || result2 == true){
            result = true
        } else result = false

        return result
    }

}