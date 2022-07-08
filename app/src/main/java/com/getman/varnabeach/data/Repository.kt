package com.getman.varnabeach.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.android.volley.NetworkResponse
import com.android.volley.Response
import com.getman.varnabeach.BuildConfig
import com.getman.varnabeach.lifecycle.OnChangeConditionListener
import com.getman.varnabeach.room.Beach
import com.getman.varnabeach.room.BeachDAO
import com.getman.varnabeach.util.ErrorHandler
import org.json.JSONException
import org.json.JSONObject
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class Repository @Inject constructor(
    beachDAO: BeachDAO,
    private val helper: VolleyHelper,
    private val errorHandler: ErrorHandler
) {
    val allBeaches: LiveData<List<Beach>> = beachDAO.allOrderByName
    private val beachConditions: MutableMap<String, String> = HashMap()
    val currentBeachConditions get(): Map<String, String> = beachConditions

    companion object {
        private const val URL_GET = "https://api.stormglass.io/v2/weather/point"
        private const val PARAMS = "windSpeed,waveHeight,airTemperature,waterTemperature"
        private const val KEY = BuildConfig.STORMGLASS_KEY
    }

    fun requestNewConditions(lat: String, lng: String, listener: OnChangeConditionListener) {
        val lastHour = Instant.now().truncatedTo(ChronoUnit.HOURS).toString()
        val url = URL_GET +
                "?lat=" + lat +
                "&lng=" + lng +
                "&params=" + PARAMS +
                "&start=" + lastHour
        val request: CacheRequest = object : CacheRequest(
            Method.GET,
            url,
            Response.Listener { response: NetworkResponse ->
                try {
                    updateCurrentConditions(response)
                    listener.onChange()
                } catch (je: JSONException) {
                    Log.d("JSON", je.toString())
                }
            }, Response.ErrorListener { errorHandler.displayError() }) {
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["Content-Type"] = "application/json"
                params["Authorization"] = KEY
                return params
            }
        }
        helper.addToRequestQueue(request)
    }

    @Throws(JSONException::class)
    private fun updateCurrentConditions(response: NetworkResponse) {
        val jsonResponse = JSONObject(String(response.data))
        val currentHourConditions = getCurrentHourConditions(jsonResponse)
        val names = currentHourConditions!!.names()
        for (i in 0 until names.length()) {
            val key = names.getString(i)
            val value = currentHourConditions[key]
            if (value is JSONObject) {
                val averageValue = average(value)
                beachConditions[key] = averageValue.toString()
            }
        }
    }

    @Throws(JSONException::class)
    private fun average(condition: JSONObject): Double {
        var sum = 0.0
        val names = condition.names()
        for (i in 0 until names.length()) {
            sum += condition.getDouble(names.getString(i))
        }
        return Math.floor(sum / names.length() * 100) / 100
    }

    private fun getCurrentHourConditions(response: JSONObject): JSONObject? {
        var result: JSONObject? = null
        try {
            val hours = response.getJSONArray("hours")
            result = hours.getJSONObject(0)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return result
    }
}