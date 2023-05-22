package com.example.vynils.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Collector(
    val id: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val comments: List<Comment>,
    val favoritePerformers: List<Performer>,
    val collectorAlbums: List<CollectorAlbum>
) : Parcelable

@Parcelize
data class Comment(
    val id: Int,
    val description: String,
    val rating: Int
) : Parcelable

@Parcelize
data class CollectorAlbum(
    val id: Int,
    val price: Int,
    val status: String
) : Parcelable
