package com.rff.boingballdemo.preferences

data class PreferencesState(
    val themeColorIndex: Int = 1,
    val altColorIndex: Int = 3,
    val drawBorders: Boolean = false,
)
