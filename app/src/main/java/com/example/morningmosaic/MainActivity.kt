package com.example.morningmosaic


import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment;
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morningmosaic.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(Home())
                R.id.news -> replaceFragment(News())
                R.id.horoscope -> replaceFragment(Horoscope())

                else ->{

                }
            }
            true
        }
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)

        // Find the ImageView in the Toolbar layout
//        val toolbarImage = findViewById<ImageView>(R.id.toolbar_image)
//        val categorySpinner = findViewById<Spinner>(R.id.category_spinner)
////        val categories: MutableList<String> = ArrayList()
//        categories.add("Category 1")
//        categories.add("Category 2")
//        // Add more categories as needed
////        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, categories)
////        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
////        categorySpinner.adapter = adapter
//
//         // Initialize RecyclerView
//        val recyclerView = findViewById<RecyclerView>(R.id.newsRecyclerView)
//        // Set up your RecyclerView adapter and layout manager here
//        // Set up your RecyclerView adapter and layout manager here
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//
//
//        // Initialize TextView for displaying horoscope
//        val horoscopeTextView = findViewById<TextView>(R.id.horoscopeTextView)
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}