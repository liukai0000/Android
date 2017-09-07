package com.example.chixi.androidsafe.ruanjian.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import com.example.chixi.androidsafe.ruanjian.entity.AppInfo;

/**
 * Created by chixi on 2017/1/29.
 */

public class EngineUtils {
    /**
     * 分享应用
     **/
    public static void shareApplication(Context context, AppInfo appInfo){
        Intent intent=new Intent("android.intent.action.SEND");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"推荐您使用一款软件，名称叫做："+appInfo.appName+"下载路径：" +
                "https://www.coolapk.com/apk?id="+appInfo.packageName);
        context.startActivity(intent);
    }
    /**
     * 开启应用程序
     **/
    public static void startApplication(Context context,AppInfo appInfo){
        PackageManager pm=context.getPackageManager();
        Intent intent=pm.getLaunchIntentForPackage(appInfo.packageName);
        if (intent!=null){
            context.startActivity(intent);
        }else{
            Toast.makeText(context,"该应用没有启动界面",Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 开启应用设置界面
     **/
    public static void settingAppDetail(Context context,AppInfo appInfo){
        Intent intent=new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:"+appInfo.packageName));
        context.startActivity(intent);
    }
    /**
     * 卸载应用
     **/
    public static void uninstallApplication(Context context,AppInfo appInfo){
        if (appInfo.isUserApp){
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("package:"+appInfo.packageName));
            context.startActivity(intent);
        }else{
            Toast.makeText(context,"这是系统应用不能卸载！",Toast.LENGTH_SHORT).show();
        }
    }
}
