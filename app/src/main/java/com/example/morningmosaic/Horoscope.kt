package com.example.morningmosaic

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import java.util.Calendar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Horoscope.newInstance] factory method to
 * create an instance of this fragment.
 */
class Horoscope : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


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



    private fun wrapper_function() {
        //wrapper function for when button is clicked
        var day = view?.findViewById<EditText>(R.id.day)?.text.toString()
        var month = view?.findViewById<EditText>(R.id.month)?.text.toString()
        Log.d("day", day)
        Log.d("month", month)
        if (month == null || day == null){
            Log.d("text", "no text input")
        } else {
            Log.d("text", "text input")
            var int_month = month.toInt()
            var int_day = day.toInt()
            var sign = computeSign(int_month, int_day)
            var horoscope = getHoroscope(sign)


        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_horoscope, container, false)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Horoscope.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Horoscope().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var button = view.findViewById<Button>(R.id.horoscope_button)


        button.setOnClickListener(){
            wrapper_function()
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

}