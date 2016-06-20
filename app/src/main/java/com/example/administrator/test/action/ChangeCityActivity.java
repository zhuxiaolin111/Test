package com.example.administrator.test.action;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.administrator.test.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ChangeCityActivity extends Activity {
	private String path = "http://api.jisuapi.com/weather/city?appkey=839fe41c39dbdb83";
	private List<Map<String, String>> list;
	private GridView citygv;
	ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_city);
		citygv = (GridView)findViewById(R.id.citygv);
		try {
			Log.i("1----------", path);
			list = new AsyncTaskCity().execute(path).get();
			SimpleAdapter adapter = new SimpleAdapter(
					ChangeCityActivity.this, 
					list, 
					R.layout.layout_change_city_item, 
					new String[]{"city"}, 
					new int[]{R.id.city_name});
			citygv.setAdapter(adapter);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static class HttpCity {
		public static String getHttp(String path){
			String result = null;
			HttpURLConnection huc = null;
			try {
				URL url = new URL(path);
				huc = (HttpURLConnection) url.openConnection();
				huc.connect();
				if (huc.getResponseCode()==200) {
					InputStream is = huc.getInputStream();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					int len = 0;
					byte[] buff = new byte[1024];
					while((len=is.read(buff))!=-1){
						baos.write(buff, 0, len);
						baos.flush();
					}
					byte[] data = baos.toByteArray();
					result = new String(data, 0, data.length);
					baos.close();
					is.close();
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try {
					if (huc!=null) {
						huc.disconnect();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			Log.i("3----------", result.toString());
			return result;
		}
	}
	public static class JsonCity{
		public static List<Map<String, String>> getHttp(String jsonStr){
			List<Map<String, String>> list=new ArrayList<Map<String,String>>();
			Map<String, String> map;
			try {
				JSONObject jobj = new JSONObject(jsonStr);
				JSONArray jsonresult = jobj.optJSONArray("result");
				for (int i = 0; i < jsonresult.length(); i++) {
					map = new HashMap<String, String>();
					JSONObject jobjhaha = jsonresult.optJSONObject(i);
					map.put("city", jobjhaha.optString("city"));
					list.add(map);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.i("7----------", list.toString());
			return list;
		}
	}
	class AsyncTaskCity extends AsyncTask<String, Void, List<Map<String, String>>>{

		@Override
		protected List<Map<String, String>> doInBackground(String... params) {
			// TODO Auto-generated method stub
			return JsonCity.getHttp(HttpCity.getHttp(params[0]));
		}

		@Override
		protected void onPostExecute(List<Map<String, String>> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//dialog.dismiss();
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = new ProgressDialog(ChangeCityActivity.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setTitle("Loadding");
			dialog.setMessage("Loadding...");
			dialog.setIcon(R.drawable.z);
			//显示
			dialog.show();
		}
		
	}
}
