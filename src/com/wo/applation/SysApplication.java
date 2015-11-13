package com.wo.applation;
import java.util.LinkedList;
import java.util.List;

import bean.Wo_Userbean;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
public class SysApplication extends Application {
	public static int a=0;
	public static final String httppath="http://10.204.1.34:8080/Scholar/";
	//运用list来保存们每一个activity是关键
	public static Wo_Userbean userbean=new Wo_Userbean();
	private List<Activity> mList = new LinkedList<Activity>();  
	public static Bitmap bitmap;
    //为了实现每次使用该类时不创建新的对象而创建的静态对象  
    private static SysApplication instance;
    @Override
    public void onCreate() {
    	userbean.setU_id(0);
    	super.onCreate();
    }
    //构造方法  
    private SysApplication(){}  
    //实例化一次  
    public synchronized static SysApplication getInstance(){   
        if (null == instance) {   
            instance = new SysApplication();   
        }   
        return instance;   
    }   
    // add Activity    
    public void addActivity(Activity activity) {   
        mList.add(activity);   
    }   
    //关闭每一个list内的activity  
    public void exit() {   
        try {   
            for (Activity activity:mList) {   
                if (activity != null)   
                    activity.finish();   
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            System.exit(0);   
        }   
    }   
    //杀进程  
    public void onLowMemory() {   
        super.onLowMemory();       
        System.gc();   
    }    
}
