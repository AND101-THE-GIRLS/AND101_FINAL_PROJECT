package com.example.morningmosaic

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //views would need to match this
        val goButton = findViewById<Button>(R.id.name_of_button)
        goButton.setOnClickListener {
            getContent()

        }
    }
    private fun getContent() {

    }
}