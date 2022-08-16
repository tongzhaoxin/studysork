package com.example.app.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.core.utils.px
import kotlin.math.cos
import kotlin.math.sin

open class  PieView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
    View(context, attrs, defStyleAttr, defStyleRes) {

    @JvmOverloads
    constructor(context: Context?, attrs: AttributeSet?=null, defStyleAttr: Int=0) : this(context, attrs, defStyleAttr, 0)

    val OFFSET = 10f.px
    val RADIUS = 150f.px
    val angles = listOf(60f, 120f, 90f, 90f)
    val colors = listOf(Color.parseColor("#00ff00")
    ,Color.parseColor("#ff0000"),
        Color.parseColor("#0000ff"),
        Color.parseColor("#ffff00"))

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val path = Path()

    init {
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 3f.px
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

    }

    override fun onDraw(canvas: Canvas) {
        var startAngle = 0f;
        for((index, angle) in angles.withIndex()) {
            paint.color = colors[index]
            if(index == 0) {
                canvas.save()
                canvas.translate((OFFSET* cos(Math.toRadians((angle/2).toDouble()))).toFloat(),
                    (OFFSET* sin(Math.toRadians((angle/2).toDouble()))).toFloat()
                )
            }
            canvas.drawArc(width/2f-RADIUS, 20f.px, width/2+RADIUS, 20f.px+2*RADIUS, startAngle, angle, true, paint)
            startAngle += angle
            if(index == 0) {
                canvas.restore()
            }
        }
    }
}