package com.swufe.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
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

    }

}
