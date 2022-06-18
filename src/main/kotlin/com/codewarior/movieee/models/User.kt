package com.codewarior.movieee.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users") // this tells spring to use this collection for making the object persistent in the database
data class User(
    @Id val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val role: String,
    val description: String
)

