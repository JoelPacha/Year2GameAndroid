package com.example.projet

// cette classe sert à gérer les modifications faites à chaque niveau

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.*
import android.media.MediaPlayer
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import kotlin.random.Random


class Levelmanager(var v : DrawingView) : Runnable{  //gere les niveaux du jeux
    var level = 1 //  compteur des niveaux
    var list = arrayListOf<Int>()
    val mediaost = v.mediaost   // definit les musiques des niveaux
    val mediaori = v.mediaori
    val mediahalo = v.mediahalo

    override fun run() {
        while(true){  // boucle tournant à l'infini et qui vérifie la situation des niveaux
            if(level ==1){
                mediaost.start()
            }
            if ((v.gameWin && v.r == 1)||(v.r==2)){  // vérifie qu'on  a perdu ou qu'on a changé de niveau
                if((v.gameWin && v.r == 1)){  // si on a changé de niveau
                    level += 1
                }
                v.r = 0    // remet l'indicateur de changement de niveau à sa valeur initiale zero
                v.gameWin = false
                when(level){

                    1 -> {  // au cas où on perd au premier niveau
                        v.resume()
                    }
                    2 -> {
                        v.Jungle = BitmapFactory.decodeResource(v.resources, R.drawable.niveau2)  //change l'arrière plan

                        mediaost.stop()
                        mediaori.start()
                        mediaori.isLooping

                        list.addAll(listOf(27,37))

                        v.modifieMonstre(2)

                        v.modifieBonus(1)

                        v.modifieMalus(list)

                        v.modifieCarres(list,1)

                        v.color("green")

                        v.resume() // relance le thread car la fonction run du drawingview n'arrete

                    }

                    3 -> {

                        v.Jungle = BitmapFactory.decodeResource(v.resources, R.drawable.niveau3)   //change l'arrière plan

                        mediaori.stop()
                        mediahalo.start()
                        mediaori.isLooping

                        list.addAll(listOf(29))

                        v.modifieMonstre(3)

                        v.modifieBonus(4)

                        v.modifieMalus(list)

                        v.modifieCarres(list,2)

                        v.color("red")

                        v.resume()

                      }

                    4 -> {
                        mediahalo.stop()
                        v.resume()
                        v.fin()

                    }
                }
            }
        }
    }
}