package com.example.vynils.ui.album

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.text.format.Time
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import com.example.vynils.R
import com.example.vynils.model.Album
import com.squareup.picasso.Picasso
import com.bumptech.glide.Glide
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Date
import java.util.Calendar


class AlbumAdapter(private var albums: List<Album>) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {
    private var onClickListener: OnClickListener? = null
    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumName: TextView = itemView.findViewById(R.id.album_name)
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
        holder.performerName.text = album.mainPerformer.name
        Glide.with(holder.itemView.context)
            .load(album.cover)
            .centerCrop()
            .into(holder.albumCover)
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, album )
            }
        }
    }

    fun addDuration(album: Album): String {
        val totalDuration = "00:00:00"
        //val ldt = LocalTime.parse(totalDuration)
        //for (track in album.track ){
            //val ldt1 = LocalTime.parse(track.duration)
        //}
        return "0"
    }

    override fun getItemCount(): Int = albums.size

    fun updateAlbums(newAlbums: List<Album>) {
        albums = newAlbums
        notifyDataSetChanged()
    }

    // A function to bind the onclickListener.
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    // onClickListener Interface
    interface OnClickListener {
        fun onClick(position: Int, model: Album)
    }
}
