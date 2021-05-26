package com.swufe.helloworld;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RateCalculate extends AppCompatActivity implements TextWatcher {
    String TAG = "RateCalculate";
    String re;
    float rate = 0f,inp = 0f;

    TextView currency,result;
    EditText rmb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_calculate);
        String title = getIntent().getStringExtra("title");
        rate = getIntent().getFloatExtra("detail", 0f);
        Log.i(TAG, "onCreate:title= " + title);
        Log.i(TAG, "onCreate: rate=" + rate);

        currency = (TextView) findViewById(R.id.currency);
        result = (TextView) findViewById(R.id.re);
        rmb = (EditText) findViewById(R.id.amount);
        //给用户输入添加监听
        rmb.addTextChangedListener(this);
        currency.setText(title);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        MyTextChange();
    }

    @Override
    public void afterTextChanged(Editable s) {
        MyTextChange();
    }

    private void MyTextChange() {
        if (rmb != null) {
            inp = Float.parseFloat(String.valueOf(rmb.getText()));
            float output = rate * (100/inp);
            re = String.valueOf(output);
        } else {
            re = "Please  Input";
        }

        result.setText(re);
    }
}