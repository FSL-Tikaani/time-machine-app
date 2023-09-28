package com.tikaani.newtimemachine.ui.screens.city_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tikaani.newtimemachine.MyApp
import com.tikaani.newtimemachine.data.models.HistoryOfCenturyModel
import com.tikaani.newtimemachine.ui.screens.city.CityUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CityHistoryViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(CityHistoryUiState())
    val uiState: StateFlow<CityHistoryUiState> = _uiState.asStateFlow()


    fun getCentury(id: String) {
        viewModelScope.launch(Dispatchers.IO){
            val arr = MyApp.api.getCentury(id)
            _uiState.emit(_uiState.value.copy(arrCentury = arr, isLoading = false))
        }

    }
}