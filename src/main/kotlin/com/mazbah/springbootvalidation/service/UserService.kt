package com.mazbah.springbootvalidation.service

import com.mazbah.springbootvalidation.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification


interface UserService {
    fun findById(id:Long): User?
    fun findAll(): List<User>
    fun findAll(paging: Pageable): Page<User>
    fun findAll(spec: Specification<User>, page: Pageable): Page<User>
    fun delete(user: User)
    fun save(user: User): User

}