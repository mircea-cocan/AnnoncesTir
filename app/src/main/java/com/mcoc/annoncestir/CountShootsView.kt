package com.mcoc.annoncestir

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.*

/**
 * Main view
 */
class CountShootsView(context: Context, attrs: AttributeSet) : View(context, attrs)  {
    private var linePaint: Paint = Paint()
    private var textPaint:Paint = Paint()
    private var shootAreaX = arrayOf(0F)
    private var shootAreaY = arrayOf(0F)
    private var squareSide: Float = min(width.toFloat(), height.toFloat())
    private var xCenter: Float = width.toFloat() / 2 + x
    private var yCenter: Float = height.toFloat() / 2 + y
    private var radius: Float = squareSide / 5F
    private var shootCount = arrayOf(1, 1, 5, 6, 8, 10, 21, 12, 9)

    init {
        linePaint.style = Paint.Style.STROKE
        linePaint.color = Color.BLUE
        linePaint.strokeWidth = 10F

        textPaint.style = Paint.Style.FILL_AND_STROKE
        textPaint.color = Color.RED
        textPaint.textSize = 100F
        textPaint.textAlign = Paint.Align.CENTER
    }

    /**
     * init draw coordinates data
     */
    private fun initData() {
        squareSide = min(width.toFloat(), height.toFloat())

        xCenter= width.toFloat() / 2 + x
        yCenter = height.toFloat() / 2 + y
        radius = squareSide / 5F

        shootAreaX = arrayOf(
            xCenter,                        // center
            xCenter,                        // top
            xCenter + radius * 2 * 0.7071F, // top right
            xCenter + radius * 1.9F,        // right
            xCenter + radius * 2 * 0.7071F, // bottom right
            xCenter,                        // bottom
            xCenter - radius * 2 * 0.7071F, // bottom left
            xCenter - radius * 1.9F,        // left
            xCenter - radius * 2 * 0.7071F) // top left
        shootAreaY = arrayOf(
            yCenter,                        // center
            yCenter - radius * 1.9F,        // top
            yCenter - radius * 2 * 0.7071F, // top right
            yCenter,                        // right
            yCenter + radius * 2 * 0.7071F, // bottom right
            yCenter + radius * 1.9F,        // bottom
            yCenter  + radius * 2 * 0.7071F,// bottom left
            yCenter,                        // left
            yCenter - radius * 2 * 0.7071F) // top left
    }
    /**
     * Draw function
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        initData()

        val topSquare:Float
        val leftSquare:Float
        val bottomSquare:Float
        val rightSquare:Float
        val angle:Float = 45F * PI.toFloat() / 180F / 2F
        val lgExt:Float = (squareSide / 2) * tan(angle)
        val lgIntX:Float = abs(radius * sin(angle))
        val lgIntY:Float = abs(radius * cos(angle))

        if(width <= height) {
            leftSquare = x
            topSquare = y + (height.toFloat() - width.toFloat()) / 2
            rightSquare = width.toFloat() + x
            bottomSquare = y + (height.toFloat() + width.toFloat()) / 2
        }
        else {
            leftSquare = x + (width.toFloat() - height.toFloat())/2
            topSquare = y
            rightSquare = x + (width.toFloat() + height.toFloat())/2
            bottomSquare = y + height.toFloat()
        }


        canvas.drawRect(leftSquare, topSquare, rightSquare, bottomSquare, linePaint)
        canvas.drawCircle(xCenter, yCenter, radius, linePaint)

        // Top
        canvas.drawLine(xCenter - lgExt,
            topSquare,
            xCenter - lgIntX,
            yCenter - lgIntY,
            linePaint)

        canvas.drawLine(xCenter + lgExt,
            topSquare,
            xCenter + lgIntX,
            yCenter - lgIntY,
            linePaint)

        //Bottom
        canvas.drawLine(xCenter - lgExt,
            bottomSquare,
            xCenter - lgIntX,
            yCenter + lgIntY,
            linePaint)

        canvas.drawLine(xCenter + lgExt,
            bottomSquare,
            xCenter + lgIntX,
            yCenter + lgIntY,
            linePaint)

        //Left
        canvas.drawLine(leftSquare,
            yCenter - lgExt,
            xCenter - lgIntY,
            yCenter - lgIntX,
            linePaint)

        canvas.drawLine(leftSquare,
            yCenter + lgExt,
            xCenter - lgIntY,
            yCenter + lgIntX,
            linePaint)

        //Right
        canvas.drawLine(rightSquare,
            yCenter - lgExt,
            xCenter + lgIntY,
            yCenter - lgIntX,
            linePaint)

        canvas.drawLine(rightSquare,
            yCenter + lgExt,
            xCenter + lgIntY,
            yCenter + lgIntX,
            linePaint)

        for(i in 0..8)
        {
            if(shootAreaX[i] > 0) {
                canvas.drawText(shootCount[i].toString(),
                    shootAreaX[i],
                    shootAreaY[i] - (textPaint.descent() + textPaint.ascent())/2,
                    textPaint)
            }
        }
    }
}