package com.mohan.internal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String URL="";  // your url for parsing
    
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.text);
        
        
    }
    
    public void parse(View view){
        
        CustumRequest custumRequest=new CustumRequest(URL, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, "onResponse: ");
                // parse your json responce here
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: ");
            }
        });

        Volley.newRequestQueue(this).add(custumRequest);
    }
}
