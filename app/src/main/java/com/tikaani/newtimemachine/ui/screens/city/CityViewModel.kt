package com.tikaani.newtimemachine.ui.screens.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tikaani.newtimemachine.MyApp
import com.tikaani.newtimemachine.ui.screens.city_selection.CitySelectionUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState.asStateFlow()


    fun getCity(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            val data = MyApp.api.getCityData(id)
            _uiState.emit(_uiState.value.copy(
                city = data.first,
                propertiesCity = data.second,
                isLoading = false,
            ))
        }
    }
}