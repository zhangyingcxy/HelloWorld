package com.swufe.helloworld;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RateListActivity extends AppCompatActivity implements Runnable {
    private static final String TAG = "RateListActivity";
    public String logDate ="";
    private final String DATE_SP_KEY = "lastRateDateStr";
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list);   //这时候必须有

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG, "handleMessage: 收到的消息是："+msg.what);
                if(msg.what==5){
                    List<String> getList = (List<String>) msg.obj;
                    for(String item:getList){
                        Log.i(TAG, "handleMessage: "+item);
                    }
                    Toast.makeText(RateListActivity.this, "汇率已更新", Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };


        SharedPreferences sp = getSharedPreferences("myrate", Context.MODE_PRIVATE);
        logDate = sp.getString(DATE_SP_KEY,"");
        Log.i("List", "onCreate: "+logDate);


//        List<String> list1 = new ArrayList<String>();
//        for(int i = 0; i< 100; i++){
//            list1.add("list"+i);
//        }
//        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list1);
//        listView.setAdapter(adapter);

    }

    @Override
    public void run() {
        Log.i("List","run...");
        List<String> retList = new ArrayList<String>();

        String today = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

        if(today.toString().equals(logDate)){
            Log.i(TAG, "onCreate: "+"不需要更新");
            DBManager dbManager = new DBManager(RateListActivity.this);
            for(RateItem rateItem : dbManager.listAll()) {
                retList.add(rateItem.getCurName() + "=>" + rateItem.getCurRate());


            }
        }else {
            //如果不等，需要更新数据，开启子线程
            Thread t = new Thread(this);
            t.start();

            try{
                List<RateItem> rateList = new ArrayList<RateItem>();
                Document doc = Jsoup.connect("http://www.usd-cny.com/bankofchina.htm").get();
                Log.i(TAG, "run: " + doc.title());
                Element tables = doc.getElementsByTag("table").first();
                Elements tds = tables.getElementsByTag("td");

                //获取tds中的数据
                for(int i=0;i<tds.size();i+=6){
                    Element td1 = tds.get(i);
                    Element td2 = tds.get(i+5);
                    String str1 = td1.text();
                    String val = td2.text();
                    Log.i(TAG, "run: " + str1 + "==>" + val);
                    float v = 100f / Float.parseFloat(val);
                    retList.add(str1 + "->" + val);

                    RateItem rateItem = new RateItem(td1.text(),td2.text());
                    rateList.add(rateItem);
                }
                DBManager dbManager = new DBManager(RateListActivity.this);
                dbManager.deleteAll();
                Log.i("db","删除所有记录");
                dbManager.addAll(rateList);
                Log.i("db","添加新记录集");
            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            //更新记录日期
            SharedPreferences sp = getSharedPreferences("myrate", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString(DATE_SP_KEY, today);
            edit.commit();
            Log.i("run","更新日期结束：" + today);

        }
        Message msg = handler.obtainMessage(5);
        msg.obj = retList;
        handler.sendMessage(msg);

    }
}