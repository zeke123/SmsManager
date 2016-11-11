package com.zhoujian.smsmanager;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity
{

    @InjectView(R.id.phone_number)
    EditText mPhoneNumber;
    @InjectView(R.id.sms_content)
    EditText mSmsContent;
    @InjectView(R.id.bt_send)
    Button mBtSend;
    private SmsManager mSmsManager;
    private String number;
    private String mContent;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mSmsManager = SmsManager.getDefault();
        clickEvent();
    }

    private void clickEvent()
    {
        mBtSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                number = mPhoneNumber.getText().toString().trim();
                mContent = mSmsContent.getText().toString().trim();
                if(TextUtils.isEmpty(number))
                {
                    Toast.makeText(MainActivity.this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(mContent))
                {
                    Toast.makeText(MainActivity.this, "短信内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                PendingIntent mPendingIntent = PendingIntent.getActivity(MainActivity.this,0,new Intent(),0);

                //<uses-permission android:name="android.permission.SEND_SMS"/>
                //发送短信
                mSmsManager.sendTextMessage(number,null,mContent,mPendingIntent,null);

                Toast.makeText(MainActivity.this, "短信发送完成", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
