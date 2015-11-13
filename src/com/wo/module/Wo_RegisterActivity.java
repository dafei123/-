package com.wo.module;

import http.HttpDown;

import com.gemptc.activities.R;
import Sms.DuanXinYanZhen;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Wo_RegisterActivity extends Activity implements OnClickListener {
	EditText et_phone, et_password, et_code;
	Button btn_getcode, btn_register;
	String password;
	String phonenumber;
	DuanXinYanZhen yz;
	int i=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wo__register);
		// 初始化
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		et_phone = (EditText) findViewById(R.id.et_phone1);
		et_password = (EditText) findViewById(R.id.et_password1);
		et_code = (EditText) findViewById(R.id.et_code1);
		btn_getcode = (Button) findViewById(R.id.btn_getCode1);
		btn_register = (Button) findViewById(R.id.btn_register1);
		// 设置监听
		btn_getcode.setOnClickListener(this);
		btn_register.setOnClickListener(this);
		 yz=new DuanXinYanZhen(Wo_RegisterActivity.this);
			yz.huidiao();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_getCode1:
            //获取验证消息
			phonenumber=et_phone.getText().toString();
			btn_getcode.setText(i+"秒");
			if(i<=0){
				 yz.code(phonenumber,2);
				 i=60;
				 new Thread(new Runnable() {
						@Override
						public void run() {
							for (; i> 0; i--) {
								if (i <= 0) {
									
									break;
								}
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							
						}
					}).start();
				 
			}
				

				
			
			 
			break;
		case R.id.btn_register1:
        //验证密码并上传
			String b=et_code.getText().toString();
			 password=et_password.getText().toString();
				yz.upload(b);
			break;

		default:
			break;
		}

	}
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		yz.clear();
	}
	 //上传数据
	public void upusermessage() {
		HttpDown http=new HttpDown();
		http.down(4,Wo_RegisterActivity.this, phonenumber, password);
	}

}
