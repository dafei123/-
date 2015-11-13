package com.wo.module;

import com.gemptc.activities.R;
import com.gemptc.activities.R.id;
import com.gemptc.activities.R.layout;
import com.gemptc.activities.R.menu;
import com.wo.applation.SysApplication;
import com.wo.module.fragement.Wo_CollectMyfragment;
import com.wo.module.fragement.Wo_Collect_Questionfragment;
import com.wo.module.fragement.Wo_Collect_talkfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Wo_CollectActivity extends Activity implements OnClickListener{
    FragmentManager fm;
    android.app.FragmentTransaction ft;
    TextView talk,quesion,my;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wo__collect);
		SysApplication.getInstance().addActivity(this);  
		fm=getFragmentManager();
		init();
		indate();
	}
	private void indate() {
		talk.setTextColor(Color.parseColor("#ffffff"));
		ft=fm.beginTransaction();
		Wo_Collect_talkfragment talkframe=new Wo_Collect_talkfragment();
		ft.replace(R.id.wo_collectlayout, talkframe);	
		ft.commit();
	}
	private void init() {
		talk=(TextView) findViewById(R.id.wo_collecttalk_fragment); 
		quesion=(TextView) findViewById(R.id.wo_collectquesion_fragment); 
	//	my=(TextView) findViewById(R.id.wo_collectmy_fragment);	
		talk.setOnClickListener(this);
		quesion.setOnClickListener(this);
		//my.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
	switch(v.getId()){
	case R.id.wo_collecttalk_fragment:
		resetcolor();
		ft=fm.beginTransaction();
		Wo_Collect_talkfragment talkframe=new Wo_Collect_talkfragment();
		ft.replace(R.id.wo_collectlayout, talkframe);	
		talk.setTextColor(Color.parseColor("#ffffff"));
		break;
		case R.id.wo_collectquesion_fragment:
			resetcolor();
			ft=fm.beginTransaction();
			Wo_Collect_Questionfragment questionframe=new Wo_Collect_Questionfragment();
			ft.replace(R.id.wo_collectlayout, questionframe);
			quesion.setTextColor(Color.parseColor("#ffffff"));
		
			break;
//		case R.id.wo_collectmy_fragment:
//			resetcolor();
//			ft=fm.beginTransaction();
//			Wo_CollectMyfragment myframe=new Wo_CollectMyfragment();
//			ft.replace(R.id.wo_collectlayout, myframe);
//			my.setTextColor(Color.parseColor("#ffffff"));
//			break;
	}
	ft.commit();	
	}
	private  void resetcolor(){
		talk.setTextColor(Color.parseColor("#000000"));
		quesion.setTextColor(Color.parseColor("#000000"));
	//	my.setTextColor(Color.parseColor("#000000"));
	}
}
