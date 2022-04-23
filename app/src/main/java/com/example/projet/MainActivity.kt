package com.example.projet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Paint
import android.app.Activity
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PixelFormat
import android.view.View
import android.widget.Toast
import  com.example.projet.R

class MainActivity : AppCompatActivity() {

    lateinit var drawingView2: DrawingView2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView2 = findViewById<DrawingView2>(R.id.vMain)
        drawingView2.setWillNotDraw(false) //efface ce qu'il y avait
        drawingView2.invalidate() //Appelle le onDraw

        this.drawingView2.setZOrderOnTop(true)
        this.drawingView2.getHolder().setFormat(PixelFormat.TRANSLUCENT)


    }

    override fun onPause() {
        super.onPause()
        drawingView2.pause()
    }

    override fun onResume() {
        super.onResume()
        drawingView2.resume()
    }
}