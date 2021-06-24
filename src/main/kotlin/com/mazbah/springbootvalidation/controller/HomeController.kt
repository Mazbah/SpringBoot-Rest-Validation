package com.mazbah.springbootvalidation.controller

import com.mazbah.springbootvalidation.exception.ResourceNotFoundException
import com.mazbah.springbootvalidation.model.User
import com.mazbah.springbootvalidation.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping


@RestController
class HomeController(private var userRepository: UserRepository) {

    @GetMapping("/users")
    fun getAllUsers(): List<User>{
        return userRepository.findAll()
    }

    //  Get User By ID
    @GetMapping("/users/{id}")
    @Throws(ResourceNotFoundException::class)
    fun getUserById(@PathVariable(value = "id") userId: Long): ResponseEntity<User>{
        val user: User = userRepository.findById(userId)
            .orElseThrow { ResourceNotFoundException("User not found for this id : $userId") }

        return ResponseEntity.ok().body(user)
    }

    // Create User
    @PostMapping("/users")
    fun createUser(@Valid @RequestBody user: User): User{
        return userRepository.save(user)
    }

    // Update User
    @PutMapping("/users/{id}")
    @Throws(ResourceNotFoundException::class)
    fun updateUser(@Valid @RequestBody users: User, @PathVariable(value = "id") userId: Long): ResponseEntity<User>{
        val existingUser: User = userRepository.findById(userId)
            .orElseThrow{ ResourceNotFoundException("User not found for this id : $userId") }

        existingUser.firstName = users.firstName
        existingUser.lastName = users.lastName
        existingUser.email = users.email

        return ResponseEntity.ok(userRepository.save(existingUser))
    }

    // Delete User
    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable("id") userId: Long): ResponseEntity<User>{
        val existingUser: User = userRepository.findById(userId)
            .orElseThrow{ ResourceNotFoundException("User not found for this id : $userId") }
        userRepository.delete(existingUser)
        return ResponseEntity.ok().build()
    }

}







