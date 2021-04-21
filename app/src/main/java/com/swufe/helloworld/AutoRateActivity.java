package com.swufe.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AutoRateActivity extends AppCompatActivity {
    public static final String EXTRA_DOLLAR="com.swufe.helloworld.MESSAGE1";
    public static final String EXTRA_EURO="com.swufe.helloworld.MESSAGE2";
    public static final String EXTRA_HAN="com.swufe.helloworld.MESSAGE3";

//    TextView result;
    EditText et1,et2,et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_rate);
    }

    Float dollar,euro,han,convert;
    public void Reset(View v){
//        result = (TextView) findViewById(R.id.result);
        et1 = (EditText) findViewById(R.id.dollar);
        et2 = (EditText) findViewById(R.id.euro);
        et3 = (EditText) findViewById(R.id.han);
        String dollar = et1.getText().toString();
        String euro = et2.getText().toString();
        String han = et3.getText().toString();

        if (dollar != null && dollar.length()>0 && euro != null && euro.length()>0 && han != null && han.length()>0){
                Intent intent = new Intent();
                intent.putExtra(EXTRA_DOLLAR, dollar);
                intent.putExtra(EXTRA_EURO, euro);
                intent.putExtra(EXTRA_HAN, han);
                setResult(RESULT_OK,intent);
                finish();

        }
        else {
            Toast.makeText(this,"请输入内容",Toast.LENGTH_SHORT).show();
        }


    }

}