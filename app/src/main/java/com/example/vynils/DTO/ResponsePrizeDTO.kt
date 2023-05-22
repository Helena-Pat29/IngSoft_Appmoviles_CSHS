package com.example.vynils.DTO

data class ResponsePrizeDTO(
    val id: Int,
    val name: String,
    val organization: String,
    val description: String,
    val performerPrize: List<PerformerPrizeDTO>
)