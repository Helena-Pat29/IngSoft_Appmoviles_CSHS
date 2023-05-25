package com.example.vynils.ui.collector

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vynils.R
import com.example.vynils.model.Collector

class CollectorAdapter(private var collectors: List<Collector>) :
    RecyclerView.Adapter<CollectorViewHolder>() {
    private var onClickListener: OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.collector_item, parent, false)
        return CollectorViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        val collector = collectors[position]
        holder.bind(collectors[position])
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, collector )
            }
        }
    }

    override fun getItemCount(): Int {
        return collectors.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCollectors(newCollectors: List<Collector>) {
        collectors = newCollectors
        notifyDataSetChanged()
    }

    // A function to bind the onclickListener.
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    // onClickListener Interface
    interface OnClickListener {
        fun onClick(position: Int, model: Collector)
    }
}

class CollectorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val collectorName: TextView = itemView.findViewById(R.id.collector_name)
    private val collectorTelephone: TextView = itemView.findViewById(R.id.collector_telephone)
    private val collectorEmail: TextView = itemView.findViewById(R.id.collector_email)
    private val performerImage: ImageView = itemView.findViewById(R.id.performer_image)

    fun bind(collector: Collector) {
        val firstPerformer = collector.favoritePerformers.firstOrNull()

        collectorName.text = collector.name
        collectorTelephone.text = collector.telephone
        collectorEmail.text = collector.email

        firstPerformer?.image?.let { image ->
            Glide.with(itemView)
                .load(image)
                .error(R.drawable.ic_launcher_background)
                .into(performerImage)
        } ?: performerImage.setImageResource(R.drawable.ic_launcher_background)
    }
}
