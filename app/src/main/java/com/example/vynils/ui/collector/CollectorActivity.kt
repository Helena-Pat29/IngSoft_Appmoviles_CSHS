package com.example.vynils.ui.collector

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vynils.R

class CollectorActivity : AppCompatActivity() {
    private val viewModel: CollectorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collector)

        val toolbar: Toolbar = findViewById(R.id.collector_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Collectors"

        // Initialize RecyclerView and its adapter
        val recyclerView: RecyclerView = findViewById(R.id.collector_recycler_view)
        val adapter = CollectorAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Observe the collectors LiveData and update the UI when the data changes
        viewModel.collectors.observe(this) { collectors ->
            Log.d("CollectorActivity", "Collectors updated: $collectors")
            adapter.updateCollectors(collectors)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
