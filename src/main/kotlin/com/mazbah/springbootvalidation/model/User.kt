package com.mazbah.springbootvalidation.model

import javax.persistence.*
import javax.validation.constraints.*


@Entity
@Table(name="users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "user_id") var id: Long? = null,

    @get:NotEmpty
    @get:Size(min = 2, message = "First Name should have at least 2 characters")  // added annotation use-site target here
    @Column(name = "first_name")  var firstName:String? = null,

    @get:NotNull @get:Size(min = 2, message = "Last Name should have at least 2 characters")
    @Column(name = "last_name") var lastName:String? = null,

    @get:Email @get:NotBlank(message = "Email is mandatory")
    @Column(name = "email") var email: String? = null
)