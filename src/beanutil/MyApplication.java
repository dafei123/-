package beanutil;

import com.gemptc.activities.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;

import android.app.Application;

public class MyApplication extends Application{	
	public static String applicationID="http://10.204.1.34:8080/Scholar";
	public static BitmapUtils bitmapUtils;
	public static HttpUtils httpclient;// 异步网络请求
	@Override
	public void onCreate() {
		//application的onCreate只会调用一次，加之bitmapUtils是static修饰的，表示所有对象共享
		bitmapUtils = new BitmapUtils(getApplicationContext());
		//bitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_launcher);
		bitmapUtils.configDefaultLoadingImage(R.drawable.camera);
		super.onCreate();
	}
}
