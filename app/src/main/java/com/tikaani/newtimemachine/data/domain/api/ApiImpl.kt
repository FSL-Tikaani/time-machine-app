package com.tikaani.newtimemachine.data.domain.api

import android.util.Log
import com.tikaani.newtimemachine.R
import com.tikaani.newtimemachine.data.models.CityModel
import com.tikaani.newtimemachine.data.models.CityPropertyModel
import com.tikaani.newtimemachine.data.models.HistoryOfCenturyModel
import com.tikaani.newtimemachine.data.models.MiniCardModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiImpl: Api {

    private val baseUrl = "https://oddly-generous-monarch.ngrok-free.app"
    private var client: HttpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }




    override suspend fun getAllMiniCards(): List<MiniCardModel> {
        val response = client.get("$baseUrl/json/getAllItemsMiniCards/")
        var data = listOf<MiniCardModel>()
        if (response.status.value in 200..299){
            data = Json.decodeFromString<List<MiniCardModel>>(response.body())
            Log.d("getAllMiniCards", "collected ${data.size} items")
        }else{
            Log.d("getAllMiniCards", "error ${response.status.value})")
        }

        return data
    }

    override suspend fun getCityData(id: String): Pair<CityModel, ArrayList<CityPropertyModel>> {
        val response = client.get("$baseUrl/json/getCity/${id}/")
        var data = CityModel()
        if (response.status.value in 200..299){
            data = Json.decodeFromString<CityModel>(response.body())
            Log.d("getCityData", "collected data items")
        }else{
            Log.d("getCityData", "error ${response.status.value})")
        }


        val properties = arrayListOf(
            CityPropertyModel(R.drawable.icon_map, data.cityName, data.arrSrc[0]),
            CityPropertyModel(R.drawable.icon_clock, data.foundationDate, data.arrSrc[1]),
            CityPropertyModel(R.drawable.icon_people, data.population, data.arrSrc[2]),
            CityPropertyModel(R.drawable.icon_tower, "Достопримечательности", data.arrSrc[3]),
        )

        return Pair(data, properties)
    }

    override suspend fun getCentury(id: String): List<HistoryOfCenturyModel> {
        val response = client.get("$baseUrl/json/getItemsHistory/${id}/")
        var data = listOf<HistoryOfCenturyModel>()
        if (response.status.value in 200..299){
            data = Json.decodeFromString<List<HistoryOfCenturyModel>>(response.body())
            Log.d("getCentury", "collected ${data.size} items")
        }else{
            Log.d("getCentury", "error ${response.status.value})")
        }

        return data
    }
}