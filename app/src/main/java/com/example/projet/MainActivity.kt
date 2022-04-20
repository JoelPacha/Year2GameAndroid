package com.example.projet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Paint
import android.app.Activity
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import android.widget.Toast
import  com.example.projet.R

class MainActivity : AppCompatActivity() {

    lateinit var drawingView: DrawingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById<DrawingView>(R.id.vMain)
        drawingView.setWillNotDraw(false) //efface ce qu'il y avait
        drawingView.invalidate() //Appelle le onDraw
    }

    override fun onPause() {
        super.onPause()
        drawingView.pause()
    }

    override fun onResume() {
        super.onResume()
        drawingView.resume()
    }
}