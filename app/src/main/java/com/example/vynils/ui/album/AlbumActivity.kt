package com.example.vynils.ui.album

import com.example.vynils.ui.album.AlbumViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.MenuItem
import com.example.vynils.R
import android.util.Log

class AlbumActivity : AppCompatActivity() {
    private val viewModel: AlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        val toolbar: Toolbar = findViewById(R.id.album_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Albums"
        // Initialize RecyclerView and its adapter
        val recyclerView: RecyclerView = findViewById(R.id.album_recycler_view)
        val adapter = AlbumAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Observe the albums LiveData and update the UI when the data changes
        viewModel.albums.observe(this) { albums ->
            Log.d("AlbumActivity", "Albums updated: $albums")
            adapter.updateAlbums(albums)
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
