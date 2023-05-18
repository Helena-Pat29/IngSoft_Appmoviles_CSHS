package com.example.vynils.ui.album

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.vynils.DTO.CreateAlbumDTO
import com.example.vynils.R

class CreateAlbumActivity : AppCompatActivity() {
    private val viewModel: CreateAlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_album)

        val submitButton: Button = findViewById(R.id.submit_button)

        submitButton.setOnClickListener {
            val name = findViewById<EditText>(R.id.name_field).text.toString()
            val cover = findViewById<EditText>(R.id.cover_field).text.toString()
            val releaseDate = findViewById<EditText>(R.id.release_date_field).text.toString()
            val description = findViewById<EditText>(R.id.description_field).text.toString()
            val genre = findViewById<EditText>(R.id.genre_field).text.toString()
            val recordLabel = findViewById<EditText>(R.id.record_label_field).text.toString()

            val album = CreateAlbumDTO(name, cover, releaseDate, description, genre, recordLabel)
            viewModel.createAlbum(album)
        }
    }
}
