package com.tikaani.newtimemachine.data.models

import kotlinx.serialization.Serializable

@Serializable
data class MiniCardModel(
    val id: String = "",
    val nameCity: String = "",
    val srcImg: String = "",
)

