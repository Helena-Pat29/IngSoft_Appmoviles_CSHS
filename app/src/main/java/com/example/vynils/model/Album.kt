package com.example.vynils.model
import android.os.Parcelable
import com.example.vynils.DTO.TrackDTO
import com.example.vynils.genre.Genre
import com.example.vynils.recordlabel.RecordLabel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: String, // String is used for simplicity; you can later use a date library to handle dates
    val description: String,
    val genre: Genre,
    val recordLabel: RecordLabel,
    val track: List<TrackDTO>,
    val mainPerformer: Performer,
) : Parcelable