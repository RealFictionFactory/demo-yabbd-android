package com.rff.boingballdemo.main

import androidx.compose.ui.graphics.Color
import com.rff.boingballdemo.ui.theme.blueColor
import com.rff.boingballdemo.ui.theme.whiteColor

data class BoingBallState(
    val themeColor: Color = blueColor,
    val altColor: Color = whiteColor,
    val drawBorders: Boolean = false,
)
