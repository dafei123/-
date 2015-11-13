package com.gemptc.activities;

import com.wo.applation.SysApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class Zq_BkActivity extends Activity {

private ImageView ivbk;
private LinearLayout linwork,linwork1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zq_bk);
		SysApplication.getInstance().addActivity(this);
		
		init();
		
	}
	
	

	private void init() {
		
		ivbk=(ImageView) findViewById(R.id.ivbk);
		ivbk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Zq_BkActivity.this.finish();
			}
		});
		
		linwork=(LinearLayout) findViewById(R.id.linword);
		linwork.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(Zq_BkActivity.this,Zq_bk_hulianActivity.class);
				startActivity(intent);
			}
		});
		
		linwork1=(LinearLayout)findViewById(R.id.linword1);
		linwork1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent intent=new Intent(Zq_BkActivity.this,Zq_bk_shougActivity.class);
			startActivity(intent);
				
			}
		});
		
		
	}
	

}
