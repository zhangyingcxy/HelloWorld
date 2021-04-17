package com.swufe.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AutoRateActivity extends AppCompatActivity {

    TextView result;
    EditText et1,et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_rate);
    }

    Float money,rate,convert;
    public void Convert(View v){
        result = (TextView) findViewById(R.id.result);
        et1 = (EditText) findViewById(R.id.money);
        et2 = (EditText) findViewById(R.id.rate);
        String input_1 = et1.getText().toString();
        String input_2 = et2.getText().toString();

        if (input_1 != null && input_1.length()>0 && input_2 != null && input_2.length()>0){
             money = Float.parseFloat(input_1);
             rate =  Float.parseFloat(input_2);
        }
        else {
            Toast.makeText(this,"请输入内容",Toast.LENGTH_SHORT).show();
        }

        if(v.getId()==R.id.convert){
            convert = money * rate;
        String convert_str = String.valueOf(convert);
        result.setText("结果为："+convert_str);


        }
    }

}