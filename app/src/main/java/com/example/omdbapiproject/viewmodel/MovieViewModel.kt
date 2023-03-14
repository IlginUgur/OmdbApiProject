package com.example.omdbapiproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.omdbapiproject.model.MovieModel
import com.example.omdbapiproject.model.MovieSearchModel
import com.example.omdbapiproject.repository.MovieDownload
import com.example.omdbapiproject.service.MovieAPI
import com.example.omdbapiproject.util.Resource
import com.example.omdbapiproject.view.RecyclerViewAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieViewModel(private val movieDownloadRepository : MovieDownload) : ViewModel() {

    val movieList = MutableLiveData<Resource<MovieSearchModel>>()
    val movieError = MutableLiveData<Resource<Boolean>>()
    val movieLoading = MutableLiveData<Resource<Boolean>>()

    private var job : Job? = null

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Hata MovieViewModel")
        println(throwable.localizedMessage)
        movieError.value = Resource.error(throwable.localizedMessage ?: "error",data = true)
    }

    fun getDataFromAPI(searchKey : String) {
        movieLoading.value = Resource.loading(data=true)


        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val resource = movieDownloadRepository.downloadMovies(searchKey)
            withContext(Dispatchers.Main){
                resource.data?.let{
                    movieList.value = resource
                    movieLoading.value = Resource.loading(data = false)
                    movieError.value = Resource.error("",data = false)
                }
            }
            }
        }


    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}