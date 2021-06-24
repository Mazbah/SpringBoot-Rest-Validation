package com.mazbah.springbootvalidation.repository

import com.mazbah.springbootvalidation.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {

}