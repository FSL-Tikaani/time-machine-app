package com.tikaani.newtimemachine.ui.screens.city

import com.tikaani.newtimemachine.data.models.CityModel
import com.tikaani.newtimemachine.data.models.CityPropertyModel

data class CityUiState (
    var city: CityModel = CityModel(),
    var propertiesCity: ArrayList<CityPropertyModel> = arrayListOf<CityPropertyModel>(),
    var isLoading: Boolean = true,
)