package com.gemptc.fragments;

import java.util.ArrayList;
import java.util.List;

import com.gemptc.activities.FaX_FuBuLunTanActivity;
import com.gemptc.activities.MainActivity;
import com.gemptc.activities.R;
import com.gemptc.faxian.fragement.fujinderenFragement;
import com.gemptc.faxian.fragement.xuebaluntanFragement;
import com.wo.applation.SysApplication;
import com.wo.module.Wo_LoginActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class FaxianFragment extends Fragment implements OnClickListener {
	View fx_view;
	ViewPager viewPager;
	Myadapter adapter;
	MainActivity activity;
	ImageView imageView1,imageView2,fatieImageView;
	List<Fragment> fragments = new ArrayList<Fragment>();    
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.activity=(MainActivity) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initData();
	}
	private void initData() {
		Fragment luntanFragment = new xuebaluntanFragement();
		Fragment fujinFragment = new fujinderenFragement();
		fragments.add(luntanFragment);
		fragments.add(fujinFragment);
		//挂后台防止崩溃
		adapter = new Myadapter(getChildFragmentManager());

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		fx_view = inflater.inflate(R.layout.fragment_fa_x, null);		
		initViews();
		return fx_view;
	}

	private void initViews() {
		viewPager = (ViewPager) fx_view.findViewById(R.id.fx_container);
		viewPager.setAdapter(adapter);
		
		imageView1=(ImageView) fx_view.findViewById(R.id.fx_imageview1);
		imageView2=(ImageView) fx_view.findViewById(R.id.fx_imageview2);
		 fatieImageView=(ImageView) fx_view.findViewById(R.id.fx_huabi3);
	    imageView1.setOnClickListener(this);
	    imageView2.setOnClickListener(this);
	    fatieImageView.setOnClickListener(this);
	}

	class Myadapter extends FragmentPagerAdapter {

		public Myadapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return fragments.get(arg0);
		}

		@Override
		public int getCount() {
			
			return fragments.size();
		}
		

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fx_imageview1:
			viewPager.setCurrentItem(0);
			break;
		case R.id.fx_imageview2:
			viewPager.setCurrentItem(1);
			break;
		case R.id.fx_huabi3:
			if(SysApplication.userbean.getU_id()==0){
				jump();
			}else{
				Intent intent=new Intent(getActivity(),FaX_FuBuLunTanActivity.class);
				startActivity(intent);
			}		
		default:
			break;
		}
		
	}

	private void jump() {
		AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
		dialog.setTitle("请先登录");
		dialog.setMessage("你还没有登录，是否登录");
		dialog.setPositiveButton("是",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent1=new Intent(getActivity(), Wo_LoginActivity.class);
				startActivity(intent1);
			}
		});
		dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		});
		dialog.create();
		dialog.show();
		
	}

}
