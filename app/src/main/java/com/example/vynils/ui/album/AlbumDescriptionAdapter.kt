package com.example.vynils.ui.album

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vynils.R
import com.example.vynils.model.Album

class AlbumDescriptionAdapter(private var albums: Album) :
    RecyclerView.Adapter<AlbumDescriptionAdapter.AlbumViewHolder>() {
    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumName: TextView = itemView.findViewById(R.id.album_name)
        val albumDescription: TextView = itemView.findViewById(R.id.album_description)
        val albumCover: ImageView = itemView.findViewById(R.id.album_cover)
        //val albumYear: TextView = itemView.findViewById(R.id.album_year)
        val albumDuration: TextView = itemView.findViewById(R.id.track_duration)
        val performerName: TextView = itemView.findViewById(R.id.performer_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_description_recycler, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums
        holder.albumName.text = album.name
        holder.albumDescription.text = album.description
        holder.performerName.text = album.mainPerformer.name
        //holder.albumYear.text = album.releaseDate
        holder.albumDuration.text = album.track[1].duration
        Glide.with(holder.itemView.context)
            .load(album.cover)
            .centerCrop()
            .into(holder.albumCover)
    }

    fun addDuration(album: Album): String {
        val totalDuration = "00:00:00"
        //val ldt = LocalTime.parse(totalDuration)
        //for (track in album.track ){
        //val ldt1 = LocalTime.parse(track.duration)
        //}
        return "0"
    }

    override fun getItemCount(): Int = 1

    fun updateAlbums(newAlbums: Album) {
        albums = newAlbums
        notifyDataSetChanged()
    }
}