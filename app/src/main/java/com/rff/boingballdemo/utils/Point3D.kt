package com.rff.boingballdemo.utils

import androidx.compose.ui.geometry.Offset
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

data class Point3D(val x: Float, val y: Float, val z: Float) {
    operator fun minus(o: Point3D) = Point3D(x - o.x, y - o.y, z - o.z)

    fun cross(point: Point3D) = Point3D(
        y * point.z - z * point.y,
        z * point.x - x * point.z,
        x * point.y - y * point.x
    )

    /** iloczyn skalarny */
    infix fun dot(o: Point3D) = x * o.x + y * o.y + z * o.z

    private fun rotate(yaw: Float, pitch: Float): Point3D {
        val cy = cos(yaw)
        val sy = sin(yaw)
        val cp = cos(pitch)
        val sp = sin(pitch)

        val x1 = x * cy + z * sy
        val z1 = -x * sy + z * cy          // yaw
        val y1 = y * cp - z1 * sp
        val z2 = y * sp + z1 * cp         // pitch

        return Point3D(x1, y1, z2)
    }

    fun rotateY(angle: Float): Point3D {
        val c = cos(angle)
        val s = sin(angle)

        return Point3D(
            x = x * c + z * s,
            y = y,
            z = -x * s + z * c
        )
    }

    fun rotateZ(angle: Float): Point3D {
        val c = cos(angle)
        val s = sin(angle)

        return Point3D(
            x = x * c - y * s,
            y = x * s + y * c,
            z = z
        )
    }

    /** simple perspective: focal = 2 × radius */
    fun project(screenCx: Float, screenCy: Float, radius: Float): Offset {
        val focal = 2f * radius
        val scale = focal / (focal - z)     // z ∈ [-1, +1]  ⇒ scale ≈ [½, ∞)
        return Offset(
            screenCx + x * radius * scale,
            screenCy - y * radius * scale
        )
    }

    fun isZero(tol: Float = 1e-6f) = abs(x) < tol && abs(y) < tol && abs(z) < tol
}
