/**
 * Course: MAD204 - Assignment 2
 * Student Name: Ashishkumar Prajapati
 * Student ID: A00194842
 * Description: The main hub. Demonstrates lifecycle logging and navigation.
 */

package com.example.assignment2ashish

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    private val TAG = "DashboardLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        Log.d(TAG, "onCreate: Dashboard Created")


        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val sharedPref = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val userName = sharedPref.getString("name", "User")
        tvWelcome.text = "Welcome, $userName"


        findViewById<Button>(R.id.btnProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        findViewById<Button>(R.id.btnCountries).setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }

        findViewById<Button>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }


        findViewById<Button>(R.id.btnLogout).setOnClickListener {

            sharedPref.edit().putBoolean("isLoggedIn", false).apply()


            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Dashboard Started")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Dashboard Resumed (Visible to user)")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Dashboard Paused")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Dashboard Stopped (Hidden)")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Dashboard Destroyed")
    }
}