package com.getman.varnabeach.data

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class VolleyHelper @Inject constructor(@ApplicationContext private val context: Context) {
    private var queue: RequestQueue? = null
    private val requestQueue: RequestQueue
        get() {
            if (queue == null) {
                queue = Volley.newRequestQueue(context)
            }
            return queue as RequestQueue
        }

    fun <T> addToRequestQueue(request: Request<T>?) {
        requestQueue.add(request)
    }
}