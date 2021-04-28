package com.swufe.helloworld;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RateActivity extends AppCompatActivity  implements Runnable{

    private static final String TAG = "RateActivity";
    TextView result;
    EditText money;
    Float dollar_rate ;
    Float euro_rate ;
    Float han_rate ;
    Float inpu,convert;
    Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        //初始化汇率
        dollar_rate= 0.1539f;
        euro_rate = 0.1282f;
        han_rate = 172.21f;
        //读取保存的数据
        SharedPreferences share = getSharedPreferences("mygrate", Activity.MODE_PRIVATE);
        dollar_rate = share.getFloat("dollar_key",0.1f);
        euro_rate = share.getFloat("euro_key",0.2f);
        han_rate = share.getFloat("han_key",0.3f);
        Log.i(TAG, "onCreate "+dollar_rate);

        //开启子线程
        Thread t = new  Thread(this);
        t.start();
        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG, "handleMessage: 收到的消息是："+msg.what);
                super.handleMessage(msg);
                if(msg.what==5){
                    String str = (String)msg.obj;
                    Log.i(TAG, "handleMessage: 转换为字符串"+str);
                    result = (TextView) findViewById(R.id.result);
                    result.setText("显示消息："+str);
                }

            }


        };

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==3){
            Bundle bundle = data.getExtras();
            dollar_rate = bundle.getFloat("dollar_key",0.0f);
            euro_rate = bundle.getFloat("euro_key",0.0f);
            han_rate = bundle.getFloat("han_key",0.0f);

        }


//        switch(requestCode){
//            case 1:
//                if(resultCode == RESULT_OK){
//                    String dollar = data.getStringExtra(AutoRateActivity.EXTRA_DOLLAR);
//                    String euro = data.getStringExtra(AutoRateActivity.EXTRA_EURO);
//                    String han = data.getStringExtra(AutoRateActivity.EXTRA_HAN);
//
//                    dollar_rate = Float.parseFloat(dollar);
//                    euro_rate = Float.parseFloat(euro);
//                    han_rate = Float.parseFloat(han);
//                    Log.i("dollar", "onActivityResult: "+dollar_rate+euro_rate+han_rate);
//                }
//                break;
//            default:
//        }

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
     //跳转到用户自行设置汇率页面
     public void jump(View v){
        open();

     }

     private void open(){
         Intent jump = new Intent(RateActivity.this,AutoRateActivity.class);
         jump.putExtra("dollar_rate_to",dollar_rate);
         jump.putExtra("euro_rate_to",euro_rate);
         jump.putExtra("han_rate_to",han_rate);
         startActivityForResult(jump,1);

         Log.i(TAG, "jump: dollar_rate="+dollar_rate);
         Log.i(TAG, "jump: euro_rate="+euro_rate);
         Log.i(TAG, "jump: han_rate="+han_rate);
     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.auto_rate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.settings){
            open();
        }
        else {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void run() {
//子线程获取网络数据
        URL url = null;
        try {
            url = new URL("http://www.usd-cny.com/bankofchina.htm");
            HttpURLConnection http  = (HttpURLConnection)url.openConnection();
            InputStream in = http.getInputStream();

            String html = inputStrem2String(in);
            Log.i(TAG, "run: 捕获到的数据："+html);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

//        Log.i(TAG, "run: ......");
//        try {
//            Thread.sleep(3000);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        //返回主线程消息
//        Message msg = handler.obtainMessage(5,"hello");
//        handler.sendMessage(msg);

    }
//将输入流转换为String
    private String inputStrem2String(InputStream inputStream) throws IOException{
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream,"gb2312");
        while(true){
            int rsz = in.read(buffer,0,buffer.length);
            if(rsz<0){
                break;
            }
            out.append(buffer,0,rsz);
        }

        return out.toString();
    }
}