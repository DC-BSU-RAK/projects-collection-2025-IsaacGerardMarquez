package com.example.vianrechelacademyincorporatedmulti_pageapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// I created this RecyclerView Adapter to display a list of Program items for the school.

class ProgramAdapter(private val programs: List<Program>) :
    RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder>() {

    // This is to keep track of expanded items by their position.
    private val expandedPositionSet = mutableSetOf<Int>()

    // This is a ViewHolder class to hold references to the program title and description TextViews.
    inner class ProgramViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.programTitle)
        val description: TextView = view.findViewById(R.id.programDescription)
    }

    // This inflates the item layout and create a ViewHolder instance.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_program, parent, false)
        return ProgramViewHolder(view)
    }

    // This binds the program data to the ViewHolder and handle click events.
    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        val program = programs[position]
        holder.title.text = program.title
        holder.description.text = program.description

        // This shows or hide the description based on whether the item is expanded.
        val isExpanded = expandedPositionSet.contains(position)
        holder.description.visibility = if (isExpanded) View.VISIBLE else View.GONE

        // This is a click listener toggle to expand or collapse the description.
        holder.itemView.setOnClickListener {
            if (isExpanded) {
                expandedPositionSet.remove(position)
            } else {
                expandedPositionSet.add(position)
            }
            notifyItemChanged(position)
        }

        // This is a long press listener that opens the ProgramDetailActivity with program details passed via Intent extras.
        holder.itemView.setOnLongClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ProgramDetailActivity::class.java).apply {
                putExtra("programTitle", program.title)
                putExtra("programDescription", program.description)
            }
            context.startActivity(intent)
            true
        }
    }

    // This returns the total number of programs to display.
    override fun getItemCount(): Int = programs.size
}
