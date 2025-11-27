package com.example.test_lab_week_12

import androidx.lifecycle.LiveData
import com.example.test_lab_week_12.api.MovieService
import com.example.test_lab_week_12.model.Movie
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MovieRepository(private val movieService: MovieService) {

    private val apiKey = "your_api_key_here"

    fun fetchMovies(): Flow<List<Movie>> {
        return flow{
            emit(movieService.getPopularMovies(apiKey).results)
        }.flowOn(Dispatchers.IO)
    }
}