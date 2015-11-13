package com.gemptc.faxian.fragement;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import bean.Loadview;

import com.example.frash.CustomListView;
import com.example.frash.CustomListView.OnLoadMoreListener;
import com.example.frash.CustomListView.OnRefreshListener;
import com.gemptc.activities.FaX_LunTanLouZhuActivity;
import com.gemptc.activities.R;
import com.gemptc.adapters.Luntanadapter;
import com.gemptc.beans.LunTanBean;
import com.gemptc.util.MyApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class xuebaluntanFragement extends Fragment {
	CustomListView listView;
	public List<LunTanBean> list = new ArrayList<LunTanBean>();
	HttpUtils httpUtils;
	Luntanadapter adapter1;
	private String url= "http://" + MyApplication.getIp()
			+ ":8080/Scholar/GetUpLoadServlet?page=";
	View xuebaluntanView;
	 
	int page=1;
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Loadview.load(getActivity());
		initData();
	}

	private void initData() {
		adapter1 = new Luntanadapter(getActivity(), list);
		
	}
	private void http(final int type) {
		httpUtils = new HttpUtils();
		
		httpUtils.send(HttpRequest.HttpMethod.GET, url+page,
			
				new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// 访问网络失败调用的方法
						Log.e(MyApplication.getLuntanTag(), arg1);
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						//访问网络成功
					
						String result = arg0.result;
						Gson gson = new Gson();
						Type typeOfT = new TypeToken<List<LunTanBean>>() {
						}.getType();
						List<LunTanBean>list1 = gson.fromJson(result, typeOfT);
						adapter1.setList(list);
						Log.e("ccc------------", list.toString());
						adapter1.notifyDataSetChanged();
						
						if (type == 0) {
							// list.addAll(0,liebiaos);
							// 解决重复数据
							int result1 = 0;
							for (LunTanBean suoYou_Detail : list1) {
								boolean flag = true;
								for (LunTanBean suoYou_Detail2 : list) {
									if (suoYou_Detail
											.equals(suoYou_Detail2)) {
										flag = false;
									}
								}
								if (flag) {
									list.add(result1, suoYou_Detail);
									result1++;
								}
							}
							adapter1.notifyDataSetChanged();
							
						} else if (type == 1) {
							for (LunTanBean suoYou_Detail : list1) {
								
								boolean flag = true;
								for (LunTanBean suoYou_Detail2 : list) {
									if (suoYou_Detail
											.equals(suoYou_Detail2)) {
										flag = false;
									}
								}
								if (flag) {
									list.add(suoYou_Detail);
								}
							}
							adapter1.notifyDataSetChanged();
						}
						Loadview.relase();
						listView.onRefreshComplete();
						listView.onLoadMoreComplete();

					}
					
				});
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		xuebaluntanView = inflater.inflate(R.layout.fa_x__lun_tan, null);
		initViews();
		return xuebaluntanView;
	}

	private void initViews() {
		listView =  (CustomListView) xuebaluntanView.findViewById(R.id.listview1);
		listView.setAdapter(adapter1);
		http(1);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(getActivity(), FaX_LunTanLouZhuActivity.class);
			    intent.putExtra("position", list.get(position-1));
			    startActivity(intent);
			}
		});
		listView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {	
				page=0;
				http(0);
				
			}
		});

		listView.setOnLoadListener(new OnLoadMoreListener() {

			@Override
			public void onLoadMore() {
				// TODO 加载更多	
				page=list.size();
				http(1);
			}
		});
		
	
	}

}
