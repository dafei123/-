package com.wo.module.fragement;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import bean.Friendbean;
import bean.Rankingbean;
import bean.Wo_Userbean;

import com.gemptc.activities.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wo.applation.SysApplication;
import com.wo.module.Wo_rankingActivity;

import adapter.RankingFriendadapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class Wo_RankingFriend_Fragement extends Fragment {
	View view;
	ListView rankingfriend;
	List<Rankingbean> list=new ArrayList<Rankingbean>();
	RankingFriendadapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		  down();
		super.onCreate(savedInstanceState);
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
     view=inflater.inflate(R.layout.wo_rankingfriend_fragment, null);
     //初始化
     init();
     //下载数据
   
     return view;
    }
	private void init() {
	
		rankingfriend=(ListView) view.findViewById(R.id.ranking_listview);
	    adapter=new RankingFriendadapter(list,view.getContext());
	    rankingfriend.setAdapter(adapter);
	}
	private void down() {
		HttpUtils http=new HttpUtils();
		RequestParams params =new RequestParams();
		String url=SysApplication.httppath+"Ronking";
		params.addBodyParameter("u_id",String.valueOf(SysApplication.userbean.getU_id()));
		params.addBodyParameter("c","0");
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(view.getContext(), "网络错误", 0).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String a=arg0.result;
				 Gson gson=new Gson();
					Type type = new TypeToken<List<Rankingbean>>(){}.getType();
					List<Rankingbean> list1=gson.fromJson(a, type);					
					list.addAll(list1);
					System.out.println(list.toString());
					adapter.notifyDataSetChanged();		
			}
		});
	}
}
