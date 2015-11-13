package com.wo.module;

import com.gemptc.activities.R;
import com.gemptc.activities.R.id;
import com.gemptc.activities.R.layout;
import com.gemptc.activities.R.menu;
import com.wo.applation.SysApplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class Wo_Set_FanKuiActivity extends Activity {
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wo__set__fankui);
		SysApplication.getInstance().addActivity(this);  
	}
}
