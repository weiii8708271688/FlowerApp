package com.example.ooooggg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class member_center extends AppCompatActivity {

    TextView email_text;
    TextView name_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_center);
        //取值方法
        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("email");
        String name = bundle.getString("name");
        setToolbar();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email_text=(TextView)findViewById(R.id.textView2);
        email_text.setText(email);
        name_text=(TextView)findViewById(R.id.textView6);
        name_text.setText(name);
    }
    private void setToolbar() {
        /**初始化Toolbar*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        /**將Toolbar綁定到setSupportActionBar*/
        setSupportActionBar(toolbar);
        /**設置大標題*/
        getSupportActionBar().setTitle("會員中心");
        /**設置大標題字體顏色*/
        toolbar.setTitleTextColor(Color.BLACK);
        /**設置副標題*/
        //toolbar.setSubtitle("副標題");
        /**設置副標題字體顏色*/
        //toolbar.setSubtitleTextColor(Color.WHITE);

        //toolbar.inflateMenu(請自己在menu設置一個xml檔案);
        //如果想使用xml Layout檔案，請去建一個吧～
    }
}