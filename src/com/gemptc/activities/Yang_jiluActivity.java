package com.gemptc.activities;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import sharedPreferencesUtil.TimesGet;
import sharedPreferencesUtil.mdTime;

import beanutil.GlobalContants;
import beanutil.Publish;
import beanutil.RecordBean;
import beanutil.StudyBean;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.Legend.LegendForm;
import com.github.mikephil.charting.utils.XLabels.XLabelPosition;
import com.github.mikephil.charting.utils.XLabels;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wo.applation.SysApplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Yang_jiluActivity extends Activity {
	BarChart barChart;
	BarData barData;
	ImageView imageView;
	EditText editText;
	HttpUtils httpUtils;
	List<StudyBean> list=new ArrayList<StudyBean>();
	List<RecordBean> R_list=new ArrayList<RecordBean>();
	int sub=0;
	String S;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yang_jilu);
		initRecord();
		initView();		 
		inithuoqu();
		
	}
	private void initRecord() {
		RequestParams params=new RequestParams();
		final int id=SysApplication.userbean.getU_id();//获取登录的id
		params.addBodyParameter("id",""+id);
		httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.POST, GlobalContants.Recordselect, params,new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				Gson gson = new Gson();
				Type typeOfT = new TypeToken<List<RecordBean>>() {
				}.getType();
				R_list = gson.fromJson(result, typeOfT);
				GlobalContants.R_list2=R_list;
				initdata();		
				Log.e("RRRRRRRRRRSSS", ""+GlobalContants.R_list2.size());
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(Yang_jiluActivity.this, "OUNO", 0).show();
				
			}
		});
		
	}
	private void initshangchuan() {
	RequestParams params2=new RequestParams();
	final int rid=SysApplication.userbean.getU_id();
	String date=mdTime.StringData2();//获取当前时期
	//int sub=(Integer) editText.getTag();
	params2.addBodyParameter("rid",""+rid);
	params2.addBodyParameter("sub",""+sub);
	params2.addBodyParameter("rdate",date);
	httpUtils=new HttpUtils();
	httpUtils.send(HttpMethod.POST, GlobalContants.Record, params2,new RequestCallBack<String>() {
		@Override
		public void onSuccess(ResponseInfo<String> responseInfo) {
			// TODO Auto-generated method stub
			Toast.makeText(Yang_jiluActivity.this, "刘邦又被爆了",Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onFailure(HttpException error, String msg) {
			// TODO Auto-generated method stub
			
		}
	});
	}
	private void inithuoqu() {
		RequestParams params=new RequestParams();
		final int id=SysApplication.userbean.getU_id();//获取登录的id
		String date=mdTime.StringData2();//获取当前时期
		Log.e("刘邦被爆菊", date);
		params.addBodyParameter("id",""+id);
		params.addBodyParameter("date",date); 
		httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.POST, GlobalContants.StudyRecord, params,new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				Log.e("DDDDDDDDDDD", result);
				Gson gson = new Gson();
				Type typeOfT = new TypeToken<List<StudyBean>>() {
				}.getType();
				list = gson.fromJson(result, typeOfT);		
					for (int i =0 ; i < list.size(); i++) {
						Log.e("小邦子", ""+list.size());
						int time=Integer.parseInt(list.get(i).getS_time());
						Log.e("小邦子爆大飞",list.get(i).getS_time());
						 sub+=time;
						Log.e("小邦子穿花衣", ""+time);
						editText.setText(String.valueOf(sub));						
					}	
					initshangchuan();
			}
			@Override
			public void onFailure(HttpException error, String msg) {  
				String text="网络出错啦";
				Toast.makeText(Yang_jiluActivity.this, text, Toast.LENGTH_SHORT)
				.show();
				
			}
		});
		
	}
	private void initView() {
		imageView=(ImageView) findViewById(R.id.yang_jlback);
		editText=(EditText) findViewById(R.id.yang_jltoday1);
		editText.setFocusable(false);
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}
	private void initdata() {
		barChart=(BarChart) findViewById(R.id.yang_jlchart);
		//Integer.parseInt(mdTime.StringData())获取mDay+"日"+"/星期"+mWay;  
		Log.e("SSSSSSSSSSSSSSAAAA", ""+mdTime.StringData());
		barData=getbarData(Integer.parseInt(mdTime.StringData()),360);		
		//barChart.setData(Integer.parseInt(mdTime.StringData()),360);
		showBarChart(barChart,barData);
	}
	private void showBarChart(BarChart barChart2, BarData barData2) {
		 barChart2.setDrawBorder(false); //是否在折线图上添加边框
		 barChart2.setDescription("");// 数据描述 
		 barChart2.setDrawGridBackground(false);// 是否显示表格颜色  
		// 表格的的颜色，在这里是是给颜色设置一个透明度 
		 barChart2.setDrawingCacheBackgroundColor(Color.WHITE & 0x70FFFFFF);
		 barChart2.setTouchEnabled(true);// 设置是否可以触摸
		 barChart2.setDragEnabled(true);// 是否可以拖拽 
		 barChart2.setScaleEnabled(true);// 是否可以缩放
		 barChart2.setPinchZoom(false);
		 //barChart2.setBackgroundColor();// 设置背景
		 barChart2.setDrawBarShadow(true);
		 barChart2.setData(barData);// 设置数据
		 Legend mLegend = barChart2.getLegend(); // 设置比例图标示 
		 mLegend.setForm(LegendForm.CIRCLE);// 样式 
		 mLegend.setFormSize(10f);// 字体 
		 mLegend.setTextColor(Color.BLACK);// 颜色   
		 barChart2.animateX(2500); // 立即执行的动画,x轴 
		barChart.setUnit("分钟");//设置单位
		barChart.setValueTextSize(15f);//设置文本的大小值
		XLabels xl=barChart.getXLabels();
		 xl.setPosition(XLabelPosition.BOTTOM);
	     xl.setCenterXLabelText(true);
	     //xl.setTypeface(t);     
	}
	public BarData getbarData(int count, int range) {//content为日期，range最大值
		
		 ArrayList<String> xValues = new ArrayList<String>();
	   int b=count;		
	  // Log.e("小邦子个小SB", ""+b);
	    for (int i = 0; i < GlobalContants.R_list2.size(); i++) {
	    	//String b=GlobalContants.R_list2.get(i).getR_date();		     
		     //String value=GlobalContants.R_list2.get(i).getR_date();
			 xValues.add(b+"日");
			 b--;
		}	
			 ArrayList<BarEntry> yValues = new ArrayList<BarEntry>(); 
			 for (int i = 0; i < GlobalContants.R_list2.size(); i++) {
				// float value = (float) (Math.random() * range/*100以内的随机数*/) + 3;	
		         int value=GlobalContants.R_list2.get(i).getR_sumtime();
		         Log.e("SDSDSDSDS", ""+value);
				 yValues.add(new BarEntry(value, i));	
			}
			 // y轴的数据集合   
			 BarDataSet bDataSet = new BarDataSet(yValues, "学习记录柱状图");  
			 bDataSet.setColor(Color.rgb(114, 188, 233));
			 ArrayList<BarDataSet> barDataSets = new ArrayList<BarDataSet>();
			 barDataSets.add(bDataSet); // add the datasets 
			 BarData barData = new BarData(xValues, barDataSets);
		return barData;
	}

}
