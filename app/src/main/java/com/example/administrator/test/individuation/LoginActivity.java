package com.example.administrator.test.individuation;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.test.R;
import com.example.administrator.test.database.MyDbHepler;

public class LoginActivity extends Activity implements OnClickListener{
	private MyDbHepler helper;
	private static SQLiteDatabase db;
	private EditText login_username,login_password;
	private Button login_login,login_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
     	// 获取ActionBar
     	ActionBar ab = this.getActionBar();
     	// 把标题换为自定义的
     	ab.setTitle("小Z桌面");
        //绑定数据库
        helper = new MyDbHepler(this, "user", null, 1);
        db = helper.getReadableDatabase();
        
        login_username = (EditText)findViewById(R.id.login_username);
        login_password = (EditText)findViewById(R.id.login_password);
        login_login = (Button)findViewById(R.id.login_login);
        login_register = (Button)findViewById(R.id.login_register);
        login_login.setOnClickListener(this);
        login_register.setOnClickListener(this);
        
    }

	@Override
	public void onClick(View v) {
		String username = login_username.getText().toString();
		String password = login_password.getText().toString();
		int id = v.getId();
		switch(id){
		case R.id.login_login:
			Cursor cursor = Login(username,password);
			if(cursor.getCount()==0){
				Toast.makeText(LoginActivity.this, "用户名或密码错误!", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(LoginActivity.this, "用户"+username+",欢迎你!", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(LoginActivity.this,IndexActivity.class);
				intent.putExtra("username", username);
				startActivity(intent);
				finish();
			}
			break;
		case R.id.login_register:
			Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(intent);
			break;
		}
	}
	public static Cursor Login(String username,String password){
    	Cursor cursor = db.query(
				"user", 
				new String[]{"username","password"}, 
				"username=? and password=?", 
				new String[]{username,password}, null, null, null);
    	Log.i("------", cursor.getCount()+"");
    	return cursor;
    }
}
