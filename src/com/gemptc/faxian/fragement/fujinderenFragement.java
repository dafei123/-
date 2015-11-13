package com.gemptc.faxian.fragement;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import beanutil.MyAppalication;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;

import com.gemptc.activities.R;
import com.gemptc.adapters.MyAdapter;
import com.gemptc.beans.MyBean;
import com.gemptc.beans.Person;
import com.gemptc.refresh_utils.MyPullListener;
import com.gemptc.refresh_utils.PullToRefreshLayout;
import com.gemptc.refresh_utils.PullToRefreshLayout.OnPullListener;
import com.gemptc.util.MyApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import android.app.Activity;
import android.content.Context;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class fujinderenFragement extends Fragment {
	ListView listView2;
	View fujinrenView;
	HttpUtils httpUtils;
	MyAdapter adapter;
	//上一个界面传来的用户经纬度
	private double latitude;
	private double longitude;
	//用户当前经纬度和其他人的经纬度
	LatLng userLatLng,otherLatLng;
	private List<Person> list =new ArrayList<Person>();//存储从网络上下载数据
	private List<MyBean> list2 = new ArrayList<MyBean>();//存储所有用户名和距离的集合
	Context context;
	Person person;
	private PullToRefreshLayout ptr1;
	private boolean isFirstIn = true;
	String url = "http://" + MyApplication.getIp()
			+ ":8080/Scholar/GetLocationServlet";
	
	int tiaoshu=0;
	int yeshu1=1;
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		Log.e("00000","009999");
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		RequestParams params = new RequestParams();
		params.addBodyParameter("yema","1");
		Log.e("aa","初始化");
		initData(params);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		fujinrenView = inflater.inflate(R.layout.fa_x__fu_jin, null);
		context =fujinrenView.getContext();
		ptr1 = (PullToRefreshLayout) fujinrenView.findViewById(R.id.ren_fresh);
		ptr1.setOnPullListener(new MyPullListener());
		initViews();

		
		return fujinrenView;	
	}
	
	private void initData(RequestParams params) {
	userLatLng=new LatLng(MyAppalication.latitude,MyAppalication.longitude);
	httpUtils = new HttpUtils();
	Log.e("aa","xiazai");
	httpUtils.send(
		HttpMethod.POST, 
		url, 
		params,
		new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				Gson gson = new Gson();
				Type typeOfSrc = new TypeToken<List<Person>>(){}.getType();
				list = gson.fromJson(result, typeOfSrc);
				Log.e("上传", list.size()+"____");
				System.out.println(list.toString());
				
				for (int i = 0; i < list.size(); i++) {
					
					person = list.get(i);
					String u_username = person.getU_username();
					int u_id=person.getU_id();
					String u_image=person.getU_image();
					String u_sex=person.getU_sex();
					int  u_age=person.getU_age();
					double lon = person.getLon();//其他用户经度
					double lat = person.getLan();//其他用户纬度						

					otherLatLng = new LatLng(lat, lon);
					//获取两点之间距离，单位为m
					
						
					
					double distance = DistanceUtil.getDistance(userLatLng, otherLatLng);
					Log.e("dis	",distance+"+++++++++");
//					if (distance<5000) {
					MyBean bean = new MyBean(u_username, u_sex, String.valueOf(u_age), u_image, distance);
					list2.add(bean);
					Log.e("33333333333333333333333", list2.toString());
//					}
					}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				Log.e("fail", msg);
			}
		});
	}
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		if (isFirstIn) {
			ptr1.autoRefresh();
			isFirstIn = false;
		}
	}


	private void initViews() {
		listView2=(ListView)ptr1.findViewById(R.id.listview2);
		adapter=new MyAdapter(context, list2);
		listView2.setAdapter(adapter);
		listView2.setOnItemClickListener(new OnItemClickListener(
				) {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						/*Intent intent=new Intent(getActivity(),FaX_LunTanPaiZhaoActivity.class);
						intent.putExtra("u_id",person.getU_id());
						startActivity(intent);*/
					}
		});
	}
	public class MyPullListener implements OnPullListener {

		@Override
		public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
			// TODO Auto-generated method stub
			// 下拉刷新操作
			new Handler() {
				@Override
				public void handleMessage(Message msg) {
					RequestParams params = new RequestParams();						
				
					int yema=adapter.getCount() / 5 + 1;
					params.addBodyParameter("yema", yema + "");
					tiaoshu=0;	
					list2.clear();
					initData(params);			
					// 千万别忘了告诉控件刷新完毕了哦！
					pullToRefreshLayout
							.refreshFinish(PullToRefreshLayout.SUCCEED);
				}
			}.sendEmptyMessageDelayed(0, 3000);
		}

		@Override
		public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
			// TODO Auto-generated method stub
			// 加载操作
			new Handler() {
				@Override
				public void handleMessage(Message msg) {					
					int yeshu = adapter.getCount() / 5 + 1;
					if (yeshu == yeshu1) {
						Toast.makeText(context,"已全部加在",Toast.LENGTH_LONG).show();
					} else {
						yeshu1 = yeshu1 + 1;
						tiaoshu=adapter.getCount();
						RequestParams params = new RequestParams();
						params.addBodyParameter("u_id", MyApplication.u_id);
						params.addBodyParameter("yema", yeshu1 + "");					   
					    initData(params);
					}
					// 千万别忘了告诉控件加载完毕了哦！
					pullToRefreshLayout
							.loadmoreFinish(PullToRefreshLayout.SUCCEED);
				}
			}.sendEmptyMessageDelayed(0, 3000);
		}
	}	
}
