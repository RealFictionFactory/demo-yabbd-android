package com.rff.boingballdemo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rff.boingballdemo.ui.theme.BoingBallDemoTheme
import com.rff.boingballdemo.ui.theme.amigaOs13Blue
import com.rff.boingballdemo.ui.theme.blueColor
import com.rff.boingballdemo.ui.theme.greenColor
import com.rff.boingballdemo.ui.theme.redColor

val DefaultAmigaOs13PickerColors = listOf(
    redColor,
    amigaOs13Blue,
    greenColor,
    Color.Black
)

@Composable
fun AmigaOs13ColorPicker(
    selectedIndex: Int = 0,
    onColorSelected: (Int) -> Unit = { _-> },
    colors: List<Color> = DefaultAmigaOs13PickerColors,
) {
    Row(
        modifier = Modifier
            .background(color = Color.White)
            .padding(1.dp)
    ) {
        colors.forEachIndexed { index, color ->
            ColorField(
                color = color,
                isSelected = selectedIndex == index,
                onClick = { onColorSelected(index) }
            )
        }
    }
}

@Composable
private fun ColorField(
    color: Color,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(50.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = if (isSelected) Color.White else amigaOs13Blue)
                .padding(1.dp)
                .background(color = amigaOs13Blue)
                .padding(if (isSelected) 1.dp else 0.dp)
                .background(color = color)
        )
    }
}

@Preview
@Composable
private fun ColorPickerPreview() {
    BoingBallDemoTheme {
        AmigaOs13ColorPicker()
    }
}
