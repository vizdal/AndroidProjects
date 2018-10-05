package com.example.viswanathms.asignment3;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by viswanathms on 2018-03-06.
 * Copy Pasted from Lab 6
 */

public class RequestQueueSingleton {
    private static RequestQueueSingleton mInstance;
    private RequestQueue mrequestQueue;
    private Context mCtx;

    public static synchronized RequestQueueSingleton getInstance(Context context) {
        if(mInstance == null){
            mInstance = new RequestQueueSingleton(context.getApplicationContext());
        }
        return mInstance;
    }

    private RequestQueueSingleton(Context ctx) {
        mCtx = ctx;
        mrequestQueue = getRequestQueue();
    }
    public RequestQueue getRequestQueue(){
        if(mrequestQueue == null) {
            mrequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mrequestQueue;
    }
    public <T> void addRequestToQueue(Request<T> req){
        getRequestQueue().add(req);
    }
}
