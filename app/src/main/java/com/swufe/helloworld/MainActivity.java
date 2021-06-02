package com.swufe.helloworld;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyListActivity";
    private static final String TB_NAME = "tb_rates";

    TextView out;
    EditText inp;
    Button btn;
    @Override   //改写方法
    protected void onCreate(Bundle savedInstanceState) {    //Bundle打包的参数集合
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise1);       //设置容器视图 R:从res资源目录来的

    }

    public void btn(View view_2) {
        out = (TextView) findViewById(R.id.view2);
        inp = (EditText) findViewById(R.id.view1);
        btn = findViewById(R.id.bt1);
        btn.setText("Convert");

        String inpstr = inp.getText().toString();
        float  ipt = Float.parseFloat(inpstr);
        float  result = ipt * 1.8f + 32;
        out.setText("华氏温度为："+result);

        //写如数据库
//        DBHelper dbHelper = new DBHelper(this);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("curname","swufe");
//        values.put("currate","123");
//        long r = db.insert(TB_NAME,null,values);
//        db.close();
//        Log.i(TAG, "btn: myclick "+r);
        //读取数据库
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query(TB_NAME,null,null,
                null,null,null,null);

        if(cursor !=null){
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("ID"));
                String cname = cursor.getString(cursor.getColumnIndex("CURNAME"));
                String crate = cursor.getString(cursor.getColumnIndex("CURRATE"));
                Log.i(TAG, "myclick:get from"+id+", "+cname+","+crate);
            }
            cursor.close();
        }
        db.close();

    }

}
