package com.example.projet

import android.graphics.PixelFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager

class Niveau3Activity : AppCompatActivity() {

    lateinit var View3: View2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        View3 = findViewById<View2>(R.id.vMain3)
        View3.setWillNotDraw(false) //efface ce qu'il y avait
        View3.invalidate() //Appelle le onDraw
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN) //remove le haut de l'écran(heure,batterie,etc..)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) //remove les boutons en bas de l'écran
        this.View3.setZOrderOnTop(true)
        this.View3.getHolder().setFormat(PixelFormat.TRANSLUCENT)
    }


    override fun onPause() {
        super.onPause()
        View3.pause()
    }

    override fun onResume() {
        super.onResume()
        View3.resume()
    }
}