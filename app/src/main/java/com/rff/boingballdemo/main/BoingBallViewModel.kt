package com.rff.boingballdemo.main

import androidx.lifecycle.ViewModel
import com.rff.boingballdemo.data.local.AppSettings
import com.rff.boingballdemo.ui.theme.AltAmigaOs13PickerColors
import com.rff.boingballdemo.ui.theme.DefaultAmigaOs13PickerColors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class BoingBallViewModel(
    private val settings: AppSettings
) : ViewModel() {
    private val _uiState : MutableStateFlow<BoingBallState> = MutableStateFlow(BoingBallState())
    val uiState = _uiState.asStateFlow()

    init {
        settings.boingBallPrefs.onEach { prefs ->
            _uiState.update {
                it.copy(
                    themeColor = DefaultAmigaOs13PickerColors[prefs.themeColorIndex],
                    altColor = AltAmigaOs13PickerColors[prefs.altColorIndex],
                    drawBorders = prefs.drawBorders
                )
            }
        }
    }
}
