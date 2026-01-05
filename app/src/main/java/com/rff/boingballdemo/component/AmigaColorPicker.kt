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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rff.boingballdemo.ui.theme.BoingBallDemoTheme
import com.rff.boingballdemo.ui.theme.DefaultAmigaOs13PickerColors
import com.rff.boingballdemo.ui.theme.amigaOs13Blue
import com.rff.boingballdemo.ui.theme.amigaOs30Grey
import com.rff.boingballdemo.ui.theme.blackColor
import com.rff.boingballdemo.ui.theme.whiteColor

@Composable
fun AmigaColorPicker(
    selectedIndex: Int = 0,
    osStyle: OSStyle,
    onColorSelected: (Int) -> Unit = { _-> },
    colors: List<Color> = DefaultAmigaOs13PickerColors,
) {
    when (osStyle) {
        OSStyle.AmigaOS13 -> AmigaOs13ColorPicker(selectedIndex, onColorSelected, colors)
        OSStyle.AmigaOS20 -> AmigaOs30ColorPicker(selectedIndex, onColorSelected, colors)
    }
}

@Composable
private fun AmigaOs13ColorPicker(
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
            AmigaOs13ColorField(
                color = color,
                isSelected = selectedIndex == index,
                onClick = { onColorSelected(index) }
            )
        }
    }
}

@Composable
private fun AmigaOs30ColorPicker(
    selectedIndex: Int = 0,
    onColorSelected: (Int) -> Unit = { _-> },
    colors: List<Color> = DefaultAmigaOs13PickerColors,
) {
    Row(
        modifier = Modifier
            .drawBehind {
                val stroke = 1.dp.toPx()
                drawRect(color = whiteColor)
                drawRect(
                    color = blackColor,
                    topLeft = Offset(stroke, size.height - stroke),
                    size = Size(size.width - 2 * stroke, stroke)
                )
                drawRect(
                    color = blackColor,
                    topLeft = Offset(size.width - stroke, 0f),
                    size = Size(stroke, size.height)
                )
            }
            .padding(1.dp)
    ) {
        colors.forEachIndexed { index, color ->
            AmigaOs30ColorField(
                color = color,
                isSelected = selectedIndex == index,
                onClick = { onColorSelected(index) }
            )
        }
    }
}

@Composable
private fun AmigaOs13ColorField(
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

@Composable
private fun AmigaOs30ColorField(
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
                .drawBehind {
                    if (isSelected) {
                        val stroke = 1.dp.toPx()
                        drawRect(color = blackColor)
                        drawRect(
                            color = whiteColor,
                            topLeft = Offset(stroke, size.height - stroke),
                            size = Size(size.width - 2 * stroke, stroke)
                        )
                        drawRect(
                            color = whiteColor,
                            topLeft = Offset(size.width - stroke, 0f),
                            size = Size(stroke, size.height)
                        )
                    } else {
                        drawRect(color = amigaOs30Grey)
                    }
                }
                .padding(1.dp)
                .background(color = amigaOs30Grey)
                .padding(if (isSelected) 1.dp else 0.dp)
                .background(color = color)
        )
    }
}

@Preview
@Composable
private fun AmigaOs13ColorPickerPreview() {
    BoingBallDemoTheme {
        AmigaOs13ColorPicker()
    }
}

@Preview
@Composable
private fun AmigaOs30ColorPickerPreview() {
    BoingBallDemoTheme {
        AmigaOs30ColorPicker()
    }
}
