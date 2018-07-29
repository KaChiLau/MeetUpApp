package com.example.kachi.storyfinderfinale.Fragment;

/**
 * Created by KaChi on 2016/12/11.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.net.Uri;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.kachi.storyfinderfinale.R;
import com.example.kachi.storyfinderfinale.app.App;
import com.example.kachi.storyfinderfinale.model.Event;
import com.example.kachi.storyfinderfinale.volley.VolleySingleton;

import java.io.Serializable;

public class EventFragment extends Fragment {

    private static final String ARG_EVENT_ID = "event_id";

    private Event mEvent;
    private TextView mTextField;
    private TextView mCityField;
    private Button mMapButton;
    private Button mWebButton;
    private TextView mDescriptionField;
    private NetworkImageView poster;

    public static EventFragment newInstance(Event event) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_EVENT_ID, (Serializable) event);

        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //UUID eventId = (UUID) getArguments().getSerializable(ARG_EVENT_ID);

        mEvent = (Event) getArguments().getSerializable(ARG_EVENT_ID);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_event, container, false);

        //findView
        mTextField = (TextView) v.findViewById(R.id.fragment_event_name);
        mCityField = (TextView) v.findViewById(R.id.fragment_event_city);
        mDescriptionField = (TextView) v.findViewById(R.id.fragment_event_description);
        poster = (NetworkImageView) v.findViewById(R.id.nivPoster);
        mWebButton = (Button) v.findViewById(R.id.webButton);
        mMapButton = (Button) v.findViewById(R.id.mapButton);



        //SetText
        mCityField.setText(mEvent.getCity());
        mTextField.setText(mEvent.getName());
        mDescriptionField.setText(fixDescription(mEvent.getDescription()));
        //mLat.setText(fixDescription("Latitude: " + mEvent.getLat()));
        //mLon.setText(fixDescription("longitude: " + mEvent.getLon()));
        mWebButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = WebViewActivity.newIntent(getActivity(), mEvent);
                startActivity(intent);
            }
        });

        mMapButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + mEvent.getLat() + "," + mEvent.getLon()));
                i.setClassName("com.google.android.apps.maps",
                        "com.google.android.maps.MapsActivity");
                System.out.println(mEvent.getLat() + " , " + mEvent.getLon());
                startActivity(i);
            }
        });

        ImageLoader imageLoader = VolleySingleton.getInstance(App.getContext()).getImageLoader();
        poster.setImageUrl(mEvent.getPoster(), imageLoader);

        return v;
    }


    public String fixDescription(String description) {
        String str = description.replaceAll("<p>", "");
        str = str.replaceAll("</p>", "");
        str = str.replaceAll("<br>", "");
        str = str.replaceAll("<span>", "");
        str = str.replaceAll("</span>", "");
        str = str.replaceAll("<strong>", "");
        str = str.replaceAll("</strong>", "");
        str = str.replaceAll("_", "");

        if(str.length() > 500) {
            str = str.substring(0,500) + "....";
        }

        return str;
    }

}
