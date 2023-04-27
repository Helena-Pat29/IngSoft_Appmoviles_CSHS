package com.example.vynils.repository

import com.example.vynils.genre.Genre
import com.example.vynils.model.Album
import com.example.vynils.recordlabel.RecordLabel

class AlbumRepository {
    // Function to fetch albums (test data for now)
    fun fetchAlbums(): List<Album> {
        return listOf(
            Album(
                id = 1,
                name = "Album 1",
                cover = "https://example.com/cover1.jpg",
                releaseDate = "2022-01-01",
                description = "Description for Album 1",
                genre = Genre.CLASSICAL,
                recordLabel = RecordLabel.SONY
            ),
            Album(
                id = 2,
                name = "Album 2",
                cover = "https://example.com/cover2.jpg",
                releaseDate = "2022-02-01",
                description = "Description for Album 2",
                genre = Genre.ROCK,
                recordLabel = RecordLabel.EMI
            )
            // Add more test data as needed
        )
    }
}
