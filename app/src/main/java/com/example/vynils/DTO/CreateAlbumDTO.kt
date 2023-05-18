package com.example.vynils.DTO

import org.json.JSONObject

data class CreateAlbumDTO(
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String
) {
    fun toJSON(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("name", name)
        jsonObject.put("cover", cover)
        jsonObject.put("releaseDate", releaseDate)
        jsonObject.put("description", description)
        jsonObject.put("genre", genre)
        jsonObject.put("recordLabel", recordLabel)
        return jsonObject
    }
}