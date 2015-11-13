package com.gemptc.adapters;

import image.SmartImageView;

import java.util.ArrayList;
import java.util.List;

import com.gemptc.activities.R;
import com.gemptc.beans.LunTanBean;
import com.gemptc.util.MyApplication;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Luntanadapter extends BaseAdapter {
	Context context;
	public List<LunTanBean> list = new ArrayList<LunTanBean>();
	LayoutInflater mInflater;
	ViewHolder mHolder;

	public Luntanadapter(Context context, List<LunTanBean> list) {
		super();
		this.context = context;
		this.list = list;
		mInflater = LayoutInflater.from(context);
	}

	public List<LunTanBean> getList() {
		return list;
	}

	public void setList(List<LunTanBean> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	class ViewHolder {
		SmartImageView u_imageView, p_imageView;// 用户头像,论坛图标
		// ImageView zan_iImageView;// 赞的图片
		// ImageView hui_ImageView;// 回复的图片
		// TextView zanTextView;// 赞的数量
		// TextView huiTextView;// 回复的数量
		TextView u_username, p_daTe;// 用户名，发表的日期
		TextView p_Text;// 文本标题
		TextView p_content;// 文本内容
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			Log.e("view", "getview");
			// 第一次进入应用，初始化listview每一行的布局
			convertView = mInflater.inflate(R.layout.luntanallnerrong, null);
			mHolder = new ViewHolder();
			mHolder.u_imageView = (SmartImageView) convertView
					.findViewById(R.id.fx_imageviewtx);
			mHolder.p_imageView = (SmartImageView) convertView
					.findViewById(R.id.fx_tiezitupian);			
			mHolder.u_username = (TextView) convertView
					.findViewById(R.id.fx_username);
			mHolder.p_daTe = (TextView) convertView.findViewById(R.id.fx_time);
			mHolder.p_Text = (TextView) convertView
					.findViewById(R.id.fx_shuoshuo);
			mHolder.p_content = (TextView) convertView
					.findViewById(R.id.fx_tiezineirong2);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		String url = "http://" + MyApplication.getIp() + ":8080/Scholar/img2/"
				+ list.get(position).getU_image();
		Log.e("url", url);
		String url3 = "http://" + MyApplication.getIp() + ":8080/Scholar/img2/"
				+ list.get(position).getP_picture();
		 mHolder.u_imageView.setstyle("circle");
		mHolder.u_imageView.setImageUrl(url);
		BitmapUtils bitmapUtils=new BitmapUtils(context);  
		bitmapUtils.display(mHolder.p_imageView, url3);
		mHolder.u_username.setText(list.get(position).getU_username());
		mHolder.p_daTe.setText(list.get(position).getP_date());
		Log.e("a----------------", list.toString());
		mHolder.p_Text.setText(list.get(position).getP_title());
		mHolder.p_content.setText(list.get(position).getP_content());
		return convertView;
	}

}
