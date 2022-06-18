package com.codewarior.movieee.service

import com.codewarior.movieee.models.Movie
import com.codewarior.movieee.models.MovieRating
import com.codewarior.movieee.repo.MovieRepo
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

import java.util.*

@Service
class MovieService constructor( val movieRepo: MovieRepo) {
    fun findAll() = this.movieRepo.findAll()
    fun save(movie: Movie): Mono<Movie> =
        this.movieRepo.save(movie)

    fun findOne(id: Int): Mono<Movie> =
        this.movieRepo.findById(id)
            .switchIfEmpty(Mono.error(IllegalArgumentException("Movie not found")))

    fun rate(id: Int, comment: String, rating: Int): Mono<Movie> {
        val movieMono: Mono<Movie> = this.findOne(id)
        return movieMono.switchIfEmpty(Mono.error(IllegalArgumentException("Movie not found")))
            .map { movie ->
                movie.ratings.add(MovieRating(comment, rating, Date()))
                movie
            }.publishOn(Schedulers.boundedElastic()).map { movie ->
                this.movieRepo.save(movie)
                    .subscribe()
                movie
            }
    }
}

