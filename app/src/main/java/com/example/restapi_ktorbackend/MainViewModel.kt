package com.example.restapi_ktorbackend

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.restapi_ktorbackend.data.Rabbit
import com.example.restapi_ktorbackend.data.RabbitsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: RabbitsApi
): ViewModel() {

    private val _state = mutableStateOf(RabbitState())
    val state: State<RabbitState> = _state

    init{
        getRandomRabbit()
    }

    fun getRandomRabbit(){
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    rabbit = api.getRandomRabbit(),
                    isLoading = false
                )
            }catch (e: Exception){
                Log.d("MainViewModel", "getRandomRabbit: ",e)
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }


    data class RabbitState(
        val rabbit: Rabbit? = null,
        val isLoading: Boolean = false
    )
}