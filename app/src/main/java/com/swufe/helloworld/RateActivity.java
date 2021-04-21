package com.swufe.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RateActivity extends AppCompatActivity {

    TextView result;
    EditText money;
    Float dollar_rate ;
    Float euro_rate ;
    Float han_rate ;
    Float inpu,convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dollar_rate= 0.1539f;
        euro_rate = 0.1282f;
        han_rate = 172.21f;

        setContentView(R.layout.activity_rate);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("dollar", "onActivityResult: ");
        switch(requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String dollar = data.getStringExtra(AutoRateActivity.EXTRA_DOLLAR);
                    String euro = data.getStringExtra(AutoRateActivity.EXTRA_EURO);
                    String han = data.getStringExtra(AutoRateActivity.EXTRA_HAN);
                    dollar_rate = Float.parseFloat(dollar);
                    euro_rate = Float.parseFloat(euro);
                    han_rate = Float.parseFloat(han);
                    Log.d("dollar", "onActivityResult: "+dollar_rate+euro_rate+han_rate);

                }
                break;
            default:
        }
    }

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
             convert = inpu * euro_rate ;
         }else if(v.getId()==R.id.dollar) {
             convert = dollar_rate * inpu;
         }
         else{
             convert = han_rate * inpu;
         }
//         String convert_str = String.valueOf(convert);
         String convert_str = String.format("%.2f",convert);
         result.setText(convert_str);
     }
     public void jump(View v){
         Intent jump = new Intent(RateActivity.this,AutoRateActivity.class);
         startActivityForResult(jump,1);
     }
     public  void  JD(View v){
         Intent jd= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jd.com"));
         startActivity(jd);

     }

}