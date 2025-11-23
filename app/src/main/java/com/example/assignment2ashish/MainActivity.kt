/**
 * Course: MAD204 - Assignment 2
 * Student Name: Ashishkumar Prajapati
 * Student ID: A00194842
 * Description: The Login Activity. It checks if a user is already logged in
 * and handles validation for email and password.
 */

package com.example.assignment2ashish

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {

            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_main)

        val etEmail = findViewById<EditText>(R.id.etLoginEmail)
        val etPassword = findViewById<EditText>(R.id.etLoginPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnGoToRegister)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            // Check if email is empty or invalid format
            if (email.isEmpty() || !email.contains("@")) {
                etEmail.error = "Please enter a valid email with '@'"
                return@setOnClickListener
            }
            if (password.length < 4) {
                etPassword.error = "Password must be at least 4 characters"
                return@setOnClickListener
            }

            // Retrieve stored user credentials from SharedPreferences for verification
            val storedEmail = sharedPref.getString("email", "")
            val storedPass = sharedPref.getString("password", "")


            if (email == storedEmail && password == storedPass) {

                // Save "isLoggedIn" state to true so the user skips login next time
                sharedPref.edit().putBoolean("isLoggedIn", true).apply()

                // Navigate to Dashboard and close Login screen
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials or user not found", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle Register Button Click
        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }
}