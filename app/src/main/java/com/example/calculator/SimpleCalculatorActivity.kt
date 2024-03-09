package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import org.nfunk.jep.JEP

class SimpleCalculatorActivity : AppCompatActivity() {

    private lateinit var displayTextView: TextView
    private lateinit var resultTextView: TextView
    private var isOperationPossible = false
    private var isDecimalPossible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_simple_calculator)
        displayTextView = findViewById(R.id.displayTextView)
        resultTextView = findViewById(R.id.resultTextView)
    }

    fun allClear(view : View){
        displayTextView.text = ""
        resultTextView.text = ""
    }
//
//    fun changeSign(view : View){
//
//    }
//
//    fun clearEnter(view : View){
//
//    }
//
    fun operationAction(view : View){
    if (isOperationPossible && view is Button) {
        displayTextView.append(view.text)
        isOperationPossible = false
        isDecimalPossible = true

    }
    }

    fun calculateAction(view : View){
    val jep = JEP()
    jep.parseExpression(displayTextView.text.toString())
    resultTextView.text = jep.value.toString()
    }

    fun numberAction(view : View){
        if (view is Button) {
            displayTextView.append(view.text)
            isOperationPossible = true
        }
    }

    fun decimalAction(view : View){
        if(isDecimalPossible && view is Button){
            val text = displayTextView.text.toString()
            if (text.isNotEmpty() && Character.isDigit(text.last())){
                displayTextView.append(view.text)
                isDecimalPossible = false
            }
        }
    }
}