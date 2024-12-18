package com.techmania.mathgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var tvTitle: TextView
    lateinit var addition: Button
    lateinit var substraction: Button
    lateinit var multiplication: Button
    lateinit var division: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvTitle=findViewById(R.id.tvTitle)
        addition=findViewById(R.id.btnAddition)
        substraction=findViewById(R.id.btnSubtraction)
        multiplication=findViewById(R.id.btnMultiplication)
        division=findViewById(R.id.btnDivision)


        addition.setOnClickListener{
            val intent= Intent(this@MainActivity, gameactivity::class.java)
            intent.putExtra("OPERATION", "addition")
            startActivity(intent)
        }

        substraction.setOnClickListener{
            val intent= Intent(this@MainActivity, gameactivity::class.java)
            intent.putExtra("OPERATION", "subtraction")
            startActivity(intent)
        }
        multiplication.setOnClickListener{
            val intent= Intent(this@MainActivity, gameactivity::class.java)
            intent.putExtra("OPERATION", "multiplication")
            startActivity(intent)
        }
        division.setOnClickListener{
            val intent= Intent(this@MainActivity, gameactivity::class.java)
            intent.putExtra("OPERATION", "division")
            startActivity(intent)
        }


    }
}