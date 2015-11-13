package com.wo.module;

import com.gemptc.activities.R;
import com.wo.applation.SysApplication;
import com.wo.module.fragement.Wo_RankingFriend_Fragement;
import com.wo.module.fragement.Wo_Rankingworld_fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class Wo_rankingActivity extends Activity {
	RadioGroup radiogroup;
	FragmentManager fm;
	FragmentTransaction ft;
	Wo_RankingFriend_Fragement friend;
	Wo_Rankingworld_fragment world;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wo_ranking);
		SysApplication.getInstance().addActivity(this);  
		fm = getFragmentManager();
		showFriends();
		 init();
	}

	private void showFriends() {
		ft = fm.beginTransaction();
		friend = new Wo_RankingFriend_Fragement();
		ft.replace(R.id.wo_ranking_laylout, friend);
		ft.commit();
	}

	private void showSession() {
		ft = fm.beginTransaction();
		world = new Wo_Rankingworld_fragment();
		ft.replace(R.id.wo_ranking_laylout, world);
		ft.commit();
	}

	private void init() {

		radiogroup = (RadioGroup) findViewById(R.id.wo_ranking_title);
		radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				Fragment fragment = null;
				switch (checkedId) {
				case R.id.wo_ranking_button:
					showFriends();
					break;
				case R.id.wo_ranking_button1:

					showSession();
					break;
				}

			}
		});
	}

}
