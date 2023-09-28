package com.tikaani.newtimemachine.data.models

import kotlinx.serialization.Serializable


@Serializable
data class HistoryOfCenturyModel(
    val title: String = "Title",
    val arrSrcImg: ArrayList<String> = arrayListOf<String>("https://oddly-generous-monarch.ngrok-free.app/static/arzamas.jpg"),
    val textDescription: String = "Description",
)
