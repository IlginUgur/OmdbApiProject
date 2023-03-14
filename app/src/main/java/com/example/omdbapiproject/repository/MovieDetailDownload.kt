package com.example.omdbapiproject.repository

import com.example.omdbapiproject.model.MovieDetailModel
import com.example.omdbapiproject.model.MovieSearchModel
import com.example.omdbapiproject.util.Resource


interface MovieDetailDownload {
    suspend fun downloadMovieDetails(imdbID : String) : Resource<MovieDetailModel>
}