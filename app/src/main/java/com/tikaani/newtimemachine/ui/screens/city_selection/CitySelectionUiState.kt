package com.tikaani.newtimemachine.ui.screens.city_selection

import com.tikaani.newtimemachine.data.models.MiniCardModel

data class CitySelectionUiState(
    var miniCards: List<MiniCardModel> = listOf<MiniCardModel>(),
    var isLoading: Boolean = true,
)
