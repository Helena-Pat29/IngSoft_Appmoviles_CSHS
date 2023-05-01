package com.example.vynils.ui.artist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vynils.R
import com.example.vynils.model.Artist
import java.text.SimpleDateFormat
import java.util.Locale

class ArtistDescriptionAdapter(private var artist: Artist) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ARTIST = 0
        private const val VIEW_TYPE_ALBUM = 1
    }

    inner class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val artistName: TextView = itemView.findViewById(R.id.artist_name)
        val artistDescription: TextView = itemView.findViewById(R.id.artist_description)
        val artistImage: ImageView = itemView.findViewById(R.id.artist_image)
        val artistBirthDate: TextView = itemView.findViewById(R.id.artist_birthdate)
    }

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumName: TextView = itemView.findViewById(R.id.album_name)
        val albumDescription: TextView = itemView.findViewById(R.id.album_description)
        val albumCover: ImageView = itemView.findViewById(R.id.album_cover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ARTIST) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.artist_description_recycler, parent, false)
            ArtistViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.album_item, parent, false)
            AlbumViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ArtistViewHolder) {
            holder.artistName.text = artist.name
            holder.artistDescription.text = artist.description
            holder.artistBirthDate.text = formatDate(artist.birthDate)
            Glide.with(holder.itemView.context)
                .load(artist.image)
                .centerCrop()
                .into(holder.artistImage)
        } else if (holder is AlbumViewHolder) {
            val album = artist.albums[position - 1]
            holder.albumName.text = album.name
            holder.albumDescription.text = album.description
            Glide.with(holder.itemView.context)
                .load(album.cover)
                .centerCrop()
                .into(holder.albumCover)
        }
    }

    override fun getItemCount(): Int {
        return artist.albums.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_ARTIST
        } else {
            VIEW_TYPE_ALBUM
        }
    }

    fun updateArtists(newArtist: Artist) {
        artist = newArtist
        notifyDataSetChanged()
    }

    private fun formatDate(birthDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        val date = inputFormat.parse(birthDate)
        return if (date != null) outputFormat.format(date) else birthDate
    }
}
