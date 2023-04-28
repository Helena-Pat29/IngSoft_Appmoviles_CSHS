package com.example.vynils.DTO

data class ResponseArtistDTO(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    val albumsArtists: List<AlbumArtistDTO>,
    val performerPrize: List<PerformerPrizeDTO>
)

data class AlbumDTO(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String
)

data class PerformerPrizeDTO(
    val id: Int,
    val premiationDate: String
)

data class AlbumArtistDTO(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String
)