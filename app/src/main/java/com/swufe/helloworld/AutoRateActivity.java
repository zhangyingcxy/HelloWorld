package com.swufe.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AutoRateActivity extends AppCompatActivity {
    public static final String EXTRA_DOLLAR="com.swufe.helloworld.MESSAGE1";
    public static final String EXTRA_EURO="com.swufe.helloworld.MESSAGE2";
    public static final String EXTRA_HAN="com.swufe.helloworld.MESSAGE3";

//
    private static final String TAG = "AutoRateActivity";
    EditText et_dollar,et_euro,et_han;
    float com_dollor,com_euro,com_han;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_rate);
        Intent com_intent = getIntent();
        com_dollor = com_intent.getFloatExtra("dollar_rate_to",0.0f);
        com_euro = com_intent.getFloatExtra("euro_rate_to",0.0f);
        com_han = com_intent.getFloatExtra("han_rate_to",0.0f);

        et_dollar = (EditText) findViewById(R.id.dollar);
        et_euro = (EditText) findViewById(R.id.euro);
        et_han = (EditText) findViewById(R.id.han);

        et_dollar.setText(String.valueOf(com_dollor));
        et_euro.setText(String.valueOf(com_euro));
        et_han.setText(String.valueOf(com_han));


        Log.i(TAG, "onCreate: AutoRateActivity com_dollor="+com_dollor);
        Log.i(TAG, "onCreate: AutoRateActivity com_euro="+com_euro);
        Log.i(TAG, "onCreate: AutoRateActivity com_han="+com_han);

    }
    

    public void Reset(View v){

        String dollar = et_dollar.getText().toString();
        String euro = et_euro.getText().toString();
        String han = et_han.getText().toString();

        com_dollor = Float.parseFloat(dollar);
        com_euro = Float.parseFloat(euro);
        com_han = Float.parseFloat(han);

        SharedPreferences share = getSharedPreferences("mygrate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putFloat("dollar_key",com_dollor);
        editor.putFloat("euro_key",com_euro);
        editor.putFloat("han_key",com_han);
        editor.apply();



        if (dollar != null && dollar.length()>0 && euro != null && euro.length()>0 && han != null && han.length()>0){
                Intent intent = new Intent();
                //方法一
                Bundle bdl = new Bundle();
                bdl.putFloat("dollar_key",com_dollor);
                bdl.putFloat("euro_key",com_euro);
                bdl.putFloat("han_key",com_han);
                intent.putExtras(bdl);
                setResult(3,intent);
                //方法二
//                intent.putExtra(EXTRA_DOLLAR, dollar);
//                intent.putExtra(EXTRA_EURO, euro);
//                intent.putExtra(EXTRA_HAN, han);
//                setResult(RESULT_OK,intent);
                finish();

        }
        else {
            Toast.makeText(this,"请输入内容",Toast.LENGTH_SHORT).show();
        }
    }
}