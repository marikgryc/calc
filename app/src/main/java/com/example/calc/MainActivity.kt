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
        val btn0 = findViewById<Button>(R.id.btn0)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnEquals = findViewById<Button>(R.id.btnEquals)
        val btnSubtract = findViewById<Button>(R.id.btnSubtract)
        val btnClear = findViewById<Button>(R.id.btnClear)


        btn0.setOnClickListener { addDigit("0") }
        btn1.setOnClickListener { addDigit("1") }
        btn2.setOnClickListener { addDigit("2") }
        btn3.setOnClickListener { addDigit("3") }
        btn4.setOnClickListener { addDigit("4") }
        btn5.setOnClickListener { addDigit("5") }
        btn6.setOnClickListener { addDigit("6") }
        btn7.setOnClickListener { addDigit("7") }
        btn8.setOnClickListener { addDigit("8") }
        btn9.setOnClickListener { addDigit("9") }

        btnClear.setOnClickListener {
            tvDisplay.text = "0"
            firstOperand = 0.0
            currentOperation = ""
        }

        btnAdd.setOnClickListener {

            firstOperand = tvDisplay.text.toString().toDouble()

            currentOperation = "+"

            tvDisplay.text = "0"
        }

        btnSubtract.setOnClickListener {
            firstOperand = tvDisplay.text.toString().toDouble()
            // Запам'ятовуємо, що ми хочемо віднімати
            currentOperation = "-"
            // Скидаємо екран до "0", щоб вводити друге число
            tvDisplay.text = "0"
        }

        btnEquals.setOnClickListener {
            val secondOperand = tvDisplay.text.toString().toDouble()
            var result = 0.0

            if (currentOperation == "+") {
                result = firstOperand + secondOperand
            } else if (currentOperation == "-") { // ДОДАНО: перевірка для віднімання
                result = firstOperand - secondOperand
            }

            tvDisplay.text = formatResult(result)
            currentOperation = ""
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
private fun formatResult(result: Double): String {
    return if (result % 1.0 == 0.0) {
        result.toLong().toString()
    } else {
        result.toString()
    }
}