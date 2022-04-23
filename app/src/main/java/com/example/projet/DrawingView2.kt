package com.example.projet

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class DrawingView2 @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback,Runnable {
    lateinit var canvas: Canvas
    var keepdrawing = true
    val backgroundPaint = Paint()
    lateinit var thread: Thread
    var drawing: Boolean = true
    val carre = Carre2(50f,40f,70f,20f,3)
    init {
        backgroundPaint.color = Color.TRANSPARENT
    }



    override fun onSizeChanged(w: Int,h: Int,oldw: Int,oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val canvasH = (h - 500).toFloat()
        val canvasW = (w - 25).toFloat()
    }

    fun pause() {
        drawing = false
        thread.join()
    }

    fun resume() {
        drawing = true
        thread = Thread(this)
        thread.start()
    }

    fun draw() {
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            canvas.drawRect(0F, 0F, canvas.getWidth()*1F,
                canvas.getHeight()*1F, backgroundPaint)
            holder.unlockCanvasAndPost(canvas)
            carre.draw(canvas)
        }
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                val x = e.rawX.toInt() - 100
                val y = e.rawY.toInt() - 500
            }
        }
        return true
    }

    override fun run() {
        var OldFrame = System.currentTimeMillis()
        while(keepdrawing){
            draw()
            /*val NewFrame = System.currentTimeMillis()
            val FrameTime = (NewFrame - OldFrame).toFloat()
            draw()                                       // fonction inspirée de celle du jeu canon
            OldFrame = NewFrame*/
        }
    }



    override fun surfaceChanged(
        holder: SurfaceHolder, format: Int,
        width: Int, height: Int) {
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread = Thread(this)
        thread.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        thread.join()
    }
}