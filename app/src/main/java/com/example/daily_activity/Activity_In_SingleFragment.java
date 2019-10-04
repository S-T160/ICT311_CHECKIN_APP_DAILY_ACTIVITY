package com.example.daily_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class Activity_In_SingleFragment extends AppCompatActivity {
    protected abstract Fragment createSingleFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.activity_fragment_container);
        if (fragment == null) {
            fragment = createSingleFragment();
            fm.beginTransaction().add(R.id.activity_fragment_container, fragment).commit();
        }
    }

    public static Intent newIntent1(Context packageContent) {


        Intent intent = new Intent(packageContent, Activity_In_SingleFragment.class);

        return intent;
    }
}
