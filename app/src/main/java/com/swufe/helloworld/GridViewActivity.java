package com.swufe.helloworld;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        GridView  gridView = (GridView)findViewById(R.id.grid1);
        List<String> list1 = new ArrayList<String >();
        for(int i = 0; i<100;i++){
            list1.add("item"+i);
        }

        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list1);
        gridView.setAdapter(adapter);
        //列表空数据视图
        gridView.setEmptyView(findViewById(R.id.nodata));
     }
}