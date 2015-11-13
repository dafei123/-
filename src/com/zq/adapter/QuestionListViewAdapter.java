package com.zq.adapter;

import java.util.ArrayList;
import java.util.List;

import com.gemptc.activities.R;

import beanutil.MyAppalication;
import beanutil.Question;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestionListViewAdapter extends BaseAdapter{

	//上下文对象
	Context context;
	View view;
	List<Question> list=new ArrayList<Question>();
	Question question;
	LayoutInflater inflater;
	ViewHolder holder;
	


	public QuestionListViewAdapter(Context context, List<Question> list) {
		super();
		this.context = context;
		this.list = list;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		holder=new ViewHolder();
		if(convertView==null){
			convertView=inflater.inflate(R.layout.zq_zywd_item, null);
			holder.usernameTextView=(TextView) convertView.findViewById(R.id.Quser);
			holder.timeTextView=(TextView) convertView.findViewById(R.id.Qtime);
			holder.contentTextView=(TextView) convertView.findViewById(R.id.tvQcontent);
			holder.questionImageView=(ImageView) convertView.findViewById(R.id.ivqpic);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		
		holder.usernameTextView.setText(list.get(position).getU_username());
		holder.timeTextView.setText(list.get(position).getQ_date());
		holder.contentTextView.setText(list.get(position).getQ_content());
		String path="http://10.204.1.34:8080/Scholar/img2/";
		MyAppalication.bitmapUtils.display(holder.questionImageView,
				path + list.get(position).getQ_picture());	
		//Log.e("获取的图片路径",path + list.get(position).getQ_picture());
		return convertView;
	}		
	
	public class ViewHolder{
		TextView usernameTextView,timeTextView,contentTextView;
		ImageView questionImageView;
	}

}
