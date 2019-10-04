package com.example.daily_activity;

import androidx.fragment.app.Fragment;

public class Daily_Activity_Record extends Activity_In_SingleFragment {
    @Override
    protected Fragment createSingleFragment(){
        return new Daily_Activity_Fragment();
    }
}
