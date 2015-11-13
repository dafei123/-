package com.wo.module;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import http.HttpDown;

import bean.Friendbean;
import bean.Wo_Userbean;

import com.gemptc.activities.R;
import com.gemptc.activities.R.id;
import com.gemptc.activities.R.layout;
import com.gemptc.activities.R.menu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wo.applation.SysApplication;

import adapter.FriendmsgAdapter;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Wo_FriendmsgActivity extends Activity implements OnClickListener {
   TextView add;
   ImageView back;
   ListView friendlist;
   FriendmsgAdapter  adapter;
   List<Friendbean> list=new ArrayList<Friendbean>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wo__friendmsg);
		back=(ImageView) findViewById(R.id.friendback);
		friendlist=(ListView) findViewById(R.id.wo_friendlist);
	    adapter=new FriendmsgAdapter(list,this);
		friendlist.setAdapter(adapter);
	//下载好友数据
		downfrend();
	//设置监听事件
		onclickdown();
	}
	private void downfrend() {
		HttpUtils http=new HttpUtils();
		String url=SysApplication.httppath+"Friendmsg";
		RequestParams params=new RequestParams();
		System.out.println(SysApplication.userbean.getU_id());
		params.addBodyParameter("id",String.valueOf(SysApplication.userbean.getU_id()));
		http.send(HttpMethod.POST,url,params, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
			Toast.makeText(Wo_FriendmsgActivity.this, "网络错误", 0).show();	
			}
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
			 String a=arg0.result;
			 Gson gson=new Gson();
				Type type = new TypeToken<List<Friendbean>>(){}.getType();
				List<Friendbean> list1=gson.fromJson(a, type);	
				if(list==null){
					list.addAll(list1);
					adapter.notifyDataSetChanged();		
				}
				else{
				  list.clear();
			       list.addAll(list1);
			       adapter.notifyDataSetChanged();
				}
			}
		});
	}
	@Override
	public void onClick(View v) {
	 switch(v.getId()){
	 case R.id.friendback:
		 finish();
	 }	
	}
  public void onclickdown(){
	  friendlist.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Friendbean friend=list.get(position);
			Intent intent=new Intent(Wo_FriendmsgActivity.this,FriendpersonActivity.class);
			intent.putExtra("friend", friend);
			startActivity(intent);			
		}
	});
  }
 @Override
	protected void onRestart() {
	      downfrend();
		super.onRestart();
	}
}