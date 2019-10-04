package com.example.daily_activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

public class PhotoUtils {
    public static Bitmap get_bitmap(String location, Activity Activities) {
        Point dimension= new Point();
        Activities.getWindowManager().getDefaultDisplay()

                .getSize(dimension);
        return getScaledBitmap(location, dimension.x, dimension.y);
    }
    public static Bitmap getScaledBitmap(String location,
                                         int width, int height) {


        BitmapFactory.Options choice = new
                BitmapFactory.Options();
        choice.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(location, choice);

        float Width_Src = choice.outWidth;
        float Height_Src= choice.outHeight;
// identify with how much the scale is down
        int InSampledimension = 1;
        if (Height_Src > height || Width_Src >
                width) {

            float heightScale = Height_Src /

                    height;

            float widthScale = Width_Src / width;
            InSampledimension = Math.round(heightScale >

                    widthScale ? heightScale :
                    widthScale);

        }
        choice = new BitmapFactory.Options();
        choice.inSampleSize = InSampledimension;
// Finalize the bitmap here
        return BitmapFactory.decodeFile(location,
               choice);
    }
}
