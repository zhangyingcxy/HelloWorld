package com.swufe.helloworld;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    int score1;
    int score2;
    TextView current1;
    TextView current2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    private void show1(int add){
        current1 = (TextView)findViewById(R.id.score);
        score1 = score1 + add;
        current1.setText(String.valueOf(score1));
    }
    private void show2(int add){
        current2 = (TextView)findViewById(R.id.score_b);
        score2 = score2 + add;
        current2.setText(String.valueOf(score2));
    }

    public void bnt_1(View v){
        if(v.getId()==R.id.score_1){
            show1(1);
        }else {
            show2(1);
        }

    }
    public void bnt_2(View v){
        if(v.getId()==R.id.score_2){
            show1(2);
        }else {
            show2(2);
        }
    }

    public void bnt_3(View v){
        if(v.getId()==R.id.score_3){
            show1(3);
        }else {
            show2(3);
        }
    }
    public void reset(View v){
        current1.setText(String.valueOf(0));
        current2.setText(String.valueOf(0));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        String scoreA =current1.toString();
        String scoreB = current2.toString();

        outState.putString("scoreA",scoreA);
        outState.putString("scoreB",scoreB);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String scoreA = savedInstanceState.getString("scoreA");
        String scoreB = savedInstanceState.getString("scoreB");

        current1.setText(scoreA);
        current2.setText(scoreB);
    }
}