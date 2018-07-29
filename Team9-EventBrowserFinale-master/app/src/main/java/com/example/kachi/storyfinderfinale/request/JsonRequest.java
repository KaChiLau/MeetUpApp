package com.example.kachi.storyfinderfinale.request;

/**
 * Created by KaChi on 2016/12/11.
 */

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.kachi.storyfinderfinale.model.Event;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JsonRequest extends Request<List<Event>> {

    private Response.Listener<List<Event>> successListener;

    public JsonRequest( int method,
                        String url,
                        Response.Listener<List<Event>> successListener,
                        Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.successListener = successListener;
    }

    @Override
    protected Response<List<Event>> parseNetworkResponse(NetworkResponse response) {
        // Convert byte[] data received in the response to String
        String jsonString = new String(response.data);
        List<Event> events;
        JSONObject jsonObject;
        Log.i(this.getClass().getName(), jsonString);
        // Try to convert JsonString to list of events
        try {
            // Convert JsonString to JSONObject
            jsonObject = new JSONObject(jsonString);
            // Get list of events from received JSON
            events = Event.parseJson(jsonObject);
        }
        // in case of exception, return volley error
        catch (JSONException e) {
            e.printStackTrace();
            // return new volley error with message
            return Response.error(new VolleyError("Failed to process the request"));
        }
        // return list of events
        return Response.success(events, getCacheEntry());
    }

    @Override
    protected void deliverResponse(List<Event> events) {
        successListener.onResponse(events);
    }


}
