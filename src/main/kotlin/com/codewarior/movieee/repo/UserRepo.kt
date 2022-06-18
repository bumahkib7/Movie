package com.codewarior.movieee.repo

import com.codewarior.movieee.models.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface UserRepo : ReactiveMongoRepository<User, Int> {
    fun findByUsername(username: String): Flux<User>
}
