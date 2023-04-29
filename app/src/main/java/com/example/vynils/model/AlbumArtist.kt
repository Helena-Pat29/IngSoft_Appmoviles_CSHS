package com.example.vynils.model

import com.example.vynils.genre.Genre
import com.example.vynils.recordlabel.RecordLabel

data class AlbumArtist(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: Genre,
    val recordLabel: RecordLabel,
)