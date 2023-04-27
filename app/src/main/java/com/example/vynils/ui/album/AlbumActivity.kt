package com.example.vynils.ui.album

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vynils.R

class AlbumActivity : AppCompatActivity() {
    private val viewModel: AlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        // Initialize RecyclerView and its adapter
        val recyclerView: RecyclerView = findViewById(R.id.album_recycler_view)
        val adapter = AlbumAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Observe the albums LiveData and update the UI when the data changes
        viewModel.albums.observe(this) { albums ->
            adapter.updateAlbums(albums)
        }
    }
}
