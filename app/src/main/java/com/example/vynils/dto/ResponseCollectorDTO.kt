package com.example.vynils.dto

data class ResponseCollectorDTO(
    val id: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val favoritePerformers: List<PerformerDTO>,
    val comments: List<CommentDTO>,
    val collectorAlbums: List<CollectorAlbumDTO>
)

data class CollectorAlbumDTO(
    val id: Int,
    val price: Int,
    val status: String,
    val album: ResponseAlbumDTO
)
