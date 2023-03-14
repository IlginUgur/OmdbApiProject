package com.example.omdbapiproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.omdbapiproject.model.MovieDetailModel
import com.example.omdbapiproject.model.MovieSearchModel
import com.example.omdbapiproject.repository.MovieDetailDownload
import com.example.omdbapiproject.util.Resource
import kotlinx.coroutines.*

class MovieDetailViewModel(private val movieDetailDownloadRepository : MovieDetailDownload) : ViewModel() {

    val movieList = MutableLiveData<Resource<MovieDetailModel>>()
    val movieError = MutableLiveData<Resource<Boolean>>()
    val movieLoading = MutableLiveData<Resource<Boolean>>()

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        movieError.value = Resource.error(throwable.localizedMessage ?: "error",data = true)
    }

    fun getDetailFromAPI(imdbID : String) {
        movieLoading.value = Resource.loading(data=true)


        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val resource = movieDetailDownloadRepository.downloadMovieDetails(imdbID)
            withContext(Dispatchers.Main){
                resource.data?.let{
                    movieList.value = resource
                    movieLoading.value = Resource.loading(data = false)
                    movieError.value = Resource.error("",data = false)
                }
            }
        }
    }

    private var job : Job? = null
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}