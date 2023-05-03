package com.example.vynils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import com.example.vynils.DTO.ResponseCollectorDTO
import com.example.vynils.repository.CollectorRepository
import com.example.vynils.ui.album.AlbumActivity
import com.example.vynils.ui.artist.ArtistActivity
import com.example.vynils.ui.collector.CollectorActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val getAlbumButton: Button = findViewById(R.id.fetch_albums_button)
        val getArtistButton: Button = findViewById(R.id.fetch_artist_button)
        val getCollectorsButton: Button = findViewById(R.id.fetch_collectors_button)


        getAlbumButton.setOnClickListener {
            val albumList = Intent(this, AlbumActivity::class.java)
            startActivity(albumList)
        }

        getArtistButton.setOnClickListener {
            val artistList = Intent(this, ArtistActivity::class.java)
            startActivity(artistList)

        }

        getCollectorsButton.setOnClickListener {
            val collectorList = Intent(this, CollectorActivity::class.java)
            startActivity(collectorList)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.layout_menu, menu)
        supportActionBar!!.title = "Inicio"
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                // Create an intent with a destination of the other Activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

