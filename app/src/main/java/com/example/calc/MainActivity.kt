package com.example.calc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private var firstOperand: Double = 0.0
    private var currentOperation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        val buttons = listOf(
            R.id.btn0 to "0",R.id.btn1 to "1", R.id.btn2 to "2", R.id.btn3 to "3",
            R.id.btn4 to "4", R.id.btn5 to "5", R.id.btn6 to "6",
            R.id.btn7 to "7", R.id.btn8 to "8", R.id.btn9 to "9"
        )

        buttons.forEach { (id, digit) ->
            findViewById<Button>(id).setOnClickListener { addDigit(digit) }
        }

        // Кнопки операцій
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnSubtract = findViewById<Button>(R.id.btnSubtract)
        val btnMultiply = findViewById<Button>(R.id.btnMultiply)
        val btnDivide = findViewById<Button>(R.id.btnDivide)
        val btnEquals = findViewById<Button>(R.id.btnEquals)
        val btnClear = findViewById<Button>(R.id.btnClear)

        btnAdd.setOnClickListener { prepareOperation("+") }
        btnSubtract.setOnClickListener { prepareOperation("-") }
        btnMultiply.setOnClickListener { prepareOperation("*") }
        btnDivide.setOnClickListener { prepareOperation("/") }

        btnClear.setOnClickListener {
            tvDisplay.text = "0"
            firstOperand = 0.0
            currentOperation = ""
        }

        btnEquals.setOnClickListener {
            val secondText = tvDisplay.text.toString()
            if (secondText == "Помилка") return@setOnClickListener

            val secondOperand = secondText.toDoubleOrNull() ?: 0.0
            var result = 0.0
            var error = false

            when (currentOperation) {
                "+" -> result = firstOperand + secondOperand
                "-" -> result = firstOperand - secondOperand
                "*" -> result = firstOperand * secondOperand
                "/" -> {
                    if (secondOperand != 0.0) {
                        result = firstOperand / secondOperand
                    } else {
                        error = true
                    }
                }
            }

            if (error) {
                tvDisplay.text = "Помилка"
            } else {
                tvDisplay.text = formatResult(result)
            }
            currentOperation = ""
        }
    }

    private fun addDigit(digit: String) {
        if (tvDisplay.text.toString() == "0" || tvDisplay.text.toString() == "Помилка") {
            tvDisplay.text = digit
        } else {
            tvDisplay.append(digit)
        }
    }

    private fun prepareOperation(operation: String) {
        val currentText = tvDisplay.text.toString()
        if (currentText == "Помилка") return

        firstOperand = currentText.toDoubleOrNull() ?: 0.0
        currentOperation = operation
        tvDisplay.text = "0"
    }

    private fun formatResult(result: Double): String {
        return if (result % 1.0 == 0.0) {
            result.toLong().toString()
        } else {
            result.toString()
        }
    }
}