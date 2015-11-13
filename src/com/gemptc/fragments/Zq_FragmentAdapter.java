package com.gemptc.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * fragmentPagerAdapter适配器
 * @author kincai
 *
 */
public class Zq_FragmentAdapter extends FragmentPagerAdapter {
	private static final int TAB_ANDROID = 0;
	private static final int TAB_JAVA = 1;
	public Zq_FragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}
	
	public final static int TAB_COUNT = 2;
	// 获取要滑动的控件的数量
	@Override
	public int getCount() {
//		return list.size();
		return TAB_COUNT;
	}
	@Override
	public Fragment getItem(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case TAB_ANDROID:
			new Zq_tk_AndroidTestFragment();
			return new Zq_tk_AndroidTestFragment();
		case TAB_JAVA:
			new Zq_tk_JavaTestFragment();
			return new Zq_tk_JavaTestFragment();
		}
		return null;
	}


}
