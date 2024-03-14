package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.nfunk.jep.JEP

class AdvancedCalculatorActivity : AppCompatActivity() {

    private lateinit var displayTextView: TextView
    private lateinit var resultTextView: TextView
    private var isOperationPossible = false
    private var isDecimalPossible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_advanced_calculator)
        displayTextView = findViewById(R.id.displayTextView)
        resultTextView = findViewById(R.id.resultTextView)
    }

    fun allClear(view : View){
        displayTextView.text = ""
        resultTextView.text = ""
        isOperationPossible = false
        isDecimalPossible = true
    }

    fun changeSign(view : View){
        val originalText = displayTextView.text.toString()
        if(originalText[0] == '-')
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
        jep.addStandardConstants()
        jep.addStandardFunctions()

        jep.parseExpression(displayTextView.text.toString())
        if(jep.hasError())
            Toast.makeText(this, "Incorrect input", Toast.LENGTH_SHORT).show()
        else {
            val result = jep.value.toString()
            if(result.length > 12)
                resultTextView.text = result.substring(0,12)
            else
                resultTextView.text = result
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

    fun singleOperatorAction(view: View){
        if (isOperationPossible && view is Button) {
            if(view.text.equals("x^2")){
                displayTextView.text = "(${displayTextView.text})^2"
            }
            else if(view.text.equals("x^y")){
                displayTextView.text = "(${displayTextView.text})^"
                isOperationPossible = false
            }
            else if(view.text.equals("%")){
                displayTextView.text = "(${displayTextView.text})%"
                isOperationPossible = false
            }
            else
                displayTextView.text = "${view.text}(${displayTextView.text})"
        }
    }
}