package com.wo.module;

import com.gemptc.activities.R;
import com.gemptc.activities.R.id;
import com.gemptc.activities.R.layout;
import com.gemptc.activities.R.menu;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wo.applation.SysApplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PersonZiLiaoActivity extends Activity {
    TextView person_save;
	ImageView personimage,personreturn;
  EditText phonenumber,personalitysign,person_sex,person_age,person_class,person_school;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_zi_liao);
		SysApplication.getInstance().addActivity(this); 
		//初始化
		init();
		//设置内容
		data();
		//设置监听
		lesion();
	}
	
	private void init() {
		personimage=(ImageView) findViewById(R.id.personimage1);
		 Log.e("个人资料界面",""+(SysApplication.bitmap!=null));
		if(SysApplication.bitmap!=null){
			personimage.setImageBitmap(SysApplication.bitmap);
		}
	    phonenumber=(EditText) findViewById(R.id.phonenumber);
	    personalitysign=(EditText) findViewById(R.id.G_gexingqianming);
	    person_sex=(EditText) findViewById(R.id.person_sex);
	    person_age=(EditText) findViewById(R.id.person_age);
	    person_class=(EditText) findViewById(R.id.person_class);
	    person_school=(EditText) findViewById(R.id.person_school);
	    person_save=(TextView) findViewById(R.id.person_save);
	}
	private void data() {
		Log.e("个人资料界面",SysApplication.userbean.toString());
		personreturn=(ImageView) findViewById(R.id.personreturn);
		//用户名称
		phonenumber.setText(SysApplication.userbean.getUsername(), null);
		personalitysign.setText(SysApplication.userbean.getSignator(),null);
		person_sex.setText(SysApplication.userbean.getU_sex(),null);
		person_age.setText(String.valueOf(SysApplication.userbean.getAge()),null);
		person_class.setText(SysApplication.userbean.getU_class(),null);
		person_school.setText(SysApplication.userbean.getSchool(),null);
	}
private void lesion() {
	personreturn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			finish();
			
		}
	});
	person_save.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		     HttpUtils http=new HttpUtils();
		     String url=SysApplication.httppath+"ziliaowanshan";
		     RequestParams params=new RequestParams();
		     params.addBodyParameter("username",phonenumber.getText().toString().trim());
		     params.addBodyParameter("qianming",personalitysign.getText().toString().trim());
		     params.addBodyParameter("sex",person_sex.getText().toString().trim());
		     params.addBodyParameter("age",  person_age.getText().toString().trim());
		     params.addBodyParameter("class", person_class.getText().toString().trim());
		     params.addBodyParameter("school",person_school.getText().toString());
		     params.addBodyParameter("id", String.valueOf(SysApplication.userbean.getU_id()));
		     http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					Toast.makeText(PersonZiLiaoActivity.this, "网络错误", 0).show();
				
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					Toast.makeText(PersonZiLiaoActivity.this, "添加成功", 0).show();
					
				}
			});
			
		}
	});
	}
	

}
