package com.gemptc.activities;

import com.wo.applation.SysApplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class Zq_bk_shougActivity extends Activity {

	private ImageView iView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zq_bk_shoug);
		SysApplication.getInstance().addActivity(this);
		
		iView=(ImageView) findViewById(R.id.ivyf);
		
		iView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Zq_bk_shougActivity.this.finish();
			}
		});
		
	}

	
}
