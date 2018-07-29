package com.example.kachi.storyfinderfinale.controller;

/**
 * Created by KaChi on 2016/12/11.
 */

import android.net.Uri;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.kachi.storyfinderfinale.MainActivity;
import com.example.kachi.storyfinderfinale.app.App;
import com.example.kachi.storyfinderfinale.model.Event;
import com.example.kachi.storyfinderfinale.request.JsonRequest;
import com.example.kachi.storyfinderfinale.volley.VolleySingleton;
import java.util.List;

public class JsonController {
    private final int TAG = 100;

    private OnResponseListener responseListener;

    public JsonController(OnResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    public void sendRequest(String query, double lat, double lon) {

        // Request Method
        int method = Request.Method.GET;

        String latitude = String.valueOf(lat);
        String longtiude = String.valueOf(lon);

        String url ="https://api.meetup.com/2/groups?key=6f425b36194a460722955297b1815c&sign=true&photo-host=public&topic=" + Uri.encode(query)
                + "&lat=" + Uri.encode(latitude)
                + "&lon=" + Uri.encode(longtiude)
                + "&page=20";


        JsonRequest request
                = new JsonRequest(
                method,
                url,
                new Response.Listener<List<Event>>() {
                    @Override
                    public void onResponse(List<Event> events) {
                        responseListener.onSuccess(events);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseListener.onFailure(error.getMessage());
                    }
                }
        );

        request.setTag(TAG);

        VolleySingleton.getInstance(App.getContext()).addToRequestQueue(request);
    }

    public void cancelAllRequests() {
        VolleySingleton.getInstance(App.getContext()).cancelAllRequests(TAG);
    }

    public interface OnResponseListener {
        void onSuccess(List<Event> movies);
        void onFailure(String errorMessage);
    }

}
