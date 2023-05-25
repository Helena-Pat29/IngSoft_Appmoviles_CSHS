package com.example.vynils.model
import android.os.Parcelable
import com.example.vynils.dto.AlbumArtistDTO
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artist(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    val albums: List<AlbumArtistDTO>,
) : Parcelable