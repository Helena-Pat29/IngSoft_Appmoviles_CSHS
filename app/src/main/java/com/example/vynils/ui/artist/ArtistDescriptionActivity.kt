package com.example.vynils.ui.artist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vynils.R
import com.example.vynils.model.Album
import com.example.vynils.model.Artist
import com.example.vynils.ui.album.AlbumActivity
import com.example.vynils.ui.album.AlbumViewModel

class ArtistDescriptionActivity : AppCompatActivity() {
    private val viewModel: AlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_artist)

        var artistList : Artist = intent.getParcelableExtra(ArtistActivity.NEXT_SCREEN)!!

        val toolbar: Toolbar = findViewById(R.id.artist_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = artistList.name.toString()

        var recyclerView: RecyclerView = findViewById(R.id.artist_details)
        var adapter = ArtistDescriptionAdapter(artistList)
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