package com.example.vianrechelacademyincorporatedmulti_pageapp

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// This is a Data class representing a single task item.
data class Task(val title: String, val description: String)

// This is an Adapter class for populating the RecyclerView with a list of Task objects.
class TaskAdapter(private val taskList: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    // I created this ViewHolder class to hold the views for each item in the list.
    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.textViewTaskTitle)
        val description: TextView = view.findViewById(R.id.textViewTaskDesc)
    }

    // This inflates the layout for each item in the RecyclerView.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    // This binds the data from the task list to the views in the ViewHolder.
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.title.text = task.title
        holder.description.text = task.description

        // This is to set a click listener on the whole item view to show the AlertDialog.
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            // When clicked, an AlertDialog shows the full task details.
            AlertDialog.Builder(context)
                .setTitle(task.title)
                .setMessage(task.description)
                .setPositiveButton("Close") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    // This returns the total number of tasks in the list.
    override fun getItemCount(): Int = taskList.size
}
