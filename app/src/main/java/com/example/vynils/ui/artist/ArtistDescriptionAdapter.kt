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

class ArtistDescriptionAdapter(private var artists: Artist) :
    RecyclerView.Adapter<ArtistDescriptionAdapter.ArtistViewHolder>() {
    class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val artistName: TextView = itemView.findViewById(R.id.artist_name)
        val artistDescription: TextView = itemView.findViewById(R.id.artist_description)
        val artistImage: ImageView = itemView.findViewById(R.id.artist_image)
        val artistBithDate: TextView = itemView.findViewById(R.id.artist_birthdate)
        val albumName:TextView=itemView.findViewById(R.id.albumartist_name)
        val albumDescription:TextView=itemView.findViewById(R.id.albumartist_description)
        val albumCover:ImageView=itemView.findViewById(R.id.albumartist_cover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.artist_description_recycler, parent, false)
        return ArtistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = artists
        if (position <1){
        holder.artistName.text = artist.name
        holder.artistDescription.text = artist.description
        holder.artistBithDate.text = artist.birthDate
        Glide.with(holder.itemView.context)
            .load(artist.image)
            .centerCrop()
            .into(holder.artistImage)
        }
        holder.albumName.text=artist.albums[position].name.toString()
        holder.albumDescription.text=artist.albums[position].description
        Glide.with(holder.itemView.context)
            .load(artist.albums[position].cover)
            .centerCrop()
            .into(holder.albumCover)
    }

    override fun getItemCount(): Int {
        if (artists.albums.size == 0) {
            return 1
        }
        return artists.albums.size
    }

    fun updateArtists(newArtists: Artist) {
        artists = newArtists
        notifyDataSetChanged()
    }
}