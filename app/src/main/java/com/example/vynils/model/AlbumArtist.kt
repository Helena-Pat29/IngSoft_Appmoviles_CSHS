package com.example.vynils.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

import com.example.vynils.genre.Genre
import com.example.vynils.recordlabel.RecordLabel
@Parcelize
data class AlbumArtist(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: Genre,
    val recordLabel: RecordLabel,
) : Parcelable