package com.example.administrator.test.individuation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.administrator.test.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassificationFragment extends Fragment {
	private GridView class_grid_view;
	private ArrayList<Map<String,Object>> list;
	private SimpleAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_class_fragment, container, false);
		class_grid_view = (GridView)view.findViewById(R.id.class_grid_view);
		
		list = new ArrayList<Map<String,Object>>();
		addItem(R.drawable.fengjing, "风景","11张");
		addItem(R.drawable.dongwu, "动物","3张");
		addItem(R.drawable.renwu, "人物","8张");
		addItem(R.drawable.mingche, "名车","2张");
		addItem(R.drawable.youxi, "游戏","10张");
		addItem(R.drawable.dongman, "动漫","9张");
		adapter = new SimpleAdapter(
				getActivity(), 
				list, 
				R.layout.layout_class_item, 
				new String[]{"icon","name","number"},
				new int[]{R.id.class_image_view,R.id.class_text_view_name,R.id.class_text_view_number});
		class_grid_view.setAdapter(adapter);
		return view;
	}
	private void addItem(int iconDrawableId,String name,String number){
        Map<String ,Object> item = new HashMap<String, Object>();
        item.put("icon",iconDrawableId);
        item.put("name", name);
        item.put("number", number);
        list.add(item);
    }
}
