package com.example.vynils.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class ResponseAlbumDTO(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String,
    val tracks: List<TrackDTO>,
    val performers: List<PerformerDTO>,
    val comments: List<CommentDTO>
)

@Parcelize
data class TrackDTO(
    val id: Int,
    val name: String,
    val duration: String
) : Parcelable

data class PerformerDTO(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String?,
    val creationDate: String?
)

data class CommentDTO(
    val id: Int,
    val description: String,
    val rating: Int
)
