package com.app.bitesmart.viewModels

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.app.bitesmart.data.ResponseTextUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TextRecognizedViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ResponseTextUIState())
    val uiState: StateFlow<ResponseTextUIState> = _uiState.asStateFlow()

    fun updateResponse(responseText: String, isResponseReceived: Boolean){
        updateResponseTextUIState(responseText, isResponseReceived )
    }

    private fun updateResponseTextUIState(updatedResponseText: String, isResponseReceived: Boolean) {
        Log.d("ViewModel", "Updating state: text=$updatedResponseText, isReceived=$isResponseReceived")
            _uiState.update { currentState ->
                currentState.copy(
                 responseText = updatedResponseText,
                    isResponseReceived =  isResponseReceived
                )
            }

    }
}
