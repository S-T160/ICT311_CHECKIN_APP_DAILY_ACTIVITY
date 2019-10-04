package com.example.daily_activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Routines {

    private static Routines sRoutines;

    private Context UContext;
    private SQLiteDatabase Udatabase;

    public static Routines get(Context context) {
        if(sRoutines==null){
            sRoutines=new Routines(context);
        }

        return sRoutines;
    }

    private Routines(Context context)
    {
        UContext=context.getApplicationContext();
        Udatabase=new BASEHELPER_FOR_DAILYACTIVITY(UContext).getWritableDatabase();

    }
    public void adddailyactivity(Daily_Activity d){
        ContentValues insertvalues = getContentValues(d);
        Udatabase.insert(Database_schema_dailyactivity.DailyActivity_Table.dailyactivity_name, null, insertvalues);

    }
    public List<Daily_Activity>getdailylist() {

        List<Daily_Activity> Dailyactivities = new ArrayList<>();
        Daily_cursorwrapper cursor = QueryDailyactivity(null,
                null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Dailyactivities.add(cursor.getDaily_activity());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return Dailyactivities;
    }

    public Daily_Activity getdailylist(UUID id){
        Daily_cursorwrapper cursor = QueryDailyactivity(Database_schema_dailyactivity.DailyActivity_Table.Colounm.UUID
                + " = ?", new String[] { id.toString() });

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getDaily_activity();
        } finally {
            cursor.close();
        }
    }
    public File getPicturefolder(Daily_Activity new1) {
        File filesDir = UContext.getFilesDir();
        return new File(filesDir,
                new1.getpicturefoldername());
    }


    public void update_dailyactivity(Daily_Activity new1) {
        String uuidString = new1.getID().toString();
        ContentValues insertvalues = getContentValues(new1);
        Udatabase.update(Database_schema_dailyactivity.DailyActivity_Table.dailyactivity_name,insertvalues,
                Database_schema_dailyactivity.DailyActivity_Table.Colounm.UUID + " = ?",
                new String[] { uuidString });
    }
    public void delete_dailyactivity(Daily_Activity myDaily_Activity){
        String uuidString = myDaily_Activity.getID().toString();
        Udatabase.delete(Database_schema_dailyactivity.DailyActivity_Table.dailyactivity_name, Database_schema_dailyactivity.DailyActivity_Table.Colounm.UUID + " =?", new String[] { uuidString });
    }



    private static ContentValues
    getContentValues(Daily_Activity new1 ) {
        ContentValues insertvalues = new ContentValues();
        insertvalues.put(Database_schema_dailyactivity.DailyActivity_Table.Colounm.UUID,
                new1.getID().toString());
        insertvalues.put(Database_schema_dailyactivity.DailyActivity_Table.Colounm.TTitle,
                new1.getUtitle());
        insertvalues.put(Database_schema_dailyactivity.DailyActivity_Table.Colounm.TDate,
                new1.getUplace());
        insertvalues.put(Database_schema_dailyactivity.DailyActivity_Table.Colounm.TDetail,
                new1.getUdetail());
        insertvalues.put(Database_schema_dailyactivity.DailyActivity_Table.Colounm.TDate,
                new1.getUdate().getTime());
        insertvalues.put(Database_schema_dailyactivity.DailyActivity_Table.Colounm.TLocation,
                new1.getUlocation());

        return insertvalues;
    }
    private Daily_cursorwrapper QueryDailyactivity(String whereClause, String[] whereArgs) {
        Cursor cursor = Udatabase.query(Database_schema_dailyactivity.DailyActivity_Table.dailyactivity_name,            null,
                // columns - null selects all columns
                whereClause,
                whereArgs,            null,
                // groupBy
                null,
                // having
                null
                // orderBy
        );
        return new Daily_cursorwrapper(cursor);
    }




    }
