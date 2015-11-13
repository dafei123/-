package com.zq.adapter;

import java.util.ArrayList;
import java.util.List;

import com.gemptc.activities.R;

import beanutil.MyAppalication;
import beanutil.Reply;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ReplyListViewAdapter extends BaseAdapter {

	List<Reply> list=new ArrayList<Reply>();
	Context context;
	LayoutInflater inflater;
	ViewHolder holder;
	
	
	public ReplyListViewAdapter(List<Reply> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		inflater=LayoutInflater.from(context);
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		holder=new ViewHolder();
		if(convertView==null){
			convertView=inflater.inflate(R.layout.zq_zywd_replyitem, null);
			//评论人的昵称，评论时间，内容
			holder.tvRusername=(TextView) convertView.findViewById(R.id.tvRusername);
			holder.tvRdate=(TextView) convertView.findViewById(R.id.tvRdate);
			holder.tvRcontent=(TextView) convertView.findViewById(R.id.tvRcontent);
			holder.ivRimage=(ImageView) convertView.findViewById(R.id.ivRimage);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		
		holder.tvRusername.setText(list.get(position).getU_username());
		holder.tvRdate.setText(list.get(position).getRe_date());
		holder.tvRcontent.setText(list.get(position).getRe_content());
		String path="http://10.204.1.34:8080/Scholar/img2/";
		MyAppalication.bitmapUtils.display(holder.ivRimage,
				path + list.get(position).getRe_picture());
		//Log.e("11111uuuu", path+list.get(position).getRe_picture());
		return convertView;
	}
	
	class ViewHolder{
		ImageView ivRimage;
		TextView tvRusername,tvRdate,tvRcontent;
	}

}
