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

class NewsAdapter (private val title: List<String>, private val author: List<String>, private val description:List<String>, private val url:List<String>, private val img:List<String>, private val category:List<String>, private val published_time:List<String>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var Image: ImageView
        var Category: TextView
        var Description: TextView
        var Author: TextView
        var Title: TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            Image = view.findViewById(R.id.news_img)
            Title = view.findViewById(R.id.title)
            Category = view.findViewById(R.id.category)
            Description = view.findViewById((R.id.description))
            Author = view.findViewById(((R.id.author)))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(img[position])
            .override(141,119)
            .into(holder.Image)
        holder.Title.text= title[position].uppercase()
        holder.Category.text="Cateogry: " + category[position]
        holder.Author.text="Source: " + author[position]
        holder.Description.text = description[position]
        val dateTime = LocalDateTime.parse(published_time[position], DateTimeFormatter.ISO_DATE_TIME)
        // Extract the time part
        val timePart = dateTime.toLocalTime()

        holder.Image.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Published at: $timePart", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = title.size
}