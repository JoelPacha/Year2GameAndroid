package com.example.projet

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
import java.lang.Byte.toString
import java.lang.reflect.Array
import java.util.*
import kotlin.collections.ArrayList

import kotlin.random.Random


open class DrawingView @JvmOverloads constructor (context: Context, var attributes: AttributeSet? = null, var defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback,Runnable {
    lateinit var canvas: Canvas
    val viePaint = Paint()
    lateinit var thread: Thread
    var keepdrawing: Boolean = true
    var Jungle = BitmapFactory.decodeResource(resources, R.drawable.niveau1) // map du jeu
    val life = BitmapFactory.decodeResource(resources,R.drawable.life)      // coeur de la vie
    var largeur = 0f    // largeur de la map
    var hauteur = 0f   // hauteur
    var param = 0f     // utile pour repérer les objets dans onsizechanged
    var e = 0f
    var count = 0
    var marge = 0f    // distance depuis le haut du smartphone
    var diametre = 0f   // diametre des monstres et de la balle
    var gameOver = false // variable pour signaler la défaite
    var gameWin = false
    var endgame = false  // signale la fin du dernier niveau
    val activity = context as FragmentActivity  // utile pour le dialog fragment

    val mediawin = MediaPlayer.create(activity,R.raw.youwin)        // initiation des différentes musiques et sons des niveaux
    val mediadefeat = MediaPlayer.create(activity,R.raw.defeat)
    val mediaost = MediaPlayer.create(activity,R.raw.ostv2)
    val mediaori = MediaPlayer.create(activity,R.raw.ostori)
    val mediahalo = MediaPlayer.create(activity,R.raw.osthalo)



    var lesParois = arrayOf<Parois>(Parois(0f,0f,0f,0f))            // initiation des objets qui seront draw. Ces objets seront par la suite inité avec les coordonnées de l'écran
    var lesMonstres =  arrayListOf<Monstre>(Monstre(0f,0f,0f) )
    var lesCarres = arrayListOf<Carre>(Carre(0f,0f,0f,0f,0))
    var lesBonus = arrayListOf<Effets>(Bonus(0f,0f,0f,0f))
    var lesMalus = arrayListOf<Effets>(Malus(0f,0f,0f,0f))
    var balle = Balle(0f,0f,0f,0)
    var plateforme = Plateforme(0f,0f,0f,0f)
    var vide = Vide(0f,0f,0f,0f)
    var transparent = Transparent(0f,0f,0f,0f)
    var ligne = Transparent(0f, 0f, 0f, 0f)
    var r = 0
    var CarreCasses = BooleanArray(1){false}


    override fun onSizeChanged(w: Int,h: Int,oldw: Int,oldh: Int) { //placement des éléments en fonction de la taille de l'écran
        super.onSizeChanged(w, h, oldw, oldh)
        hauteur = h.toFloat()
        largeur = w.toFloat()
        marge = hauteur/15.393f
        param = (largeur - 2*w/47)/ 10f
        diametre = h/38f
        e = param/2
        ligne = Transparent(0f,h * 7 / 8f - w / 10-h/461.8f, largeur, h * 7 / 8f - w / 10)
        ligne.color = Color.rgb(107, 50, 187)
        plateforme = Plateforme(w/3f+50f,h*7/8f - w/50, w-w/3f-50f, h* 7/8f + w/50)
        balle = Balle( w * 1/2f -diametre/2 , h* 7/8f-w/50-diametre-10f, diametre,3)
        vide = Vide(0f,hauteur-w/50f,largeur,hauteur)
        transparent = Transparent(0f,h/2f,largeur,h/2f +h/461.8f)

        lesMalus = arrayListOf<Effets>(Malus(w/47f + 2*param, marge+w/47f+e, w/47f + 4*param,marge+w/47f+ 2*e))
        lesBonus = arrayListOf<Effets>(Bonus(w/47f + 4*param, marge+w/47f+8*e, w/47f + 6*param,marge+w/47f+ 9*e))


        lesMonstres = arrayListOf<Monstre>(
            Monstre(1*param,marge+w/47f+5*e,diametre)
        )


        lesParois = arrayOf<Parois>(
            Parois(0f, marge+2f, w/50f, hauteur), // gauche
            Parois(0f, marge+2f, largeur, w/50f+marge+2f), //haut
            Parois(largeur - w/50f, marge+2f, largeur, hauteur)) //droite



        lesCarres = arrayListOf<Carre>(
            Carre(w/47f , marge+w/47f, w/47f+ 2*param ,marge+w/47f+ e,0),
            Carre(w/47f + 2*param , marge+w/47f , w/47f + 4*param,marge+w/47f+ e,0), //
            Carre(w/47f + 4*param, marge+w/47f, w/47f + 6*param,marge+w/47f + e,0),
            Carre(w/47f + 6*param, marge+w/47f, w/47f + 8*param,marge+w/47f + e,0),
            Carre(w/47f + 8*param, marge+w/47f, w/47f + 10 *param,marge+w/47f + e,0),


            Carre(w/47f , marge+w/47f+e, w/47f+ 2*param,marge+w/47f+ 2*e,0),
            //Carre(w/47f + 2*param, marge+w/47f+e, w/47f + 4*param,marge+w/47f+ 2*e,0), surtout ne pas enlever les //
            Carre(w/47f + 4*param , marge+w/47f+e, w/47f + 6*param,marge+w/47f+ 2*e,0),
            Carre(w/47f + 6*param , marge+w/47f+e , w/47f + 8*param ,marge+w/47f+2*e,0),
            Carre(w/47f + 8*param, marge+w/47f+e, w/47f + 10*param,marge+w/47f+ 2*e,0),

            Carre(w/47f , marge+w/47f+2*e, w/47f+ 2*param ,marge+w/47f+ 3*e,0),
            Carre(w/47f + 2*param , marge+w/47f+2*e , w/47f + 4*param,marge+w/47f+ 3*e,0),
            Carre(w/47f + 4*param, marge+w/47f+2*e, w/47f + 6*param,marge+w/47f+ 3*e,0),
            Carre(w/47f + 6*param, marge+w/47f+2*e, w/47f + 8*param,marge+w/47f+ 3*e,0),
            Carre(w/47f + 8*param, marge+w/47f+2*e, w/47f + 10*param,marge+w/47f + 3*e,0),

            Carre(w/47f , marge+w/47f+3*e, w/47f+ 2*param,marge+w/47f+ 4*e,0),
            Carre(w/47f + 2*param, marge+w/47f+3*e, w/47f + 4*param,marge+w/47f+ 4*e,0),
            Carre(w/47f + 4*param , marge+w/47f+3*e, w/47f + 6*param,marge+w/47f+ 4*e,0),
            Carre(w/47f + 6*param , marge+w/47f+3*e , w/47f + 8*param ,marge+w/47f+4*e,0),
            Carre(w/47f + 8*param, marge+w/47f+3*e, w/47f + 10*param,marge+w/47f+ 4*e,0),

            Carre(w/47f , marge+w/47f+4*e, w/47f+ 2*param ,marge+w/47f+ 5*e,0),
            Carre(w/47f + 2*param , marge+w/47f+4*e , w/47f + 4*param,marge+w/47f+ 5*e,0),
            Carre(w/47f + 4*param, marge+w/47f+4*e, w/47f + 6*param,marge+w/47f+ 5*e,0),
            Carre(w/47f + 6*param, marge+w/47f+4*e, w/47f + 8*param,marge+w/47f+ 5*e,0),
            Carre(w/47f + 8*param, marge+w/47f+4*e, w/47f + 10*param,marge+w/47f + 5*e,0),

            Carre(w/47f , marge+w/47f+5*e, w/47f+ 2*param,marge+w/47f+ 6*e,0),
            Carre(w/47f + 2*param, marge+w/47f+5*e, w/47f + 4*param,marge+w/47f+ 6*e,0),
            Carre(w/47f + 4*param , marge+w/47f+5*e, w/47f + 6*param,marge+w/47f+ 6*e,0), //
            Carre(w/47f + 6*param , marge+w/47f+5*e , w/47f + 8*param ,marge+w/47f+6*e,0),
            Carre(w/47f + 8*param, marge+w/47f+5*e, w/47f + 10*param,marge+w/47f+ 6*e,0),

            Carre(w/47f , marge+w/47f+6*e, w/47f+ 2*param ,marge+w/47f+ 7*e,0),
            Carre(w/47f + 2*param , marge+w/47f+6*e , w/47f + 4*param,marge+w/47f+ 7*e,0),
            Carre(w/47f + 4*param, marge+w/47f+6*e, w/47f + 6*param,marge+w/47f+ 7*e,0),
            Carre(w/47f + 6*param, marge+w/47f+6*e, w/47f + 8*param,marge+w/47f+ 7*e,0),
            Carre(w/47f + 8*param, marge+w/47f+6*e, w/47f + 10*param,marge+w/47f + 7*e,0),

            Carre(w/47f , marge+w/47f+7*e, w/47f+ 2*param ,marge+w/47f+ 8*e,0),
            Carre(w/47f + 2*param , marge+w/47f+7*e , w/47f + 4*param,marge+w/47f+ 8*e,0),
            Carre(w/47f + 4*param, marge+w/47f+7*e, w/47f + 6*param,marge+w/47f+ 8*e,0), //
            Carre(w/47f + 6*param, marge+w/47f+7*e, w/47f + 8*param,marge+w/47f+ 8*e,0),
            Carre(w/47f + 8*param, marge+w/47f+7*e, w/47f + 10*param,marge+w/47f + 8*e,0),

            Carre(w/47f , marge+w/47f+8*e, w/47f+ 2*param ,marge+w/47f+ 9*e,0),
            Carre(w/47f + 2*param , marge+w/47f+8*e , w/47f + 4*param,marge+w/47f+ 9*e,0),
            //Carre2(w/47f + 4*param, marge+w/47f+8*e, w/47f + 6*param,marge+w/47f+ 9*e,0), surtout ne pas enlever les //
            Carre(w/47f + 6*param, marge+w/47f+8*e, w/47f + 8*param,marge+w/47f+ 9*e,0),
            Carre(w/47f + 8*param, marge+w/47f+8*e, w/47f + 10*param,marge+w/47f + 9*e,0),

            Carre(w/47f , marge+w/47f+9*e, w/47f+ 2*param ,marge+w/47f+ 10*e,0),
            Carre(w/47f + 2*param , marge+w/47f+9*e , w/47f + 4*param,marge+w/47f+ 10*e,0),
            Carre(w/47f + 4*param, marge+w/47f+9*e, w/47f + 6*param,marge+w/47f+ 10*e,0),
            Carre(w/47f + 6*param, marge+w/47f+9*e, w/47f + 8*param,marge+w/47f+ 10*e,0),



            Carre(w/47f + 8*param, marge+w/47f+9*e, w/47f + 10*param,marge+w/47f + 10*e,0),

            )

        CarreCasses = BooleanArray(lesCarres.size){false}    // liste de booleen false ou chaque booleen passerait en true lorsque un carre disparait

    }


    fun pause() {           //fonction permettant d'arreter de dessiner
        keepdrawing = false
        thread.join()
    }

    fun resume() {         //fonction permettant de continuer de dessiner
        keepdrawing = true
        thread = Thread(this)
        thread.start()
    }

    fun draw() {       // dessine les différents objets
        if (holder.surface.isValid) {
            viePaint.setStyle(Paint.Style.FILL)
            viePaint.setColor(Color.WHITE)
            viePaint.setTextSize(110F)
            canvas = holder.lockCanvas()
            canvas.drawBitmap(Jungle,null,Rect(0, 0, canvas.getWidth(),canvas.getHeight()),null)
            canvas.drawBitmap(life,null,Rect(125,25,225,117),null)
            canvas.drawText(balle.vie.toString(),50f,110f,viePaint)

            for (parois in lesParois){
                parois.draw(canvas)
            }

            for (carre in lesCarres){
                carre.draw(canvas)
            }


            for (bonus in lesBonus){
                bonus.draw(canvas)
            }

            for (malus in lesMalus){
                malus.draw(canvas)
            }

            for (monstres in lesMonstres){
                monstres.draw(canvas)
            }

            ligne.draw(canvas)
            transparent.draw(canvas)
            balle.draw(canvas)
            plateforme.draw(canvas)
            vide.draw(canvas)


            holder.unlockCanvasAndPost(canvas) //Liberation du canvas
        }
    }

    fun modifieMonstre(a: Int){                        // ajoute dans la liste initale un monstre a chaque appelation
        lesMonstres.add(Monstre(3*a*param,marge+largeur/47f+5*e,diametre))
    }


    fun modifieBonus(pos:Int){                      // ajoute dans la liste initale un bonus associé à la position pos d'un carre dans la liste
        for (i in 0..lesCarres.size - 1){
            if (i == pos){
                lesBonus.add(Bonus(lesCarres[i].x1,lesCarres[i].y1,lesCarres[i].x2,lesCarres[i].y2))
            }
        }
    }

    fun modifieMalus(pos:ArrayList<Int>){         // ajoute dans la liste initale un malus associé à la position pos d'un carre dans la liste
        for (i in 0..lesCarres.size - 1){
            for (j in pos){
                if (i == j){
                    lesMalus.add(Malus(lesCarres[i].x1,lesCarres[i].y1,lesCarres[i].x2,lesCarres[i].y2))
                }
            }
        }
    }



    fun modifieCarres( list: ArrayList<Int>, a: Int){           //modifie la liste initiale des carres pour y enlever tout les bonus/malus grace a leur position, le parametre "a" donne la resistance des carres futurs
        val nouveauCarres = arrayListOf<Carre>()
        count += 1

        if (count == 2 && endgame == false){                    //ajoute à la variable count +1 a chaque fois que la fonction est appele pour pouvoir arreter le jeu en fin de partie
            endgame = true
        }
        for (i in 0..lesCarres.size-1){
            if(!(i in list)){         // on récupère seuleument les carrés qui ne seront pas remplacés par des effets
                nouveauCarres.add(lesCarres[i])
                nouveauCarres[i].resistance = a
                nouveauCarres[i].NbreDeCollisions = 0
                CarreCasses[i] = false
            }
            else{
                nouveauCarres.add(Carre(0f,0f,0f,0f,0))   //rajoute un carre vide fictif pour ne pas avoir de trou dans la liste comparer à la liste initiale
                CarreCasses[i] = true

            }
        }
        lesCarres = nouveauCarres

    }

    fun color(txt:String){                      // fonction qui est appele dans le levelmanager pour changer les couleurs
        if (txt == "red"){ //niveau 3
            for (carre in lesCarres){
                carre.color = Color.rgb(239,177,28)
            }

            for (parois in lesParois){
                parois.color = Color.rgb(26,0,11)
            }

            ligne.color = Color.rgb(26,0,11)


            plateforme.color = Color.rgb(239,177,28)

            for (bonus in lesBonus){
                bonus.color = Color.rgb(246,255,0)
            }

            for (malus in lesMalus){
                malus.color = Color.rgb(92,33,0)
            }

        }

        else if (txt == "green"){  //niveau 2
            for (carre in lesCarres){
                carre.color = Color.rgb(92, 164, 147)
            }

            for (parois in lesParois){
                parois.color = Color.rgb(0,67,131)
            }

            ligne.color = Color.rgb(0,67,131)

            plateforme.color = Color.rgb(92, 164, 147)

            for (bonus in lesBonus){
                bonus.color = Color.rgb(78,216,254)
            }

            for (malus in lesMalus){
                malus.color = Color.rgb(0,95,31)
            }

        }

    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(e: MotionEvent): Boolean {   // définit la limite entre là ou la plateforme fait rebondir la balle et là ou elle s'y colle
        if (e.rawY > hauteur/2f +hauteur/461.8f) {
            if ((balle.dx != 0f) && (balle.dy != 0f)) {
                plateforme.bouge(e, hauteur, largeur)
            }
        }
        else {
            if((balle.dx == 0f) && (balle.dy == 0f)){
                balle.throwEvent(e)
            }
        }
        return true
    }


    override fun run() {
        var OldFrame = System.currentTimeMillis()   // recupere le temps du jeu
        while(keepdrawing){
            val NewFrame = System.currentTimeMillis()
            val FrameTime = (NewFrame- OldFrame).toDouble()
            refreshAll(FrameTime)                         // on calcule le temps que prends le temps qui s'écoule entre deux frame que dessine le DrawingView
            draw()
            OldFrame = NewFrame
            }
        }





    fun refreshAll(FrameTime: Double){  // refresh chaque position des objets
        val interval = FrameTime/1000 // A chaque frame, la fonction rafraîchit tout le DrawingView et assigne les nouvelles positions aux Ovnis
        balle.bouge(interval) // fait bouger la balle
        plateforme.Reactionballe(balle, hauteur, largeur) //fait rebondir la balle sur la plateforme
        vide.Reactionballe(balle)


        for (monstre in lesMonstres){
            monstre.bouge(interval)
            monstre.mangerBalle(balle)
            transparent.Reactionballe(monstre)
            monstre.interactionmutuelle(lesMonstres)
        }

        for (parois in lesParois){
            parois.Reactionballe(balle)
            for (monstre in lesMonstres){
                parois.Reactionballe(monstre)
            }
        }

        for (i in 0..lesCarres.size-1){
            lesCarres[i].Reactionballe(balle)
            if(lesCarres[i].verifdisparition()){
                CarreCasses[i] = true
            }

        }

        for (bonus in lesBonus){
            bonus.ReactionBalle(balle,plateforme, largeur )
        }

        for (malus in lesMalus){
            malus.ReactionBalle(balle,plateforme, largeur)
        }

        if (balle.vie == 0 && balle.diametre != 0f){ // Si l'utilisateur utilise toute les vies, alors le jeu relance la partie au même niveau
            keepdrawing = false
            gameOver = true
            showGameOverDialog("GameOver")
        }

        if (!(false in CarreCasses)){  // Si la liste ne contient que des true, alors le jeu passe au niveau suivant
            keepdrawing = false
            gameWin = true
            showGameOverDialog("GameWin")
        }

        if (!(false in CarreCasses) && endgame == true ){
            showGameOverDialog("Félicitations vous avez terminé le jeu ! ")
            endgame = false

        }
    }


    fun showGameOverDialog(messageId: String) {   //Affiche le dialoginterface pour intéragir avec le texte
        class GameResult: DialogFragment() {
            override fun onCreateDialog(bundle: Bundle?): Dialog {
                val builder = AlertDialog.Builder(getActivity())

                builder.setTitle(messageId)
                builder.setMessage("Nombre de vie: " + balle.vie)
                if (messageId == "GameWin"){
                    mediawin.start()
                    builder.setPositiveButton("Niveau suivant",
                        DialogInterface.OnClickListener{ _, _->nextlevel()})
                }
                else if  (messageId == "GameOver"){
                    mediadefeat.start()
                    builder.setPositiveButton("Redemarre le jeu",
                        DialogInterface.OnClickListener { _, _-> newGame()})
                }
                else{
                    builder.setPositiveButton("Retour au menu",
                        DialogInterface.OnClickListener{_, _-> fin()})
                }
                return builder.create()
            }
        }
        activity.runOnUiThread(
            Runnable {
                val ft = activity.supportFragmentManager.beginTransaction()

                val prev =
                    activity.supportFragmentManager.findFragmentByTag("dialog")
                if (prev != null) {
                    ft.remove(prev)
                }
                ft.addToBackStack(null)
                val gameResult = GameResult()
                gameResult.setCancelable(false)
                gameResult.show(ft,"dialog")
            }

        )
    }


    fun fin(){  //retourne au MainActivity
        activity.finish()
    }

    fun newGame(){  // relance la partie et replace la balle à la position initiale
        balle.reset()
        keepdrawing = true
        r = 2
        if(gameOver) {
            gameOver = false

        }
    }

    fun nextlevel() {  // définit r = 1 qui sera detecté gràce au thread du levelmanager
        balle.reset()
        r = 1

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