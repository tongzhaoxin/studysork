package com.example.app.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.core.utils.px
import kotlin.math.cos
import kotlin.math.sin

open class  DashboardView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
    View(context, attrs, defStyleAttr, defStyleRes) {

    @JvmOverloads
    constructor(context: Context?, attrs: AttributeSet?=null, defStyleAttr: Int=0) : this(context, attrs, defStyleAttr, 0)
    val RADIAN = 120f
    val RADIUS = 150f.px
    val LENGTH = 120f.px
    val DASH_WIDTH = 2f.px
    val DASH_LENGTH = 10f.px
    val ADVANCE_COUNT = 20
    private lateinit var pathDashPathEffect : PathDashPathEffect
    private var count : Int

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val path = Path()
    val dash = Path()

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f.px
        dash.addRect(0f, 0f, DASH_WIDTH, DASH_LENGTH, Path.Direction.CCW)
        count = 10
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addArc(width/2f-RADIUS, 20f.px, width/2+RADIUS, 20f.px+2*RADIUS, 90+RADIAN/2f, 360f-RADIAN)

        var measure = PathMeasure(path, false)
        pathDashPathEffect = PathDashPathEffect(dash, (measure.length-DASH_WIDTH)/(ADVANCE_COUNT), 0f, PathDashPathEffect.Style.ROTATE)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 画圆弧
        canvas.drawPath(path, paint)

        // 画刻度
        paint.pathEffect = pathDashPathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null

        // 画指针
        canvas.drawLine(width/2f, 20f.px+RADIUS, width/2f+LENGTH * cos(Math.toRadians((90 + RADIAN/2f+(360-RADIAN)/20*count).toDouble())).toFloat(), 20f.px+RADIUS + LENGTH * sin(Math.toRadians((90 + RADIAN/2f+(360-RADIAN)/20*count).toDouble())).toFloat(), paint)
    }
}