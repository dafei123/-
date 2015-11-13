package com.wo.module.fragement;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import beanutil.Question;

import com.gemptc.activities.R;
import com.gemptc.activities.Zq_ZywdActivity;
import com.gemptc.activities.Zq_zywd_huifu;
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

public class Wo_Collect_Questionfragment extends Fragment {
	ListView questionlist;
	View view;
	Wo_collect_all_adapter adapter;
	List<String[]> list=new ArrayList<String[]>();
	List<Question>list1=new ArrayList<Question>();
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	view=inflater.inflate(R.layout.wo_collectquestion_fragment, null);
	questionlist=(ListView) view.findViewById(R.id.wo_collect_quesionlist);
	adapter=new Wo_collect_all_adapter(list, getActivity());
	questionlist.setAdapter(adapter);
	download();
	//监听
	onclick();
	return  view ;
}
private void onclick() {
	questionlist.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
		   HttpUtils http=new HttpUtils();
		   RequestParams params=new RequestParams();
		   params.addBodyParameter("qid",list.get(position)[2]);
		   http.send(HttpMethod.POST,SysApplication.httppath+"CollectQuestion", params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result=arg0.result;
				Type typeOfSrc = new TypeToken<List<Question>>(){}.getType();
				Gson gson = new Gson();
				List<Question>list2=gson.fromJson(result, typeOfSrc);
				list1.addAll(list2);
				Intent intent=new Intent(getActivity(),Zq_zywd_huifu.class);								
				//获取单击的问题id，发帖人的名称，内容等
				Question question=list1.get(0);
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
	});
	
}
private void download() {

	String url=SysApplication.httppath+"Wo_collect";
	HttpUtils http=new HttpUtils();
	RequestParams params=new RequestParams();
	params.addBodyParameter("userid",String.valueOf(SysApplication.userbean.getU_id()));
	params.addBodyParameter("key","2");
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

}
