package com.example.chixi.androidsafe.tongxun.entity;

/**
 * Created by chixi on 2017/1/22.
 */

public class BlackContactInfo {
    public String phoneNumber;
    public String contactName;
    public int mode;
    public String getModeString(int mode){
        switch (mode){
            case 1:
                return "电话拦截";
            case 2:
                return "短信拦截";
            case 3:
                return "电话、短信拦截";
        }
        return "";
    }
}
