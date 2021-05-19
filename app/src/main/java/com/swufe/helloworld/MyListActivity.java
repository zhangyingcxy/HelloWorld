package com.swufe.helloworld;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyListActivity extends ListActivity  implements Runnable{

    private static final String TAG = "RateActivity";
    Handler handler;
    String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //开启子线程
//        RateActivity r = new RateActivity();
//        r.sendHandler(handler);
        Thread t = new  Thread(this);
        t.start();

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG, "handleMessage: 收到的消息是："+msg.what);

                if(msg.what==5){
                    List<String> list2 = (List<String>)msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(MyListActivity.this,
                            android.R.layout.simple_list_item_1,list2);
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };

//        List<String> list1 = new ArrayList<String>();
//        for(int i = 0; i< 100; i++){
//            list1.add("list"+i);
//        }
//        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list1);
//        setListAdapter(adapter);

    }


    @Override
    public void run() {
        List<String> list3 = new ArrayList<String>();
        try {
            Document doc = Jsoup.connect("http://www.usd-cny.com/bankofchina.htm").get();
            Log.i(TAG, "run: " + doc.title());
            Element tables = doc.getElementsByTag("table").first();
            Elements tds = tables.getElementsByTag("td");
            //获取tds中的数据
            for (int i = 0; i < tds.size(); i += 6) {
                Element td1 = tds.get(i);
                Element td2 = tds.get(i + 5);
                String str1 = td1.text();
                String val = td2.text();
                output = str1+"==>"+val;
                list3.add(output);
                Log.i(TAG, "run: " + str1 + "==>" + val);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Message msg = handler.obtainMessage(5);
        msg.obj =list3;
        handler.sendMessage(msg);
    }
}