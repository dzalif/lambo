package com.example.lambo.model

import com.squareup.moshi.Json


data class MovieResponse(
    val page: Int,
    @Json(name="total_results")
    val totaResults: Int,
    @Json(name="total_pages")
    val totalPages: Int,
    val results: MutableList<Movie>
)