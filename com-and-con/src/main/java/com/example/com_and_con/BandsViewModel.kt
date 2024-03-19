package com.example.com_and_con

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.com_and_con.api.createRetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BandsViewModel: ViewModel() {
        //_bandsFlow wird nur innerhalb des ViewModels verwendet, daher private
        private val _bandsFlow: MutableStateFlow<List<BandCode>> = MutableStateFlow(emptyList())
        //bandsFlow wird nach aussen als Flow bereitgestellt
        val bandsFlow: Flow<List<BandCode>> = _bandsFlow

        fun loadBands() {
            viewModelScope.launch(Dispatchers.IO) {
                val service = createRetrofitService()
                _bandsFlow.value = service.getBandCodes()
            }
        }


}