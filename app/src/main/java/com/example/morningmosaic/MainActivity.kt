package com.example.morningmosaic
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.morningmosaic.databinding.ActivityMainBinding
import okhttp3.Headers
import java.util.Calendar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.activity_main_nav_host_frag)
        setupWithNavController(binding.bottomNavigationView, navController)

        val horoscopeButton = findViewById<Button>(R.id.horoscope_button)
        horoscopeButton.setOnClickListener{
            //when the button is clicked, all functions wrapped together to prevent errors
            wrapper_function()
        }
    }
    private fun wrapper_function(){
        //wrapper function for when button is clicked
        var day = findViewById<EditText>(R.id.dayEditText).text.toString().toInt()
        var month = findViewById<EditText>(R.id.monthEditText).text.toString().toInt()
        var sign = computeSign(month, day)
        getHoroscope(sign)
        //maybe this should also get the news


    }
    private fun getHoroscope(sign: String) {
        //get the current date
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Months are 0-based, so add 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val currentDate = "$year-${String.format("%02d", month)}-${String.format("%02d", day)}"
        val client = AsyncHttpClient()
        //there needs to be something that gets today's date
        client["https://newastro.vercel.app/$sign?date=$currentDate&lang=en", object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                //If the horoscope API sucessfully retrieves data put the horoscope into "info" and log it
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
                //If the Horoscope API fails, log it
                Log.d("API FAIL", response)
            }

        }]
    }

    private fun computeSign(month: Int, day: Int): String {
        //function takes day and month as input and computes the user's zodiac sign
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