package com.gemptc.adapters;

import image.SmartImageView;

import java.util.List;

import com.gemptc.activities.R;
import com.gemptc.beans.MyBean;
import com.gemptc.util.MyApplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	Context context;
	List<MyBean> list;
	LayoutInflater mInflater;
	
	
	public MyAdapter(Context context, List<MyBean> list) {
		super();
		this.context = context;
		this.list = list;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		arg1 = mInflater.inflate(R.layout.fx_fujinitem, null);
		SmartImageView  u_ImageView=(SmartImageView) arg1.findViewById(R.id.fx_friendlist);
		TextView friendname= (TextView) arg1.findViewById(R.id.friendAdaptername);	
		TextView U_sex=(TextView) arg1.findViewById(R.id.fx_sex);
		TextView U_age=(TextView) arg1.findViewById(R.id.fx_age);
		TextView distance1=(TextView) arg1.findViewById(R.id.fx_distance);
		String url = "http://" + MyApplication.getIp() + ":8080/Scholar/img2/"
				+ list.get(arg0).getU_image();
		u_ImageView.setstyle("circle");
		u_ImageView.setImageUrl(url);
		friendname.setText(list.get(arg0).getU_username());//左侧显示用户名
		U_sex.setText(list.get(arg0).getU_sex());
		U_age.setText(list.get(arg0).getU_age());
		//把距离转换为千米和米的形式，去掉小数点
		int distance =  (int)list.get(arg0).getDistance();
		int km = distance/1000;//千米
		int m = distance%1000;//米
		if (km > 0) {
			distance1.setText(km + "千米" + m + "米");
		} else {
			distance1.setText(m + "米");
		}		
		return arg1;
	}

}
