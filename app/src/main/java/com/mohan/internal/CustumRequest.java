package com.mohan.internal;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohan on 18/5/16.
 */
public class CustumRequest extends Request<JSONObject> {


    // note : your res[ponce must be json object


    private static String TAG ="CustumRequest";
    Response.Listener<JSONObject> listener;
    public CustumRequest(String URL, Response.Listener listener, Response.ErrorListener errorListener) {
        super(Method.GET, URL, errorListener);
        this.listener=listener;

    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/x-www-form-urlencoded");
        return params;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            JSONObject resObj = new JSONObject(jsonString);

            return Response.success(resObj, HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            Log.e(TAG, "parseNetworkResponse: "+" "+je.getMessage() );
            je.printStackTrace();
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        listener.onResponse(response);
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {

        Log.e(TAG, "parseNetworkError: "+volleyError.getMessage());
        return volleyError;

    }
}
