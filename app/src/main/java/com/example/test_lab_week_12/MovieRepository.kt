package com.example.test_lab_week_12

import androidx.lifecycle.LiveData
import com.example.test_lab_week_12.api.MovieService
import com.example.test_lab_week_12.model.Movie
import androidx.lifecycle.MutableLiveData


class MovieRepository(private val movieService: MovieService) {

    private val apiKey = "your_api_key_here"

    private val movieLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = movieLiveData

    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String>
        get() = errorLiveData

    suspend fun fetchMovies() {
        try{
            val popularMovies = movieService.getPopularMovies(apiKey)
            movieLiveData.postValue(popularMovies.results)
        } catch (exception: Exception) {
            errorLiveData.postValue(
                "An error occurred: ${exception.message}"
            )
        }
    }
}