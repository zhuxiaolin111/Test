package com.example.administrator.test.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHepler extends SQLiteOpenHelper {
	private final static String DBNAME = "user.db";
	private final static int VERSION = 1;
	public MyDbHepler(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DBNAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table user(" +
				"_id integer primary key autoincrement," +
				"username varchar," +
				"password varchar)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
