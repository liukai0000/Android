package com.example.chixi.androidsafe.fangdao.Dialog;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.chixi.androidsafe.R;

/**
 * Created by chixi on 2017/1/20.
 */

public class SetUp1Activity extends BaseSetUpActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup1);
        initView();
    }
    private void initView(){
        ((RadioButton)findViewById(R.id.rb_first)).setChecked(true);
    }
    @Override
    public void showNext() {
        startActivityAndFinishSelf(SetUp2Activity.class);
    }

    @Override
    public void showPre() {
        Toast.makeText(this,"当前页面已经是第一页",Toast.LENGTH_SHORT).show();
    }
}
