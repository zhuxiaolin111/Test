package com.example.administrator.test.admin;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.test.R;
import com.example.administrator.test.database.MyDbHepler;

import java.util.List;
import java.util.Map;

public class ShowAllUserActivity extends Activity {
	private ListView showall_listview;
	private TextView nulldata;
	private List<Map<String, String>> list;
	
	private SQLiteDatabase db;
	private MyDbHepler helper;
	private SimpleCursorAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_all_user);
		showall_listview = (ListView)findViewById(R.id.showall_listview);
		nulldata = (TextView)findViewById(R.id.nulldata);
		
		helper = new MyDbHepler(this, "user", null, 1);
		db = helper.getReadableDatabase();
		Cursor cursor = db.query(
				"user", 
				null, 
				null, 
				null, 
				null, 
				null, 
				null);
		adapter = new SimpleCursorAdapter(
				this, 
				R.layout.layout_show_all_user_item, 
				cursor, 
				new String[]{"_id","username"}, 
				new int[]{R.id.userid,R.id.username}, 
				SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		showall_listview.setAdapter(adapter);
		showall_listview.setEmptyView(nulldata);
		showall_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub
				Builder builder = new Builder(ShowAllUserActivity.this);
				builder.setTitle("提示")
				.setMessage("确定删除吗")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						View idView = showall_listview.getChildAt(position);
						TextView idtvView = (TextView) idView.findViewById(R.id.userid);
						int userid = Integer.parseInt(idtvView.getText().toString());
						db.delete("user", "_id=?", new String[]{String.valueOf(userid)});
						Cursor cursor = db.query(
								"user", 
								null, 
								null, 
								null, 
								null, 
								null, 
								null);
						adapter = new SimpleCursorAdapter(
								ShowAllUserActivity.this, 
								R.layout.layout_show_all_user_item, 
								cursor, 
								new String[]{"_id","username"}, 
								new int[]{R.id.userid,R.id.username}, 
								SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
						showall_listview.setAdapter(adapter);
						Toast.makeText(ShowAllUserActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				}).create().show();
				return false;
			}
		});
	}
}
