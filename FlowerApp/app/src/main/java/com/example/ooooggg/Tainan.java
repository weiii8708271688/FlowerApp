package com.example.ooooggg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;
//Textview改圖片大小方法
import android.os.Handler;
import android.os.Message;
import 	android.widget.*;
import 	android.graphics.drawable.Drawable;
//畫圓形
import android.widget.ImageView;
import android.graphics.*;
import android.content.*;
import android.util.*;
import android.graphics.drawable.*;
import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Tainan extends AppCompatActivity implements OnMapReadyCallback  {
    Context context=this;
    private GoogleMap mMap;
    public static ImageView[] weatherImg;
    public static TextView[] weatherText;
    public static Drawable sun;
    public static Drawable suncloud;
    public static Drawable cloud;
    public static Drawable rain;
    public static Drawable heavyrain;
    int[] id = {R.id.day1,R.id.day2,R.id.day3,R.id.day4,R.id.day5,R.id.day6,R.id.day7};
    int[] idText = {R.id.imgtext1,R.id.imgtext2,R.id.imgtext3,R.id.imgtext4,R.id.imgtext5,R.id.imgtext6,R.id.imgtext7};
    Button b1;
    TextView t1,t2,t3;
    ListView LV;
    int x_last=0;
    String x_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("白河蓮花季");

        //取值方法
        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("name");
        setContentView(R.layout.activity_tainan);
        setToolbar();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //ddate.setText("Calendar获取当前日期"+year+"年"+month+"月"+day+"日"+hour+":"+minute+":"+second);
        // ddate.setText("Calendar获取当前日期"+year+"年"+month+"月"+day+"日"+hour+":"+minute+":"+second);
        b1=(Button)findViewById(R.id.button8);

        t1= (TextView) findViewById(R. id.editTextTextPersonName6);

        LV=(ListView)findViewById(R.id.LV_tainan);


        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("usermessage_tainan");

        final DatabaseReference myRef2=myRef.child("data01");
        firebase_select(myRef2);
        //add
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x_last+=1;

                Calendar calendar = Calendar.getInstance();

                int year = calendar.get(Calendar.YEAR);
                String years= Integer.toString(year);

                int month = calendar.get(Calendar.MONTH)+1;
                String months= Integer.toString(month);

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String days= Integer.toString(day);

                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                //hour=hour+8;
                String hours= Integer.toString(hour);

                int minute = calendar.get(Calendar.MINUTE);
                String minutes= Integer.toString(minute);

                int second = calendar.get(Calendar.SECOND);
                String seconds= Integer.toString(second);

                String bigdate=years+"/"+months+"/"+days+"\n"+hours+":"+minutes+":"+seconds;

                myRef2.child(String.valueOf(x_last)).setValue(new message_string(t1.getText().toString(),bigdate,email));
                firebase_select(myRef2);
                t1.setText("");
            }
        });


        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t1_s=(TextView)view.findViewById(R.id.text1);
                t3.setText(t1_s.getText().toString());
                TextView t2_s= view.findViewById(R.id.text2);
                t1.setText(t2_s.getText().toString());
                TextView t3_s=(TextView)view.findViewById(R.id.text3);
                t2.setText(t3_s.getText().toString());

            }
        });

        LV.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    ViewGroup scrollView;
                    scrollView=(ViewGroup)findViewById(R.id.SV);
                    scrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });


        sun=getResources().getDrawable(R.drawable.sunny);
        suncloud=getResources().getDrawable(R.drawable.suncloud);
        cloud=getResources().getDrawable(R.drawable.cloud);
        rain=getResources().getDrawable(R.drawable.rain);
        heavyrain=getResources().getDrawable(R.drawable.heavyrain);
        weatherImg = new ImageView[7];
        weatherText = new TextView[7];
        for(int i=0;i<7;i++){
            weatherImg[i] = findViewById(id[i]);
            weatherText[i] = findViewById(idText[i]);
        }
        Thread t1 = new TainanThread();
        t1.start();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //賞花期
        TextView textView1= (TextView) findViewById(R.id.textView1);
        Drawable drawable1=getResources().getDrawable(R.drawable.images_icon1);
        drawable1.setBounds(0,20,100,120);
        textView1.setCompoundDrawables(drawable1,null,null,null);

        //天氣
        TextView textView2= (TextView) findViewById(R.id.weather);
        Drawable drawable2=getResources().getDrawable(R.drawable.weather);
        drawable2.setBounds(0,20,100,120);
        textView2.setCompoundDrawables(drawable2,null,null,null);

        //map
        TextView textView3= (TextView) findViewById(R.id.maptext);
        Drawable drawable3=getResources().getDrawable(R.drawable.map);
        drawable3.setBounds(0,20,100,120);
        textView3.setCompoundDrawables(drawable3,null,null,null);

        //tour
        TextView textView4= (TextView) findViewById(R.id.tour);
        Drawable drawable4=getResources().getDrawable(R.drawable.tour);
        drawable4.setBounds(0,20,100,120);
        textView4.setCompoundDrawables(drawable4,null,null,null);

        //newres
        TextView textView5= (TextView) findViewById(R.id.newres);
        Drawable drawable5=getResources().getDrawable(R.drawable.newres);
        drawable5.setBounds(0,20,100,120);
        textView5.setCompoundDrawables(drawable5,null,null,null);

        //tour
        TextView textView6= (TextView) findViewById(R.id.res);
        Drawable drawable6=getResources().getDrawable(R.drawable.resp);
        drawable6.setBounds(0,20,100,120);
        textView6.setCompoundDrawables(drawable6,null,null,null);

        //parking
        TextView textView7= (TextView) findViewById(R.id.parking);
        Drawable drawable7=getResources().getDrawable(R.drawable.parking);
        drawable7.setBounds(0,20,100,120);
        textView7.setCompoundDrawables(drawable7,null,null,null);


        //星期
        SimpleDateFormat sdf = new SimpleDateFormat("E"); // 設定時間格式
        Calendar cal = Calendar.getInstance();
        TextView[] imgDay = new TextView[7];
        int[] idDay = {R.id.imgday1,R.id.imgday2,R.id.imgday3,R.id.imgday4,R.id.imgday5,R.id.imgday6,R.id.imgday7};
        for(int i=0;i<7;i++){
            imgDay[i] = findViewById(idDay[i]);
            imgDay[i].setText(sdf.format(cal.getTime()));
            imgDay[i].setTextSize(30);
            cal.add(Calendar.DATE,1);
        }

        //button
        ImageButton btn1;
        btn1 = findViewById(R.id.Baihe_seemore1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("https://www.klook.com/zh-TW/blog/hot-spring-guanziling-tainan-taiwan/");
                Intent i=new Intent(Intent.ACTION_VIEW ,uri);
                startActivity(i);
            }
        });


        ImageButton btn2;
        btn2 = findViewById(R.id.Baihe_seemore2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("https://www.siraya-nsa.gov.tw/zh-tw/attractions/detail/2");
                Intent i=new Intent(Intent.ACTION_VIEW ,uri);
                startActivity(i);
            }
        });


        ImageButton btn3;
        btn3 = findViewById(R.id.Baihe_seemore3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("https://www.siraya-nsa.gov.tw/zh-tw/attractions/detail/33");
                Intent i=new Intent(Intent.ACTION_VIEW ,uri);
                startActivity(i);
            }
        });

        //parking
        TextView parking1=(TextView)findViewById(R.id.tainanP1);
        parking1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("https://www.google.com.tw/maps/place/%E7%99%BD%E6%B2%B3%E5%81%9C%E8%BB%8A%E5%A0%B4/@23.3872466,120.347757,13z/data=!4m9!1m2!2m1!1z55m95rKz5YGc6LuK5aC0!3m5!1s0x346e91949aa4e2c3:0x2ce5ebcb9948ff1f!8m2!3d23.3872466!4d120.4177948!15sCg_nmb3msrPlgZzou4rloLSSARRwdWJsaWNfcGFya2luZ19zcGFjZQ?hl=zh-TW");
                Intent i=new Intent(Intent.ACTION_VIEW ,uri);
                startActivity(i);
            }
        });

        TextView parking2=(TextView)findViewById(R.id.tainanP2);
        parking2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("https://www.google.com.tw/maps/place/%E7%AB%B9%E9%96%80%E9%88%BA%E9%9B%AA%E5%86%B0%E6%9E%9D%E5%BA%97%E5%81%9C%E8%BB%8A%E5%A0%B4/@23.3981177,120.3965088,13z/data=!4m9!1m2!2m1!1z55m95rKz5YGc6LuK5aC0!3m5!1s0x346e9218332ed865:0x2ca3982087564dfd!8m2!3d23.3764764!4d120.4464893!15sCg_nmb3msrPlgZzou4rloLSSAQtwYXJraW5nX2xvdA?hl=zh-TW");
                Intent i=new Intent(Intent.ACTION_VIEW ,uri);
                startActivity(i);
            }
        });

        TextView parking3=(TextView)findViewById(R.id.tainanP3);
        parking3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("https://www.google.com.tw/maps/place/%E7%99%BD%E6%B2%B3%E8%93%AE%E5%90%88%E5%9C%8B%E4%BC%91%E9%96%92%E7%94%9F%E6%85%8B%E5%9C%92%E5%8D%80/@23.3555729,120.4093753,17z/data=!3m1!4b1!4m5!3m4!1s0x346e8e18697abffd:0xcf6fec4d7162d062!8m2!3d23.355573!4d120.41386?hl=zh-TW");
                Intent i=new Intent(Intent.ACTION_VIEW ,uri);
                startActivity(i);
            }
        });

        TextView parking4=(TextView)findViewById(R.id.tainanP4);
        parking4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("https://www.google.com.tw/maps/place/%E7%99%BD%E8%8D%B7%E8%93%AE%E9%A6%99%E4%BA%AD/@23.3669968,120.4098224,17z/data=!3m1!4b1!4m5!3m4!1s0x346e940008c8d701:0x9dbd3a504bc5b92c!8m2!3d23.3669968!4d120.4120111?hl=zh-TW");
                Intent i=new Intent(Intent.ACTION_VIEW ,uri);
                startActivity(i);
            }
        });


    }

    private void firebase_select(DatabaseReference db) {

        final List<Map<String, Object>> items=new ArrayList<Map<String, Object>>();
        db.addListenerForSingleValueEvent(new ValueEventListener(){

            //取值方法
            Bundle bundle = getIntent().getExtras();
            String email = bundle.getString("name");
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot) {
                int x_sum=(int)dataSnapshot.getChildrenCount();

                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    message_string user_data=ds.getValue (message_string.class);
                    Map<String, Object> item=new HashMap<String, Object>();
                    item.put("email",user_data.getEmail());
                    // item.put("name",ds.getKey());
                    item.put("message",user_data.getmessage());
                    item.put("time",user_data.gettime());
                    //item.put("time",user_data.gettime());
                    items.add(item);
                    x_last=Integer.parseInt((ds.getKey()));

                }

                //  SimpleAdapter SA=new SimpleAdapter(context,items,R.layout.message_string,new String[]{"email","message","time"},new int[]{R.id.text1,R.id.text2,R.id.text3});
                MyAdapter SA=new MyAdapter(context,items);
                LV.setAdapter(SA);

                LV.setOnItemClickListener(new ListView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //Toast.makeText(context,items.get(i).get("message").toString(),Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

         /*   @Override
            public void onCancelled (@NonNull DataSnapshot dataSnapshot) {

            }*/

        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoomLevel = 16.0f;
       /* CameraUpdateFactory.zoomTo(16);

        mapController = mMap.getController();
        mapController.setZoom(17); //設定放大倍率1(地球)-21(街景)*/
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(23.387611, 120.417714);
        mMap.addMarker(new MarkerOptions().position(sydney).title("白河蓮花公園"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(18));
    }

    public static Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle gotMsg = msg.getData();
            ArrayList a1 = gotMsg.getIntegerArrayList("MaxT");
            ArrayList a2 = gotMsg.getIntegerArrayList("MinT");
            ArrayList a3 = gotMsg.getIntegerArrayList("Wx");
            //test.setText(a1.get(2).toString()+" "+a2.get(2).toString());
            Drawable now = sun;
            for (int i = 0; i < 7; i++) {
                int ele = Integer.parseInt(a3.get(i).toString());
                if (ele == 1) {
                    now = sun;
                } else if (ele >= 2 && ele <= 3) {
                    now = suncloud;
                } else if (ele >= 4 && ele <= 6) {
                    now = cloud;
                } else if ((ele >= 7 && ele <= 20) || (ele >= 29 && ele <= 32) || (ele >= 37 && ele <= 41)) {
                    now = rain;
                } else if ((ele >= 21 && ele <= 22) || (ele >= 33 && ele <= 36)) {
                    now = heavyrain;
                }
                weatherImg[i].setImageDrawable(now);
                weatherText[i].setText(a1.get(i).toString()+"°   "+a2.get(i).toString()+"°");
                weatherText[i].setTextSize(30);
            }
        }
    };
    private void setToolbar() {
        /**初始化Toolbar*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        /**將Toolbar綁定到setSupportActionBar*/
        setSupportActionBar(toolbar);
        /**設置大標題*/
        getSupportActionBar().setTitle("白河蓮花季");
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