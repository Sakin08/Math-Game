package com.techmania.mathgame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class gameactivity : AppCompatActivity() {

    private lateinit var textScore: TextView
    private lateinit var textLife: TextView
    private lateinit var textTime: TextView
    private lateinit var textQuestion: TextView
    private lateinit var editTextAns: EditText
    private lateinit var buttonOK: Button
    private lateinit var buttonNext: Button

    private var score = 0
    private var life = 3
    private var correctAnswer = 0
    private var timer: CountDownTimer? = null
    private var operation = "addition" // Default operation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameactivity)

        // Get the operation type from the Intent
        operation = intent.getStringExtra("OPERATION") ?: "addition"

        // Initialize views
        textScore = findViewById(R.id.textViewScore)
        textLife = findViewById(R.id.textViewLife)
        textTime = findViewById(R.id.textViewTime)
        textQuestion = findViewById(R.id.textViewQuestion)
        editTextAns = findViewById(R.id.editTextAns)
        buttonOK = findViewById(R.id.button_ok)
        buttonNext = findViewById(R.id.button_next)

        // Start the game
        gameContinue()

        buttonOK.setOnClickListener {
            val input = editTextAns.text.toString()
            if (input.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Please enter an answer or press the Next button.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                try {
                    val userAns = input.toInt()
                    if (userAns == correctAnswer) {
                        score += 10
                        textQuestion.text = "Congratulations, your answer is correct!"
                        textScore.text = "Score: $score"

                        Toast.makeText(this, "Correct! +10 points", Toast.LENGTH_SHORT).show()
                    } else {
                        life--
                        textQuestion.text = "Sorry, your answer is wrong."
                        textLife.text = "Life: $life"
                        Toast.makeText(this, "Wrong! -1 life", Toast.LENGTH_SHORT).show()
                        if (life <= 0) {
                            endGame()
                        }
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
                }
            }
        }

        buttonNext.setOnClickListener {
            gameContinue()
            editTextAns.text.clear()  // Clear the input field
        }
    }

    private fun gameContinue() {
        if (life <= 0) {
            endGame()
            return
        }

        // Generate two random numbers
        val number1 = Random.nextInt(1, 100)
        val number2 = Random.nextInt(1, 100)

        // Generate question based on the selected operation
        when (operation) {
            "addition" -> {
                correctAnswer = number1 + number2
                textQuestion.text = "$number1 + $number2"
            }
            "subtraction" -> {
                if (number1 >= number2) {
                    correctAnswer = number1 - number2
                    textQuestion.text = "$number1 - $number2"
                } else {
                    correctAnswer = number2 - number1
                    textQuestion.text = "$number2 - $number1"
                }
            }
            "multiplication" -> {
                correctAnswer = number1 * number2
                textQuestion.text = "$number1 × $number2"
            }
        }

        // Update the score and life displays
        textLife.text = "Life: $life"
        textScore.text = "Score: $score"

        // Start the timer for the question
        startTimer()
    }

    private fun startTimer() {
        timer?.cancel()  // Cancel any previous timer
        timer = object : CountDownTimer(30000, 1000) {  // 30 seconds countdown
            override fun onTick(millisUntilFinished: Long) {
                textTime.text = "Time: ${millisUntilFinished / 1000}s"
            }

            override fun onFinish() {
                life--
                textLife.text = "Life: $life"
                Toast.makeText(this@gameactivity, "Time's up! -1 life", Toast.LENGTH_SHORT).show()
                if (life <= 0) {
                    endGame()
                } else {
                    gameContinue()  // Continue with the next question
                }
            }
        }.start()
    }

    private fun endGame() {
        timer?.cancel()

        // Navigate to ResultActivity
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("SCORE", score)  // Pass the score to ResultActivity
        startActivity(intent)
        finish()  // Close the current activity
    }
}
