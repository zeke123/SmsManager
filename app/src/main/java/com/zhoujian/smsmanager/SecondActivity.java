package com.zhoujian.smsmanager;

import android.app.Activity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.app.AlertDialog;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zhoujian on 2016/11/11.
 * <p>
 * 短信群发的界面
 */

public class SecondActivity extends Activity {
    // 记录需要群发的号码列表
    ArrayList<String> sendList = new ArrayList<String>();
    private Cursor mCursor;

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

        //选择联系人的按钮
        mSelect.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                mCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

                BaseAdapter madapter = new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return mCursor.getCount();
                    }

                    @Override
                    public Object getItem(int position) {
                        return position;
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View view, ViewGroup group) {
                        mCursor.moveToPosition(position);
                        CheckBox rb = new CheckBox(SecondActivity.this);
                        // 获取联系人的电话号码，并去掉中间的中划线、空格
                        String number = mCursor
                                .getString(mCursor.getColumnIndex(ContactsContract
                                        .CommonDataKinds.Phone.NUMBER))
                                .replace("-", "")
                                .replace(" ", "");
                        rb.setText(number);
                        // 如果该号码已经被加入发送人名单、默认勾选该号码
                        if (isChecked(number)) {
                            rb.setChecked(true);
                        }
                        return rb;
                    }
                };

                // 加载list.xml布局文件对应的View
                View selectView = getLayoutInflater().inflate(R.layout.list, null);
                // 获取selectView中的名为list的ListView组件
                final ListView listView = (ListView) selectView.findViewById(R.id.list);
                listView.setAdapter(madapter);
                new AlertDialog.Builder(SecondActivity
                        .this)
                        .setView(selectView)
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // 清空sendList集合
                                        sendList.clear();
                                        // 遍历listView组件的每个列表项
                                        for (int i = 0; i < listView.getCount(); i++) {
                                            CheckBox checkBox = (CheckBox) listView
                                                    .getChildAt(i);
                                            // 如果该列表项被勾选
                                            if (checkBox.isChecked()) {
                                                // 添加该列表项的电话号码
                                                sendList.add(checkBox.getText()
                                                        .toString());
                                            }
                                        }
                                        mNumbers.setText(sendList.toString());
                                    }
                                }).show();
            }
        });

        //群发短信的按钮
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    // 判断某个电话号码是否已在群发范围内
    public boolean isChecked(String phone) {
        for (String s1 : sendList) {
            if (s1.equals(phone)) {
                return true;
            }
        }
        return false;
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
