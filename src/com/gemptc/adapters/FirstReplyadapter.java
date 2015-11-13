package com.gemptc.adapters;

import image.SmartImageView;

import java.util.ArrayList;
import java.util.List;

import com.gemptc.activities.R;
import com.gemptc.adapters.Luntanadapter.ViewHolder;
import com.gemptc.beans.ResponseUserBean;
import com.gemptc.util.MyApplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FirstReplyadapter extends BaseAdapter {
    Context context;
    List<ResponseUserBean> list3=new ArrayList<ResponseUserBean>();
    LayoutInflater mInflater;
    ViewHolder mHolder;
	

	public FirstReplyadapter(Context context, List<ResponseUserBean> list3) {
		super();
		this.context = context;
		this.list3 = list3;
		mInflater = LayoutInflater.from(context) ;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list3.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list3.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	class ViewHolder {
		SmartImageView uImageView,rImageView;//用户头像，回复图像
		TextView usernameTextView;//用户姓名
		TextView  louceng;//所在楼层
		TextView timeTextView;//当前时间
		TextView  response;//回复的内容
		ImageView huifuImageView;//回复的图标
		TextView huifunum;//回复的数量
		ListView listView ;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
       if (convertView==null) {
    	// 第一次进入应用，初始化listview每一行的布局
    	   convertView=mInflater.inflate(R.layout.fx_firststatehuifu,null);
    	   mHolder=new ViewHolder();
    	   mHolder.uImageView=(SmartImageView) convertView.findViewById(R.id.fx_imageviewtx5);
	       mHolder.rImageView=(SmartImageView) convertView.findViewById(R.id.fx_huifupic);
	       mHolder.usernameTextView=(TextView) convertView.findViewById(R.id.fx_username4);
           mHolder.louceng=(TextView) convertView.findViewById(R.id.fx_louceng);
           mHolder.timeTextView=(TextView) convertView.findViewById(R.id.fx_time5);
           mHolder.response=(TextView) convertView.findViewById(R.id.fx_huifucontent);
           mHolder.huifuImageView=(ImageView) convertView.findViewById(R.id.fx_luntanhuifu4);
           mHolder.huifunum=(TextView) convertView.findViewById(R.id.fx_huifunum);
           convertView.setTag(mHolder);
       }
       else{
    	   mHolder = (ViewHolder) convertView.getTag(); 
       }
       String url2 = "http://" + MyApplication.getIp() + ":8080/Scholar/img2/"
				+list3.get(position).getU_image();
       String url3="http://" + MyApplication.getIp() + ":8080/Scholar/img2/"
				+list3.get(position).getRs_picture();
       mHolder.uImageView.setImageUrl(url2);
       if (url3.equals("null")){    	   
    	   mHolder.rImageView.setVisibility(1);
	   }else {
		   mHolder.rImageView.setImageUrl(url3);
	   }       
       mHolder.louceng.setText(list3.get(position).getRs_storey()+"楼");
       mHolder.timeTextView.setText(list3.get(position).getRs_date());
       mHolder.response.setText(list3.get(position).getRs_content());
       mHolder.usernameTextView.setText(list3.get(position).getU_username());     
       return convertView;
	}

}
