/**
 * Course: MAD204 - Assignment 2
 * Student Name: Ashishkumar Prajapati
 * Student ID: A00194842
 * Description: Displays a RecyclerView list of countries.
 * Features: Click to view, Swipe to delete (with Undo), Sort A-Z.
 */

package com.example.assignment2ashish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class ListActivity : AppCompatActivity() {


    private val countryList = arrayListOf(
        "Canada", "USA", "Italy", "France", "Germany",
        "India", "China", "Japan", "Australia", "Mexico"
    )

    private lateinit var adapter: CountryAdapter
    private lateinit var tvEmpty: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        tvEmpty = findViewById(R.id.tvEmpty)
        val recyclerView = findViewById<RecyclerView>(R.id.rvCountries)

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CountryAdapter(countryList)
        recyclerView.adapter = adapter

        // Handle Sorting Button
        findViewById<Button>(R.id.btnSort).setOnClickListener {
            countryList.sort() // Sorts the list alphabetically
            adapter.notifyDataSetChanged() // Refreshes the screen
            Toast.makeText(this, "Sorted A-Z", Toast.LENGTH_SHORT).show()
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(rv: RecyclerView, v: RecyclerView.ViewHolder, t: RecyclerView.ViewHolder): Boolean {
                return false // We don't support drag-and-drop moving, only swiping
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deletedItem = countryList[position]


                countryList.removeAt(position)
                adapter.notifyItemRemoved(position)
                checkEmptyState()


                Snackbar.make(recyclerView, "$deletedItem deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO") {

                        countryList.add(position, deletedItem)
                        adapter.notifyItemInserted(position)
                        checkEmptyState()
                    }.show()
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


    private fun checkEmptyState() {
        if (countryList.isEmpty()) {
            tvEmpty.visibility = View.VISIBLE
        } else {
            tvEmpty.visibility = View.GONE
        }
    }


    inner class CountryAdapter(private val items: List<String>) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById(R.id.tvCountryName)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
            return ViewHolder(view)
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val country = items[position]
            holder.textView.text = country

            holder.itemView.setOnClickListener {
                Toast.makeText(this@ListActivity, "You selected: $country", Toast.LENGTH_SHORT).show()
            }
        }

        override fun getItemCount() = items.size
    }
}