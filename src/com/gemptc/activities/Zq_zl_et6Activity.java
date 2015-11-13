package com.gemptc.activities;

import com.wo.applation.SysApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class Zq_zl_et6Activity extends Activity {

	private LinearLayout linword, linsentence, linyufa;
	private ImageView ivet4zl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zq_zl_et6);
		SysApplication.getInstance().addActivity(this);
		init();
		
	}
	private void init() {
		ivet4zl=(ImageView) findViewById(R.id.ivet4zl);
		linword = (LinearLayout) findViewById(R.id.linword);
		linsentence = (LinearLayout) findViewById(R.id.linsentence);
		linyufa = (LinearLayout) findViewById(R.id.linyufa);

		ivet4zl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Zq_zl_et6Activity.this.finish();
			}
		});

		linword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Zq_zl_et6Activity.this,
						Zq_zl_et4wordActivity.class);
				startActivity(intent);
			}
		});

		linsentence.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(Zq_zl_et6Activity.this,
						Zq_zl_et4sentenceActivity.class);
				startActivity(intent1);
			}
		});

	}
	
	
}
