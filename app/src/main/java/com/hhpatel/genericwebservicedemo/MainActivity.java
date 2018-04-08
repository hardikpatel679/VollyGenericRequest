package com.hhpatel.genericwebservicedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements Response {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btnCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCall = findViewById(R.id.btnCall);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://new.comnet.com.sg/cognifyxservice/1.0/User/LoginUser";
                Map<String,String> param = new HashMap<>();
                param.put("EMAIL","hardik@comnet.com.sg");
                param.put("PASSWORD","aaqwe123@A");
                param.put("DEVICETYPE","1");
                param.put("DEVICEID","cq8iIZ753CY:APA91bFJTE1HHk17mtL3dJjUBrINX7ucvQ0KQSPzVcjP3hJz30uF0XghlKx3GPwaU32yZNo46qtugbV7FCji_S4soZNv3qj4eJ94pFg3ZYhr_sV8IFN8noVdxYgKvonnWEXGMHrapDUB");
                param.put("FIRSTTIMELOGINDATE","08/04/2018 09:46:02 AM");
                param.put("SECONDTIMELOGINDATE","08/04/2018 09:46:02 AM");
                param.put("APPMODE","Development");
                param.put("APPVERSION","1");
                new NetworkUtil().makeRequest(MainActivity.this, Request.Method.POST,url,param,null,WsResponse.class,1001);
            }
        });
    }

    @Override
    public <T> void success(T outputType, int resultCode) {
        WsResponse mWsResponse = (WsResponse) outputType;
        if(mWsResponse !=null) {
            Log.e(TAG, new Gson().toJson(mWsResponse));
        }else{
            Log.e(TAG, "some goes wrong");
        }
    }

    @Override
    public void error(Object error, int resultCode) {
        VolleyError error1 = (VolleyError) error;
        Log.e(TAG, "some goes wrong"+ error1.getMessage());
    }
}
