package com.techmania.mathgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var textFinalScore: TextView
    private lateinit var buttonPlayAgain: Button
    private lateinit var buttonExit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Initialize views
        textFinalScore = findViewById(R.id.textViewFinalScore)
        buttonPlayAgain = findViewById(R.id.buttonPlayAgain)
        buttonExit = findViewById(R.id.buttonExit)

        // Get the score from the Intent
        val score = intent.getIntExtra("SCORE", 0)
        textFinalScore.text = "Congratulations! Your Score: $score"

        // Set up button listeners
        buttonPlayAgain.setOnClickListener {
            // Restart the game
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonExit.setOnClickListener {
            // Exit the app
            finish()
        }
    }
}
