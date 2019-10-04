package com.example.daily_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class MainActivity extends Activity_In_SingleFragment {
   private static final String EXTRA_DAILYACTIVITY_ID="com.bignerdranch.android.daily_activity.daily_id";
    public static Intent newIntent(Context packageContext, UUID dailyid){
        Intent intent = new Intent(packageContext,MainActivity.class);
        intent.putExtra(EXTRA_DAILYACTIVITY_ID,dailyid);
        return intent;
    }


   @Override
    protected Fragment createSingleFragment(){
      UUID dailyid=(UUID)getIntent().getSerializableExtra(EXTRA_DAILYACTIVITY_ID);
      return ActivityFragment.newInstance(dailyid);
   }
}
