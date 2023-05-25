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

        var albumTrackId: TextView = itemView.findViewById(R.id.track_id)
        var albumTrackName: TextView = itemView.findViewById(R.id.track_name)
        var albumTrackDuration: TextView = itemView.findViewById(R.id.track_duration)

        val albumYear: TextView = itemView.findViewById(R.id.album_year)
        val performerName: TextView = itemView.findViewById(R.id.performer_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_description_recycler, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums

        if(position < 1) {
            holder.albumName.text = album.name
            holder.albumDescription.text = album.description
            holder.performerName.text = album.mainPerformer.name
            holder.albumYear.text = album.releaseDate.substring(0,4)
            Glide.with(holder.itemView.context)
                .load(album.cover)
                .centerCrop()
                .into(holder.albumCover)
        }
        else {
            holder.albumCover.visibility = View.GONE
            holder.albumName.visibility = View.GONE
            holder.albumDescription.visibility = View.GONE
            holder.performerName.visibility = View.GONE
            holder.albumYear.visibility = View.GONE
        }

        if(album.track.isNotEmpty()) {
            holder.albumTrackId.text = album.track[position].id.toString()
            holder.albumTrackName.text = album.track[position].name
            holder.albumTrackDuration.text = album.track[position].duration
        }

    }

    override fun getItemCount(): Int {
        if (albums.track.isEmpty()) {
            return 1
        }
        return  albums.track.size
    }

}