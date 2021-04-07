package com.swufe.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    int score;
    TextView current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    private void show(int add){
        current = (TextView)findViewById(R.id.score);
        score = score + add;
        current.setText(String.valueOf(score));
    }
    public void bnt_1(View v){
        show(1);
    }
    public void bnt_2(View v){
        show(2);
    }

    public void bnt_3(View v){
        show(3);
    }
    public void reset(View v){
        current.setText(String.valueOf(0));
    }
}