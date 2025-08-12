package com.rff.boingballdemo.preferences

import androidx.compose.ui.graphics.Color

sealed interface PreferencesAction {
    data class ChangeThemeColor(val color: Color) : PreferencesAction
    data class ChangeAltColor(val color: Color) : PreferencesAction
    data class ChangeFrameDraw(val draw: Boolean) : PreferencesAction
    data object BringDefaults : PreferencesAction
    data object SaveSettings : PreferencesAction
}
