package com.getman.varnabeach;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyHelper {
    private static VolleyHelper instance;
    private RequestQueue queue;
    private static Context context;

    private VolleyHelper(Context ctx) {
        // in case context other than application one is provided
        context = ctx.getApplicationContext();
        getRequestQueue();
    }

    public static VolleyHelper getInstance(Context ctx) {
        if (instance == null) {
            instance = new VolleyHelper(ctx);
        }
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if (queue == null) {
            queue = Volley.newRequestQueue(context);
        }
        return queue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
