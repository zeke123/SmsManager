package com.zhoujian.smsmanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zhoujian on 2016/11/11.
 * <p>
 * 短信群发的界面
 */

public class SecondActivity extends Activity {

    @InjectView(R.id.numbers)
    EditText mNumbers;
    @InjectView(R.id.content)
    EditText mContent;
    @InjectView(R.id.select)
    Button mSelect;
    @InjectView(R.id.send)
    Button mSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //option+command+L   代码格式化快捷键
        //option+command+k    对生命周期方法进行排序
        setContentView(R.layout.activity_second);
        ButterKnife.inject(this);
        clickEvent();
    }

    private void clickEvent() {

    }



    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
