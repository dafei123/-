package com.wo.module;

import java.util.ArrayList;
import java.util.List;

import http.HttpDown;

import com.gemptc.activities.MainActivity;
import com.gemptc.activities.R;
import com.wo.applation.SysApplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Wo_LoginActivity extends Activity implements OnClickListener{
TextView register,xuigai;
Button login;
EditText phonenumber,password;
SharedPreferences pref;
SharedPreferences.Editor editor;
List<String> list;
 final Handler mHandler=new Handler(){
	@Override
	public void handleMessage(Message msg) {
		switch (msg.arg1) {
		case 3:
			String sb=list.get(0);
			 Log.e("list",sb);
			 if(list.get(0).equals("error")||list.get(0).equals("0")){
				 Toast.makeText(Wo_LoginActivity.this, "账号或密码错误，请重试",0).show();			 
			 }else {		
				 SysApplication.userbean.setU_id(Integer.parseInt(list.get(0)));
					SysApplication.a=1;
					Intent intent=new Intent(Wo_LoginActivity.this,MainActivity.class);
					startActivity(intent);
			 } 
			break;
		default:
			break;
		}
		super.handleMessage(msg);
		}
};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wo__login);
		SysApplication.getInstance().addActivity(this); 
      //初始化；
		init();
	 //设置监听事件
		lision();
	}
	private void lision() {
		register.setOnClickListener(this);
		xuigai.setOnClickListener(this);
		login.setOnClickListener(this);
	}
	private void init() {
		pref=PreferenceManager.getDefaultSharedPreferences(this);
		register=(TextView) findViewById(R.id.wo_register_phone);
		xuigai=(TextView) findViewById(R.id.wo_xuigai);
		login=(Button) findViewById(R.id.btn_login);
		phonenumber=(EditText) findViewById(R.id.et_phone);
		password=(EditText) findViewById(R.id.et_password);
	    String name=pref.getString("name","");
	    String password1=pref.getString("password","");
	    phonenumber.setText(name);
	 //  boolean rember_password=pref.getBoolean("rember",false); 
	  // if(rember_password){
	    password.setText(password1);
	//    }	    
	}
	
	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.wo_register_phone:
			intent=new Intent(Wo_LoginActivity.this, Wo_RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.wo_xuigai:
		 intent=new Intent(Wo_LoginActivity.this, Wo_XuiGaiActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_login:
			//测试代码
//			 SysApplication.a=1;
//			 intent=new Intent(Wo_LoginActivity.this,MainActivity.class);
//				startActivity(intent);
			//登录按钮监听相应事件	
        	//	正文	  			
			jump();
				break;
		default:
			break;
		}
		
	}
	private void jump() {
	 String phonetext=phonenumber.getText().toString();
	 String passwordtext=password.getText().toString();
	 editor=pref.edit();
	 editor.putString("name",phonetext);
	 editor.putString("password",passwordtext);
	 editor.commit();
	 list=new ArrayList<String>();
	 HttpDown.down(1,list,mHandler,this,phonetext,passwordtext);	
//	System.out.println(list.toString());
//	if(list.get(0)!=null){
//	  editor.putInt("user_id",list.get(0));
//	  Log.e("sas",""+p.getInt("user_id",0)) ;
//		SysApplication.a=1;
//		Intent intent=new Intent(Wo_LoginActivity.this,MainActivity.class);
//		startActivity(intent);}
//	else {
//		Toast.makeText(this, "用户名或密码错误请重新输入", 0).show();
//	}
	}
	}

