package com.example.vynils.ui.album

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vynils.R
import com.example.vynils.model.Album
import com.squareup.picasso.Picasso
import com.bumptech.glide.Glide


class AlbumAdapter(private var albums: List<Album>) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumName: TextView = itemView.findViewById(R.id.album_name)
        val albumDescription: TextView = itemView.findViewById(R.id.album_description)
        val albumCover: ImageView = itemView.findViewById(R.id.album_cover)
        val performerName: TextView = itemView.findViewById(R.id.performer_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums[position]
        holder.albumName.text = album.name
        holder.albumDescription.text = album.description
        holder.performerName.text = album.mainPerformer.name
        Glide.with(holder.itemView.context)
            .load(album.cover)
            .centerCrop()
            .into(holder.albumCover)
    }

    override fun getItemCount(): Int = albums.size

    fun updateAlbums(newAlbums: List<Album>) {
        albums = newAlbums
        notifyDataSetChanged()
    }
}
