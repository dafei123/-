package com.wo.module.fragement;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.gemptc.activities.FaX_LunTanLouZhuActivity;
import com.gemptc.activities.R;
import com.gemptc.beans.LunTanBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wo.applation.SysApplication;

import adapter.Wo_collect_all_adapter;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Wo_Collect_talkfragment extends Fragment {
	ListView collecttalk;
	  View view;
	  List<String[]> list =new ArrayList<String[]>();
	  Wo_collect_all_adapter adapter;
  @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	   view=inflater.inflate(R.layout.wo_collecttalk_fragment, null);
	 collecttalk=  (ListView) view.findViewById(R.id.wo_collect_talklist);
	 //创建适配器并绑定
	init();
	//设置监听
	onclick();
	//下载数据
	download();
	return view;
}
   public void onclick(){
	   collecttalk.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view,  int position,
				long id) {
			HttpUtils http=new HttpUtils();
			RequestParams params=new RequestParams();
			params.addQueryStringParameter("pid",list.get(position)[2]);
			http.send(HttpMethod.GET,SysApplication.httppath+"CollectTalking", params, new RequestCallBack<String>() {
				@Override
				public void onFailure(HttpException arg0, String arg1) {
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					List<LunTanBean>list1=new ArrayList<LunTanBean>();
					String result=arg0.result;
					Gson gson = new Gson();
					Type typeOfT = new TypeToken<List<LunTanBean>>() {
					}.getType();
						list1= gson.fromJson(result, typeOfT);
						Intent intent=new Intent(getActivity(), FaX_LunTanLouZhuActivity.class);
					    intent.putExtra("position", list1.get(0));
					    startActivity(intent);
				}
			});
		
		}
	});
   }
private void download() {
	String url=SysApplication.httppath+"Wo_collect";
	HttpUtils http=new HttpUtils();
	RequestParams params=new RequestParams();
	params.addBodyParameter("userid",String.valueOf(SysApplication.userbean.getU_id()));
	params.addBodyParameter("key","1");
	http.send(HttpMethod.POST, url,params,new RequestCallBack<String>() {
		@Override
		public void onFailure(HttpException arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onSuccess(ResponseInfo<String> arg0) {
			String result=arg0.result;
			Gson gson=new Gson();
			Type type = new TypeToken<List<String[]>>() {}.getType();
			List<String[]> list1=new ArrayList<String[]>();
			list1=gson.fromJson(result, type);
			list.addAll(list1);
			Log.e("list",list.get(0)[0]);
			adapter.notifyDataSetChanged();
		}
	});
	
}
private void init() {
    adapter=new Wo_collect_all_adapter(list,getActivity());
	collecttalk.setAdapter(adapter);
}

}
