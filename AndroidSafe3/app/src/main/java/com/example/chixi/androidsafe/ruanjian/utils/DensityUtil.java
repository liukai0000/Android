package com.example.chixi.androidsafe.ruanjian.utils;

import android.content.Context;

/**
 * Created by chixi on 2017/1/29.
 */

public class DensityUtil {
    public static int dip2px(Context context,float dpValue){
        try{
            final float scale=context.getResources().getDisplayMetrics().density;
            return (int)(dpValue*scale+0.5f);
        }catch (Exception e){
            e.printStackTrace();
        }
        return (int)dpValue;
    }
    public static int dp2dip(Context context,float pxValue){
        try{
            final float scale=context.getResources().getDisplayMetrics().density;
            return (int)(pxValue/scale+0.5f);
        }catch (Exception e){
            e.printStackTrace();
        }
        return (int)pxValue;
    }
}
