package com.example.omdbapiproject.repository

import com.example.omdbapiproject.model.MovieDetailModel
import com.example.omdbapiproject.service.MovieAPI
import com.example.omdbapiproject.util.Resource

class MovieDetailDownloadImpl(private val api : MovieAPI) : MovieDetailDownload {

    override suspend fun downloadMovieDetails(imdbID : String): Resource<MovieDetailModel> {
        return try {
            val response = api.getMovieDetail(imdbID)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Sunucudan cevap gelmedi", null)
            } else {
                Resource.error("Internetinizi kontrol edin", null)
            }
        }catch(e: java.lang.Exception){
                Resource.error("Data hic yok", null)
            }

    }
}