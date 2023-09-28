package com.tikaani.newtimemachine.ui.screens.city_selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tikaani.newtimemachine.MyApp
import com.tikaani.newtimemachine.R
import com.tikaani.newtimemachine.data.models.MiniCardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CitySelectionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CitySelectionUiState())
    val uiState: StateFlow<CitySelectionUiState> = _uiState.asStateFlow()

    init {
        getAllMiniCards()
    }


    fun getAllMiniCards(){
        viewModelScope.launch(Dispatchers.IO) {
            val listCityCards = MyApp.api.getAllMiniCards()
            _uiState.emit(_uiState.value.copy(miniCards = listCityCards, isLoading = false))
        }
    }
}




