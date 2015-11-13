package com.gemptc.fragments;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sharedPreferencesUtil.SharedHelper;
import sharedPreferencesUtil.StringUtils;
import sharedPreferencesUtil.TimesGet;

import yang.framents.shenghuoFrament;
import yang.framents.studyFrament;
import yang.framents.yuleFrament;

import beanutil.Publish;

import com.gemptc.activities.FaX_LunTanLouZhuActivity;
import com.gemptc.activities.MainActivity;
import com.gemptc.activities.R;
import com.gemptc.activities.Yang_jiluActivity;
import com.gemptc.activities.Yang_jishiActivity;
import com.gemptc.activities.Yang_studyActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wo.applation.SysApplication;
import com.wo.module.Wo_LoginActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

public class MiddleFragment extends Fragment implements OnClickListener {
	LinearLayout yang_layout1,yang_layout2;
	RelativeLayout layout;
	Intent intent;
	View view;
	ViewPager viewPager;
	List<Fragment> fragments = new ArrayList<Fragment>();
	MainActivity activity;
	MyAdapter adapter;
	RadioGroup radioGroup;
	SharedPreferences.Editor editor = null;
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.activity = (MainActivity) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initdata();
	}

	private void initdata() {
		// 初始化fragments，添加学习、生活、娱乐三个碎片
		Fragment study = new studyFrament();
		Fragment shenghuo = new shenghuoFrament();
		Fragment yule = new yuleFrament();
		fragments.add(study);
		fragments.add(shenghuo);
		fragments.add(yule);
		// 不能传入getFragmentManager(),建议使用getChildFragmentManager，否则
		// 后台转前台，报错。
		adapter = new MyAdapter(getChildFragmentManager());
		
		editor = getActivity().getSharedPreferences("mysp", 0).edit();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 取出activity传递过来的数据
		view = inflater.inflate(R.layout.middle, null);
		initData();
		initview();
		return view;
	}

	private void initview() {
		viewPager = (ViewPager) view.findViewById(R.id.yang_viewpager);
		viewPager.setAdapter(adapter);

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					// 说明viewpager滑动到了推荐页面，同步修改推荐选项被选中
					radioGroup.check(R.id.yang_xuexitext);// 参数是当前被选中radiobutton的id
					break;
				case 1:
					radioGroup.check(R.id.yang_shenghuotext);
					break;
				case 2:
					radioGroup.check(R.id.yang_yuletext);
					break;
				default:
					break;
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		// 初始化顶部的单选按钮组
		radioGroup = (RadioGroup) view.findViewById(R.id.yang_toptabs);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			// 同步修改viewpager对应的值
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.yang_xuexitext:
					// viewpager指向推荐界面
					viewPager.setCurrentItem(0);
					break;
				case R.id.yang_shenghuotext:
					viewPager.setCurrentItem(1);
					break;
				case R.id.yang_yuletext:
					viewPager.setCurrentItem(2);
					break;
				default:
					break;
				}

			}
		});
	}

	private void initData() {
		// manager=getFragmentManager();
		yang_layout1=(LinearLayout) view.findViewById(R.id.xxjd);
		yang_layout2=(LinearLayout) view.findViewById(R.id.xxjl);
		// 添加监听器
		yang_layout1.setOnClickListener(this);
		yang_layout2.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		int timesub=0;
		switch (v.getId()) {
		case R.id.xxjd:
			SharedHelper sp = new SharedHelper(getActivity());
			String currentTime = TimesGet.getCurrentTime();	
			Map<String, String> map = sp.read();
			String starttime = map.get("starttime");
			String settime = map.get("settime");
			System.out.println("ddd-----" + currentTime+"-----"+starttime);
			if (!TextUtils.isEmpty(starttime)) { 
				String[] time1 = StringUtils.getTimeSplit(currentTime);//当前时间
				String[] time2 = StringUtils.getTimeSplit(starttime);//倒计时间
				int hours1 = Integer.parseInt(time1[3]) * 3600;
				int min1 = Integer.parseInt(time1[4]) * 60;
				int sec1 = Integer.parseInt(time1[5]);
				int hours2 = Integer.parseInt(time2[3]) * 3600;
				int min2 = Integer.parseInt(time2[4]) * 60;
				int sec2 = Integer.parseInt(time2[5]);
				timesub = hours1 + min1 + sec1 - hours2 - min2 - sec2;		
				if (Integer.parseInt(time1[2]) <= Integer.parseInt(time2[2])) {//判断是否是当天
					if(Integer.parseInt(settime)>timesub){
						//判断设置时间是否大于时间差---》
						//大于就跳到计时页面否者跳到设置时间页面
					intent = new Intent(getActivity(), Yang_jishiActivity.class);
					intent.putExtra("flag", 0);
					intent.putExtra("timesub", timesub);
					intent.putExtra("settime", settime);
					startActivity(intent);	
					} else {
						//editor.clear();
						//editor.commit();
						intent= new Intent(getActivity(), Yang_studyActivity.class);// content获取当前界面
						startActivity(intent);
					}
								
				} else {
//					editor.clear();
//					editor.commit();
					intent= new Intent(getActivity(), Yang_studyActivity.class);// content获取当前界面
					startActivity(intent);
				}
			}else{				
				intent = new Intent(getActivity(), Yang_studyActivity.class);// content获取当前界面
				startActivity(intent);
			}		
			break;
		case R.id.xxjl:
			if(SysApplication.userbean.getU_id()==0){
				jump();
			}else{
				intent = new Intent(getActivity(), Yang_jiluActivity.class);
				startActivity(intent);
			}			
			break;
		default:
			break;
		}
		

	}

	// 写一个适配器，继承FragmentPagerAdapter
	class MyAdapter extends FragmentPagerAdapter {

		public MyAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return fragments.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return fragments.size();
		}
	}
	public void jump(){
		AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
		dialog.setTitle("请先登录");
		dialog.setMessage("你还没有登录，是否登录");
		dialog.setPositiveButton("是",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent1=new Intent(getActivity(), Wo_LoginActivity.class);
				startActivity(intent1);
			}
		});
		dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		});
		dialog.create();
		dialog.show();
		
	}
	 
}
