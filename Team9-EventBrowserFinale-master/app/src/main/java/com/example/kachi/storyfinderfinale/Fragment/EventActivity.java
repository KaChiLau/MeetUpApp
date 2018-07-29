package com.example.kachi.storyfinderfinale.Fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.kachi.storyfinderfinale.model.Event;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by KaChi on 2016/12/11.
 */

public class EventActivity extends  SingleFragmentActivity {

    public static final String EXTRA_EVENT_ID =
            "com.example.kachi.storyfinderfinale.event_id";

    public static Intent newIntent(Context packageContext, Event event) {
        Intent intent = new Intent(packageContext, EventActivity.class);
        intent.putExtra(EXTRA_EVENT_ID, (Serializable) event);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        //UUID eventId = (UUID) getIntent().getSerializableExtra(EXTRA_EVENT_ID);
        Event event = (Event) getIntent().getSerializableExtra(EXTRA_EVENT_ID);
        return EventFragment.newInstance(event);
    }
}
