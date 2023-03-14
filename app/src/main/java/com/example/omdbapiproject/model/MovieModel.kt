package com.example.omdbapiproject.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class MovieModel (
    @SerializedName("Title"  ) var Title  : String? = null,
    @SerializedName("Year"   ) var Year   : String? = null,
    @SerializedName("imdbID" ) var imdbID : String? = null,
    @SerializedName("Type"   ) var Type   : String? = null,
    @SerializedName("Poster" ) var Poster : String? = null
) : Serializable
