package com.example.morningmosaic

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.morningmosaic.databinding.FragmentNewsBinding
import com.example.randompet.NewsAdapter
import com.google.android.material.button.MaterialButton
import okhttp3.Headers
import java.time.LocalDate
import java.time.format.DateTimeFormatter


/**
 * A simple [Fragment] subclass.
 * Use the [News.newInstance] factory method to
 * create an instance of this fragment.
 */
class News : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentNewsBinding
    private lateinit var rvNews: RecyclerView
    private lateinit var adapter_news: NewsAdapter

    var title= mutableListOf<String>()
    var author = mutableListOf<String>()
    var description = mutableListOf<String>()
    var url= mutableListOf<String>()
    var img = mutableListOf<String>()
    var category = mutableListOf<String>()
    var published_time = mutableListOf<String>()
    var selectedItem=String()
    private lateinit var button: Button

    @androidx.annotation.RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchNewsAPI(selectedItem)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        val view = binding.root
        val spinner = binding.categorySpinner
        // Initialize RecyclerView and set up the adapter
        rvNews = binding.newsList
        adapter_news = NewsAdapter(title, author, description, url, img, category, published_time)
        rvNews.adapter = adapter_news
        rvNews.layoutManager = LinearLayoutManager(requireContext())
        rvNews.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))


                // Fetch news data
                fetchNewsAPI(selectedItem)
                return view
            }

            @RequiresApi(Build.VERSION_CODES.O)
            private fun fetchNewsAPI(cat: String = "") {
                val client = AsyncHttpClient()
                val currentDate = LocalDate.now()
                val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val newsapi: String = if (cat.isNotBlank()) {
                    "http://api.mediastack.com/v1/news?access_key=9765790b07648fda326a77b745b78b06&date=$formattedDate&limit=20&categories=$cat"
                } else {
                    "http://api.mediastack.com/v1/news?access_key=9765790b07648fda326a77b745b78b06&date=$formattedDate&limit=20"
                }
                client[newsapi, object : JsonHttpResponseHandler() {

                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON
                    ) {
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

    @androidx.annotation.RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val spinner: Spinner = view.findViewById(R.id.categorySpinner)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_items,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedItem = parent?.getItemAtPosition(position).toString()

                // Fetch news data when spinner item is selected
                clearLists()
                fetchNewsAPI(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case where nothing is selected (optional)
                selectedItem = ""
            }
        })

    }

    }


