package com.swufe.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MyListActivity2 extends AppCompatActivity implements Runnable, AdapterView.OnItemClickListener {

    private static final String TAG = "MyListActivity2";
    String output;
    Handler handler;
    ListView listView3;
    ProgressBar proBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list2);
        listView3 = findViewById(R.id.listview3);
        proBar = findViewById(R.id.parbar);

        Thread t = new  Thread(this);
        t.start();


        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==5){
                    //构造数据
                    ArrayList<HashMap<String,String>> listItems = (ArrayList<HashMap<String,String>>)msg.obj;

                    //生成适配器的Item
//                    SimpleAdapter listItemAdapter = new SimpleAdapter(MyListActivity2.this,listItems,R.layout.list_item,
//                            new  String[]{"ItemTitle","ItemDetail"},
//                            new int[]{R.id.leftitem,R.id.rightitem}
//                    );
//                    listView3.setAdapter(listItemAdapter);
                    //自定义适配器
                    MyAdapter adapter  =new MyAdapter(MyListActivity2.this,R.layout.list_item,listItems);
                    listView3.setAdapter(adapter);

                    proBar.setVisibility(View.GONE);
                    listView3.setVisibility(View.VISIBLE);

                }
                super.handleMessage(msg);
            }
        };
        listView3.setOnItemClickListener(this);


    }

    @Override
    public void run() {
        ArrayList<HashMap<String,String>> ret = new ArrayList<HashMap<String,String>>();
       // List<String> list3 = new ArrayList<String>();
        try {
            Thread.sleep(1000);
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
                HashMap<String,String> map =  new HashMap<String,String>();
                map.put("ItemTitle", str1);
                map.put("ItemDetail",val);
                ret.add(map);
                Log.i(TAG, "run: " + str1 + "==>" + val);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        Message msg = handler.obtainMessage(5);
        msg.obj =ret;
        handler.sendMessage(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //测试
//        Log.i(TAG, "onItemClick: "+parent);
//        Log.i(TAG, "onItemClick: "+view);
//        Log.i(TAG, "onItemClick: "+position);
//        Log.i(TAG, "onItemClick: "+id);
        HashMap<String,String> map = (HashMap<String,String> )listView3.getItemAtPosition(position);
        String titltstr = map.get("ItemTitle");
        String detailstr = map.get("ItemDetail");
        Log.i(TAG, "onItemClick: "+titltstr);
        Log.i(TAG, "onItemClick: "+detailstr);

        //打开新的页面
        Intent rateCal = new Intent(this,RateCalculate.class);
        rateCal.putExtra("title",titltstr);
        rateCal.putExtra("detail",Float.parseFloat(detailstr));
        startActivity(rateCal);

    }
}