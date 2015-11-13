package com.gemptc.activities;

import com.wo.applation.SysApplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Zq_zl_et4sentenceActivity extends Activity {

	private ImageView iView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zq_zl_et4sentence);
		SysApplication.getInstance().addActivity(this);
		initView();
	}
	private void initView() {
		iView=(ImageView) findViewById(R.id.ivet4zlst);
		iView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Zq_zl_et4sentenceActivity.this.finish();
			}
		});
		
	}

}
