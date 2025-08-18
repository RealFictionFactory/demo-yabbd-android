package com.rff.boingballdemo.preferences

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rff.boingballdemo.data.local.AppSettings
import com.rff.boingballdemo.ui.theme.redColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PreferencesViewModel(
    val settings: AppSettings,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PreferencesState())
    val uiState: StateFlow<PreferencesState> = _uiState.asStateFlow()

    init {
        loadSettings()
    }

    fun onAction(action: PreferencesAction) {
        when (action) {
            is PreferencesAction.ChangeThemeColor -> TODO()
            is PreferencesAction.ChangeAltColor -> TODO()
            is PreferencesAction.ChangeFrameDraw -> {
                _uiState.update { it.copy(drawFrames = action.draw) }
            }
            PreferencesAction.BringDefaults -> {
                _uiState.update {
                    it.copy(
                        themeColor = redColor,
                        altColor = Color.White,
                        drawFrames = true,
                    )
                }
            }
            PreferencesAction.SaveSettings -> {
                saveCurrentSettings()
            }
        }
    }

    private fun loadSettings() {
    }

    private fun saveCurrentSettings() {
    }
}
