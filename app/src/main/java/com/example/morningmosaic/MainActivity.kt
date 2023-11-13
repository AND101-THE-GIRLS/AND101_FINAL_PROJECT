package com.example.morningmosaic


import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler


import okhttp3.Headers


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Find the ImageView in the Toolbar layout
        val toolbarImage = findViewById<ImageView>(R.id.toolbar_image)
        val categorySpinner = findViewById<Spinner>(R.id.category_spinner)
        val categories: MutableList<String> = ArrayList()
        categories.add("Category 1")
        categories.add("Category 2")
        // Add more categories as needed
//        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, categories)
//        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
//        categorySpinner.adapter = adapter

         // Initialize RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.newsRecyclerView)
        // Set up your RecyclerView adapter and layout manager here
        // Set up your RecyclerView adapter and layout manager here
        recyclerView.layoutManager = LinearLayoutManager(this)



        // Initialize TextView for displaying horoscope
        val horoscopeTextView = findViewById<TextView>(R.id.horoscopeTextView)

        getHoroscopeURL()
    }


    private fun getHoroscopeURL() {
        val client = AsyncHttpClient()

        client["https://aztro.sameerkumar.website?sign=$sign&day=$today", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Horoscope", "response successful$json")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Horoscope Error", errorResponse)
            }
        }]
    }

}