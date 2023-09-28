package com.tikaani.newtimemachine.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CityModel (
    val id: String = "",
    val cityName: String = "",
    val arrPhotosCity: ArrayList<String> = arrayListOf(),
    val cityDescription: String = "",
    val foundationDate: String = "",
    val population: String = "",
    val arrSrc: ArrayList<String> = arrayListOf()
)