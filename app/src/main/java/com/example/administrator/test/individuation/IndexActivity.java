package com.example.administrator.test.individuation;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.administrator.test.R;

import java.util.ArrayList;

public class IndexActivity extends FragmentActivity implements
		OnCheckedChangeListener {
	private long startTime = 0;
	private ViewPager main_viewPager;
	private RadioGroup rg;
	private ArrayList<Fragment> fList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		// 获取ActionBar
		ActionBar ab = this.getActionBar();
		// 把标题换为自定义的
		ab.setTitle("小Z桌面");
		main_viewPager = (ViewPager) findViewById(R.id.main_ViewPager);
		rg = (RadioGroup) findViewById(R.id.main_tab_RadioGroup);
		rg.setOnCheckedChangeListener(this);
		InitViewPager();
	}

	private void InitViewPager() {
		// TODO Auto-generated method stub
		main_viewPager = (ViewPager) findViewById(R.id.main_ViewPager);

		fList = new ArrayList<Fragment>();

		Fragment hotFragment = new Fragment();
		Fragment classificationFragment = new ClassificationFragment();
		Fragment lingshengFragment = new LingshengFragment();
		Fragment myFragment = new MyFragment();
		
		//传值给MyFragment
		Intent intent = getIntent();
		String username = intent.getStringExtra("username");
		Bundle bundle = new Bundle();
		bundle.putString("username", username);
		myFragment.setArguments(bundle);

		// 将各Fragment加入数组中
		fList.add(hotFragment);
		fList.add(classificationFragment);
		fList.add(lingshengFragment);
		fList.add(myFragment);

		// 设置ViewPager的设适配器
		main_viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),
				fList));
		// 当前为第一个页面
		main_viewPager.setCurrentItem(0);
		// ViewPager的页面改变监听器
		main_viewPager.setOnPageChangeListener(new MyListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.index, menu);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		// 手机按键按下都会触发该事件
		// 判断是否按下返回键
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (System.currentTimeMillis() - startTime > 2000) {
				Toast.makeText(this, "再次一次退出程序", Toast.LENGTH_LONG).show();
				startTime = System.currentTimeMillis();
			} else {
				this.finish();
			}
		}
		return true;
	}

	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.tcdl:
			Toast.makeText(this, "点击了退出登录", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(IndexActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		// Fragment框跟随
		int current = 0;
		switch (checkedId) {
		case R.id.hot_item:
			current = 0;
			break;
		case R.id.classification_item:
			current = 1;
			break;
		case R.id.lingsheng_item:
			current = 2;
			break;
		case R.id.my_item:
			current = 3;
			break;
		}
		if (main_viewPager.getCurrentItem() != current) {
			main_viewPager.setCurrentItem(current);
		}
	}

	public class MyAdapter extends FragmentPagerAdapter {
		ArrayList<Fragment> list;

		public MyAdapter(FragmentManager fm, ArrayList<Fragment> list) {
			super(fm);
			this.list = list;
		}

		@Override
		public Fragment getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public int getCount() {
			return list.size();
		}
	}

	class MyListener implements OnPageChangeListener {
		// 滑动Fragment让底部按钮跟随
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			int current = main_viewPager.getCurrentItem();
			switch (current) {
			case 0:
				rg.check(R.id.hot_item);
				break;
			case 1:
				rg.check(R.id.classification_item);
				break;
			case 2:
				rg.check(R.id.lingsheng_item);
				break;
			case 3:
				rg.check(R.id.my_item);
				break;
			}
		}

	}
}
