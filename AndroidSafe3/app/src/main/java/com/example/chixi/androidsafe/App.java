package com.example.chixi.androidsafe;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by chixi on 2017/1/20.
 */

public class App extends Application {
    public void onCreate(){
        super.onCreate();
        correctSIM();
    }
    public void correctSIM(){
        SharedPreferences sp=getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean protecting=sp.getBoolean("protecting",true);
        if (protecting){
            String bindsim=sp.getString("sim","");
            TelephonyManager tm=(TelephonyManager)getSystemService(Context.TELECOM_SERVICE);
            String realssim=tm.getSimSerialNumber();
            if (bindsim.equals(realssim)){
                Log.i("","sim卡未发生变化，还是您的手机");
            }else{
                Log.i("","SIM卡变化了");
                String safenumber=sp.getString("safenumber","");
                if(!TextUtils.isEmpty(safenumber)){
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(safenumber,null,"您的亲友手机SIM卡已经被更换！",null,null);
                }
            }
        }
    }
}
