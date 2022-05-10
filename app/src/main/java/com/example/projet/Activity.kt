package com.example.projet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.PixelFormat
import android.media.MediaPlayer
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import kotlin.concurrent.thread

class Activity : AppCompatActivity() {

    lateinit var drawingView: DrawingView
    lateinit var levelup : Levelmanager
    lateinit var thread: Thread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity)
        drawingView = findViewById<DrawingView>(R.id.vMain)
        drawingView.setWillNotDraw(false) //efface ce qu'il y avait
        drawingView.invalidate() //Appelle le onDraw

        levelup = Levelmanager(drawingView) //initie l'objet de la classe levelmanager
        thread = Thread(levelup) // creer un nouveau thread pour la verification des niveaux
        thread.start()  //lance le thread

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN) //remove le haut de l'écran(heure,batterie,etc..)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) //remove les boutons en bas de l'écran
        this.drawingView.setZOrderOnTop(true)
        this.drawingView.getHolder().setFormat(PixelFormat.TRANSLUCENT)

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
