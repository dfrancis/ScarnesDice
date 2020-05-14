package com.example.scarnesdice

// import android.R
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
// import kotlinx.android.synthetic.main.activity_main.view.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    val COMP_HOLD_VAL = 20

    var userTotalScore = 0 // the user's overall score state
    var userTurnScore = 0  // the user's turn score
    var compTotalScore = 0 // the computer's overall score
    var compTurnScore = 0  // the computer's turn score

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /** Called when the user taps the ROLL button */
    fun onRollButton(view: View) {
        var rollVal : Int = Random.nextInt(1, 6)
        var dieRes : Int = R.drawable.dice1
        when (rollVal) {
            1 -> dieRes = R.drawable.dice1
            2 -> dieRes = R.drawable.dice2
            3 -> dieRes = R.drawable.dice3
            4 -> dieRes = R.drawable.dice4
            5 -> dieRes = R.drawable.dice5
            6 -> dieRes = R.drawable.dice6
        }
        var dieImage : Drawable = getResources().getDrawable(dieRes)
        var imageView : ImageView = findViewById(R.id.imageView)
        imageView.setImageDrawable(dieImage)
        var scoreView : TextView = findViewById(R.id.textView)
        if (rollVal == 1) {
            userTurnScore = 0
            scoreView.setText("Your score: " + userTotalScore + " computer score: " + compTotalScore + " your turn score: " + userTurnScore)

            val btn1: Button = findViewById<View>(R.id.button1) as Button
            btn1.setEnabled(false)
            val btn2: Button = findViewById<View>(R.id.button2) as Button
            btn2.setEnabled(false)

            computerTurn()

            btn1.setEnabled(true)
            btn2.setEnabled(true)
        }
        else {
            userTurnScore += rollVal
            scoreView.setText("Your score: " + userTotalScore + " computer score: " + compTotalScore + " your turn score: " + userTurnScore)
        }
    }

    /** Called when the user taps the HOLD button */
    fun onHoldButton(view: View) {
        userTotalScore += userTurnScore
        userTurnScore = 0

        var scoreView : TextView = findViewById(R.id.textView)
        scoreView.setText("Your score: " + userTotalScore + " computer score: " + compTotalScore + " your turn score: " + userTurnScore)

        val btn1: Button = findViewById<View>(R.id.button1) as Button
        btn1.setEnabled(false)
        val btn2: Button = findViewById<View>(R.id.button2) as Button
        btn2.setEnabled(false)

        computerTurn()

        btn1.setEnabled(true)
        btn2.setEnabled(true)
    }

    /** Called when the user taps the RESET button */
    fun onResetButton(view: View) {
        userTotalScore = 0
        userTurnScore = 0
        compTotalScore = 0
        compTurnScore = 0

        var scoreView : TextView = findViewById(R.id.textView);
        scoreView.setText("Your score: " + userTotalScore + " computer score: " + compTotalScore + " your turn score: " + userTurnScore)
    }

    fun computerTurn() {
        var done = false


        var imageView : ImageView = findViewById(R.id.imageView)

        var scoreView : TextView = findViewById(R.id.textView)
        var rollVal : Int
        var dieRes : Int = R.drawable.dice1

        while (! done) {
            // pause

            rollVal = Random.nextInt(1, 6)
            when (rollVal) {
                1 -> dieRes = R.drawable.dice1
                2 -> dieRes = R.drawable.dice2
                3 -> dieRes = R.drawable.dice3
                4 -> dieRes = R.drawable.dice4
                5 -> dieRes = R.drawable.dice5
                6 -> dieRes = R.drawable.dice6
            }
            var dieImage : Drawable = getResources().getDrawable(dieRes)
            imageView.setImageDrawable(dieImage)
            if (rollVal == 1) {
                compTurnScore = 0
                done = true
            }
            else {
                compTurnScore += rollVal
                if (compTurnScore >= COMP_HOLD_VAL) {
                    done = true
                    compTotalScore += compTurnScore
                }
            }

            scoreView.setText("Your score: " + userTotalScore + " computer score: " + compTotalScore + " your turn score: " + userTurnScore)
        }
    }
}
