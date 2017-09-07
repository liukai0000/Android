package com.example.chixi.androidsafe.ruanjian.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.example.chixi.androidsafe.ruanjian.entity.AppInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chixi on 2017/1/29.
 */

public class AppInfoParser {
    public static List<AppInfo> getAppInfos(Context context){
        PackageManager pm=context.getPackageManager();
        List<PackageInfo>packageInfos=pm.getInstalledPackages(0);
        List<AppInfo>appInfos=new ArrayList<AppInfo>();
        for (PackageInfo packInfo:packageInfos){
            AppInfo appinfo=new AppInfo();
            String packname=packInfo.packageName;
            appinfo.packageName=packname;
            Drawable icon=packInfo.applicationInfo.loadIcon(pm);
            appinfo.icon=icon;
            String appname=packInfo.applicationInfo.loadLabel(pm).toString();
            appinfo.appName=appname;
            String apkpath=packInfo.applicationInfo.sourceDir;
            appinfo.apkPath=apkpath;
            File file=new File(apkpath);
            long appSize=file.length();
            appinfo.appSize=appSize;
            int flags=packInfo.applicationInfo.flags;
            if ((ApplicationInfo.FLAG_EXTERNAL_STORAGE & flags)!=0){
                appinfo.isInRoom=false;
            }else {
                appinfo.isInRoom=true;
            }
            if ((ApplicationInfo.FLAG_SYSTEM & flags)!=0){
                appinfo.isUserApp=false;
            }else {
                appinfo.isUserApp=true;
            }
            appInfos.add(appinfo);
            appinfo=null;
        }
        return appInfos;
    }
}
