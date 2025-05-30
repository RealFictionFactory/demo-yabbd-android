package com.rff.boingballdemo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AmigaToolbar(
    title: String,
    modifier: Modifier = Modifier,
    toolbarHeight: Dp = 28.dp,
    bgColor: Color = Color(0xFFC0C0C0),
    lightEdge: Color = Color.White,
    darkEdge: Color = Color(0xFF404040)
) {
    // 1px in canvas coordinates
    val strokePx = with(LocalDensity.current) { 2.dp.toPx() }

    Box(
        modifier
            .height(toolbarHeight)
            .fillMaxWidth()
            .drawBehind {
                // background
                drawRect(Color.White)
            }
    ) {
        Row(
            Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Close gadget
            CloseGadget(
                modifier = Modifier.size(28.dp),
            )
            Spacer(Modifier.width(4.dp))
            // Title
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color.Black,
                fontFamily = FontFamily.Monospace
            )
            Spacer(Modifier.width(4.dp))
            // Depth gadget
            EmptyToolbar(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
        }
    }
}

@Composable
private fun CloseGadget(
    modifier: Modifier = Modifier,
) {
    val strokePx = with(LocalDensity.current) { 2.dp.toPx() }
    val baseGadgetSize = with(LocalDensity.current) { 10.dp.toPx() }

    Box(
        modifier = modifier
            .drawBehind {
                var rectSize: Float
                val baseSize = (size.width * strokePx / baseGadgetSize)
                // background
                drawRect(Color.White)
                rectSize = 4 * baseSize
                drawRect(
                    color = Color.Black,
                    topLeft = Offset((size.width - rectSize) / 2, (size.height - rectSize) / 2),
                    size = Size(rectSize, rectSize)
                )
                rectSize = 3 * baseSize
                drawRect(
                    color = Color.White,
                    topLeft = Offset((size.width - rectSize) / 2, (size.height - rectSize) / 2),
                    size = Size(rectSize, rectSize)
                )
                rectSize = baseSize
                drawRect(
                    color = Color.Black,
                    topLeft = Offset((size.width - rectSize) / 2, (size.height - rectSize) / 2),
                    size = Size(rectSize, rectSize)
                )
            }
    )
}

@Preview
@Composable
private fun CloseGadgetPreview() {
    Box(
        modifier = Modifier.size(50.dp),
        contentAlignment = Alignment.Center,
    ) {
        CloseGadget(
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun EmptyToolbar(
    modifier: Modifier = Modifier
) {
    val strokePx = with(LocalDensity.current) { 2.dp.toPx() }
    val baseGadgetSize = with(LocalDensity.current) { 10.dp.toPx() }

    Box(
        modifier = modifier
            .drawBehind {
                drawRect(color = Color.White)
                val baseSize = (size.minDimension * strokePx / baseGadgetSize)
                drawRect(
                    color = Color.Black,
                    topLeft = Offset(baseSize, size.height - 2 * baseSize),
                    size = Size(size.width - 2 * baseSize, baseSize)
                )
                drawRect(
                    color = Color.Black,
                    topLeft = Offset(baseSize, size.height - 4 * baseSize),
                    size = Size(size.width - 2 * baseSize, baseSize)
                )
            }
    )
}

@Preview
@Composable
private fun EmptyToolbarPreview() {
    Box(
        modifier = Modifier.size(250.dp, 50.dp),
        contentAlignment = Alignment.Center,
    ) {
        EmptyToolbar(
            modifier = Modifier.size(64.dp, 32.dp)
        )
    }
}

@Preview
@Composable
private fun AmigaToolbarPreview() {
    AmigaToolbar(title = "Amiga Toolbar")
}