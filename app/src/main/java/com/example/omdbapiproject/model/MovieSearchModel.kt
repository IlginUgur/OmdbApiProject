package com.example.omdbapiproject.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieSearchModel (
    @SerializedName("Search"       ) var Search       : ArrayList<MovieModel> = arrayListOf(),
    @SerializedName("totalResults" ) var totalResults : String?           = null,
    @SerializedName("Response"     ) var Response     : String?           = null
): Serializable
