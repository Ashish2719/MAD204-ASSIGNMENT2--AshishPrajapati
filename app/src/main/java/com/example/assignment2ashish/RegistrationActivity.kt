/**
 * Course: MAD204 - Assignment 2
 * Student Name: Ashishkumar Prajapati
 * Student ID: A00194842
 * Description: Registration screen. Validates input and saves it to storage.
 */

package com.example.assignment2ashish

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val etName = findViewById<EditText>(R.id.etRegName)
        val etEmail = findViewById<EditText>(R.id.etRegEmail)
        val etPass = findViewById<EditText>(R.id.etRegPassword)
        val etAge = findViewById<EditText>(R.id.etRegAge)
        val etProgram = findViewById<EditText>(R.id.etRegProgram)
        val btnReg = findViewById<Button>(R.id.btnRegister)

        btnReg.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val pass = etPass.text.toString()
            val ageStr = etAge.text.toString()
            val program = etProgram.text.toString()


            if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || ageStr.isEmpty() || program.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val age = ageStr.toIntOrNull() ?: 0
            if (age <= 0) {
                etAge.error = "Age must be greater than 0"
                return@setOnClickListener
            }


            val sharedPref = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("name", name)
            editor.putString("email", email)
            editor.putString("password", pass)
            editor.putString("program", program)
            editor.putInt("age", age)
            editor.apply()

            Toast.makeText(this, "Registration Successful! Please Login.", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}