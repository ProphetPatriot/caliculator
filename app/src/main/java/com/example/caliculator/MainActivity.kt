package com.example.caliculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var editA: EditText
    private lateinit var editB: EditText
    private lateinit var editC: EditText
    private lateinit var resultText: TextView
    private lateinit var buttonSolve: Button
    private lateinit var buttonSum: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Связываем элементы
        editA = findViewById(R.id.editA)
        editB = findViewById(R.id.editB)
        editC = findViewById(R.id.editC)
        resultText = findViewById(R.id.resultText)
        buttonSolve = findViewById(R.id.buttonSolve)
        buttonSum = findViewById(R.id.buttonSum)

        // Решение квадратного уравнения
        buttonSolve.setOnClickListener {
            solveEquation()
        }

        // Сумма коэффициентов
        buttonSum.setOnClickListener {

            val a = editA.text.toString().toDoubleOrNull()
            val b = editB.text.toString().toDoubleOrNull()
            val c = editC.text.toString().toDoubleOrNull()

            if (a == null || b == null || c == null) {
                Toast.makeText(this, "Введите корректные числа!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sum = a + b + c
            resultText.text = "Сумма коэффициентов = $sum"
        }
    }

    private fun solveEquation() {

        val strA = editA.text.toString().trim()
        val strB = editB.text.toString().trim()
        val strC = editC.text.toString().trim()

        if (strA.isEmpty() || strB.isEmpty() || strC.isEmpty()) {
            Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val a = strA.toDouble()
            val b = strB.toDouble()
            val c = strC.toDouble()

            if (a == 0.0) {
                resultText.text = "Это не квадратное уравнение (A = 0)"
                return
            }

            val d = b * b - 4 * a * c
            Toast.makeText(this, "Дискриминант = $d", Toast.LENGTH_LONG).show()

            val result = when {
                d > 0 -> {
                    val x1 = (-b + sqrt(d)) / (2 * a)
                    val x2 = (-b - sqrt(d)) / (2 * a)
                    "X1 = %.2f, X2 = %.2f".format(x1, x2)
                }
                d == 0.0 -> {
                    val x = -b / (2 * a)
                    "Один корень: X = %.2f".format(x)
                }
                else -> "Действительных корней нет (D < 0)"
            }

            resultText.text = result

        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Ошибка: введите корректные числа!", Toast.LENGTH_SHORT).show()
        }
    }
}