package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


/**
 * Created by Administrator on 2016/5/26.
 */
public abstract class baseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(savedInstanceState);
        //setContentView(R.layout.toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        toolbar.setTitle("地煞");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mif = new MenuInflater(this);
        mif.inflate(R.menu.tool_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "点击了Home图标", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    protected abstract void initContentView(Bundle savedInstanceState);

}
