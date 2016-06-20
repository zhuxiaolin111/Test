package com.example.administrator.test.individuation;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.administrator.test.R;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LingshengFragment extends Fragment {
	private ListView music_list_view;
	private SimpleAdapter adapter;
	private File path;
	private List<Map<String, Object>> musicInfo;
	private MediaPlayer mp = new MediaPlayer();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_lingsheng_fragment, container, false);
		// 检测SD卡是否存在
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					path = Environment.getExternalStorageDirectory();
				} else {
					Toast.makeText(getActivity(), "没有SD卡", Toast.LENGTH_LONG).show();
					getActivity().finish();
				}
				music_list_view = (ListView)view.findViewById(R.id.music_list_view);
				musicInfo = new ArrayList<Map<String,Object>>();
				getMusic(path);
				Log.i("-------", musicInfo.toString());
				adapter = new SimpleAdapter(
						getActivity(), 
						musicInfo, 
						R.layout.layout_music_item, 
						new String[]{"musicName"}, 
						new int[]{R.id.music_text_view});
				music_list_view.setAdapter(adapter);
				music_list_view.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						final Map<String, Object> map1= musicInfo.get(position);
						Builder builder = new Builder(getActivity());
						builder.setTitle("正在播放")
						.setMessage(map1.get("musicName").toString())
						.setPositiveButton("播放", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								try {
									if (!mp.isPlaying()) {
										mp.setDataSource(map1.get("musicPath").toString());
										mp.prepare();
										mp.start();
										Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing"); 
										field.setAccessible(true); 
										field.set(dialog, false);
									}
								} catch (IllegalArgumentException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (SecurityException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IllegalStateException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (NoSuchFieldException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						})
						.setNegativeButton("停止", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								mp.stop();
								mp.reset();
								try {
									Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
									field.setAccessible(true);
									field.set(dialog, true);
								} catch (NoSuchFieldException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
						}).create().show();
					}
				});
				music_list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						Builder builder = new Builder(getActivity());
						builder.setTitle("提示")
						.setMessage("是否设置为铃声")
						.setPositiveButton("是", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Toast.makeText(getActivity(), "设置铃声成功", Toast.LENGTH_SHORT).show();
							}
						})
						.setNegativeButton("否", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dialog.dismiss();
							}
						}).create().show();
						return false;
					}
				});
		return view;
	}
	public List<Map<String, Object>> getMusic(File paths) {
		File files[] = paths.listFiles();
		if(files != null){
            for (File file : files){  
                if(file.isDirectory()){  
                	getMusic(file);
                }else{
                	if (file.getName().endsWith(".mp3")) {
                		Map<String,Object> map = new HashMap<String, Object>();
                		map.put("musicName", file.getName());
                		map.put("musicPath", file);
                    	musicInfo.add(map);
    				}
                }  
            }  
        }
		return musicInfo;
	}
}
