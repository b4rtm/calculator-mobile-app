package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
    }

    fun goToSimpleCalculator(view: View){
        val intent = Intent(this, SimpleCalculatorActivity::class.java)
        startActivity(intent)
    }

    fun goToAdvancedCalculator(view: View){
        val intent = Intent(this, AdvancedCalculatorActivity::class.java)
        startActivity(intent)
    }

    fun goToInfo(view: View){
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
    }

    fun goToExit(view: View){
        finishAffinity()
        exitProcess(0)
    }


}