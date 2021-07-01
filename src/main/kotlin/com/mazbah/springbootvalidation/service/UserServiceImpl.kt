package com.mazbah.springbootvalidation.service

import com.mazbah.springbootvalidation.model.User
import com.mazbah.springbootvalidation.repository.UserRepository
import org.springframework.stereotype.Service

@Service("UserService")
class UserServiceImpl(private var userRepository: UserRepository): UserService {
    override fun findById(id: Long): User? {
        val user = userRepository.findById(id)
        return user.orElse(null)
    }

    override fun findAll(): Iterable<User?> {
        return userRepository.findAll()
    }

    override fun deleteById(user: User) {
        userRepository.delete(user)
    }

    override fun save(user: User): User {
        return userRepository.save(user)
    }


}