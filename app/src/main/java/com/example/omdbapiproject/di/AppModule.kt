package com.example.omdbapiproject.di

import com.example.omdbapiproject.model.MovieDetailModel
import com.example.omdbapiproject.repository.MovieDetailDownload
import com.example.omdbapiproject.repository.MovieDetailDownloadImpl
import com.example.omdbapiproject.repository.MovieDownload
import com.example.omdbapiproject.repository.MovieDownloadImpl
import com.example.omdbapiproject.service.MovieAPI
import com.example.omdbapiproject.util.Constants.BASE_URL
import com.example.omdbapiproject.viewmodel.MovieDetailViewModel
import com.example.omdbapiproject.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    //singleton scope
    single{

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    single<MovieDownload> {
        MovieDownloadImpl(get())
    }

    viewModel{
        MovieViewModel(get())
    }

    single<MovieDetailDownload> {
        MovieDetailDownloadImpl(get())
    }

    viewModel{
        MovieDetailViewModel(get())
    }

}