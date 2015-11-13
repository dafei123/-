package adapter;

import java.util.List;

import com.gemptc.activities.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Wo_collect_all_adapter extends BaseAdapter {
	List<String[]> list;
	Context context;
	LayoutInflater inflater;

	public Wo_collect_all_adapter(List<String[]> list, Context context) {
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
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

	class Hom {
		TextView title;
		TextView nirong;
	}
	Hom hom;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		Hom hom = new Hom();
		if (convertView == null) {
			hom = new Hom();
			convertView = inflater.inflate(R.layout.wo_collect_talk_item, null);
			hom.title = (TextView) convertView
					.findViewById(R.id.wo_collect_talk_title);
			hom.nirong = (TextView) convertView
					.findViewById(R.id.wo_collect_talk_nickname);
			convertView.setTag(hom);
		} else {
			hom = (Hom) convertView.getTag();
		}
		hom.title.setText(list.get(position)[0]);
		hom.nirong.setText(list.get(position)[1]);
		return convertView;
	}

}
