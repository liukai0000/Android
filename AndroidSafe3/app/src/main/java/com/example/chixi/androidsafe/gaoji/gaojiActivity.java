package com.example.chixi.androidsafe.gaoji;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.chixi.androidsafe.R;

/**
 * Created by chixi on 2017/2/4.
 */

public class gaojiActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 初始化布局
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gaoji);
    }
}
