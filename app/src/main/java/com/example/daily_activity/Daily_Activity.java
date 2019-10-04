package com.example.daily_activity;

import android.icu.text.CaseMap;
import android.media.Image;
import android.widget.Button;

import java.util.Date;
import java.util.UUID;

public class Daily_Activity {
 private UUID UID;
 private String Utitle;
 private String Uplace;
 private String Udetail;
 private String Ulocation;
 private Date Udate;

    public Daily_Activity(){
        this(UUID.randomUUID());
    }
    public Daily_Activity(UUID id) {
        UID = id;
        Udate = new Date();
    }
    public UUID getID(){
        return UID;
    }
    //store Title
    public String getUtitle(){
        return Utitle;
    }
    public void setUtitle(String utitle){
        Utitle=utitle;
    }

    //store place
    public String getUplace(){
        return Uplace;
    }
    public void setUplace(String uplace){
        Uplace=uplace;
    }

    //store detail
    public String getUdetail() {
        return Udetail;
    }

    public void setUdetail(String udetail) {
        Udetail = udetail;
    }
    //store date
    public Date getUdate(){
        return Udate;
    }
    public void setUdate(Date udate){
        Udate=udate;
    }
    //store location
    public String getUlocation(){
        return Ulocation;
    }
    public void setUlocation(String ulocation){
        Ulocation=ulocation;

    }
    public String getpicturefoldername() {
        return "IMG_" + getID().toString() + ".jpg";

    }
}
