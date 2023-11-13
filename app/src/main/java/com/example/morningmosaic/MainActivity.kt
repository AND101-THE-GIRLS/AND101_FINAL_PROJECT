package com.example.morningmosaic


import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment;
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.morningmosaic.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.activity_manin_nav_host_frag)
        setupWithNavController(binding.bottomNavigationView, navController)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchNewsAPI(){
        val client = AsyncHttpClient()
        val currentDate = LocalDate.now()
        val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val newsapi = "http://api.mediastack.com/v1/news?access_key = 42c1d418f08524ae927959dece61b3eb&date=2020-01-01"

        client[newsapi, object : JsonHttpResponseHandler(){

            override fun onSuccess(statusCode:Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                val results = json.jsonObject.getJSONArray("results")

                for (i in 0 until results.length()) {
                    val pokemon = results.getJSONObject(i)
                    val pokemonNames = pokemon.getString("name")
                    val pokemonUrl = pokemon.getString("url")

                    // Fetch detailed information for each Pokémon

                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("", errorResponse)
            }
        }]
    }
}