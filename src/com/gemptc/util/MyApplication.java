package com.gemptc.util;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.gemptc.beans.LunTanBean;
import com.gemptc.beans.UserBean;
import com.lidroid.xutils.HttpUtils;
import com.loopj.android.http.PersistentCookieStore;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
	public LocationClient locationClient;
	private static String ip = "10.204.1.34";
	public static HttpUtils httpclient;// 异步网络请求
	public static UserBean user;
	public static ACache mCache;
	public static LunTanBean lunTanBean;
	public  static String  u_id="2";
	public static double latitude;
	public static double longitude;
	public static String getU_id() {
		return u_id;
	}

	public static void setU_id(String u_id) {
		MyApplication.u_id = u_id;
	}
	public static UserBean getUser() {
		return user;
	}

	public static void setUser(UserBean user) {
		MyApplication.user = user;
	}

	public static LunTanBean getLunTanBean() {
		return lunTanBean;
	}

	public static void setLunTanBean(LunTanBean lunTanBean) {
		MyApplication.lunTanBean = lunTanBean;
	}

	private static String luntanTag = "luntantype";
    
	
	public static String getLuntanTag() {
		return luntanTag;
	}

	public static String getIp() {
		return ip;
	}

@Override
public void onCreate() {
	super.onCreate();
	/**
     * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
     * io.rong.push 为融云 push 进程名称，不可修改。
     */
 
	mCache = ACache.get(this);
	httpclient = new HttpUtils();
	// PreferencesCookieStore myCookieStore =new
	// PreferencesCookieStore(this);
	PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
	httpclient.configCookieStore(myCookieStore);

	System.out.println("=========" + mCache.getAsObject("user"));
	user = (UserBean) mCache.getAsObject("user");
	// user=(User) mCache.getAsObject("user");
	// System.out.println(mCache.getAsString("user1"));
	
	locationClient=new LocationClient(this);	
}

/*public static void saveLoginStatus(UserBean user) {
	MyApplication.user = user;
	mCache.put("user", user);
	System.out.println(user);
}*/

/**
 * 获得当前进程的名字
 * 
 * @param context
 * @return 进程号
 */
public static String getCurProcessName(Context context) {

	int pid = android.os.Process.myPid();

	ActivityManager activityManager = (ActivityManager) context
			.getSystemService(Context.ACTIVITY_SERVICE);

	for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
			.getRunningAppProcesses()) {

		if (appProcess.pid == pid) {
			return appProcess.processName;
		}
	}
	return null;
}	
}
