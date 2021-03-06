package com.example.atry.viewModels
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atry.viewModels.stateModels.TaskListState
import com.example.atry.data.remote.RetroApi
import com.example.atry.viewModels.stateModels.DayInfoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetDayInfoViewModel @Inject constructor(
    private val api:RetroApi

):ViewModel(){

    private val _state = mutableStateOf(DayInfoListState())
    val state: State<DayInfoListState> = _state
    val loading = mutableStateOf(false)
    val dataLoaded = mutableStateOf(false)


    fun findDaysOfMonth(month:String){
        viewModelScope.launch {
            loading.value = true
            try {
                _state.value = state.value.copy(isLoading = true)
                delay(2000)
                _state.value = state.value.copy(
                    days = api.getDayOfMonth(month),
                    isLoading = false
                )
                dataLoaded.value = true

            loading.value = false
            }
            catch (e:Exception){
                Log.e("GetDayInfoViewModel","GetDayInfoViewModel:",e)
                _state.value = state.value.copy(isLoading = false,error="error")
                dataLoaded.value = false
                loading.value = true

            }
        }


    }

}