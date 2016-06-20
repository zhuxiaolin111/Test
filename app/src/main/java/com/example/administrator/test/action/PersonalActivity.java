package com.example.administrator.test.action;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.test.R;
import com.example.administrator.test.admin.ShowAllUserActivity;

public class PersonalActivity extends Activity {
	private TextView una;
	private Button btselectall;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal);
		una = (TextView)findViewById(R.id.una);
		btselectall = (Button)findViewById(R.id.btselectall);
		ActionBar bar = getActionBar();
		bar.setTitle("个人中心");
		Intent intent = getIntent();
		String strName = intent.getStringExtra("username");
		if (strName.equals("admin")) {
			Toast.makeText(PersonalActivity.this, "管理员"+strName+"已登入!", Toast.LENGTH_SHORT).show();
			btselectall.setVisibility(View.VISIBLE);
			btselectall.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(PersonalActivity.this, ShowAllUserActivity.class);
					startActivity(intent);
				}
			});
		}
		una.setText(strName);
	}
}
