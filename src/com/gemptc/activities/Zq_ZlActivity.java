package com.gemptc.activities;

import com.wo.applation.SysApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Zq_ZlActivity extends Activity {

	private ImageView ivzl;
	private TextView tvet4,tvet6;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zq_zl);
		SysApplication.getInstance().addActivity(this);
		initview();
	}
	
	private void initview() {
		ivzl=(ImageView) findViewById(R.id.ivzl);
		tvet4=(TextView) findViewById(R.id.tvet4);
		tvet6=(TextView) findViewById(R.id.tvet6);
		
		ivzl.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {				
				Zq_ZlActivity.this.finish();
			}
		});
		
		tvet4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Zq_ZlActivity.this,Zq_zl_et4Activity.class);
				startActivity(intent);				
			}
		});
		
		tvet6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Zq_ZlActivity.this,Zq_zl_et6Activity.class);
				startActivity(intent);
			}
		});
		
	}

}
