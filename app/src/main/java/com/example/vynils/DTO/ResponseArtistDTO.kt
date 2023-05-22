package com.example.vynils.DTO
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ResponseArtistDTO(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    val albums: List<AlbumArtistDTO>,
    val performerPrizes: List<PerformerPrizeDTO>
)
@Parcelize
data class AlbumArtistDTO(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String
) : Parcelable

@Parcelize
data class PerformerPrizeDTO(
    val id: Int,
    val premiationDate: String
) : Parcelable