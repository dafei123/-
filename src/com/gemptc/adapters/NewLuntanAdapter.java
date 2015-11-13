package com.gemptc.adapters;

import image.SmartImageView;

import java.util.ArrayList;
import java.util.List;

import util.NoScrollListView;

import com.gemptc.activities.R;
import com.gemptc.beans.Lun_tan;
import com.gemptc.util.MyApplication;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewLuntanAdapter extends BaseAdapter {

	private List<Lun_tan> parent_list, all_list;
	private Context context;
	private LayoutInflater inflater;
	private Handler handler;

	public NewLuntanAdapter(Context context, List<Lun_tan> parent_list,
			List<Lun_tan> all_list,Handler handler) {
		this.parent_list = parent_list;
		this.all_list = all_list;
		this.context = context;
		this.handler = handler;
		inflater = LayoutInflater.from(this.context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return parent_list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return parent_list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	private ViewHolder viewHolder;
	private HuifuAdapter adapter;

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		if (arg1 == null) {
			viewHolder = new ViewHolder();
			arg1 = inflater.inflate(R.layout.fax_tiezi_adapter, null);
			viewHolder.user_pic = (SmartImageView) arg1
					.findViewById(R.id.tiezi_user_pic);
			viewHolder.content = (TextView) arg1
					.findViewById(R.id.tiezi_content);
			viewHolder.username = (TextView) arg1
					.findViewById(R.id.tiezi_username);
			viewHolder.date = (TextView) arg1.findViewById(R.id.tiezi_time);
			viewHolder.louceng = (TextView) arg1
					.findViewById(R.id.tiezi_louceng);
			viewHolder.huifuTextView = (TextView) arg1
					.findViewById(R.id.tiezi_reply_text);
		
			viewHolder.listView = (NoScrollListView) arg1
					.findViewById(R.id.tiezi_reply);
			arg1.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) arg1.getTag();
		}

		Lun_tan lun_tan = parent_list.get(arg0);
		viewHolder.content.setText(lun_tan.getContent());
		viewHolder.username.setText(lun_tan.getUsername());
		viewHolder.date.setText(lun_tan.getDate());
		viewHolder.louceng.setText((arg0 + 1) + "楼");
		List<Lun_tan> list = new ArrayList<Lun_tan>(0);
		for (Lun_tan lun_tan2 : all_list) {
			if (lun_tan2.getReplyid() == lun_tan.getLun_tanid()) {
				list.add(lun_tan2);
			}
		}
		viewHolder.huifuTextView.setOnClickListener(new TextviewClickListener(arg0));
		String url = "http://" + MyApplication.getIp() + ":8080/Scholar/img2/"//修改过
				+ parent_list.get(arg0).getPicture();
		viewHolder.user_pic.setstyle("circle");
		viewHolder.user_pic.setImageUrl(url);
		adapter = new HuifuAdapter(context, list, all_list,
				lun_tan.getUsername(),handler);
		viewHolder.listView.setAdapter(adapter);
		return arg1;
	}

	class ViewHolder {
		SmartImageView user_pic;
		TextView username, date, content, louceng, huifuTextView;
		NoScrollListView listView;

	}
	public void add(Lun_tan lun_tan){
		all_list.add(lun_tan);
	}
	
	@Override
	public void notifyDataSetChanged() {
		if(adapter!=null){
			adapter.notifyDataSetChanged();
		}
		super.notifyDataSetChanged();
	}
	private final class TextviewClickListener implements OnClickListener {
		private int position;
		public TextviewClickListener(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tiezi_reply_text:
				Message message = new Message();
				message.what = 10;
				message.obj = parent_list.get(position);
				handler.sendMessage(message);
				break;
			}
		}
	}

}
