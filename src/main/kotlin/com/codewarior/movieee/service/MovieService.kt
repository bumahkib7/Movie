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
            .switchIfEmpty(Mono.error(MovieNotFoundException.create(id)))

    fun rate(id: Int, Comment: String, Rating: Int): Mono<Movie> {
        val movieMono: Mono<Movie> = this.findOne(id)
        return movieMono.switchIfEmpty(Mono.error(MovieNotFoundException.create(id)))
            .map { movie ->
                movie.ratings.add(MovieRating(Comment, Rating, Date()))
                movie
            }.publishOn(Schedulers.boundedElastic()).map { movie ->
                this.save(movie).subscribe()
                movie
            }
    }


    class MovieNotFoundException(message: String) {
        companion object {
            fun create(id: Int): MovieNotFoundException {
                return MovieNotFoundException("Movie by id $id not found")
            }

        }
    }
}

