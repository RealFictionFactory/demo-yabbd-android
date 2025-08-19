package com.rff.boingballdemo.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rff.boingballdemo.data.local.AppSettings
import com.rff.boingballdemo.data.local.BoingBallPrefs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PreferencesViewModel(
    private val settings: AppSettings,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PreferencesState())
    val uiState: StateFlow<PreferencesState> = _uiState.asStateFlow()

    init {
        settings.boingBallPrefs
            .onEach { prefs ->
                _uiState.update {
                    it.copy(
                        themeColorIndex = prefs.themeColorIndex,
                        altColorIndex = prefs.altColorIndex,
                        drawBorders = prefs.drawBorders,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: PreferencesAction) {
        when (action) {
            is PreferencesAction.ChangeThemeColor -> {
                _uiState.update { it.copy(themeColorIndex = action.index) }
            }
            is PreferencesAction.ChangeAltColor -> {
                _uiState.update { it.copy(altColorIndex = action.index) }
            }
            is PreferencesAction.ChangeFrameDraw -> {
                _uiState.update { it.copy(drawBorders = action.draw) }
            }
            PreferencesAction.BringDefaults -> {
                _uiState.update {
                    it.copy(
                        themeColorIndex = 0,
                        altColorIndex = 3,
                        drawBorders = false,
                    )
                }
            }
            PreferencesAction.SaveSettings -> {
                saveCurrentSettings()
            }
        }
    }

    private fun saveCurrentSettings() {
        viewModelScope.launch {
            settings.saveBoingBallPrefs(
                BoingBallPrefs(
                    themeColorIndex = _uiState.value.themeColorIndex,
                    altColorIndex = _uiState.value.altColorIndex,
                    drawBorders = _uiState.value.drawBorders,
                )
            )
        }
    }
}
