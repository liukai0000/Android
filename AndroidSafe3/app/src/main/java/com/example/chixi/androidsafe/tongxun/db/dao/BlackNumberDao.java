package com.example.chixi.androidsafe.tongxun.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;

import com.example.chixi.androidsafe.tongxun.db.BlackNumberOpenHelper;
import com.example.chixi.androidsafe.tongxun.entity.BlackContactInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chixi on 2017/1/22.
 */

public class BlackNumberDao {
    private BlackNumberOpenHelper blackNumberOpenHelper;
    public BlackNumberDao(Context context){
        super();
        blackNumberOpenHelper=new BlackNumberOpenHelper(context);
    }
    /**
     * 添加数据
     */
    public boolean add(BlackContactInfo blackContactInfo){
        SQLiteDatabase db=blackNumberOpenHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        if (blackContactInfo.phoneNumber.startsWith("+86")){
            blackContactInfo.phoneNumber=blackContactInfo.phoneNumber.substring(3,blackContactInfo.phoneNumber.length());
        }
        values.put("number",blackContactInfo.phoneNumber);
        values.put("name",blackContactInfo.contactName);
        values.put("mode",blackContactInfo.mode);
        long rowid=db.insert("blacknumber",null,values);
        if (rowid==-1){
            return false;
        }else {
            return true;
        }
    }
    /**
     * 删除数据
     */
    public boolean delete(BlackContactInfo blackContactInfo){
        SQLiteDatabase db=blackNumberOpenHelper.getWritableDatabase();
        int rownumber=db.delete("blacknumber","number=?",new String[]{blackContactInfo.phoneNumber});
        if (rownumber==0){
            return false;
        }else {
            return true;
        }
    }
    /*查询*/
    public List<BlackContactInfo>getPageBlackNumber(int pagenumber,int pagesize){
        SQLiteDatabase db=blackNumberOpenHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select number,mode,name from blacknumber limit ? offset ?",
                new String[]{String.valueOf(pagesize),String.valueOf(pagesize * pagenumber)});
        List<BlackContactInfo>mBlackContactInfos=new ArrayList<BlackContactInfo>();
        while (cursor.moveToNext()){
            BlackContactInfo info=new BlackContactInfo();
            info.phoneNumber=cursor.getString(0);
            info.mode=cursor.getInt(1);
            info.contactName=cursor.getString(2);
            mBlackContactInfos.add(info);
        }
        cursor.close();
        db.close();
        SystemClock.sleep(30);
        return mBlackContactInfos;
    }
    /*判断号码是否存在*/
    public boolean isNumberExist(String number){
        SQLiteDatabase db=blackNumberOpenHelper.getReadableDatabase();
        Cursor cursor=db.query("blacknumber",null,"number=?",new String[]{number},null,null,null);
        if (cursor.moveToNext()){
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }
    /*根据号码查询黑名单信息*/
    public int getBlackContactMode(String number){
        SQLiteDatabase db=blackNumberOpenHelper.getReadableDatabase();
        Cursor cursor=db.query("blacknumber",new String[]{"mode"},"number=?",new String[]{number},null,null,null);
        int mode=0;
        if (cursor.moveToNext()){
            mode=cursor.getInt(cursor.getColumnIndex("mode"));
        }
        cursor.close();
        db.close();
        return mode;
    }
    /**
     * 获取数据库总条目个数
     */
    public int getTotalNumber(){
        SQLiteDatabase db=blackNumberOpenHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select count(*) from blacknumber",null);
        cursor.moveToNext();
        int count=cursor.getInt(0);
        cursor.close();
        db.close();
        return count;
    }
}
