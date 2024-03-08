package com.example.calculator

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SimpleCalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_simple_calculator)
    }

    fun allClear(view : View){

    }

    fun changeSign(view : View){

    }

    fun clearEnter(view : View){

    }

    fun operationAction(view : View){

    }

    fun calculateAction(view : View){

    }

    fun numberAction(view : View){

    }
}