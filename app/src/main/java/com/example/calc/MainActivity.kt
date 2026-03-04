package com.example.calc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView

    // Змінні для збереження першого числа та математичної операції
    private var firstOperand: Double = 0.0
    private var currentOperation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)

        // Знаходимо кнопки "+" та "="
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnEquals = findViewById<Button>(R.id.btnEquals)

        btn1.setOnClickListener { addDigit("1") }
        btn2.setOnClickListener { addDigit("2") }
        btn3.setOnClickListener { addDigit("3") }
        btn4.setOnClickListener { addDigit("4") }
        btn5.setOnClickListener { addDigit("5") }
        btn6.setOnClickListener { addDigit("6") }

        // Що робимо, коли тиснемо "+"
        btnAdd.setOnClickListener {

            firstOperand = tvDisplay.text.toString().toDouble()

            // 2. Запам'ятовуємо, що ми хочемо саме додавати
            currentOperation = "+"

            // 3. Скидаємо екран до "0", щоб вводити друге число
            tvDisplay.text = "0"
        }

        // Що робимо, коли тиснемо "="
        btnEquals.setOnClickListener {
            val secondOperand = tvDisplay.text.toString().toDouble()
            var result = 0.0

            if (currentOperation == "+") {
                result = firstOperand + secondOperand
            }


            tvDisplay.text = result.toString()
        }
    }

    private fun addDigit(digit: String) {
        if (tvDisplay.text.toString() == "0") {
            tvDisplay.text = digit
        } else {
            tvDisplay.append(digit)
        }
    }
}