package com.wo.module;

import com.gemptc.activities.R;
import com.gemptc.activities.R.id;
import com.gemptc.activities.R.layout;
import com.gemptc.activities.R.menu;
import com.wo.applation.SysApplication;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Wo_SetActivity extends Activity implements OnClickListener {
	LinearLayout shuoming, fankui, tongzhi;
	Button exit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wo__set);
		SysApplication.getInstance().addActivity(this);  
		// 初始化
		init();
	}

	private void init() {
		shuoming = (LinearLayout) findViewById(R.id.wo_set_shuoming);
		fankui = (LinearLayout) findViewById(R.id.wo_set_fankui);
		tongzhi = (LinearLayout) findViewById(R.id.wo_set_tongzhi);
		exit = (Button) findViewById(R.id.wo_set_tuichu);
		shuoming.setOnClickListener(this);
		fankui.setOnClickListener(this);
		tongzhi.setOnClickListener(this);
		exit.setOnClickListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.wo_set_shuoming:
			 intent = new Intent(Wo_SetActivity.this,
					Wo_Set_ShuoMingActivity.class);
			startActivity(intent);
			break;
		case R.id.wo_set_fankui:
			 intent = new Intent(Wo_SetActivity.this,
					Wo_Set_FanKuiActivity.class);
			startActivity(intent);
			break;
		case R.id.wo_set_tongzhi:
			Toast.makeText(this, "已是最新版本",0).show();
			break;
		case R.id.wo_set_tuichu:
			SysApplication.getInstance().exit();           
			break;
		default:
			break;
		}

	}

}
