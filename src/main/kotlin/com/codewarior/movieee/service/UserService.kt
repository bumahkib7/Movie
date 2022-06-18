package com.codewarior.movieee.service

import com.codewarior.movieee.models.User
import com.codewarior.movieee.repo.UserRepo
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService( val userRepo : UserRepo) {
    fun getByUsername(username: String) : Flux<User> {
        return userRepo.findByUsername(username)
    }

    fun save(user: User)  : Mono<User>
    {
        return userRepo.save(user)
    }
}