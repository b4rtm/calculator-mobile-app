package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
        setContentView(R.layout.activity_simple_calculator)
        displayTextView = findViewById(R.id.displayTextView)
        resultTextView = findViewById(R.id.resultTextView)

        if (savedInstanceState != null) {
            displayTextView.text = savedInstanceState.getString("displayText")
            resultTextView.text = savedInstanceState.getString("resultText")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("displayText", displayTextView.text.toString())
        outState.putString("resultText", resultTextView.text.toString())
    }

    fun allClear(view : View){
        displayTextView.text = ""
        resultTextView.text = ""
        isOperationPossible = false
        isDecimalPossible = true
    }

    fun changeSign(view : View){
        val originalText = displayTextView.text.toString()
        if(originalText.get(0) == '-')
            displayTextView.text=originalText.substring(1)
        else{
            displayTextView.text = "-"
            displayTextView.append(originalText)
        }
    }

    fun backspaceAction(view : View){
        if(displayTextView.text.isNotEmpty()){
            val ch = displayTextView.text.last()
            if(ch == '/' || ch == '*' || ch == '-' || ch == '+'){
                isOperationPossible = true;
            }
            displayTextView.text = displayTextView.text.substring(0,displayTextView.text.length-1)
        }
        if (displayTextView.text.isEmpty()){
            isOperationPossible = false
            isDecimalPossible = true
        }
    }

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
        if(jep.hasError())
            Toast.makeText(this, "Incorrect input", Toast.LENGTH_SHORT).show()
        else {
            val result = jep.value
            if (result % 1 == 0.0) {
                resultTextView.text = result.toInt().toString()
            } else {
                if (result.toString().length > 12)
                    resultTextView.text = result.toString().substring(0, 11)
                else
                    resultTextView.text = result.toString()
            }
        }
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