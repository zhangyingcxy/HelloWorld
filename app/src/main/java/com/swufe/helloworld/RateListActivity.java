package com.swufe.helloworld;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RateListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list);   //这时候必须有
        ListView listView = findViewById(R.id.mylist);

        List<String> list1 = new ArrayList<String>();
        for(int i = 0; i< 100; i++){
            list1.add("list"+i);
        }
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list1);
        listView.setAdapter(adapter);



    }
}