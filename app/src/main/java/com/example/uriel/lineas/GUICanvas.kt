package com.example.uriel.lineas


import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import kotlinx.android.synthetic.main.activity_guicanvas.*


class GUICanvas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guicanvas)
        title = "Grafico"
        var zoom = 7f
        var arreglo = intent.getIntegerArrayListExtra("valores")
        supportActionBar?.subtitle = if(arreglo[4]==0) "Basico" else if(arreglo[4]==1) "DDA" else "Bresenham"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var fondo = Lienzo(this, arreglo)
        layaout1.addView(fondo)
        layaout1.removeView(button)
        layaout1.removeView(button3)
        layaout1.addView(button)
        layaout1.addView(button3)
        button.setOnClickListener {
            layaout1.removeView(fondo)
            if(zoom<20f) fondo = Lienzo(this, arreglo, ++zoom)
            else{
                val mp = MediaPlayer.create(this, R.raw.alert)
                mp.start()
                mp.setOnCompletionListener { mp.release() }
            }
            layaout1.addView(fondo)

            layaout1.removeView(button3)
            layaout1.addView(button3)
            layaout1.removeView(button)
            layaout1.addView(button)
        }
        button3.setOnClickListener {
            layaout1.removeView(fondo)
            layaout1.removeView(fondo)
            if(zoom>1f) fondo = Lienzo(this, arreglo, --zoom)
            else{
                val mp = MediaPlayer.create(this, R.raw.alert)
                mp.start()
                mp.setOnCompletionListener { mp.release() }
            }
            layaout1.addView(fondo)
            layaout1.removeView(button)
            layaout1.addView(button)
            layaout1.removeView(button3)
            layaout1.addView(button3)
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    class Lienzo(context: Context, arreglo: ArrayList<Int>, tamaño:Float=7f) : View(context) {
        val arreglo = arreglo
        val tamaño = tamaño
        override fun onDraw(canvas: Canvas) {
            val A = floatArrayOf(arreglo[0].toFloat(),arreglo[1].toFloat())
            val B = floatArrayOf(arreglo[2].toFloat(),arreglo[3].toFloat())
            val opc = arreglo[4]
            val ancho = canvas.getWidth()
            val alto = canvas.getHeight()
            val pincel1 = Paint()
            val pincel2 = Paint()
            val pincel3 = Paint()
            val incrementoX = B[0]-A[0]
            val incrementoY = B[1]-A[1]
            val x = ancho/2f
            val y = alto/2f
            val incremento = tamaño
            var incrementoAux = incremento
            pincel1.strokeWidth = incremento
            pincel2.strokeWidth = 2f
            pincel1.setARGB(255, 0, 0, 0)
            pincel2.setARGB(255,255,0,0)
            pincel3.setARGB(255,0,255,0)
            if (incrementoAux<=5) incrementoAux = 5f
            for (i in y.toInt()+(incrementoAux/2f).toInt()..alto step incrementoAux.toInt()){
                canvas.drawLine(0f,i.toFloat(),ancho.toFloat(),i.toFloat(),pincel3)
                canvas.drawLine(0f,y+(y-i),ancho.toFloat(),y+(y-i),pincel3)
            }
            for (i in (x+(incrementoAux/2f)).toInt()..ancho step incrementoAux.toInt()){
                canvas.drawLine(i.toFloat(),0f,i.toFloat(),alto.toFloat(),pincel3)
                canvas.drawLine(x-(i-x),0f,x-(i-x),alto.toFloat(),pincel3)
            }
            canvas.drawLine(0f,alto/2f,ancho.toFloat(),alto/2f,pincel2)
            canvas.drawLine(ancho/2f,0f,ancho/2f,alto.toFloat(),pincel2)
            val ar:ArrayList<FloatArray>
            val pasos:Float

            when(opc){
                0 -> {
                    ar = Metodos().Basico(A,B)
                    pasos = B[0]-A[0]
                    for (i in 0..(pasos).toInt()){
                        canvas.drawPoint(x+((Math.round(ar[0][i]).toFloat()*incremento)),
                                y-((Math.round(ar[1][i]).toFloat()*incremento)), pincel1)
                    }
                }
                1 -> {
                    ar = Metodos().DDA(A,B)
                    if (Math.abs(incrementoX)>Math.abs(incrementoY)) {
                        pasos = Math.abs(incrementoX)
                    }
                    else {
                        pasos = Math.abs(incrementoY)
                    }
                    for (i in 0..(pasos).toInt()){
                        canvas.drawPoint(x+((Math.round(ar[0][i]).toFloat()*incremento)),
                                y-((Math.round(ar[1][i]).toFloat()*incremento)), pincel1)
                    }

                }
                2 -> {
                    ar = Metodos().Bressenham(A,B)
                    if (Math.abs(incrementoX)>Math.abs(incrementoY)) {
                        pasos = Math.abs(incrementoX)
                    }
                    else {
                        pasos = Math.abs(incrementoY)
                    }
                    for (i in 0..(pasos).toInt()){
                        canvas.drawPoint(x+((Math.round(ar[0][i]).toFloat()*incremento)),
                                y-((Math.round(ar[1][i]).toFloat()*incremento)), pincel1)
                    }
                }
            }

        }
    }
}
