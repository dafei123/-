package com.wo.module;

import com.gemptc.activities.R;
import com.gemptc.activities.R.layout;
import com.wo.applation.SysApplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Wo_DreamEditActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wo__dream_edit);
		SysApplication.getInstance().addActivity(this);  
	}

	
}
