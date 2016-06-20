package com.example.administrator.test.individuation;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.test.R;
import com.example.administrator.test.database.MyDbHepler;

public class RegisterActivity extends Activity implements OnClickListener{

	private MyDbHepler helper;
	private SQLiteDatabase db;
	private EditText register_username,register_password;
	private Button register_register;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		helper = new MyDbHepler(this, "user", null, 1);
        db = helper.getReadableDatabase();
        
		register_username = (EditText)findViewById(R.id.register_username);
		register_password = (EditText)findViewById(R.id.register_password);
		register_register = (Button)findViewById(R.id.register_register);
		register_register.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String username = register_username.getText().toString();
		String password = register_password.getText().toString();
		/*switch (v.getId()) {
		case R.id.register_register:
			ContentValues cv = new ContentValues();
			cv.put("username", username);
			cv.put("password", password);
			db.insert("user", "username", cv);
			Toast.makeText(RegisterActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
			break;
		}*/
		if (v.getId()==R.id.register_register) {
			Cursor cursor = db.query(
					"user", 
					new String[]{"username"}, 
					"username=?", 
					new String[]{username}, 
					null, null, null);
			if (cursor.getCount()!=0) {
				Toast.makeText(RegisterActivity.this, "该用户名已存在!", Toast.LENGTH_SHORT).show();
			} else {
				ContentValues cv = new ContentValues();
				cv.put("username", username);
				cv.put("password", password);
				db.insert("user", "username", cv);
				Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
				finish();
			}
		}
		
	}
}
