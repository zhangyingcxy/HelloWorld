package com.swufe.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RateActivity extends AppCompatActivity {

    TextView result;
    EditText money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
    }

    Float inpu,convert;
     public void Convert(View v){
        result = (TextView) findViewById(R.id.result);
        money = (EditText) findViewById(R.id.rate);
        String inp = money.getText().toString();
        if (inp != null && inp.length()>0){
            inpu = Float.parseFloat(inp);
        }
        else {
            Toast.makeText(this,"请输入内容",Toast.LENGTH_SHORT).show();
        }
         if(v.getId()==R.id.euro){
             convert = inpu * 0.1278f;
         }else if(v.getId()==R.id.dollar) {
             convert = 7.0f* inpu;
         }
         else{
             convert = 170.6231f * inpu;
         }
         String convert_str = String.valueOf(convert);
         result.setText(convert_str);
     }
     public void jump(View v){
         Intent jump = new Intent(this,MainActivity2.class);
         startActivity(jump);
     }
     public  void  JD(View v){
         Intent jd= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jd.com"));
         startActivity(jd);

     }

}