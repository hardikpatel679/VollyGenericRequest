package com.hhpatel.genericwebservicedemo;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hhpatel on 18-Feb-18.
 */

public class NetworkUtil {
    private RequestQueue mRequestQueue;
    private Map param;
    private com.hhpatel.genericwebservicedemo.Response listener;

    public <T> void makeRequest(final com.hhpatel.genericwebservicedemo.Response listener, int method, String URL, final Map param, final Map file, final Class<T> outputType, final int resulCode){
        mRequestQueue = MyApplication.getInstance().getRequestQueue();
        if(file == null){
            simpleRequest(listener,method,URL,param,outputType,resulCode);
        }else {
            multiparRequest(listener,method,URL,param,file,outputType,resulCode);
        }
    }

    public <T> void simpleRequest(final com.hhpatel.genericwebservicedemo.Response listener, int method, String URL, final Map param, final Class<T> outputType, final int resulCode) {


        StringRequest stringRequest = new StringRequest(method, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);
                T mobject = new Gson().fromJson(response,outputType);
                listener.success(mobject,resulCode);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Log.e("Error.Response", error.getMessage());
                listener.error(error,resulCode);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return param;
            }

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//
//                //Optional headers
//                //headers.put("Accept", MyApplication.ACCEPT);
//                headers.put("Content-Type", MyApplication.CONTENT_TYPE);
//                //headers.put("Authorization", MyApplication.AUTHTOKEN);
//                return headers;
//            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        mRequestQueue.add(stringRequest);
    }


    public <T> void multiparRequest(final com.hhpatel.genericwebservicedemo.Response listener, int method, String URL, final Map param, final Map file, final Class<T> outputType, final int resulCode) {

        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(method, URL, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                String str = new String(response.data);
                Log.d("Response", str);
                T mobject = new Gson().fromJson(str,outputType);
                listener.success(mobject,resulCode);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error.Response", error.getMessage());
                listener.error(error,resulCode);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return param;
            }

            @Override
            protected Map<String, DataPart> getByteData() throws AuthFailureError {
                return file;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //Optional headers
                headers.put("Accept", MyApplication.ACCEPT);
                headers.put("Content-Type", MyApplication.CONTENT_TYPE);
                headers.put("Authorization", MyApplication.AUTHTOKEN);
                return headers;
            }
        };

        mRequestQueue.add(multipartRequest);
    }


}
