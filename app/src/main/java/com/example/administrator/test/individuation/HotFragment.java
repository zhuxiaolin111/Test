package com.example.administrator.test.individuation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.administrator.test.R;
import com.example.administrator.test.action.ChangeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotFragment extends Fragment implements OnItemClickListener{
	private GridView hot_grid_view;
	private int[] image = new int[]{
			R.drawable.a,
			R.drawable.b,
			R.drawable.c,
			R.drawable.d,
			R.drawable.e,
			R.drawable.f,
			R.drawable.g,
			R.drawable.h,
			R.drawable.i,
			R.drawable.j};
	private SimpleAdapter adapter;
	private List<Map<String, Object>> list;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view  = inflater.inflate(R.layout.activity_hot_fragment, container, false);
		hot_grid_view = (GridView)view.findViewById(R.id.hot_grid_view);
		list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < image.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", image[i]);
			list.add(map);
		}
		adapter = new SimpleAdapter(
				getActivity(), 
				list, 
				R.layout.layout_hot_item, 
				new String[]{"image"}, 
				new int[]{R.id.hot_image_view});
		hot_grid_view.setAdapter(adapter);
		hot_grid_view.setOnItemClickListener(this);
		return view;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "点击", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this.getActivity(), ChangeActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("image", image[position]);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	

}
