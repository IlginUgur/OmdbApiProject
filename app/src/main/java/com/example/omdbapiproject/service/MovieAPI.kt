package com.example.omdbapiproject.service

import com.example.omdbapiproject.model.MovieDetailModel
import com.example.omdbapiproject.model.MovieModel
import com.example.omdbapiproject.model.MovieSearchModel
import com.example.omdbapiproject.util.Constants.API_KEY
import com.example.omdbapiproject.util.Constants.MOVIE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    //@GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    //@GET("?t=pulp&apikey=e188e3cc")
   // @GET("?i=tt3896198&apikey=e188e3cc")
    //https://www.omdbapi.com/?i=tt3896198&apikey=e188e3cc
  //  suspend fun getData() : Response<List<MovieModel>>


    @GET(".")
    suspend fun getData(
        @Query("s") searchString :String,
        @Query("apikey") apiKey :String = API_KEY
    ) : Response<MovieSearchModel>

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId : String,
        @Query("apikey") apiKey: String = API_KEY
    ) : Response<MovieDetailModel>

}