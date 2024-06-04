package com.mcoc.annoncestir

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.toColor
import kotlin.math.*

/**
 * Main view
 */
class DeclareShootView(context: Context, attrs: AttributeSet) : View(context, attrs)  {
    private var linesPaint: Paint = Paint()
    private var shootPaint: Paint = Paint()
    private var shootArea: Int = -1
    private var shootAreaX = arrayOf(0F)
    private var shootAreaY = arrayOf(0F)
    private var squareSide: Float = min(width.toFloat(), height.toFloat())
    private var xCenter: Float = width.toFloat() / 2 + x
    private var yCenter: Float = height.toFloat() / 2 + y
    private var radius: Float = squareSide / 5F

    public var shootsModel:ShootsModel = ShootsModel()

    init {
        linesPaint.style = Paint.Style.STROKE
        linesPaint.color = ResourcesCompat.getColor(getResources(), R.color.AndroidGreen, null)
        linesPaint.strokeWidth = 15F

        shootPaint.style = Paint.Style.FILL_AND_STROKE
        shootPaint.color = ResourcesCompat.getColor(getResources(), R.color.Cerise, null)
        shootPaint.strokeWidth = 15F
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


        canvas.drawRect(leftSquare, topSquare, rightSquare, bottomSquare, linesPaint)
        canvas.drawCircle(xCenter, yCenter, radius, linesPaint)

        // Top
        canvas.drawLine(xCenter - lgExt,
            topSquare,
            xCenter - lgIntX,
            yCenter - lgIntY,
            linesPaint)

        canvas.drawLine(xCenter + lgExt,
            topSquare,
            xCenter + lgIntX,
            yCenter - lgIntY,
            linesPaint)

        //Bottom
        canvas.drawLine(xCenter - lgExt,
            bottomSquare,
            xCenter - lgIntX,
            yCenter + lgIntY,
            linesPaint)

        canvas.drawLine(xCenter + lgExt,
            bottomSquare,
            xCenter + lgIntX,
            yCenter + lgIntY,
            linesPaint)

        //Left
        canvas.drawLine(leftSquare,
            yCenter - lgExt,
            xCenter - lgIntY,
            yCenter - lgIntX,
            linesPaint)

        canvas.drawLine(leftSquare,
            yCenter + lgExt,
            xCenter - lgIntY,
            yCenter + lgIntX,
            linesPaint)

        //Right
        canvas.drawLine(rightSquare,
            yCenter - lgExt,
            xCenter + lgIntY,
            yCenter - lgIntX,
            linesPaint)

        canvas.drawLine(rightSquare,
            yCenter + lgExt,
            xCenter + lgIntY,
            yCenter + lgIntX,
            linesPaint)

        if(shootArea >= 0) {
            canvas.drawCircle(shootAreaX[shootArea], shootAreaY[shootArea], radius/3, shootPaint)
        }
    }

    /**
     * onTouchEvent override
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                shootArea = checkShootArea(event.x, event.y)
                shootsModel.AddShoot(shootArea)
                invalidate()
            }
            MotionEvent.ACTION_UP ->
            {
                performClick()
            }
            else -> {}
        }
        return super.onTouchEvent(event)

    }

    /**
     * preformClick override
     */
    override fun performClick(): Boolean {
        return super.performClick()
    }

    /**
     * Check shoot area in function of click coordinates
     */
    private fun checkShootArea(x:Float, y:Float):Int {
        initData()

        if( abs(x - xCenter) <= radius &&
            abs(y - yCenter) <= radius) {
            // center shoot
            return 0
        }
        else {
            // calculate radius
            if(y == yCenter)
            {
                return if(x >= xCenter)
                    1
                else
                    5
            }
            else
            {
                var ang = atan(( x- xCenter) / ( y - yCenter)) * 180 / PI.toFloat()

                ang = when {
                    x >= xCenter -> {
                        if( y <= yCenter)
                            90 - (ang + 90)
                        else
                            180 - ang
                    }

                    else -> {
                        if( y <= yCenter)
                            360 - ang
                        else
                            180 - ang
                    }
                }

                if(ang < 45 / 2)
                    return 1
                else if(ang < 45 * 3 / 2)
                    return 2
                else if(ang < 45 * 5 / 2)
                    return 3
                else if(ang < 45 * 7 / 2)
                    return 4
                else if(ang < 45 * 9 / 2)
                    return 5
                else if(ang < 45 * 11 / 2)
                    return 6
                else if(ang < 45 * 13 / 2)
                    return 7
                else if(ang < 45 * 15 / 2)
                    return 8
                else
                    return 1
            }
        }
    }
}