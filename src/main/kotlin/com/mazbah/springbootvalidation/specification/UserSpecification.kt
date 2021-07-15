package com.mazbah.springbootvalidation.specification

import com.mazbah.springbootvalidation.model.User
import org.springframework.data.jpa.domain.Specification

class UserSpecification(
    private val firstName: String? = null,
    private val lastName: String? = null,
    private val email: String? = null
) {
    fun specification(): Specification<User> {
        listOfNotNull(firstnameSpec(), lastnameSpec(), emailSpec()).let {
            if (it.isEmpty()) throw Exception("At least one criteria is required")

            return it.reduce { one, two -> one.and(two) }
        }
    }

    private fun firstnameSpec(): Specification<User>? {
        if (firstName.isNullOrBlank()) return null

        return Specification { root, _, builder -> builder.equal(root.get<Any>("firstName"), firstName) }
    }

    private fun lastnameSpec(): Specification<User>? {
        if (lastName.isNullOrBlank()) return null

        return Specification { root, _, builder -> builder.equal(root.get<Any>("lastName"), lastName) }
    }

    private fun emailSpec(): Specification<User>? {
        if (email.isNullOrBlank()) return null

        return Specification { root, _, builder -> builder.equal(root.get<Any>("email"), email) }
    }
}
