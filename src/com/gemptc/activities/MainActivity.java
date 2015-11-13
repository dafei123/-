package com.gemptc.activities;

import beanutil.MyAppalication;
import beanutil.MyApplication;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.gemptc.fragments.ZhuanquFragment;
import com.gemptc.fragments.MiddleFragment;
import com.gemptc.fragments.WoFragment;
import com.gemptc.fragments.FaxianFragment;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wo.applation.SysApplication;
import com.wo.module.Wo_LoginActivity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.style.BulletSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity implements OnClickListener {
	FragmentManager manager;
	FragmentTransaction transaction;
	private ConnectivityManager conmanager;
	ImageView i1, i2, i3, i4;
	//初始化定位
		//地图主要定位类
		private LocationClient mLocClient;
		//监听当前位置类
		MyLocationListenner myListener = new MyLocationListenner();
		
		private double latitude;
		private double longitude;
		private static String HOST = "http://" + MyAppalication.getIp() + ":8080/Scholar/JW_MServlet";
		private RequestParams params;
		LatLng userLatLng;
		GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用
		MyAppalication myApplication=new MyAppalication();
		// 需要初始化顶部菜单栏、中间的主要模块、底部导航栏
	// 需要初始化顶部菜单栏、中间的主要模块、底部导航栏
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext());  
		setContentView(R.layout.activity_main);
		checkNetworkState();
	
	}

	/**
	 * 检测网络是否连接
	 * @return
	 */
	//判断手机是否联网	
	private boolean checkNetworkState() {
		boolean flag = false;
		//得到网络连接信息
		conmanager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		//去进行判断网络是否连接
		if (conmanager.getActiveNetworkInfo() != null) {
			flag = conmanager.getActiveNetworkInfo().isAvailable();
		}
		if (!flag) {
			setNetwork();
		}else{
			SysApplication.getInstance().addActivity(this);  
			initData();
			initBottom();
			initMiddle();
			initMiddle1();
			if(SysApplication.userbean.getU_id()!=0){						
			//获取位置
			getLocation();
		}
				
		}
		return flag;
	}
	private void setNetwork(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("网络提示信息");
		builder.setMessage("网络不可用，如果继续，请先设置网络！");
		builder.setPositiveButton("设置",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {

				Intent intent = null;
				/**
				 * 判断手机系统的版本！如果API大于10 就是3.0+
				 * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同
				 */
				if (android.os.Build.VERSION.SDK_INT > 10) {
					intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
				} else {
					intent = new Intent();
					ComponentName component = new ComponentName(
							"com.android.settings",
							"com.android.settings.WirelessSettings");
					intent.setComponent(component);
					intent.setAction("android.intent.action.VIEW");
				}
				startActivity(intent);				
			}
		});
	builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			
		}
	});
	builder.create();
	builder.show();
		
	}
	/** 
	* 定位SDK监听函数 
	*/  
	class MyLocationListenner implements BDLocationListener {  

		public void onReceiveLocation(BDLocation location) { 
	    	//获取用户当前经纬度
	        latitude = location.getLatitude();//纬度
	        longitude = location.getLongitude();//经度
	    	userLatLng = new LatLng(latitude, longitude);
	        
	        //实际项目中，定位成功后需要及时修改当前用户的经纬度值     
	        MyAppalication.latitude=latitude;
	        MyAppalication.longitude=longitude;
			if (myApplication.u_id!=null) {
				uploadMethod1(params,HOST);
			}
		}  
	 
	    public void onReceivePoi(BDLocation poiLocation) {  
	    }  
	}

	private void initMiddle1() {
		boolean flag = getIntent().getBooleanExtra("faxian", false);
		if(flag){
			replaceFragment(transaction,"发现", 3);
			reset();
			i3.setImageResource(R.drawable.faxian2);
		}else {
			// 动态添加中介碎片
			transaction = manager.beginTransaction();
			MiddleFragment fragment = new MiddleFragment();
			// 传值
			Bundle bundle = new Bundle();
			bundle.putString("title", "全部");
			fragment.setArguments(bundle);
			transaction.replace(R.id.container, fragment);
			transaction.commit();
		}
		
	}

	private void initData() {
		manager = getSupportFragmentManager();
		// 初始化底部导航栏图片
		i1 = (ImageView) findViewById(R.id.yangxueba);
		i2 = (ImageView) findViewById(R.id.zhuanqu);
		i3 = (ImageView) findViewById(R.id.faxian);
		i4 = (ImageView) findViewById(R.id.wo);
		// 添加监听器
		i1.setOnClickListener(this);
		i2.setOnClickListener(this);
		i3.setOnClickListener(this);
		i4.setOnClickListener(this);

	}

	private void initBottom() {
		// 初始化底部导航栏，底部导航栏是静态的，不需要动态嵌入
		// 设置进入首页面时图片样式
		i1.setImageResource(R.drawable.yang2);
		i2.setImageResource(R.drawable.zhuanqu1);
		i3.setImageResource(R.drawable.faxian1);
		i4.setImageResource(R.drawable.wo1);

	}

	private void initMiddle() {
	// int a= SysApplication.getA();  
	  if(SysApplication.a==1){
		 
		transaction = manager.beginTransaction();
		  replaceFragment(transaction,"我", 4);
			reset();
			i4.setImageResource(R.drawable.wo2);
			transaction.commit();
	  }else{
		// 动态添加中介碎片
		transaction = manager.beginTransaction();
		MiddleFragment fragment = new MiddleFragment();
		transaction.replace(R.id.container, fragment);
		transaction.commit();
	  }
	}
	@Override
	public void onClick(View v) {
		// 监听所有控件单击事件,单击某一张图片，即使动态修改中间的模块
		// 动态添加中介碎片
		transaction = manager.beginTransaction();

		switch (v.getId()) {
		case R.id.yangxueba:			
			replaceFragment(transaction,"养学霸", 1);
			reset();
			i1.setImageResource(R.drawable.yang2);
			break;
		case R.id.zhuanqu:
			replaceFragment(transaction,"专区", 2);
			reset();
			i2.setImageResource(R.drawable.zhuanqu2);
			break;
		case R.id.faxian:
			replaceFragment(transaction,"发现", 3);
			reset();
			i3.setImageResource(R.drawable.faxian2);
			break;
		case R.id.wo:
			if(SysApplication.a==1){replaceFragment(transaction,"我", 4);
			reset();
			i4.setImageResource(R.drawable.wo2);
			break;}else{
				Intent intent =new Intent(MainActivity.this,Wo_LoginActivity.class);
				startActivity(intent);
				replaceFragment(transaction,"我", 4);
				reset();
				i4.setImageResource(R.drawable.wo2);
			}

			break;

		default:
			break;
		}

		transaction.commit();
	}

	// 所有的图片为不打开状态
	private void reset() {
		i1.setImageResource(R.drawable.yang1);
		i2.setImageResource(R.drawable.zhuanqu1);
		i3.setImageResource(R.drawable.faxian1);
		i4.setImageResource(R.drawable.wo1);
	}

	// 单击每一个图片替换中间碎片的方法
	private void replaceFragment(FragmentTransaction transaction,
			String title,
			int flag) {
		Fragment fragment = null;
		Bundle bundle;
		// flag的值为1,2,3,4,分别表示单击了四个选项卡图标
		switch (flag) {
		case 1:
			fragment = new MiddleFragment();		
			break;
		case 2:
			fragment = new ZhuanquFragment();		
			break;
		case 3:
			fragment = new FaxianFragment();	
			break;
		case 4:
			fragment = new WoFragment();	
			break;

		default:
			break;
		}
		// 传值
		bundle = new Bundle();
		bundle.putString("title", title);
		fragment.setArguments(bundle);
		transaction.replace(R.id.container, fragment);
      
	}
	private void uploadMethod1(RequestParams params, String HOST) {		
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.configSoTimeout(5000);
		params = new RequestParams();
		params.addBodyParameter("u_id", String.valueOf(SysApplication.userbean.getU_id()));
		params.addBodyParameter("latitude",String.valueOf(latitude));
		params.addBodyParameter("longitude",String.valueOf(longitude));			
		
		httpUtils.send(HttpMethod.POST, HOST, params,
				new RequestCallBack<String>() {
					public void onStart() {

					}
					public void onLoading(long total, long current,
							boolean isUploading) {
						// 上传中
						System.out.println("经纬度上传中  ");
					}

					public void onSuccess(ResponseInfo<String> arg0) {
						System.out.println("经纬度成功了");
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						System.out.println("经纬度上传失败======");
					}
				});
	}
	private void getLocation() {
		// 定位初始化  
		mLocClient = new LocationClient(getApplicationContext());  
		mLocClient.registerLocationListener(myListener);  
		//定位配置类
		LocationClientOption option = new LocationClientOption();  
		option.setOpenGps(true);// 打开gps  
		option.setCoorType("bd09ll"); // 设置坐标类型  ：经纬度类型
		option.setScanSpan(10000); //设置扫描间隔，单位是ms，这里假设10s钟定位一次 
		mLocClient.setLocOption(option);  
		mLocClient.start();	//开始定位
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 退出应用的时候停止定位,这个时候其实还应该把经纬度置0，自己去实现
		mLocClient.stop();
	}
@Override
protected void onResume() {
	checkNetworkState();
	super.onResume();
}
}
