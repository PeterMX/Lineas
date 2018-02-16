package com.example.uriel.lineas




/**
 * Created by uriel on 13/02/18.
 */
class Metodos{
    fun DDA(A: FloatArray, B: FloatArray): ArrayList<FloatArray> {

        val incrementoX = B[0]-A[0]
        val incrementoY = B[1]-A[1]

        val x1 = arrayListOf<Float>()
        val y1 = arrayListOf<Float>()

        val pasos:Float

        if (Math.abs(incrementoX)>Math.abs(incrementoY)) {
            pasos = Math.abs(incrementoX)
        }
        else {
            pasos = Math.abs(incrementoY)
        }

        val ix = incrementoX/pasos
        val iy = incrementoY/pasos

        for (i in 0..(pasos).toInt()){
            if (i!=0){
                x1.add(x1[i-1]+ix)
                y1.add(y1[i-1]+iy)
            }else{
                x1.add(A[0])
                y1.add(A[1])
            }
        }



        val retornar = arrayListOf<FloatArray>(x1.toFloatArray(),y1.toFloatArray())
        return retornar
    }
    fun Basico(A: FloatArray, B: FloatArray): ArrayList<FloatArray> {

        val x1 = arrayListOf<Float>()
        val y1 = arrayListOf<Float>()

        var m:Float=0f
        var b:Float=0f
        for (i in A[0].toInt()..B[0].toInt()){
            if (i==A[0].toInt()){
                m= ((B[1]-A[1])/(B[0]-A[0]))
                b=A[1]-m*A[0]
            }
            x1.add(i.toFloat())
            y1.add((m*i.toFloat())+b)
        }
        val retornar = arrayListOf<FloatArray>(x1.toFloatArray(),y1.toFloatArray())
        return retornar
    }

    fun Bressenham(A: FloatArray, B: FloatArray): ArrayList<FloatArray>{
        val m = 2f*(B[1]-A[1])
        var inclinacion = m - (B[0]-A[0])

        val x1 = arrayListOf<Float>()
        val y1 = arrayListOf<Float>()


        var x = A[0]
        var y = A[1]
        while (x <= B[0]) {
            x1.add(x)
            y1.add(y)

            inclinacion += m
            if (inclinacion>=0){
                y++
                inclinacion -= 2f*(B[0]-A[0])
            }
            x++
        }


        val retornar = arrayListOf<FloatArray>(x1.toFloatArray(),y1.toFloatArray())
        return retornar
    }
}