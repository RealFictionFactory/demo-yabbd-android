package com.rff.boingballdemo.preferences

import androidx.compose.ui.graphics.Color
import com.rff.boingballdemo.ui.theme.redColor

data class PreferencesState(
    val themeColor: Color = redColor,
    val altColor: Color = Color.White,
    val drawBorders: Boolean = true,
)
