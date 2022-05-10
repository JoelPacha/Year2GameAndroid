package com.example.projet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.PixelFormat
import android.media.MediaPlayer
import android.view.View
import android.view.WindowManager

class Niveau2Activity : AppCompatActivity() {

    lateinit var View2: View2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mediaPlayer = MediaPlayer.create(this,R.raw.ost1)
        mediaPlayer.start()
        mediaPlayer.isLooping = true

        setContentView(R.layout.activity_niveau2)
        View2 = findViewById<View2>(R.id.vMain2)
        View2.setWillNotDraw(false) //efface ce qu'il y avait
        View2.invalidate() //Appelle le onDraw
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN) //remove le haut de l'écran(heure,batterie,etc..)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) //remove les boutons en bas de l'écran
        this.View2.setZOrderOnTop(true)
        this.View2.getHolder().setFormat(PixelFormat.TRANSLUCENT)
    }





    override fun onPause() {
        super.onPause()
        View2.pause()
    }

    override fun onResume() {
        super.onResume()
        View2.resume()
    }
}
