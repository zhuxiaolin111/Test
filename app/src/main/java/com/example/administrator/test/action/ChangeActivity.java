package com.example.administrator.test.action;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.test.R;

import java.io.IOException;

public class ChangeActivity extends Activity implements OnClickListener{
	private ImageView change_image_view;
	private Button change_button;
	private Intent intent;
	private int image;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置为全屏
		setContentView(R.layout.activity_change);
		change_image_view = (ImageView)findViewById(R.id.change_image_view);
		change_button = (Button)findViewById(R.id.change_button);
		change_button.setOnClickListener(this);
		intent = getIntent();
		image = intent.getIntExtra("image", 0);
		change_image_view.setImageResource(image);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Resources res=getResources(); 
		BitmapDrawable bmpDraw=(BitmapDrawable)res.getDrawable(image); 
		Bitmap bmp=bmpDraw.getBitmap(); 
		try{ 
		setWallpaper(bmp); 
		}catch(IOException e) { 
		e.printStackTrace(); 
		}
		Toast.makeText(getApplicationContext(), "更换壁纸成功!", Toast.LENGTH_SHORT).show();
	}
}
