package com.example.vynils.ui.collector

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vynils.R
import com.example.vynils.model.Collector

class CollectorDescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_collector)

        val collectorList : Collector = intent.getParcelableExtra(CollectorActivity.NEXT_SCREEN)!!

        val toolbar: Toolbar = findViewById(R.id.album_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = collectorList.name.toString()

        val recyclerView: RecyclerView = findViewById(R.id.collector_details)
        val adapter = CollectorDescriptionAdapter(collectorList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

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