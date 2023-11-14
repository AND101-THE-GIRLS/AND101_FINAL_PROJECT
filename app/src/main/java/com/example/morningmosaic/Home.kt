package com.example.morningmosaic

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.morningmosaic.databinding.FragmentHomeBinding
import com.example.randompet.NewsAdapter_Home
import okhttp3.Headers
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar


/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentHomeBinding
    private lateinit var rvNews: RecyclerView
    private lateinit var adapter_news: NewsAdapter_Home
    var title= mutableListOf<String>()
    var author = mutableListOf<String>()
    var description = mutableListOf<String>()
    var url= mutableListOf<String>()
    var img = mutableListOf<String>()
    var category = mutableListOf<String>()
    var published_time = mutableListOf<String>()
    @androidx.annotation.RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fetchNewsAPI()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view =binding.root
        // Initialize RecyclerView and set up the adapter
        rvNews = binding.newsRecycleView

        adapter_news = NewsAdapter_Home(title, author, description, url, img, category, published_time)
        rvNews.adapter = adapter_news
        rvNews.layoutManager = LinearLayoutManager(requireContext())
        rvNews.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))


        fetchNewsAPI()
        wrapper_function()
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchNewsAPI(){
        val client = AsyncHttpClient()
        val currentDate = LocalDate.now()
        val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val newsapi= "http://api.mediastack.com/v1/news?access_key=9765790b07648fda326a77b745b78b06&date=$formattedDate&limit=10"


        client[newsapi, object : JsonHttpResponseHandler(){

            override fun onSuccess(statusCode:Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                val results = json.jsonObject.getJSONArray("data")

                for (i in 0 until results.length()) {
                    val newsItem = results.getJSONObject(i)

                    // Extract information from the JSON object
                    author.add(newsItem.getString("author"))
                    title.add(newsItem.getString("title"))
                    description.add(newsItem.getString("description"))
                    url.add(newsItem.getString("url"))
                    img.add(newsItem.getString("image"))
                    category.add(newsItem.getString("category"))
                    published_time.add(newsItem.getString("published_at"))

                    // Example: Log the title of each news item
                    Log.d("News Title", "News API work")
                    adapter_news.notifyDataSetChanged()
                }

            }
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("News", errorResponse)
            }
        }]

    }
    private fun clearLists() {
        title.clear()
        author.clear()
        description.clear()
        url.clear()
        img.clear()
        category.clear()
        published_time.clear()
        adapter_news.notifyDataSetChanged()
    }

    private fun getHoroscope(sign: String) {
        //get the current date
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Months are 0-based, so add 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val currentDate = "$year-${String.format("%02d", month)}-${String.format("%02d", day)}"
        val client = AsyncHttpClient()
        var info = ""
        //there needs to be something that gets today's date
        client["https://newastro.vercel.app/$sign?date=$currentDate&lang=en", object :
            JsonHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                //If the horoscope API sucessfully retrieves data put the horoscope into "info" and log it
                Log.d("DEBUG OBJECT", json.jsonObject.toString())
                var info = json.jsonObject.getString("horoscope")
                Log.d("API Desc", info)
                Log.d("API", "API worked")
                when {
                    !info.isEmpty() && info != null -> {
                        Log.d("horoscope", info)
                        val textView = view?.findViewById<TextView>(R.id.horoscopeText)
                        textView?.text = info
                        val textView2 = view?.findViewById<TextView>(R.id.horoscopeTextView)
                        textView2?.text = info
                    }
                }
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



    @androidx.annotation.RequiresApi(Build.VERSION_CODES.O)
    private fun wrapper_function() {
        //wrapper function for when button is clicked
        val today = LocalDate.now()

        // Get the month and day
        val month = today.monthValue
        val day = today.dayOfMonth
        var sign = computeSign(month, day)
        getHoroscope(sign)
        }

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

