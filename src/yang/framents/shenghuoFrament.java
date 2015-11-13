package yang.framents;

import image.SmartImageView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import yang_view.RefreshListView;
import yang_view.RefreshListView.OnRefreshListener;
import beanutil.GlobalContants;
import beanutil.MyAppalication;

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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class shenghuoFrament extends Fragment {
	private RefreshListView listView;
	View view;
	HttpUtils httpUtils;
	List<LunTanBean> list = new ArrayList<LunTanBean>();
	//List<Publish> listsum;// 第一个为当前获取到的数据，第二个是每次获取之后数据的总和
	MyAdapter adapter;
	int pc = 1;
	RequestParams params2, params;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.y_shenghuo, null);
		initview();
		//initcache();
		initdata();
		return view;
	}
/*	private void initcache() {
		String cache = CacheUtils.getCache(GlobalContants.SERVLER_URL
				+ "?key=2&pc=" + pc, getActivity());
		if (!TextUtils.isEmpty(cache)) {
			// 如果缓存存在，直接解析数据，，无需访问网络
			parseData(cache, false);
		}
		//没有缓存直接访问网络下载数据
		initdata();

	}*/

	private void initview() {
		listView = (RefreshListView) view.findViewById(R.id.yang_shenghuolist);
		adapter = new MyAdapter();
		listView.setAdapter(adapter);
		listView.setOnRefreshListener(new OnRefreshListener() {
			// 设置下拉刷新监听
			@Override
			public void onRefresh() {
			   pc=1;
				initdata();

			}

			// 设置上拉加载
			@Override
			public void onLoadMore() {
				
				// 加载更多数据
				initmoredata();
				listView.onRefreshComplete(true);
			}

		});
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(getActivity(), FaX_LunTanLouZhuActivity.class);
			    intent.putExtra("position", list.get(position));
			    startActivity(intent);
			}
		});
	}

	// 用户最初进入首页，默认加载网络中的第一页数据

	private void initdata() {
		params = new RequestParams();
		params.addQueryStringParameter("key", "2");
		String a=String.valueOf(pc);
		params.addQueryStringParameter("pc", a);
		httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, GlobalContants.SERVLER_URL, params,
				new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = responseInfo.result;
						System.out
								.println("-------->>>>>>>>>>>>>>>>>>>>----------->"
										+ result);
						parseData(result, false);// 调用解析获取的数据方法
						// 调用收起下拉刷新方法
						listView.onRefreshComplete(true);
						/*CacheUtils.setCache(GlobalContants.SERVLER_URL
								+ "?key=2&pc=" + pc, result, getActivity());*/
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub
						Log.e("-------------------------------------------------->>>>>>>",
								msg);
						Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT)
								.show();
						error.printStackTrace();// ?报错错误
						// 调用收起下拉刷新方法
						listView.onRefreshComplete(false);
					}
				});

	}

	private void initmoredata() {
		params2 = new RequestParams();
		params2.addQueryStringParameter("key", "2");
		params2.addQueryStringParameter("pc", "" + (++pc));
		httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, GlobalContants.SERVLER_URL, params2,
				new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						Toast.makeText(getActivity(), arg1, Toast.LENGTH_SHORT)
								.show();
						arg0.printStackTrace();// ?报错错误
						Log.e("ddddddddddddddddddddddd", "ssss");
						// 调用收起下拉刷新方法
						listView.onRefreshComplete(false);

					}
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = responseInfo.result;
						if (!result.isEmpty()) {
							// 网页传回的数值不等于0的时候，下拉加载时获取页面下一页的内容
							System.out
									.println("-------->>>>>>>>>>>>>>>>>>>>----------->"
											+ result);
							parseData(result, true);// 调用解析获取的数据方法
						} else {
							Log.e("zuihou!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", ""+list.size());
							// 网页传回的数值等于0的时候，下拉加载时提醒用户此页已经为最后一页
							Toast.makeText(getActivity(), "最后一页了",
									Toast.LENGTH_SHORT).show();
							listView.onRefreshComplete(true);// 收起加载更多布局
						}

					}
				});

	}

	private void parseData(String result, boolean isMore) {
		Gson gson = new Gson();
		Type typeOfT = new TypeToken<List<LunTanBean>>() {
		}.getType();
		if(!isMore){
			//第一次
			list = gson.fromJson(result, typeOfT);
		} else {					
				list.addAll((Collection) gson.fromJson(result, typeOfT));			
		}
	
		Log.e("hua",list.size() + "--->" + list.toString());

		adapter.notifyDataSetChanged();
	}

	class MyAdapter extends BaseAdapter {
		LunTanBean item;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {

			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		boolean flag = true;
		ViewHolder holder;

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = view.inflate(getActivity(),
						R.layout.yang_dongtaiitem, null);
				holder = new ViewHolder();
				holder.dtname = (TextView) convertView
						.findViewById(R.id.yang_dtname);
				holder.dtdate = (TextView) convertView
						.findViewById(R.id.yang_dtdate);
				holder.dtcontent = (TextView) convertView
						.findViewById(R.id.yang_dtneirong);
				holder.dtimg = (ImageView) convertView
						.findViewById(R.id.yang_dtimg);
				holder.dttouxiang=(SmartImageView) convertView.findViewById(R.id.yang_dttouxiang);
				convertView.setTag(holder);// 忘了写会报空指针
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			System.out.println("itemitemitemitem----" + item);
			item = (LunTanBean) getItem(position);
			System.out.println("itemitemitemitem----" + item);
			holder.dtname.setText("" + item.getU_username());// Settext取的是整值id为int类型需要加上{“”+}
			holder.dtdate.setText(item.getP_date());
			holder.dtcontent.setText(item.getP_content());
			String path = "http://10.204.1.34:8080/Scholar/img2/";
			// myApplication不要忘了在日志文件中获取
			MyAppalication.bitmapUtils.display(holder.dtimg,
					path + item.getP_picture());
			String path2="http://10.204.1.34:8080/Scholar/img2/"+item.getU_image();
			holder.dttouxiang.setstyle("circle");
			holder.dttouxiang.setImageUrl(path2);	
			return convertView;
		}
	}

	static class ViewHolder {
		public TextView dtname, dtdate, dtcontent;
		public ImageView dtimg;
		private SmartImageView dttouxiang;
	}
}
