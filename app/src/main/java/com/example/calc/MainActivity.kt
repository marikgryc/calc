package com.example.calc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)

        btn1.setOnClickListener { addDigit("1") }
        btn2.setOnClickListener { addDigit("2") }
        btn3.setOnClickListener { addDigit("3") }
    }

    private fun addDigit(digit: String) {
        if (tvDisplay.text == "0") {
            tvDisplay.text = digit
        } else {
            tvDisplay.append(digit)
        }
    }
}