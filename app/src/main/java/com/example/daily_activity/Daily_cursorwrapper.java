package com.example.daily_activity;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

public class Daily_cursorwrapper extends CursorWrapper {
    public Daily_cursorwrapper(Cursor cursor) {
        super(cursor);
    }
    public Daily_Activity getDaily_activity() {
        String uuidString =
                getString(getColumnIndex(Database_schema_dailyactivity.DailyActivity_Table.Colounm.UUID));
        String title =
                getString(getColumnIndex(Database_schema_dailyactivity.DailyActivity_Table.Colounm.TTitle));
        String place =
                getString(getColumnIndex(Database_schema_dailyactivity.DailyActivity_Table.Colounm.TPlace));
        String detail =
                getString(getColumnIndex(Database_schema_dailyactivity.DailyActivity_Table.Colounm.TDetail));
        long date =
                getLong(getColumnIndex(Database_schema_dailyactivity.DailyActivity_Table.Colounm.TDate));
        String location =
                getString(getColumnIndex(Database_schema_dailyactivity.DailyActivity_Table.Colounm.TLocation));




        Daily_Activity new1 = new Daily_Activity(UUID.fromString(uuidString));
        new1.setUtitle(title);
        new1.setUplace(place);
        new1.setUdetail(detail);
        new1.setUdate(new Date(date));
        new1.setUlocation(location);
        return new1;
    }
}
