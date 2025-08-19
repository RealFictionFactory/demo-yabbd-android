package com.rff.boingballdemo.preferences

sealed interface PreferencesAction {
    data class ChangeThemeColor(val index: Int) : PreferencesAction
    data class ChangeAltColor(val index: Int) : PreferencesAction
    data class ChangeFrameDraw(val draw: Boolean) : PreferencesAction
    data object BringDefaults : PreferencesAction
    data object SaveSettings : PreferencesAction
}
