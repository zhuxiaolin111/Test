package com.example.administrator.test.individuation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.test.R;
import com.example.administrator.test.action.AboutActivity;
import com.example.administrator.test.action.PersonalActivity;
import com.example.administrator.test.simallutil.WeatherActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFragment extends Fragment {
	private List<Map<String, Object>> list;
	private String[] strData = new String[]{"个人中心","小工具","关于","设置"};
	private int[] intData = new int[]{R.drawable.my,R.drawable.tool,R.drawable.about};
	private SimpleAdapter adapter;
	private ListView my_list_view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_my_fragment, container, false);
		my_list_view = (ListView)view.findViewById(R.id.my_list_view);
		list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < strData.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", intData[i]);
			map.put("title", strData[i]);
			list.add(map);
		}
		adapter = new SimpleAdapter(
				this.getActivity(), 
				list, 
				R.layout.layout_my_item, 
				new String[]{"image","title"}, 
				new int[]{R.id.my_image_view,R.id.my_text_view});
		my_list_view.setAdapter(adapter);
		my_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 我的中的点击事件
				switch (position) {
				case 0:
					Intent intent = new Intent(getActivity(),PersonalActivity.class);
					Bundle bundle = getArguments();
					String strName = bundle.getString("username");
					intent.putExtra("username", strName);
					startActivity(intent);
					break;
				case 2:
					Intent intent2 = new Intent(getActivity(),AboutActivity.class);
					startActivity(intent2);
					break;
				case 1:
					Intent intent1 = new Intent(getActivity(),WeatherActivity.class);
					startActivity(intent1);
					break;
				}
			}
		});
		return view;
	}
}
