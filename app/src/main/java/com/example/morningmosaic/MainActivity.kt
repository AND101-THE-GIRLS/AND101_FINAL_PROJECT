package com.example.morningmosaic
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.morningmosaic.databinding.ActivityMainBinding
import com.example.randompet.NewsAdapter
import okhttp3.Headers
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {
    //Variables for News API




    private lateinit var rvNews: RecyclerView
    private lateinit var rvNews_2: RecyclerView
    private lateinit var adapter_news: NewsAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    @androidx.annotation.RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.activity_main_nav_host_frag)
        setupWithNavController(binding.bottomNavigationView, navController)

        // Handle navigation item selected listener for the BottomNavigationView

        getHoroscope()
    }

    private fun getHoroscope() {
        val client = AsyncHttpClient()
        //there needs to be something that gets today's date
        //there needs to be something that gets the user's sign
        client["https://newastro.vercel.app/aries?date=2022-04-20&lang=en", object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.d("DEBUG OBJECT", json.jsonObject.toString())

                var info = json.jsonObject.getString("horoscope")
                Log.d("API Desc", info)
                Log.d("API", "API worked")


            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable
            ) {
                Log.d("API FAIL", response)
            }

        }]
    }

    private fun computeSign(month: Int, day: Int): String {
        // Add error handling for invalid input such as dates that do not exist
        if (month == 1) {
            if (day < 20) {
                return "Capricorn"
            } else if (day >= 20) {
                return "Aquarius"
            }
        } else if (month == 2) {
            if (day < 19) {
                return "Aquarius"
            } else if (day >= 19) {
                return "Pisces"
            }
        } else if (month == 3) {
            if (day < 21) {
                return "Pisces"
            } else if (day >= 21) {
                return "Aries"
            }
        } else if (month == 4) {
            if (day < 20) {
                return "Aries"
            } else if (day >= 20) {
                return "Taurus"
            }
        } else if (month == 5) {
            if (day < 21) {
                return "Taurus"
            } else if (day >= 21) {
                return "Gemini"
            }
        } else if (month == 6) {
            if (day < 21) {
                return "Gemini"
            } else if (day >= 21) {
                return "Cancer"
            }
        } else if (month == 7) {
            if (day < 23) {
                return "Cancer"
            } else if (day >= 23) {
                return "Leo"
            }
        } else if (month == 8) {
            if (day < 23) {
                return "Leo"
            } else if (day >= 23) {
                return "Virgo"
            }
        } else if (month == 9) {
            if (day < 23) {
                return "Virgo"
            } else if (day >= 23) {
                return "Libra"
            }
        } else if (month == 10) {
            if (day < 23) {
                return "Libra"
            } else if (day >= 23) {
                return "Scorpio"
            }
        } else if (month == 11) {
            if (day < 22) {
                return "Scorpio"
            } else if (day >= 22) {
                return "Sagittarius"
            }
        } else if (month == 12) {
            if (day < 22) {
                return "Sagittarius"
            } else if (day >= 22) {
                return "Capricorn"
            }
        }
        // Handle invalid input by returning "Unknown" or an appropriate message.
        return "Unknown"
    }



}


