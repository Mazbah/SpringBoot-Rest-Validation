package com.mazbah.springbootvalidation.model

import com.mazbah.springbootvalidation.validation.EmailDomain
import javax.persistence.*
import javax.validation.constraints.*

@Entity
@Table(name="users")
//@CustomEmail("Email isn't Valid.")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "user_id") var id: Long? = null,

    @field:NotEmpty(message = "First name can't be empty")
    @field:Pattern(regexp="[^0-9]{1,10}",message="length must be 1-10")
  //  @field:Size(min = 2, message = "First Name should have at least 2 characters")  // added annotation use-site target here
    @Column(name = "first_name")  var firstName:String? = null,

    @field:NotNull(message = "Last name can't be null")
//    @field:Min(value=3, message="must be equal or greater than 3")
//    @field:Max(value=5, message="must be equal or less than 4")
    @field:Size(min = 2, max = 20, message = "Last Name should have at least 2 characters")
    @Column(name = "last_name") var lastName:String? = null,


    @field:EmailDomain(message = "Email isn't Valid.Please use robi.com.bd or reddotdigitalit.com domain")
    @field:Email @field:NotBlank(message = "Email is mandatory")
    @Column(name = "email") var email: String
)