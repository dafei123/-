package com.wo.module;

import http.HttpDown;

import com.gemptc.activities.R;
import com.gemptc.activities.R.id;
import com.gemptc.activities.R.layout;

import cn.smssdk.SMSSDK;
import Sms.DuanXinYanZhen;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Wo_XuiGaiActivity extends Activity implements OnClickListener {
	EditText et_phone, et_code, et_password;
	Button btn_getcode, btn_register;
	DuanXinYanZhen yz;
	String password;
	String phonenumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wo__xui_gai);
		// 初始化控件；
		init();
	}

	private void init() {
		// 初始化
		et_phone = (EditText) findViewById(R.id.et_phone2);
		et_password = (EditText) findViewById(R.id.et_password2);
		et_code = (EditText) findViewById(R.id.et_code2);
		btn_getcode = (Button) findViewById(R.id.btn_getCode);
		btn_register = (Button) findViewById(R.id.btn_register);
		// 设置监听事件
		btn_getcode.setOnClickListener(this);
		btn_register.setOnClickListener(this);
		 yz=new DuanXinYanZhen(this);
		yz.huidiao();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_getCode:
               //短信验证方法
		 phonenumber=et_phone.getText().toString();
	     yz.code(phonenumber,1);
			break;
		case R.id.btn_register:
                //上传数据用于注册
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
	http.down(3,this, phonenumber, password);
}

}
