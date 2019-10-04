package com.example.daily_activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.daily_activity.Database_schema_dailyactivity;

public class BASEHELPER_FOR_DAILYACTIVITY extends SQLiteOpenHelper {
    private static final int MODEL =1;
    private static final  String name_of_database="dailyactivity.db";
    public BASEHELPER_FOR_DAILYACTIVITY(Context context){
        super(context,name_of_database,null,MODEL);

    }
    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(" create table " + Database_schema_dailyactivity.DailyActivity_Table.dailyactivity_name+"("+"" +
                "_id integer primary key autoincrement,"+
                Database_schema_dailyactivity.DailyActivity_Table.Colounm.UUID+","+
                Database_schema_dailyactivity.DailyActivity_Table.Colounm.TTitle+","+
                Database_schema_dailyactivity.DailyActivity_Table.Colounm.TPlace+","+
                Database_schema_dailyactivity.DailyActivity_Table.Colounm.TDetail+","+
                Database_schema_dailyactivity.DailyActivity_Table.Colounm.TDate+","+
                Database_schema_dailyactivity.DailyActivity_Table.Colounm.TLocation+ ")"
        );

    }
    @Override
    public void onUpgrade(SQLiteDatabase database,int olderMODEL,int newMODEL){

    }

}
