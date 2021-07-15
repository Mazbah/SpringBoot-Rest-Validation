package com.mazbah.springbootvalidation.controller

import com.mazbah.springbootvalidation.exception.ResourceNotFoundException
import com.mazbah.springbootvalidation.i18n.AppConfig
import com.mazbah.springbootvalidation.model.User
import com.mazbah.springbootvalidation.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping

import com.mazbah.springbootvalidation.repository.UserRepository
import com.mazbah.springbootvalidation.specification.UserSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Configuration
import org.springframework.context.i18n.LocaleContextHolder

import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Configuration
@RestController
@Validated
class HomeController(private val userService: UserService, private val userRepository: UserRepository,
    private val messageSource: MessageSource) {


    @GetMapping("/users/search")
    fun search(
        @RequestParam(required = false) firstName: String?,
        @RequestParam(required = false) lastName: String?,
        @RequestParam(required = false) email: String?,
        pageIndex:Int = 1, size:Int
    ): Page<User> {
        val spec = UserSpecification(firstName = firstName, lastName = lastName, email = email).specification()
        val paging: Pageable = PageRequest.of(pageIndex - 1,size, Sort.by("id").ascending())
        return userRepository.findAll(spec, paging)
    }

    //internationalization
    @GetMapping("/greeting")
    fun messageI18N(): String{
        return messageSource.getMessage("greeting", null, LocaleContextHolder.getLocale())
    }

    // Get users by Page
    @GetMapping("/users")
    fun getAllUsers(pageIndex:Int = 1, size:Int): Page<User> {
        val paging: Pageable = PageRequest.of(pageIndex - 1,size, Sort.by("firstName").ascending())
        return userService.findAll(paging)
    }

    //  Get User By ID
    @GetMapping("/users/{id}")
    @Throws(ResourceNotFoundException::class)
    fun getUserById(@PathVariable(value = "id") userId: Long): ResponseEntity<User>{
        val user: User = userService.findById(userId) ?: throw ResourceNotFoundException("User not found for this id : $userId")

        return ResponseEntity.ok().body(user)
    }

    // Create User
    @PostMapping("/users")
    fun createUser(@Valid @RequestBody user: User): User{
        return userService.save(user)
    }

    // Update User
    @PutMapping("/users/{id}")
    @Throws(ResourceNotFoundException::class)
    fun updateUser(@Valid @RequestBody users: User, @PathVariable(value = "id") userId: Long): ResponseEntity<User> {
        val existingUser: User = userService.findById(userId) ?:
                                    throw ResourceNotFoundException("User not found with id :$userId")

        existingUser.firstName = users.firstName
        existingUser.lastName = users.lastName
        existingUser.email = users.email

        return ResponseEntity.ok(userService.save(existingUser))
    }

    // Delete User
    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable("id") userId: Long): ResponseEntity<User>{
        val existingUser: User = userService.findById(userId) ?:
                                    throw ResourceNotFoundException("User not found for this id : $userId")

        userService.delete(existingUser)
        return ResponseEntity.ok().build()
    }
}
