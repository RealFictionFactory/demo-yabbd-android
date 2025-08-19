package com.rff.boingballdemo.preferences

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rff.boingballdemo.data.local.AppSettings
import com.rff.boingballdemo.data.local.BoingBallPrefs
import com.rff.boingballdemo.ui.theme.AltAmigaOs13PickerColors
import com.rff.boingballdemo.ui.theme.DefaultAmigaOs13PickerColors
import com.rff.boingballdemo.ui.theme.redColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PreferencesViewModel(
    private val settings: AppSettings,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PreferencesState())
    val uiState: StateFlow<PreferencesState> = _uiState.asStateFlow()

    init {
        settings.boingBallPrefs.onEach { prefs ->
            _uiState.update {
                it.copy(
                    themeColor = DefaultAmigaOs13PickerColors[prefs.themeColorIndex],
                    altColor = AltAmigaOs13PickerColors[prefs.altColorIndex],
                    drawFrames = prefs.drawFrames,
                )
            }
        }
    }

    fun onAction(action: PreferencesAction) {
        when (action) {
            is PreferencesAction.ChangeThemeColor -> {
                _uiState.update { it.copy(themeColor = DefaultAmigaOs13PickerColors[action.index]) }
            }
            is PreferencesAction.ChangeAltColor -> {
                _uiState.update { it.copy(altColor = AltAmigaOs13PickerColors[action.index]) }
            }
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

    private fun saveCurrentSettings() {
        viewModelScope.launch {
            settings.saveBoingBallPrefs(
                BoingBallPrefs(
                    themeColorIndex = DefaultAmigaOs13PickerColors.indexOf(_uiState.value.themeColor),
                    altColorIndex = AltAmigaOs13PickerColors.indexOf(_uiState.value.altColor),
                    drawFrames = _uiState.value.drawFrames,
                )
            )
        }
    }
}
