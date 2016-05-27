package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.view.View.*;

/**
 * Created by Administrator on 16-5-16.
 */
public class click_test extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick_test);
        Button bt= (Button) findViewById(R.id.btn);
        bt.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

    }

}
