package com.rff.boingballdemo.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.rff.boingballdemo.utils.Point3D
import com.rff.boingballdemo.ui.theme.redColor
import com.rff.boingballdemo.ui.theme.whiteColor
import com.rff.boingballdemo.utils.Face
import com.rff.boingballdemo.utils.TAU
import com.rff.boingballdemo.utils.toRadians
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

internal const val ROTATION_SPEED = 0.05f
internal const val BOING_BALL_ROWS = 8
internal const val BOING_BALL_COLUMNS = 16

@Composable
fun BoingBall(
    modifier : Modifier = Modifier,
    tilt     : Float    = -23.5f // Earth-like axial tilt
) {
    val vBounce = remember { Animatable(0f) }
    var angle by remember { mutableFloatStateOf(0f) }
    var direction by remember { mutableStateOf(false) }
    var hBouncePrev by remember { mutableFloatStateOf(0f) }
    val boing = rememberBoingBallAudio()
    // Horizontal movement fraction [0..1]
    val hBounce = rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    ).value
    LaunchedEffect(Unit) {
        while (true) {
            if (direction)
                angle += ROTATION_SPEED // radians per frame
            else
                angle -= ROTATION_SPEED // radians per frame
            withFrameNanos { /* keep looping */ }
        }
    }
    LaunchedEffect(Unit) {
        while (true) {
            angle += 0.005f // radians per frame
            // fall quickly
            vBounce.animateTo(
                targetValue = 1f,
                animationSpec = tween(500, easing = FastOutLinearInEasing)
            )
            boing.play()
            // rise more slowly
            vBounce.animateTo(
                targetValue = 0f,
                animationSpec = tween(900, easing = LinearOutSlowInEasing)
            )
        }
    }
    LaunchedEffect(direction) {
        if (direction)
            boing.playRight()
        else
            boing.playLeft()
    }

    Canvas(modifier = modifier) {
        /* ----- geometry constants ----- */
        val radius = size.minDimension * 0.2f
        val bounceMax = size.height - (size.height - size.height * .9f) / 2 - radius
        val bounceMin = size.height - size.height * .9f + radius
        val offsetY = lerp(bounceMax, bounceMin, vBounce.value)

        val maxX = size.width - radius
        val cx = radius + (maxX - radius) * hBounce
        direction = hBouncePrev > hBounce
        hBouncePrev = hBounce
        val tz = tilt.toRadians()

        drawCircle(
            color = Color.DarkGray,
            radius = radius,
            center = Offset(cx + 50f, offsetY - 10f),
            alpha = .3f
        )
        boingBall(
            cx = cx,
            cy = offsetY,
            radius = radius,
            rotationAngle = angle,
            earthTiltAngle = tz
        )
    }
}

private fun lerp(max: Float, min: Float, value: Float) = min + (max - min) * value

private fun DrawScope.boingBall(
    cx: Float,
    cy: Float,
    radius: Float,
    rotationAngle: Float,
    earthTiltAngle: Float,
) {
    val columns = BOING_BALL_COLUMNS
    val rows = BOING_BALL_ROWS
    val view = Point3D(0f, 0f, -1f)

    /* ----- generate vertices on the fly ----- */
    fun vertex(rowIndex: Int, colIndex: Int): Point3D {
        val lat = ((PI / rows) * (rowIndex - rows / 2f)).toFloat()   // +π/2 → –π/2
        val lon = ((TAU / columns) * colIndex)
        return Point3D(
            x = cos(lat) * cos(lon),
            y = sin(lat),
            z = cos(lat) * sin(lon)
        )
            .rotateY(rotationAngle) // spin first
            .rotateZ(earthTiltAngle) // constant axial tilt
    }

    // collect every quad (or pole triangle) into a list
    val faces = mutableListOf<Face>()

    for (row in 0 until rows) {
        for (column in 0 until columns) {
            // 4 (or 3) corners, wrapped in longitude
            val v1 = vertex(row, column)
            val v2 = vertex(row, (column + 1) % columns)
            val v3 = vertex(row + 1, (column + 1) % columns)
            val v4 = vertex(row + 1, column)
            // compute normal, use alternative edge if first is zero
            val e1 = v2 - v1
            val e2 = v3 - v1
            val normal = if (e1.isZero()) (v4 - v1).cross(e2) else e1.cross(e2)
            val facing = normal dot view

            // unified cull: skip any face pointing away, but keep south-pole winding correct
            if ((row > 0 && facing < 0f) || (row == 0 && facing > 0f)) continue

            // build path in 2-D screen space
            val p1 = v1.project(cx, cy, radius)
            val p2 = v2.project(cx, cy, radius)
            val p3 = v3.project(cx, cy, radius)
            val p4 = v4.project(cx, cy, radius)

            val path = Path().apply {
                moveTo(p1.x, p1.y)
                if (p1 != p2) lineTo(p2.x, p2.y)
                lineTo(p3.x, p3.y)
                if (p3 != p4) lineTo(p4.x, p4.y)
                close()
            }

            val col = if (((row + column) and 1) == 0) redColor else whiteColor
            val depth = (v1.z + v2.z + v3.z + v4.z) * 0.25f
            faces += Face(path, depth, col)
        }
    }

    /* ----- painter’s algorithm: far → near ----- */
    faces.sortedBy { it.depth }
        .forEach { f ->
            drawPath(f.path, color = f.color)
            drawPath(f.path, color = Color.Black, style = Stroke(width = 0.8f))
        }
}
