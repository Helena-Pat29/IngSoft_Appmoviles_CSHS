package com.example.vynils.ui.collector

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vynils.R
import com.example.vynils.model.Collector

class CollectorDescriptionAdapter(private var collectors: Collector) :
    RecyclerView.Adapter<CollectorDescriptionAdapter.CollectorViewHolder>() {
    class CollectorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val collectorName: TextView = itemView.findViewById(R.id.collector_name)
        val collectorTel: TextView = itemView.findViewById(R.id.collector_telephone)
        val collectorEmail: TextView = itemView.findViewById(R.id.collector_email)

        var commentId: TextView = itemView.findViewById(R.id.comment_id)
        var commentDesc: TextView = itemView.findViewById(R.id.comment_description)
        var commentRating: TextView = itemView.findViewById(R.id.comment_rating)

        val performerId: TextView = itemView.findViewById(R.id.performer_id)
        val performerName: TextView = itemView.findViewById(R.id.performer_name)
        val performerImage: ImageView = itemView.findViewById(R.id.performer_image)

        val collectorAlbumId: TextView = itemView.findViewById(R.id.collector_album_id)
        val collectorAlbumPrice: TextView = itemView.findViewById(R.id.collector_album_price)
        val collectorAlbumStatus: TextView = itemView.findViewById(R.id.collector_album_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.collector_description_recycler, parent, false)
        return CollectorViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        val collector = collectors

        if(position < 1) {
            holder.collectorName.text = collector.name
            holder.collectorTel.text = collector.telephone
            holder.collectorEmail.text = collector.email
        }
        else {
            holder.collectorName.visibility = View.GONE
            holder.collectorTel.visibility = View.GONE
            holder.collectorEmail.visibility = View.GONE
        }

        if(collector.comments.isNotEmpty() && position <= collector.comments.size) {
            holder.commentId.text = collector.comments[position].id.toString()
            holder.commentDesc.text = collector.comments[position].description
            holder.commentRating.text = collector.comments[position].rating.toString()
        }

        if(collector.favoritePerformers.isNotEmpty() && position <= collector.favoritePerformers.size) {
            holder.performerId.text = collector.favoritePerformers[position].id.toString()
            holder.performerName.text = collector.favoritePerformers[position].name
            Glide.with(holder.itemView.context)
                .load(collector.favoritePerformers[position].image)
                .centerCrop()
                .into(holder.performerImage)
        }

        if(collector.collectorAlbums.isNotEmpty() && position <= collector.collectorAlbums.size) {
            holder.collectorAlbumId.text = collector.collectorAlbums[position].id.toString()
            holder.collectorAlbumPrice.text = collector.collectorAlbums[position].price.toString()
            holder.collectorAlbumStatus.text = collector.collectorAlbums[position].status
        }
    }

    override fun getItemCount(): Int {
        if (collectors.favoritePerformers.isEmpty() &&
            collectors.comments.isEmpty() &&
            collectors.collectorAlbums.isEmpty()) {
            return 1
        }
        when {
            collectors.favoritePerformers.size >= collectors.comments.size && collectors.favoritePerformers.size >= collectors.collectorAlbums.size -> return collectors.favoritePerformers.size
            collectors.comments.size >= collectors.favoritePerformers.size && collectors.comments.size >= collectors.collectorAlbums.size -> return collectors.comments.size
            else -> return collectors.collectorAlbums.size
        }
    }
}