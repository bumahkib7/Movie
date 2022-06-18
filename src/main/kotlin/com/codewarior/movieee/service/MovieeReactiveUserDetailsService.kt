package com.codewarior.movieee.service

import com.codewarior.movieee.models.User
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class MovieeReactiveUserDetailsService(private val userService: UserService) : ReactiveUserDetailsService {
    override fun findByUsername(username: String): Mono<UserDetails> {
        return userService.getByUsername(username)
            .toMono()
            .map { user ->
                User.withUsername(user.username)
                    .password(user.password)
                    .roles(user.roles)
                    .build() as UserDetails}


    }
}