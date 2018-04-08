package com.hhpatel.genericwebservicedemo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyApplication extends Application {
    private static MyApplication mInstance;
    private RequestQueue mRequestQueue;
    public static String ACCEPT = "";
    public static String CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static String AUTHTOKEN = "";

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }
}
