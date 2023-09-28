package com.tikaani.newtimemachine.ui.screens.city_history

import com.tikaani.newtimemachine.data.models.HistoryOfCenturyModel

data class CityHistoryUiState(
    var arrCentury: List<HistoryOfCenturyModel> = arrayListOf(),
    var isLoading: Boolean = true,
)
