package com.gemptc.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class Yang_study_setActivity extends Activity implements OnClickListener{
LinearLayout layout2,layout3,layout4;
Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yang_study_set);		
		initData();
	}

	private void initData() {
		layout3=(LinearLayout) findViewById(R.id.yang_study_set_jieshao);
		layout4=(LinearLayout) findViewById(R.id.yang_study_set_jieshao1);
		//设置监听
		layout3.setOnClickListener(this);
		layout4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
	switch (v.getId()) {
	case R.id.yang_study_set_jieshao:
		intent=new Intent(this,FanqiejieshaoActivity.class);
		startActivity(intent);
		
		break;
	case R.id.yang_study_set_jieshao1:
		intent=new Intent(this,XuexifangshijieshaoActivity.class);
		startActivity(intent);
		
		break;
	default:
		break;
	}
	}

	
}
