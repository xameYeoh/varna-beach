package com.getman.varnabeach.data

import android.app.Application
import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.getman.varnabeach.data.VolleyHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class VolleyHelper @Inject constructor(@ApplicationContext private val context: Context) {
    private var queue: RequestQueue? = null
    val requestQueue: RequestQueue
        get() {
            if (queue == null) {
                queue = Volley.newRequestQueue(context)
            }
            return queue?
        }

    fun <T> addToRequestQueue(request: Request<T>?) {
        requestQueue.add(request)
    }
}