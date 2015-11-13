package com.gemptc.fragments;

import com.gemptc.activities.R;
import com.gemptc.activities.Zq_BkActivity;
import com.gemptc.activities.Zq_BwlActivity;
import com.gemptc.activities.Zq_DrawText;
import com.gemptc.activities.Zq_Tk_main;
import com.gemptc.activities.Zq_ZlActivity;
import com.gemptc.activities.Zq_ZywdActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ZhuanquFragment extends Fragment implements OnClickListener{
	View view;
	Zq_DrawText d1, d2;
	Intent intent;
	LinearLayout lin1, lin2,lin3;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 取出activity传递过来的数据
		String title = getArguments().getString("title");
		view = inflater.inflate(R.layout.zhuanqu, null);
		// 初始化
		initData();
		return view;
	}

	private void initData() {
		d1 = (Zq_DrawText) view.findViewById(R.id.id_zywd);
		d2 = (Zq_DrawText) view.findViewById(R.id.id_bwl);
		lin1 = (LinearLayout) view.findViewById(R.id.linzl);
		lin2 = (LinearLayout) view.findViewById(R.id.linbk);
		lin3=(LinearLayout) view.findViewById(R.id.lintk);
		// 监听事件
		d1.setOnClickListener(this);
		d2.setOnClickListener(this);
		lin1.setOnClickListener(this);
		lin2.setOnClickListener(this);
		lin3.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_zywd:
			intent = new Intent(getActivity(), Zq_ZywdActivity.class);
			startActivity(intent);
			break;
		case R.id.id_bwl:
			intent = new Intent(getActivity(), Zq_BwlActivity.class);
			startActivity(intent);
			break;
		case R.id.linzl:
			intent = new Intent(getActivity(), Zq_ZlActivity.class);
			startActivity(intent);
			break;
		case R.id.linbk:
			intent = new Intent(getActivity(), Zq_BkActivity.class);
			startActivity(intent);
			break;
		case R.id.lintk:
			intent = new Intent(getActivity(), Zq_Tk_main.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}
}
