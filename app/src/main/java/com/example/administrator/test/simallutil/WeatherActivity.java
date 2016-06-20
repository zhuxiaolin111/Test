package com.example.administrator.test.simallutil;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.test.R;
import com.example.administrator.test.action.ChangeCityActivity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class WeatherActivity extends Activity {
	private List<Map<String, String>> list;
	private GridView gv;
	private String strpath = "http://api.jisuapi.com/weather/query?appkey=839fe41c39dbdb83&city=%E5%A4%A7%E8%BF%9E";
	private TextView city,date,week,weather,temp,templow,temphigh,winddirect,windpower,updatetime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		ActionBar bar = getActionBar();
		bar.setTitle("天气预报");
		gv = (GridView)findViewById(R.id.gv);
		city = (TextView)findViewById(R.id.city);
		date = (TextView)findViewById(R.id.date);
		week = (TextView)findViewById(R.id.week);
		weather = (TextView)findViewById(R.id.weather);
		temp = (TextView)findViewById(R.id.temp);
		templow = (TextView)findViewById(R.id.templow);
		temphigh = (TextView)findViewById(R.id.temphigh);
		winddirect = (TextView)findViewById(R.id.winddirect);
		windpower = (TextView)findViewById(R.id.windpower);
		updatetime = (TextView)findViewById(R.id.updatetime);
		try {
			list = new MyAsyncTask(this).execute(strpath).get();
			Log.i("List is:", list.toString());
			Map<String, String> map = list.get(0);
			city.setText(map.get("city"));
			date.setText(map.get("date"));
			week.setText(map.get("week"));
			weather.setText(map.get("weather"));
			temp.setText(map.get("temp"));
			temphigh.setText(map.get("temphigh"));
			templow.setText(map.get("templow"));
			winddirect.setText(map.get("winddirect"));
			windpower.setText(map.get("windpower"));
			updatetime.setText(map.get("updatetime"));
			SimpleAdapter adapter = new SimpleAdapter(
					WeatherActivity.this, 
					list, 
					R.layout.layout_weather_item, 
					new String[]{"ybweek","ybweather","ybtemphigh","ybwinddirect","ybwindpower","ybtemplow"}, 
					new int[]{R.id.ybweek,R.id.ybweather,R.id.ybtemphigh,R.id.ybwinddirect,R.id.ybwindpower,R.id.ybtemplow});
			gv.setAdapter(adapter);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.weather, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Toast.makeText(WeatherActivity.this, "更换城市", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(WeatherActivity.this,ChangeCityActivity.class);
		startActivity(intent);
		return super.onOptionsItemSelected(item);
	}
	
}
