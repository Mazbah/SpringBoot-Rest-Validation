package com.mazbah.springbootvalidation.service

import com.mazbah.springbootvalidation.model.User
import com.mazbah.springbootvalidation.repository.UserRepository
import com.mazbah.springbootvalidation.specification.UserSpecification
//import com.mazbah.springbootvalidation.repository.UserRepository
//import com.mazbah.springbootvalidation.specification.UserSpecification
import org.springframework.stereotype.Service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification


@Service("UserService")
class UserServiceImpl(private val userRepository: UserRepository): UserService {

    override fun findAll(): List<User> {
        return userRepository.findAll()
    }

    override fun findAll(paging: Pageable): Page<User> {
        return userRepository.findAll(paging)
    }

    override fun findAll(spec: Specification<User>, page: Pageable): Page<User> {
        return userRepository.findAll(spec, page)
    }

    override fun findById(id: Long): User? {
        val user = userRepository.findById(id)
        return user.orElse(null)
    }

    override fun delete(user: User) {
        userRepository.delete(user)
    }

    override fun save(user: User): User {
        return userRepository.save(user)
    }

//    override fun getUserList(user: User): UserResponseList {
//        var list: List<User>
//        var pages: Page<User>
//        if (user.getPageNumber() == null) {
//            pages = PageImpl<Any?>(userRepository.findAll(userSpecification.getUsers(request)))
//        } else {
//            if (request.getPageSize() == null) {
//                request.setPageSize(defaultPageSize)
//            }
//            val paging: Pageable = PageRequest.of(request.getPageNumber() - 1, request.getPageSize())
//            pages = userRepository.findAll(userSpecification.getUsers(request), paging)
//        }
//        if (pages != null && pages.content != null) {
//            list = pages.content
//            if (list != null && list.size > 0) {
//                val respList = UserResponseList()
//                respList.setTotalPages(pages.totalPages)
//                respList.setTotalCount(pages.totalElements)
//                respList.setPageNo(pages.number + 1)
//                respList.setUsers(ArrayList<UserResponseDto>())
//                for (users in list) {
//                    val obj = UserResponseDto()
//                    obj.populateObject(users)
//                    respList.getUsers().add(obj)
//                }
//                return respList
//            }
//        }
//        return null
//    }
}
