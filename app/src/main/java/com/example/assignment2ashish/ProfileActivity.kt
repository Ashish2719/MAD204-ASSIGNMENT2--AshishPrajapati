/**
 * Course: MAD204 - Assignment 2
 * Student Name: Ashishkumar Prajapati
 * Student ID: A00194842
 * Description: Allows user to view and edit their profile data.
 */

package com.example.assignment2ashish

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private val TAG = "ProfileLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Log.d(TAG, "onCreate: Profile Screen Created")

        val etName = findViewById<EditText>(R.id.etProfileName)
        val etProgram = findViewById<EditText>(R.id.etProfileProgram)
        val btnUpdate = findViewById<Button>(R.id.btnUpdateProfile)

        val sharedPref = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)


        etName.setText(sharedPref.getString("name", ""))
        etProgram.setText(sharedPref.getString("program", ""))

        btnUpdate.setOnClickListener {
            // Save the new values back to storage
            val editor = sharedPref.edit()
            editor.putString("name", etName.text.toString())
            editor.putString("program", etProgram.text.toString())
            editor.apply()

            Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show()
        }
    }

    // Simple logging for start/stop
    override fun onStart() { super.onStart(); Log.d(TAG, "onStart") }
    override fun onStop() { super.onStop(); Log.d(TAG, "onStop") }
}