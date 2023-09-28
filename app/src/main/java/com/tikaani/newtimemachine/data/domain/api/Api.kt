package com.tikaani.newtimemachine.data.domain.api

import com.tikaani.newtimemachine.data.models.CityModel
import com.tikaani.newtimemachine.data.models.CityPropertyModel
import com.tikaani.newtimemachine.data.models.HistoryOfCenturyModel
import com.tikaani.newtimemachine.data.models.MiniCardModel
import io.ktor.client.statement.HttpResponse

interface Api {
    suspend fun getAllMiniCards(): List<MiniCardModel>

    suspend fun getCityData(id: String): Pair<CityModel, ArrayList<CityPropertyModel>>

    suspend fun getCentury(id: String): List<HistoryOfCenturyModel>
}