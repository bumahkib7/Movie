package com.codewarior.movieee.repo

import com.codewarior.movieee.models.Movie
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepo : ReactiveMongoRepository<Movie, Int>