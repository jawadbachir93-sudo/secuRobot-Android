package com.example.securobot

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.*

class JoystickView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    interface Listener {
        /**
         * @param x Normalisé [-1..1] (gauche → droite)
         * @param y Normalisé [-1..1] (haut → bas) (attention : haut = -1)
         */
        fun onMove(x: Float, y: Float)
        fun onRelease()
    }

    var listener: Listener? = null

    private val basePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        alpha = 40
    }
    private val knobPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        alpha = 160
    }

    private var cx = 0f
    private var cy = 0f
    private var baseRadius = 0f
    private var knobRadius = 0f

    private var knobX = 0f
    private var knobY = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cx = w / 2f
        cy = h / 2f
        baseRadius = min(w, h) * 0.42f
        knobRadius = baseRadius * 0.35f
        resetKnob()
    }

    override fun onDraw(canvas: Canvas) {
        // base
        canvas.drawCircle(cx, cy, baseRadius, basePaint)
        // knob
        canvas.drawCircle(knobX, knobY, knobRadius, knobPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                val dx = event.x - cx
                val dy = event.y - cy
                val dist = hypot(dx, dy)

                val maxDist = baseRadius - knobRadius
                val scale = if (dist > maxDist && dist > 0f) maxDist / dist else 1f

                knobX = cx + dx * scale
                knobY = cy + dy * scale
                invalidate()

                // normalisation [-1..1]
                val nx = ((knobX - cx) / maxDist).coerceIn(-1f, 1f)
                val ny = ((knobY - cy) / maxDist).coerceIn(-1f, 1f)
                listener?.onMove(nx, ny)
                return true
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                resetKnob()
                invalidate()
                listener?.onRelease()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun resetKnob() {
        knobX = cx
        knobY = cy
    }
}