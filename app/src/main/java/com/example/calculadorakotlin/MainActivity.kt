package com.example.calculadorakotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
//Variables
    private var num1: Double = 0.0
    private var num2: Double = 0.0
    private var operacion: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//contenedor para el resultado
        textViewResultado.text ="0"
        operacion = NO_OPERACION
//Digitos
        buttonUno.setOnClickListener { numeroPresionado("1")}
        buttonDos.setOnClickListener { numeroPresionado("2")}
        buttonTres.setOnClickListener { numeroPresionado("3")}
        buttonCuatro.setOnClickListener { numeroPresionado("4")}
        buttonCinco.setOnClickListener { numeroPresionado("5")}
        buttonSeis.setOnClickListener { numeroPresionado("6")}
        buttonSiete.setOnClickListener { numeroPresionado("7")}
        buttonocho.setOnClickListener { numeroPresionado("8")}
        buttonNueve.setOnClickListener { numeroPresionado("9")}
        buttonCero.setOnClickListener { numeroPresionado("0")}
        buttonPunto.setOnClickListener { numeroPresionado(".")}

//OPERACIONES
        buttonSuma.setOnClickListener { operacionPrecionada(SUMA) }
        buttonMenos.setOnClickListener { operacionPrecionada(RESTA) }
        buttonMultiplicacion.setOnClickListener { operacionPrecionada(MULTIPLICACION) }
        buttonDivision.setOnClickListener { operacionPrecionada(DIVISION) }
//IGUAL / Resultado
        buttonIgual.setOnClickListener { resultadoPrecionado() }
//LimpiarTodo
        buttonLimpiar.setOnClickListener { limpiarTodo() }
    }

    //Obtiene el numero precionado por el usuario
    private fun numeroPresionado(digito: String){
        if(textViewResultado.text == "0" && digito != ".") {
            textViewResultado.text = "$digito"
        } else {
            textViewResultado.text = "${textViewResultado.text}$digito"
        }
        if (operacion == NO_OPERACION){
            num1 = textViewResultado.text.toString().toDouble()
        } else{
            num2 = textViewResultado.text.toString().toDouble()
        }
    }

    //Obtiene la operacion precionada
    private fun operacionPrecionada(operacion:Int){
        this.operacion = operacion
        num1 = textViewResultado.text.toString().toDouble()
        textViewResultado.text = "0"
    }

    //Realiza la operacion
    private fun resultadoPrecionado(){
        val result = when(operacion) {
            SUMA -> num1 + num2
            RESTA -> num1 - num2
            MULTIPLICACION -> num1 * num2
            DIVISION -> num1 / num2
            else -> 0
        }
        num1 = result as Double
        textViewResultado.text = if("$result".endsWith(".0")) { "$result".replace(".0","") } else { "%.2f".format(result) }
    }
//limpia las variables y pantalla
    private fun limpiarTodo(){
        textViewResultado.text = "0"
        num1 = 0.0
        num2 = 0.0
    }

    //Constantes para operaciones
    companion object{
        const val SUMA = 1
        const val RESTA = 2
        const val MULTIPLICACION = 3
        const val DIVISION = 4
        const val NO_OPERACION = 0
    }
}