package com.mazbah.springbootvalidation.service

import com.mazbah.springbootvalidation.model.User

interface UserService {
    fun findById(id:Long): User?
    fun findAll(): Iterable<User?>
    fun deleteById(user: User)
    fun save(user: User): User

}