package com.rff.boingballdemo.component

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import com.rff.boingballdemo.ui.theme.wireframeColor

private const val STROKE_WIDTH = 2f

@Composable
fun BoingBallBackground(
    modifier: Modifier = Modifier,
    horizonRatio: Float = 0.9f,  // 0..1 where floor meets wall
    floorRows: Int = 4          // how many “bands” on the floor
) {
    Canvas(modifier = modifier) {
        //println("Background canvas size = $size")
        val w       = size.width
        val h       = size.height
        val cellSize = (size.width / 17).toInt().toFloat()
        val horizon = h * horizonRatio

        // *** BACK WALL ***
        // verticals
        val cellsCountH: Int = (w / cellSize).toInt() - 2
        val cellsCountV: Int = (horizon / cellSize).toInt() - 1
        val cellsWidth = cellsCountH * cellSize
        val cellsHeight = cellsCountV * cellSize
        var x = (w - cellsWidth) / 2
        var y = cellSize
        while (x <= (w - cellsWidth) / 2 + cellsWidth) {
            drawLine(wireframeColor, Offset(x, y), Offset(x, y + cellsHeight), STROKE_WIDTH)
            x += cellSize
        }
        // horizontals
        x = (w - cellsWidth) / 2
        while (y <= horizon) {
            drawLine(wireframeColor, Offset(x, y), Offset(x + cellsWidth, y), STROKE_WIDTH)
            y += cellSize
        }

        // *** FLOOR ***
        val horizonY = cellSize + cellsHeight
        val centerX = size.width / 2f
        val maxSlope = 1f

        val xList = mutableListOf<Pair<Float, Float>>()
        while (x <= (w - cellsWidth) / 2 + cellsWidth) {
            val dxNorm = (x - centerX) / centerX
            val slope = dxNorm * maxSlope
            val dy = h - horizonY
            xList.add(x to x + slope * dy)
            x += cellSize
        }

        xList.forEach { (x, endX) ->
            drawLine(wireframeColor, Offset(x, horizonY), Offset(endX, h), STROKE_WIDTH)
        }

        for (i in 1 until floorRows) {
            val t = i / (floorRows - 1f)
            val ty = t * t
            val dy = horizonY + ty * (h - horizonY)
            val firstHX = xList.first().first
            val lastHX = xList.last().first
            val firstBX = xList.first().second
            val lastBX = xList.last().second

            val frac = (dy - horizonY) / (h - horizonY)
            val leftX = firstHX + (firstBX - firstHX) * frac
            val rightX = lastHX + (lastBX - lastHX) * frac

            drawLine(wireframeColor, Offset(leftX, dy), Offset(rightX, dy), STROKE_WIDTH)
        }
    }
}
