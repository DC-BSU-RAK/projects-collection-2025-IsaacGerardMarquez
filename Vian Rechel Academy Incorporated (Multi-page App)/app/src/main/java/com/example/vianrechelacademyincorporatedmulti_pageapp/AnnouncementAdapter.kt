package com.example.vianrechelacademyincorporatedmulti_pageapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// I created this adapter for displaying a list of announcements in a RecyclerView.
class AnnouncementAdapter(
    private val items: List<Announcement>
) : RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder>() {

    // I created this ViewHolder class that holds the views for each announcement item.
    inner class AnnouncementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.icon)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val description: TextView = itemView.findViewById(R.id.description)

        // This binds the data to the views and sets click listener to open detail activity.
        fun bind(announcement: Announcement) {
            title.text = announcement.title
            description.text = announcement.description

            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, AnnouncementDetailActivity::class.java).apply {
                    putExtra("title", announcement.title)
                    putExtra("description", announcement.description)
                }
                context.startActivity(intent)
            }
        }
    }

    // This inflates the layout for each item in the RecyclerView.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.announcement_item, parent, false)
        return AnnouncementViewHolder(view)
    }

    // This binds the data to the ViewHolder at the given position.
    override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
        holder.bind(items[position])
    }

    // This returns the total number of items.
    override fun getItemCount(): Int = items.size
}
