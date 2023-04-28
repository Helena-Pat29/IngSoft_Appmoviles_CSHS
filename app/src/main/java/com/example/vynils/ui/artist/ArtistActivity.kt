package com.example.vynils.ui.artist

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vynils.R

class ArtistActivity : AppCompatActivity() {
    private val viewModel: ArtistViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)

        val toolbar: Toolbar = findViewById(R.id.artist_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Artists"
        // Initialize RecyclerView and its adapter
        val recyclerView: RecyclerView = findViewById(R.id.artist_recycler_view)
        val adapter = ArtistAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Observe the artists LiveData and update the UI when the data changes
        viewModel.artists.observe(this) { artists ->
            Log.d("ArtistActivity", "Artists updated: $artists")
            adapter.updateArtists(artists)
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