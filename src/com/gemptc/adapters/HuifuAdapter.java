package com.gemptc.adapters;

import java.util.ArrayList;
import java.util.List;

import util.NoScrollListView;

import com.gemptc.activities.R;
import com.gemptc.beans.Lun_tan;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HuifuAdapter extends BaseAdapter {

	private List<Lun_tan> parent_list, all_list;
	private Context context;
	private LayoutInflater inflater;
	private String username;

	public HuifuAdapter(Context context, List<Lun_tan> parent_list,
			List<Lun_tan> all_list, String username,Handler handler) {
		this.parent_list = parent_list;
		Log.e("list", parent_list.toString());
		this.all_list = all_list;
		this.context = context;
		this.username = username;
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
	private ArrayList<Lun_tan> list;
	private Handler handler;

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		if (arg1 == null) {
			arg1 = inflater.inflate(R.layout.fax_replay_adapter, null);
			viewHolder = new ViewHolder();
			viewHolder.content = (TextView) arg1
					.findViewById(R.id.reply_content);
			viewHolder.username = (TextView) arg1
					.findViewById(R.id.reply_username);
			viewHolder.listView = (NoScrollListView) arg1
					.findViewById(R.id.reply_reply);
			viewHolder.reply_reply_text = (TextView) arg1
					.findViewById(R.id.reply_reply_text);
			arg1.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) arg1.getTag();
		}
		Lun_tan lun_tan = parent_list.get(arg0);
		viewHolder.content.setText(lun_tan.getContent());
		viewHolder.username.setText(lun_tan.getUsername() + "回复" + username
				+ "：");
		viewHolder.reply_reply_text
				.setOnClickListener(new TextviewClickListener(arg0));
		list = new ArrayList<Lun_tan>();
		for (Lun_tan lun_tan2 : all_list) {

			if (lun_tan2.getReplyid() == lun_tan.getLun_tanid()) {
				list.add(lun_tan2);
			}
		}
		adapter = new HuifuAdapter(context, list, all_list,
				lun_tan.getUsername(),handler);
		viewHolder.listView.setAdapter(adapter);
		return arg1;
	}

	class ViewHolder {
		TextView username, content, reply_reply_text;
		NoScrollListView listView;
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
