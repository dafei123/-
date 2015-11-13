package com.gemptc.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wo.applation.SysApplication;

import sharedPreferencesUtil.SharedHelper;
import sharedPreferencesUtil.TimesGet;
import sharedPreferencesUtil.mdTime;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.preference.RingtonePreference;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class Yang_jishiActivity extends Activity {
	static int minute = -1;
	static int second = -1;
	TextView timeView, stopview;
	Timer timer;
	TimerTask timerTask;
	Intent intent;
	boolean flag=true;
	SharedPreferences.Editor editor = null;	
	//ServiceReceiver receiver;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			System.out.println("handle!");
			Log.e("minute",""+minute);
			Log.e("second",""+second);
			if (minute == 0) {
				if (second == 0) {
					//发送广播
//					Intent intent = new Intent("zero");
//					sendBroadcast(intent);
				//SharedPreferences.Editor editor = null;
					//editor = (Editor) getSharedPreferences("mysp", 0);
					
				// timeView.setText("Time out !");
					// 创建通知  	
					createNotification();
				    upload();

						intent = new Intent(Yang_jishiActivity.this,
								Yang_finishActivity.class);			
					startActivity(intent);
//					editor.clear();
//					editor.commit();
					if (timer != null) {
						timer.cancel();
						timer = null;
					}
					if (timerTask != null) {
						timerTask = null;
					}
				} else {
					second--;
					if (second >= 10) {
						timeView.setText("0" + minute + ":" + second);
					} else {
						timeView.setText("0" + minute + ":0" + second);
					}
				}
			} else {
				//分钟不为0
				if (second == 0) {
					second = 59;
					minute--;
					if (minute >= 10) {
						timeView.setText(minute + ":" + second);
					} else {
						timeView.setText("0" + minute + ":" + second);
					}
				} else {
					second--;
					if (second >= 10) {
						if (minute >= 10) {
							timeView.setText(minute + ":" + second);
						} else {
							timeView.setText("0" + minute + ":" + second);
						}
					} else {
						if (minute >= 10) {
							timeView.setText(minute + ":0" + second);
						} else {
							timeView.setText("0" + minute + ":0" + second);
						}
					}
				}
			}
		}	
	};
	@SuppressLint("NewApi")
	private void createNotification() {//通知
		
		Intent intentNo=new Intent();//初始化意图
		//这是要打开的activity，第一个参数是谁打开的，第二个参数是要打开谁
		intentNo.setClass(Yang_jishiActivity.this, Yang_finishActivity.class);
		//PendingIntent 即将到来的意图
		PendingIntent pendingIntent=PendingIntent.getActivity(Yang_jishiActivity.this, 0, intentNo, 0);
		//NotificationManager ：  是状态栏通知的管理类，负责发通知、清楚通知等。
		//NotificationManager 是一个系统Service，必须通过 getSystemService()方法来获取。
		NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification notification=new Notification.Builder(getApplicationContext())
		.setContentTitle("学习成功")
		.setContentText("恭喜您离学习又近一步")
		.setSmallIcon(R.drawable.ic_item_jinri)
		.setContentIntent(pendingIntent)
		.setAutoCancel(true)
		.build();
		notificationManager.notify(0, notification);
		// 发出系统通知声音和调用震动  
	    ringAndVibrator();
	    
	};
	protected void upload() {//上传数据
		// TODO Auto-generated method stub
		SharedHelper sp = new SharedHelper(this);
		Map<String, String> map = sp.read();
		HttpUtils httpUtils=new HttpUtils();
		RequestParams params=new RequestParams();
		String set = map.get("settime");//获取设置时间时传来的数据
		int S_time=Integer.parseInt(set)/60;
		//minute=S_time/60;
		int id=SysApplication.userbean.getU_id();//获取登录的id
		String date=mdTime.StringData2();//获取当前时期
	params.addBodyParameter("time",""+S_time);
	params.addBodyParameter("id",""+id);
	params.addBodyParameter("date",date);
	String url="http://192.168.155.1:8080/Scholar/S_Addtime";
	httpUtils.send(HttpMethod.POST,url, params, new RequestCallBack<String>() {

		@Override
		public void onSuccess(ResponseInfo<String> responseInfo) {
			Toast.makeText(Yang_jishiActivity.this, "刘邦被爆了",Toast.LENGTH_SHORT).show();
			
		}

		@Override
		public void onFailure(HttpException error, String msg) {
			// TODO Auto-generated method stub
			
		}
	});
	}

	private void ringAndVibrator() {
		// 获取系统通知声音 
		Uri alert=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		MediaPlayer player = new MediaPlayer();  //调用音乐播放器
		try {
			player.setDataSource(this,alert);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// AudioManager声音管理
		final AudioManager audioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
		if(audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION)!=0){
			player.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
			player.setLooping(false);
			try {
				player.prepare();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		player.start();
	}
	/*private class ServiceReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			
			String action=intent.getAction();//minute==0&second==0
			if(action.equals("zero")){
				 AlertDialog dialog = new AlertDialog.Builder(Yang_jishiActivity.this)
				 .setTitle("学习监督")
				 .setMessage("恭喜您离学习又近一步")
				 .setPositiveButton("好的", null)				
				 .create();				
				 dialog.show();
				
			
		}
		}
	}
*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jishi);
		/*receiver=new ServiceReceiver();
		IntentFilter filter=new IntentFilter("zero");
		registerReceiver(receiver, filter);*/
		initstop();
		Log.e("DDDDDDDDDDDDD>>>>>>>>>>>>",1111 +"");
		timeView = (TextView) findViewById(R.id.yang_myTime);
		editor = this.getSharedPreferences("mysp", 0).edit();		
		Log.e("aa",""+minute);
		Log.e("aa",""+second);
		Intent intent = getIntent();
		ArrayList<Integer> times = intent
				.getIntegerArrayListExtra("times");
		Log.e("aaa", ""+(minute == -1 && second == -1));
		if ((minute ==0 && second ==0)||(minute == -1 && second == -1)) {	
			//if(minute == -1 && second == -1){
			Log.e("SSSSSSSSSSSSSSSSSSSSSSSSSSSS", ""+minute+second);
			int flag = intent.getIntExtra("flag", -1);
			Log.e("flag",""+flag);
			if (flag == 1) {
				//已经超时				
				minute = times.get(0);
				second = times.get(1);
				Log.e("刘邦菊花被大飞爆成向日葵", ""+minute+second);				
			} else if (flag == 0) {
				//未超时
				int timesub=intent.getIntExtra("timesub", 0);
				Log.e("剩余时间", "-----"+timesub);
				String s=intent.getStringExtra("settime");
				Log.e("剩余时间", "-----"+"settime");
				int n=Integer.parseInt(s)-timesub;
				minute =n/60;
				second=n%60;
			}

		}

		timeView.setText(minute + ":" + second);

		timerTask = new TimerTask() {

			@Override
			public void run() {
				Message msg = new Message();
				msg.what = 0;
				handler.sendMessage(msg);
			}
		};

		timer = new Timer();
		timer.schedule(timerTask, 0, 1000);

	}

	private void initstop() {
		stopview = (TextView) findViewById(R.id.jishigiveup);
		stopview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(
						Yang_jishiActivity.this);
				builder.setTitle("学霸养成记");
				builder.setMessage("学霸，你确定要放下这个头衔吗？");
				builder.setPositiveButton("是",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								editor.clear();
								editor.commit();
								Intent intent = new Intent(
										Yang_jishiActivity.this,
										Yang_fangqiActivity.class);								
								startActivity(intent);
								finish();
							}
						});
				builder.setNegativeButton("否", null);
				builder.show();
			}
		});
	}

	@Override
	protected void onDestroy() {
		if (timer != null) {			
			timer.cancel();
			timer = null;
		}
		if (timerTask != null) {
			timerTask = null;
		}
		minute = -1;
		second = -1;		
		super.onDestroy();		
		//unregisterReceiver(receiver);
	}

	@Override
	protected void onStart() {
		
		super.onStart();
	}

	@Override
	protected void onStop() {
//		minute = -1;
//		second = -1;	
		super.onStop();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onPause() {
		super.onPause();

	}
}
