/**
 * Course: MAD204 - Assignment 2
 * Student Name: Ashishkumar Prajapati
 * Student ID: A00194842
 * Description: Settings screen to toggle Dark Mode.
 */

package com.example.assignment2ashish

import android.content.Context
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val switchDarkMode = findViewById<Switch>(R.id.switchDarkMode)
        val sharedPref = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)


        val isDarkMode = sharedPref.getBoolean("darkMode", false)
        switchDarkMode.isChecked = isDarkMode


        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            // Save the new preference
            sharedPref.edit().putBoolean("darkMode", isChecked).apply()

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}