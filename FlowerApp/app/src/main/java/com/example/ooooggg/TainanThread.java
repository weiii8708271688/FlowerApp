package com.example.ooooggg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

public class TainanThread extends Thread {
    public void run(){
        try {
            //HttpURLConnection
            //最高溫
            String url = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-079?Authorization=CWB-8DC6CA43-0E15-41F6-942A-DF486DD93867&elementName=MaxT";
            InputStream is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            StringBuilder sb = new StringBuilder();
            int cp;
            while((cp = rd.read()) != -1){
                sb.append((char)cp);
            }
            String weatherStr = sb.toString();
            JSONObject json = new JSONObject(weatherStr);
            JSONObject s = json.getJSONObject("records");
            JSONArray s1 = s.getJSONArray("locations");
            JSONObject s2 = s1.getJSONObject(0);
            JSONArray s3 = s2.getJSONArray("location");
            JSONObject s4 = s3.getJSONObject(28);
            JSONArray s5 = s4.getJSONArray("weatherElement");
            JSONObject s6 = s5.getJSONObject(0);
            JSONArray s7 = s6.getJSONArray("time");

            Calendar cal=Calendar.getInstance();
            int h=cal.get(Calendar.HOUR_OF_DAY);
            int dataIndex = 0;

            if((h>=0&&h<6)||h>=18){
                dataIndex = 1;
            }

            ArrayList<Integer> myArrayList = new ArrayList();
            for(;dataIndex< s7.length();dataIndex+=2){
                String temp;
                temp = s7.getJSONObject(dataIndex).getJSONArray("elementValue").getJSONObject(0).getString("value");
                myArrayList.add(Integer.parseInt(temp));
            }

            //MinT
            String url1 = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-079?Authorization=CWB-8DC6CA43-0E15-41F6-942A-DF486DD93867&elementName=MinT";
            InputStream is1 = new URL(url1).openStream();
            BufferedReader rd1 = new BufferedReader(new InputStreamReader(is1,"UTF-8"));
            StringBuilder sb1 = new StringBuilder();
            int cp1;
            while((cp1 = rd1.read()) != -1){
                sb1.append((char)cp1);
            }
            String weatherStr1 = sb1.toString();
            JSONObject jsonB = new JSONObject(weatherStr1);
            JSONObject sB = jsonB.getJSONObject("records");
            JSONArray sB1 = sB.getJSONArray("locations");
            JSONObject sB2 = sB1.getJSONObject(0);
            JSONArray sB3 = sB2.getJSONArray("location");
            JSONObject sB4 = sB3.getJSONObject(28);
            JSONArray sB5 = sB4.getJSONArray("weatherElement");
            JSONObject sB6 = sB5.getJSONObject(0);
            JSONArray sB7 = sB6.getJSONArray("time");


            int dataIndex1 = 0;

            if((h>=0&&h<6)||h>=18){
                dataIndex1 = 1;
            }

            ArrayList<Integer> myArrayList1 = new ArrayList();
            for(;dataIndex1< sB7.length();dataIndex1+=2){
                String temp;
                temp = sB7.getJSONObject(dataIndex1).getJSONArray("elementValue").getJSONObject(0).getString("value");
                myArrayList1.add(Integer.parseInt(temp));
            }

            //Wx
            String url2 = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-079?Authorization=CWB-8DC6CA43-0E15-41F6-942A-DF486DD93867&elementName=Wx";
            InputStream is2 = new URL(url2).openStream();
            BufferedReader rd2 = new BufferedReader(new InputStreamReader(is2,"UTF-8"));
            StringBuilder sb2 = new StringBuilder();
            int cp2;
            while((cp2 = rd2.read()) != -1){
                sb2.append((char)cp2);
            }
            String weatherStr2 = sb2.toString();
            JSONObject jsonC = new JSONObject(weatherStr2);
            JSONObject sC = jsonC.getJSONObject("records");
            JSONArray sC1 = sC.getJSONArray("locations");
            JSONObject sC2 = sC1.getJSONObject(0);
            JSONArray sC3 = sC2.getJSONArray("location");
            JSONObject sC4 = sC3.getJSONObject(28);
            JSONArray sC5 = sC4.getJSONArray("weatherElement");
            JSONObject sC6 = sC5.getJSONObject(0);
            JSONArray sC7 = sC6.getJSONArray("time");


            int dataIndex2 = 0;

            if((h>=0&&h<6)||h>=17){
                dataIndex2 = 1;
            }

            ArrayList<Integer> myArrayList2 = new ArrayList();
            for(;dataIndex2< sC7.length();dataIndex2+=2){
                String temp;
                temp = sC7.getJSONObject(dataIndex2).getJSONArray("elementValue").getJSONObject(1).getString("value");
                myArrayList2.add(Integer.parseInt(temp));
            }
            Bundle carrier = new Bundle();
            carrier.putIntegerArrayList("MaxT",myArrayList);
            carrier.putIntegerArrayList("MinT",myArrayList1);
            carrier.putIntegerArrayList("Wx",myArrayList2);
            Message msg = new Message();
            msg.setData(carrier);
            Tainan.mHandler.sendMessage(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}