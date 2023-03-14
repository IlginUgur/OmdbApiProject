package com.example.omdbapiproject.repository

import com.example.omdbapiproject.model.MovieSearchModel
import com.example.omdbapiproject.util.Resource

interface MovieDownload {
   // suspend fun downloadMovies(searchKey : String = "pulp") : Resource<List<MovieSearchModel>>
   suspend fun downloadMovies(searchKey : String) : Resource<MovieSearchModel>
}