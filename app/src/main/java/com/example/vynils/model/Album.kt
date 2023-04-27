package com.example.vynils.model
import com.example.vynils.genre.Genre
import com.example.vynils.recordlabel.RecordLabel

data class Album(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: String, // String is used for simplicity; you can later use a date library to handle dates
    val description: String,
    val genre: Genre,
    val recordLabel: RecordLabel,
    val mainPerformer: Performer,
)
