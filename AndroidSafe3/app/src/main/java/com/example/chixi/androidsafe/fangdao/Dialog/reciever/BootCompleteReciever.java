package com.example.chixi.androidsafe.fangdao.Dialog.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.chixi.androidsafe.App;

/**
 * Created by chixi on 2017/1/20.
 */

public class BootCompleteReciever extends BroadcastReceiver {
    private static final String TAG=BootCompleteReciever.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        ((App)context.getApplicationContext()).correctSIM();
    }
}
