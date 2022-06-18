package com.codewarior.movieee.service

import com.codewarior.movieee.models.User
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.kotlin.core.publisher.toMono


@Service
class MovieeReactiveUserDetailsService(val userService: UserService) : ReactiveUserDetailsService {
    fun findByUsername(username: String?): Mono<UserDetails> {
        if (username != null) {
            return userService.getByUsername(username)
                .toMono()
                .map { user -> User.withUsername(user.username)
                    .password(user.password)
                    .roles(user.role)
                    .build()}
        }
        return Mono.empty()
    }

}
