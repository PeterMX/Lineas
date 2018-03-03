package com.example.uriel.lineas

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class TablaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabla)
        title = "Tabla"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val tabla = intent.getIntegerArrayListExtra("valores")
        var tablaValores:ArrayList<FloatArray>? = null
        when(tabla[4]){
            0 -> tablaValores = Metodos().Basico(floatArrayOf(tabla[0].toFloat(), tabla[1].toFloat()),
                    floatArrayOf(tabla[2].toFloat(), tabla[3].toFloat()))
            1 -> tablaValores = Metodos().DDA(floatArrayOf(tabla[0].toFloat(), tabla[1].toFloat()),
                    floatArrayOf(tabla[2].toFloat(), tabla[3].toFloat()))
            2 -> tablaValores = Metodos().Bressenham(floatArrayOf(tabla[0].toFloat(), tabla[1].toFloat()),
                    floatArrayOf(tabla[2].toFloat(), tabla[3].toFloat()))
            3 -> tablaValores = Metodos().Archivo()
        }
        val numFil = tablaValores!![0].size
        val cadena = arrayListOf<String>("K","X","Y","NF")
        val table = findViewById(R.id.tableLayout) as TableLayout
        val layoutFila = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT)
        /*val layoutK = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT)
        val layoutX = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT)
        val layoutY = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT)*/
        val fondo = getDrawable(R.drawable.shape)
        table.removeAllViews()
        for (i in 0 .. numFil!!){
            val tableRow = TableRow(this)
            tableRow.layoutParams = layoutFila
            val k = TextView(this)
            k.gravity = Gravity.RIGHT
            k.setPadding(10,0,50,0)
            //k.background = getDrawable(R.drawable.shape)
            k.layoutParams = layoutFila
            tableRow.addView(k)
            val x = TextView(this)
            x.setPadding(0,0,50,0)
            //x.background = getDrawable(R.drawable.shape)
            x.layoutParams = layoutFila
            tableRow.addView(x)
            val y = TextView(this)
            y.setPadding(0,0,50,0)
            //y.background = getDrawable(R.drawable.shape)
            y.layoutParams = layoutFila
            //val operacion = TextView(this)
            //operacion.setPadding(0,0,10,0)
            //operacion.layoutParams = layoutY
            if (i!=0){
                k.textSize = 18f
                x.textSize = 18f
                y.textSize = 18f
                //operacion.textSize = 18f
                k.text = "${i-1}"
                x.text = "${Math.round(tablaValores[0][i-1])}"
                y.text = "${Math.round(tablaValores[1][i-1])}"
                //operacion.text = String.format("%.2f", tablaValores[1][i-1])
                        // String.format("%.${longitud}f",string)
            }else{
                k.textSize = 22f
                k.typeface = Typeface.DEFAULT_BOLD
                x.textSize = 22f
                x.typeface = Typeface.DEFAULT_BOLD
                y.textSize = 22f
                y.typeface = Typeface.DEFAULT_BOLD
                //operacion.textSize = 22f
                //operacion.typeface = Typeface.DEFAULT_BOLD
                k.text = "${cadena[0]}"
                x.text = "${cadena[1]}"
                y.text = "${cadena[2]}"
                //operacion.text = "${cadena[3]}"
            }

            tableRow.addView(y)
            //tableRow.addView(operacion)
            tableRow.background = fondo
            table.addView(tableRow)
        }
        tablaValores.clear()
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    public override fun onDestroy() {
        super.onDestroy()
        Runtime.getRuntime().gc()
    }
}
