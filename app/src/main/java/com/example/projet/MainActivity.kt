package com.example.projet

import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager

class MainActivity : AppCompatActivity(), View.OnClickListener{
    var soundPool : SoundPool? = null
    val soundId = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val mediaPlayer = MediaPlayer.create(this,R.raw.ost)
        // mediaPlayer.start()

        soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool!!.load(baseContext, R.raw.vibration, 1)
        soundPool?.play(soundId,1f,1f,1,99,1f)

        setContentView(R.layout.activity_menu)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN) //remove le haut de l'écran(heure,batterie,etc..)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)//remove les boutons en bas de l'écran

    }

    override fun onClick(view: View) {
        val intent = Intent(this,Activity::class.java)
            startActivity(intent)
    }
}