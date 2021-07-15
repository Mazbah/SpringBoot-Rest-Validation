package com.mazbah.springbootvalidation.validation

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailDomainValidator: ConstraintValidator<EmailDomain, String> {

    override fun isValid(email: String, context: ConstraintValidatorContext): Boolean {
        val domain = email.split("@").toTypedArray()

        return listOf("robi.com.bd", "reddotdigitalit.com").contains(domain.last())
    }

}