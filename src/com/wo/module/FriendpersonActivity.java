package com.wo.module;

import http.HttpDown;
import image.SmartImageView;

import bean.Friendbean;

import com.gemptc.activities.R;
import com.gemptc.activities.R.id;
import com.gemptc.activities.R.layout;
import com.gemptc.activities.R.menu;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wo.applation.SysApplication;

import adapter.FriendAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class FriendpersonActivity extends Activity {
	int a = 0;
	SmartImageView imageView;
	ToggleButton toggle;
	Friendbean friend;
	Intent intent;
	HttpUtils http = new HttpUtils();
	String url = SysApplication.httppath + "ignorefriend";
    ListView  listview ;
   FriendAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friendperson);
		// 初始化
		init();
		// 判断是否为好友
		judge();
		// 监听
		lison();
		
	}

	private void judge() {
		upload("0");
	}
	private void init() {
		Intent intent = getIntent();
		friend = (Friendbean) intent.getSerializableExtra("friend");
		toggle = (ToggleButton) findViewById(R.id.quxiaoguanzhu);
		imageView = (SmartImageView) findViewById(R.id.wo_imagebt1);
         listview=(ListView) findViewById(R.id.wo_guanzhugeren);
         adapter=new FriendAdapter(friend,this);
         listview.setAdapter(adapter);
	}

	private void lison() {
		// 监听
		toggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					// 添加好友
					if(a==1){
					   upload("1");
					}
				} else {
					// 删除好友
					if(a==1){
						upload("2");
					}
					
				}

			}
		});

	}

	// 上传数据
	public void upload(String s) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("friend_id",
				String.valueOf(friend.getFriend_id()));
		params.addBodyParameter("u_id",
				String.valueOf(SysApplication.userbean.getU_id()));
		params.addBodyParameter("choose",s);
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(FriendpersonActivity.this, "网络错误", 0).show();
			}
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				if (arg0.result.equals("true")) {
					// 该对象为好友
					toggle.setChecked(true);
					a = 1;
				} else if(arg0.equals("false")){
					// 该对象不好友
					toggle.setChecked(false);
					a = 1;
				}
			}
		});
	}
}
