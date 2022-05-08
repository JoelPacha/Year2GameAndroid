package com.example.projet

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.PixelFormat
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    lateinit var drawingView1: DrawingView1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView1 = findViewById<DrawingView1>(R.id.vMain)
        drawingView1.setWillNotDraw(false) //efface ce qu'il y avait
        drawingView1.invalidate() //Appelle le onDraw
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN) //remove le haut de l'écran(heure,batterie,etc..)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) //remove les boutons en bas de l'écran
        this.drawingView1.setZOrderOnTop(true)
        this.drawingView1.getHolder().setFormat(PixelFormat.TRANSLUCENT)
    }

    /*override fun onClick(view:View){
        val intent = Intent(this,Niveau2Activity::class.java)
            startActivity(intent)
    }*/


    override fun onPause() {
        super.onPause()
        drawingView1.pause()
    }

    override fun onResume() {
        super.onResume()
        drawingView1.resume()
    }
}
