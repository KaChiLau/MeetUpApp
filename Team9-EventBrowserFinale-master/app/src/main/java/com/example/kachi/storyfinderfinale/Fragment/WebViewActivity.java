package com.example.kachi.storyfinderfinale.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.kachi.storyfinderfinale.R;
import com.example.kachi.storyfinderfinale.model.Event;

import java.io.Serializable;

/**
 * Created by KaChi on 2016/12/17.
 */

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;
    private Event mEvent;

    public static final String EXTRA_EVENT_ID =
            "com.example.kachi.storyfinderfinale.event_id";

    public static Intent newIntent(Context packageContext, Event event) {
        Intent intent = new Intent(packageContext, WebViewActivity.class);
        intent.putExtra(EXTRA_EVENT_ID, (Serializable) event);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_webview);

        mEvent = (Event) getIntent().getSerializableExtra(EXTRA_EVENT_ID);

        mWebView =(WebView) findViewById(R.id.activity_main_webview);
        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl(mEvent.getLink());

    }
}
