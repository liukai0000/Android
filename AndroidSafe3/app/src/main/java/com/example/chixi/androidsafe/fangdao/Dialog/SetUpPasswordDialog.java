package com.example.chixi.androidsafe.fangdao.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chixi.androidsafe.R;

/**
 * Created by chixi on 2017/1/18.
 */

public class SetUpPasswordDialog extends Dialog implements android.view.View.OnClickListener {
    private TextView mTitleTV;
    public EditText mFirstPWDET;
    public EditText mAffirmET;
    private MyCallBack myCallBack;

    public SetUpPasswordDialog(Context context) {
        super(context, R.style.dialog_custom);
    }
    public void setCallBack(MyCallBack myCallBack){
        this.myCallBack=myCallBack;
    }
    protected void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.setup_password_dialog);
        super.onCreate(savedInstanceState);
        initView();
    }
    private void initView(){
        mTitleTV=(TextView)findViewById(R.id.tv_setuppwd_title);
        mFirstPWDET=(EditText)findViewById(R.id.et_firstpwd);
        mAffirmET=(EditText)findViewById(R.id.et_affirm_parent);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        findViewById(R.id.btn_cancle).setOnClickListener(this);
    }
    public void setTitle(String title){
        if(!TextUtils.isEmpty(title)){
            mTitleTV.setText(title);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok:
                myCallBack.ok();
                break;
            case R.id.btn_cancle:
                myCallBack.cancle();
                break;
        }
    }
    public interface  MyCallBack{
        void ok();
        void cancle();
    }
}
