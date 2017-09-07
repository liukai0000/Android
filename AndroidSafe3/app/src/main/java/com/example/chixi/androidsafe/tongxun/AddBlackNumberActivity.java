package com.example.chixi.androidsafe.tongxun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chixi.androidsafe.R;
import com.example.chixi.androidsafe.fangdao.Dialog.ContactSelectActivity;
import com.example.chixi.androidsafe.tongxun.db.dao.BlackNumberDao;
import com.example.chixi.androidsafe.tongxun.entity.BlackContactInfo;

/**
 * Created by chixi on 2017/1/23.
 */

public class AddBlackNumberActivity extends Activity implements View.OnClickListener{
    private CheckBox mSmsCB;
    private CheckBox mTelCB;
    private EditText mNumET;
    private EditText mNameET;
    private BlackNumberDao dao;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_blacknumber);
        dao=new BlackNumberDao(this);
        initView();
    }
    private void initView(){
        findViewById(R.id.r1_titlebar).setBackgroundColor(getResources().getColor(R.color.bright_purple));
        ((TextView)findViewById(R.id.tv_title)).setText("添加黑名单");
        ImageView mLeftImgv=(ImageView)findViewById(R.id.imgv_leftbtn);
        mLeftImgv.setOnClickListener(this);
        mLeftImgv.setImageResource(R.drawable.back);
        mSmsCB=(CheckBox)findViewById(R.id.cb_blacknumber_sms);
        mTelCB=(CheckBox)findViewById(R.id.cb_blacknumber_tel);
        mNameET=(EditText)findViewById(R.id.et_blackname);
        mNumET=(EditText)findViewById(R.id.et_blacknumber);
        findViewById(R.id.add_blacknum_btn).setOnClickListener(this);
        findViewById(R.id.add_formcontact_btn).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgv_leftbtn:
                finish();
                break;
            case R.id.add_blacknum_btn:
                String number=mNumET.getText().toString().trim();
                String name=mNameET.getText().toString().trim();
                if (TextUtils.isEmpty(number)||TextUtils.isEmpty(name)){
                    Toast.makeText(this,"电话号码不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    BlackContactInfo blackContactInfo=new BlackContactInfo();
                    blackContactInfo.phoneNumber=number;
                    blackContactInfo.contactName=name;
                    if (mSmsCB.isChecked()&mTelCB.isChecked()){
                        blackContactInfo.mode=3;
                    }else if (mSmsCB.isChecked()&!mTelCB.isChecked()){
                        blackContactInfo.mode=2;
                    }else if (!mSmsCB.isChecked()&mTelCB.isChecked()){
                        blackContactInfo.mode=1;
                    }else{
                        Toast.makeText(this,"请选择拦截模式！",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!dao.isNumberExist(blackContactInfo.phoneNumber)){
                        dao.add(blackContactInfo);
                    }else{
                        Toast.makeText(this,"该号码已经被添加到黑名单",Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
                break;
            case R.id.add_formcontact_btn:
                startActivityForResult(new Intent(this, ContactSelectActivity.class),0);
                break;
        }
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (data!=null){
            String phone=data.getStringExtra("phone");
            String name=data.getStringExtra("name");
            mNumET.setText(phone);
            mNameET.setText(name);
        }
    }
}
