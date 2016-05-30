package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 16-5-13.
 */
public class AirplaneActivity extends Activity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AirplaneView a = new AirplaneView(this);

        setContentView(a);
    }


}
