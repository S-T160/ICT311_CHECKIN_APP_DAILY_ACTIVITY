package com.example.daily_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class dailyactivity_activity_pager extends AppCompatActivity {
    private static final String EXTRA_DAILYACTIVITY_ID = "com.bignerdranch.android.daily_activity.daily_id";
    private ViewPager Uview_pager;
    private List<Daily_Activity> UDaily_Activitys;

    public static Intent newIntent(Context packageContext, UUID dailyid) {
        Intent intent = new Intent(packageContext, dailyactivity_activity_pager.class);
        intent.putExtra(EXTRA_DAILYACTIVITY_ID, dailyid);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_dailyactivity);
        UUID dailyid = (UUID) getIntent().getSerializableExtra(EXTRA_DAILYACTIVITY_ID);
        Uview_pager = (ViewPager) findViewById(R.id.view_pager_dailyactivity);
        UDaily_Activitys = Routines.get(this).getdailylist();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Uview_pager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Daily_Activity new1 = UDaily_Activitys.get(position);
                return ActivityFragment.newInstance(new1.getID());
            }

            @Override
            public int getCount() {
                return UDaily_Activitys.size();
            }
        });
        for (int i = 0; i < UDaily_Activitys.size(); i++) {
            if (UDaily_Activitys.get(i).getID().equals(dailyid)) {
                Uview_pager.setCurrentItem(i);
                break;
            }
        }
    }
}
