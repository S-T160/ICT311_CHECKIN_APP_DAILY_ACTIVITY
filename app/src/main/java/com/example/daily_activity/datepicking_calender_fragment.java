package com.example.daily_activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class datepicking_calender_fragment extends DialogFragment {
    public static final String EXTRA_DATE="com.bignerdranch.android.daily_activity.date";
    public static final String argument_date="date";
    private DatePicker Upicking_Date;
    public static datepicking_calender_fragment newInstance(Date dates){
        Bundle args=new Bundle();
        args.putSerializable(argument_date,dates);
        datepicking_calender_fragment fragment=new datepicking_calender_fragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date dates=(Date)getArguments().getSerializable(argument_date);
        Calendar patra=Calendar.getInstance();
        patra.setTime(dates);
        int sal=patra.get(Calendar.YEAR);
        int mahina=patra.get(Calendar.MONTH);
        int gate=patra.get(Calendar.DAY_OF_MONTH);
        View viewer = LayoutInflater.from(getActivity()).inflate(R.layout.layout_date_picker_dialog, null);
        Upicking_Date=(DatePicker) viewer.findViewById(R.id.show_datepick);
        Upicking_Date.init(sal,mahina,gate,null);
        return new AlertDialog.Builder(getActivity()).setView(viewer).setTitle(R.string.date_picker).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int datetime) {
                int sal=Upicking_Date.getYear();
                int mahina=Upicking_Date.getMonth();
                int gate=Upicking_Date.getDayOfMonth();
                Date dates=new GregorianCalendar(sal,mahina,gate).getTime();
                passdate(Activity.RESULT_OK,dates);

            }
        }).create();

    }
    private void passdate(int idcode,Date dates){
        if (getTargetFragment()== null){

        }
        Intent intent=new Intent();
        intent.putExtra(EXTRA_DATE,dates);
        getTargetFragment().onActivityResult(getTargetRequestCode(),idcode,intent);

    }
}


