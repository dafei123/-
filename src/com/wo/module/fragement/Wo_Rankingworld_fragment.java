package com.wo.module.fragement;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import bean.Rankingbean;

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

import adapter.RankingFriendadapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class Wo_Rankingworld_fragment extends Fragment {
	List<Rankingbean> list=new ArrayList<Rankingbean>();
	ListView worldlist;
	RankingFriendadapter adapter;
	 View view;
  @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	  view=inflater.inflate(R.layout.wo_rankingworld_fragment, null);
	  init(); 
	  download();
	return view;
}
private void init() {
	worldlist=(ListView) view.findViewById(R.id.ranking_listviews);
	adapter=new RankingFriendadapter(list, view.getContext());
	worldlist.setAdapter(adapter);
}
public void download(){

	HttpUtils http=new HttpUtils();
	RequestParams params =new RequestParams();
	String url=SysApplication.httppath+"Ronking";
	params.addBodyParameter("u_id",String.valueOf(SysApplication.userbean.getU_id()));
	params.addBodyParameter("c","1");
	http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {
		@Override
		public void onFailure(HttpException arg0, String arg1) {
			Toast.makeText(getActivity(), "网络错误", 0).show();
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
