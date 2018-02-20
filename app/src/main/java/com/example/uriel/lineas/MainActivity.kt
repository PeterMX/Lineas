package com.example.uriel.lineas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val opciones = arrayListOf<String>("Basico","DDA","Bresenham")
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opciones)
        spinner2.adapter = adapter

        button2.setOnClickListener {
            if (x1.text.isNotEmpty()&&x2.text.isNotEmpty()&&y1.text.isNotEmpty()&&y2.text.isNotEmpty()){
                val valores = arrayListOf<Int>((x1.text).toString().toInt(),(y1.text).toString().toInt()
                        ,(x2.text).toString().toInt(),(y2.text).toString().toInt(),spinner2.selectedItemPosition)
                val siguiente = Intent(this,GUICanvas::class.java)
                siguiente.putExtra("valores",valores)
                startActivity(siguiente)
            }else{
                val simpleAlert = AlertDialog.Builder(this@MainActivity).create()
                simpleAlert.setTitle("Error:")
                simpleAlert.setMessage("No puede dejar espacios vacios")

                simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", {
                    _, _ ->
                    Toast.makeText(applicationContext, "Ingresa los valores", Toast.LENGTH_SHORT).show()
                })

                simpleAlert.show()
            }

        }
    }
}
