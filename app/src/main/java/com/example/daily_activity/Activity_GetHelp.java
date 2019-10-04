package com.example.daily_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_GetHelp extends AppCompatActivity {
    WebView webpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gethelp);

        webpage = (WebView) findViewById(R.id.website_display_help);

        webpage.getSettings().setJavaScriptEnabled(true);
        webpage.setWebViewClient(new WebViewClient());
        webpage.loadUrl("https://www.wikihow.com/Check-In-on-Facebook");
    }

    public static Intent newIntent(Context packageContent){

        Intent intent = new Intent(packageContent, Activity_GetHelp.class);
        return intent;

    }

}


