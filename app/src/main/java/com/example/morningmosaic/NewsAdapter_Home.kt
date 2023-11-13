package com.example.randompet

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.morningmosaic.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewsAdapter_Home (private val title: List<String>, private val author: List<String>, private val description:List<String>, private val url:List<String>, private val img:List<String>, private val category:List<String>, private val published_time:List<String>) : RecyclerView.Adapter<NewsAdapter_Home.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //var Category: TextView
        var Description: TextView
        var Title: TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            Title = view.findViewById(R.id.title)
            Description = view.findViewById((R.id.description))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item_1, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Title.text= title[position].uppercase()
        holder.Description.text = description[position]
    }

    override fun getItemCount() = title.size
}