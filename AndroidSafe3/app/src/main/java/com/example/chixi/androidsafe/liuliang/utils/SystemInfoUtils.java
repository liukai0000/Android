package com.example.chixi.androidsafe.liuliang.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

import java.util.List;

public class SystemInfoUtils {
    /**
     * 判断一个服务是否处于运行状态
     * @param context 上下文
     * @return
     */
    public static boolean isServiceRunning(Context context,String className){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningServiceInfo> infos = am.getRunningServices(200);
        for(RunningServiceInfo info:infos){
            String serviceClassName = info.service.getClassName();
            if(className.equals(serviceClassName)){
                return true;
            }
        }
        return false;
    }

}
