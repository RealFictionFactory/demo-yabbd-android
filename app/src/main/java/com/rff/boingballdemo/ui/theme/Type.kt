package com.rff.boingballdemo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rff.boingballdemo.R

val TopazFont = FontFamily(
    Font(R.font.topaz_a500)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = TopazFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.White,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = TopazFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.White,
        lineHeight = 20.sp,
        letterSpacing = 0.3.sp
    ),
    bodySmall = TextStyle(
        fontFamily = TopazFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = Color.White,
        lineHeight = 16.sp,
        letterSpacing = 0.1.sp
    ),
    labelLarge = TextStyle(
        fontFamily = TopazFont,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = Color.White,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
)
