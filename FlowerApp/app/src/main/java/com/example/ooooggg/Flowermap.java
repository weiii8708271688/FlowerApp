package com.example.ooooggg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.data.DataBufferUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class Flowermap extends AppCompatActivity {
    Button Taoyuan;
    Button Taipei;
    Button Tainan;
    Button Hualien;
    String email;
    private ActionBar mActionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowmap);
        setToolbar();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Taoyuan=(Button)findViewById(R.id.button6);

        //取值方法
        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("email");
        String name = bundle.getString("name");
       // Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        Taoyuan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Flowermap.this,MainActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("email",email);
                bundle.putString("name",name);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
        Tainan=(Button)findViewById(R.id.button4);
        Tainan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Flowermap.this,Tainan.class);
                Bundle bundle = new Bundle();
                bundle.putString("email",email);
                bundle.putString("name",name);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Taipei=(Button)findViewById(R.id.button7);
        Taipei.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Flowermap.this,Taipei.class);
                Bundle bundle = new Bundle();
                bundle.putString("email",email);
                bundle.putString("name",name);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Hualien=(Button)findViewById(R.id.button5);
        Hualien.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Flowermap.this,Hualien.class);
                Bundle bundle = new Bundle();
                bundle.putString("email",email);
                bundle.putString("name",name);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }



    //@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.membermenu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    // @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user_p:
              //Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
                //取值方法
                Bundle bundle = getIntent().getExtras();
                String name = bundle.getString("name");
                String email = bundle.getString("email");

                Intent intent = new Intent(Flowermap.this,member_center.class);

                Bundle bundle2 = new Bundle();
                bundle2.putString("email",email);
                bundle2.putString("name",name);
                intent.putExtras(bundle2);
                startActivity(intent);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void setToolbar() {
        /**初始化Toolbar*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        /**將Toolbar綁定到setSupportActionBar*/
        setSupportActionBar(toolbar);
        /**設置大標題*/
        getSupportActionBar().setTitle("被你花現了！");
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