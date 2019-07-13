package com.example.lambo.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lambo.model.Movie
import com.example.lambo.network.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

val API_KEY = "b93e3d84dc8a6327bc9c8c27103fb16f"


class HomeViewModel : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        getMovies()
    }

    private fun getMovies() {
        coroutineScope.launch {
            val getMoviesDeferred = Api.retrofitService.getMovies(API_KEY)
            try {
                val listResult = getMoviesDeferred.await()
                _movies.value = listResult.results
            } catch (e: Exception) {
                _movies.value = ArrayList()
            }
        }
    }
}