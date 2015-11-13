package com.gemptc.activities;

import yang.framents.fanqieFrament;
import yang.framents.jishiFrament;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
public class Yang_studyActivity extends FragmentActivity implements OnClickListener {
Spinner spinner;
private static final String[] set={"计时学习","番茄学习"};//spinner要显示的内容
ImageView backimg ,setimg;
private ArrayAdapter<String> adapter;
Intent intent;
FragmentManager manager;
FragmentTransaction transaction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yang_study);
		initTop();
		initback();
		initjishi();
		initfanqie();
	}
	private void initfanqie() {
		manager = this.getSupportFragmentManager();
		transaction=manager.beginTransaction();
		fanqieFrament fanqie=new fanqieFrament();
		transaction.replace(R.id.yang_jishiqi, fanqie);
		transaction.commit();	
		
	}



	private void initjishi() {
		manager = this.getSupportFragmentManager();
		transaction=manager.beginTransaction();
		jishiFrament jishiqi=new jishiFrament();
		transaction.replace(R.id.yang_jishiqi, jishiqi);
		transaction.commit();	
	}

	private void initback() {
		// TODO Auto-generated method stub
		backimg=(ImageView) findViewById(R.id.yang_fanhui);
		setimg=(ImageView) findViewById(R.id.yang_shezi);
		//设置监听事件
		backimg.setOnClickListener(this);
		setimg.setOnClickListener(this);
	}
	private void initTop() {
		spinner=(Spinner) findViewById(R.id.yang_studyspinner);
		 //将可选内容与ArrayAdapter连接起来
		adapter=new ArrayAdapter<String>(this,R.layout.yang_spinner,set);
		spinner.setAdapter(adapter);//将adapter 添加到spinner中  
		spinner.setOnItemSelectedListener(new SpinnerSelectedListener());  //添加事件Spinner事件监听 
		
		//设置下拉列表的风格  
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
		//设置默认值  
        spinner.setVisibility(View.VISIBLE); 
	}
class SpinnerSelectedListener implements OnItemSelectedListener{

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		view.setTag(set[position]);
		switch (position) {//设置spinner的元素监听
		case 0:
			initjishi();
			break;
		case 1:
			initfanqie();
			break;
		default:
			break;
		}
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
@Override
public void onClick(View v) {
	switch (v.getId()) {
	case R.id.yang_fanhui:
		finish();
		break;
	case R.id.yang_shezi:
		intent=new Intent(Yang_studyActivity.this,Yang_study_setActivity.class);
		startActivity(intent);
		break;
		
	default:
		break;
	}
	
}


}
