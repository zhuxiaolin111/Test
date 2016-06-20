package com.example.administrator.test.simallutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MyJson {
	public static List<Map<String, String>> getJson(String jsonStr){
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		try {
			JSONObject jobj = new JSONObject(jsonStr);
			JSONObject jsonresult = jobj.optJSONObject("result");
			Map<String, String> map = new HashMap<String, String>();
			map.put("city", jsonresult.optString("city"));
			map.put("date", jsonresult.optString("date"));
			map.put("week", jsonresult.optString("week"));
			map.put("weather", jsonresult.optString("weather"));
			map.put("temp", jsonresult.optString("temp"));
			map.put("temphigh", jsonresult.optString("temphigh"));
			map.put("templow", jsonresult.optString("templow"));
			map.put("winddirect", jsonresult.optString("winddirect"));
			map.put("windpower", jsonresult.optString("windpower"));
			map.put("updatetime", jsonresult.optString("updatetime"));
			list.add(map);
			JSONArray jsondaily = jsonresult.optJSONArray("daily");
			for (int i = 0; i < jsondaily.length(); i++) {
				Map<String, String> map1 = new HashMap<String, String>();
				JSONObject json = jsondaily.optJSONObject(i);
				JSONObject jsonday = json.optJSONObject("day");
				JSONObject jsonnight = json.optJSONObject("night");
				map1.put("ybweek", json.optString("week"));
				map1.put("ybweather", jsonday.optString("weather"));
				map1.put("ybtemphigh", jsonday.optString("temphigh"));
				map1.put("ybwinddirect", jsonday.optString("winddirect"));
				map1.put("ybwindpower", jsonday.optString("windpower"));
				map1.put("ybtemplow", jsonnight.optString("templow"));
				list.add(map1);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
