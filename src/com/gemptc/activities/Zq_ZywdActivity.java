package com.gemptc.activities;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import sharedPreferencesUtil.TimesGet;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wo.applation.SysApplication;
import com.zq.adapter.QuestionListViewAdapter;

import beanutil.MyApplication;
import beanutil.Question;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class Zq_ZywdActivity extends Activity {

	private final String TAG="XListViewActivity";
	//private Handler handler;
	String time=TimesGet.getCurrentTime();
	private ImageView ivzywd,ivAddzywd;//返回专区页面。添加问题
	private XListView lvlist;//问题列表
	List<Question> list=new ArrayList<Question>();
	private HttpUtils httpUtils;
	private MyApplication application;
	private QuestionListViewAdapter adapter;
	private int page=1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zq_zywd);
		SysApplication.getInstance().addActivity(this);		
		click();		
		downloadData("1");
	}	
	String url=application.applicationID + "/Zq_QUSelectServlet";	
	private void downloadData(String a) {
		HttpUtils http=new HttpUtils();
		RequestParams params= new RequestParams();
		params.addBodyParameter("page", a);
		params.addBodyParameter("U_id","1");
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				//获取服务器端返回的数据结果
				String result=arg0.result;
				//使用GSon框架进行json解析
				Gson gson=new Gson();
				Type typeOfT=new TypeToken<List<Question>>(){}.getType();
				List<Question> list1=gson.fromJson(result, typeOfT);
				Log.e("sssssssssssssssssssssss", list1.toString());
				list.addAll(list1);
				adapter.notifyDataSetChanged();
				Log.e("jijgiejfjifejfjfie------------",list.toString());
			}			
		});		
	}
	private void click() {
		
		ivzywd=(ImageView) findViewById(R.id.ivzywd1);
		ivAddzywd=(ImageView) findViewById(R.id.ivAddzywd);
		lvlist=(XListView) findViewById(R.id.lvlistqu);
		
		page=1;
		adapter=new QuestionListViewAdapter(Zq_ZywdActivity.this,list);
		lvlist.setAdapter(adapter);
		
		/*page=0;
		adapter=new QuestionListViewAdapter(Zq_ZywdActivity.this,list);
		lvlist.setAdapter(adapter);*/		
		lvlist.setRefreshTime(time);
		lvlist.setPullRefreshEnable(true);
		lvlist.setPullLoadEnable(true);
		lvlist.setXListViewListener(new IXListViewListener() {
			int a=1;
			@Override
			public void onRefresh() {
				list.clear();
				a=1;
				downloadData(""+a);
				/*lvlist.setAdapter(adapter);
				adapter.notifyDataSetChanged();*/
				lvlist.stopRefresh();

			}
			
			@Override
			public void onLoadMore() {		  
				a++;
				
				downloadData(String.valueOf(a));
				/*lvlist.setAdapter(adapter);
				adapter.notifyDataSetChanged();*/
				onLoad();
			}
		});
		
		ivzywd.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
			Zq_ZywdActivity.this.finish();				
			}
		});
		
		ivAddzywd.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Zq_ZywdActivity.this,Zq_zywd_add.class);
				startActivity(intent);
				finish();
			}
		});
		
		lvlist.setOnItemClickListener(new OnItemClickListener() {
			//单击事件，单击每个item跳转
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {											
				Intent intent=new Intent(Zq_ZywdActivity.this,Zq_zywd_huifu.class);								
				//获取单击的问题id，发帖人的名称，内容等
				Question question=list.get(position-1);
				int Q_id=question.getQ_id();
				intent.putExtra("Q_id", Q_id);
				
				int U_id=SysApplication.userbean.getU_id();
				String U_username=question.getU_username();
				String Q_time=question.getQ_date();
				String Q_content=question.getQ_content();
				String Q_picture=question.getQ_picture();
				
				
				intent.putExtra("U_id", U_id);
				intent.putExtra("U_username",U_username);
				intent.putExtra("Q_time", Q_time);
				intent.putExtra("Q_content", Q_content);
				intent.putExtra("Q_picture", Q_picture);
				Log.e("pppp", Q_picture);
				startActivity(intent);
			}
		});

	}
	private void onLoad() {
		lvlist.stopRefresh();
		lvlist.stopLoadMore();
		if (time == null) {
			lvlist.setRefreshTime("刚刚");
		} else {
			lvlist.setRefreshTime(time);
		}		
	}
	
}
