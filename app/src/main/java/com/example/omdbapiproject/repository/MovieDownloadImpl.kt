package com.example.omdbapiproject.repository

import com.example.omdbapiproject.model.MovieSearchModel
import com.example.omdbapiproject.service.MovieAPI
import com.example.omdbapiproject.util.Resource

class MovieDownloadImpl(private val api : MovieAPI) : MovieDownload{


    override suspend fun downloadMovies(searchKey : String): Resource<MovieSearchModel> {
        return try {
            println("hata MovieDownloadImpl data")
            val response = api.getData(searchKey)
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