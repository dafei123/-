package adapter;

import image.SmartImageView;

import java.util.List;

import com.gemptc.activities.R;
import com.wo.applation.SysApplication;

import bean.Rankingbean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RankingFriendadapter extends BaseAdapter {
	List<Rankingbean> list;
	Context context;
	LayoutInflater inflater;
    public RankingFriendadapter(List<Rankingbean> list,Context contexrt){
    	this.list=list;
    	this.context=contexrt;
    	inflater=LayoutInflater.from(contexrt);
    }
	@Override
	public int getCount() {
		
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {		
		return position;
	}
Itemview iten=new Itemview();
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {		
		if(convertView==null){
			convertView=inflater.inflate(R.layout.friendadapterview, null);
			iten.image=(SmartImageView) convertView.findViewById(R.id.friendlist);
		    iten.name=(TextView) convertView.findViewById(R.id.friendAdaptername);
		   iten.sign=(TextView) convertView.findViewById(R.id.friendsign);
		   convertView.setTag(iten);
		}else{
			iten=(Itemview) convertView.getTag();
		}
		String url=SysApplication.httppath+"webproject/img/"+list.get(position).getUserimage();
		 iten.image.setstyle("circle");
		iten.image.setImageUrl(url);
		iten.name.setText(list.get(position).getUsername());
		iten.sign.setText(list.get(position).getSignator());
		return convertView;
	}
class Itemview{
	SmartImageView image;
	TextView name ;
	TextView sign;
}
}
