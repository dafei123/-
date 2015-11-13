package adapter;

import image.SmartImageView;

import java.util.List;

import com.gemptc.activities.R;
import com.wo.applation.SysApplication;

import bean.Friendbean;

import android.content.Context;
import android.provider.ContactsContract.CommonDataKinds.Nickname;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FriendmsgAdapter extends BaseAdapter {
	List<Friendbean> list;
	Context context;
   LayoutInflater inflater;
   public FriendmsgAdapter(List<Friendbean> list,Context context){
	   this.list=list;
	   this.context=context;
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
	Handview hand=new Handview(); 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {	
		if(convertView==null){
			   convertView=inflater.inflate(R.layout.friendadapterview, null);
			   hand.imageview=(SmartImageView) convertView.findViewById(R.id.friendlist);
			   hand.nickname=(TextView) convertView.findViewById(R.id.friendAdaptername);
			   hand.sign=(TextView) convertView.findViewById(R.id.friendsign);
			   convertView.setTag(hand);
		}else{
			  convertView.getTag();
		}
		if(list.get(position).getFriend_image()!="0"){
		
		String url=SysApplication.httppath+"webproject/img/"+list.get(position).getFriend_image();
	    hand.imageview.setstyle("circle");
		hand.imageview.setImageUrl(url);}
	    hand.nickname.setText(list.get(position).getFriend_username());
	    hand.sign.setText(list.get(position).getFriend_signator());
		return convertView;
	}
class Handview
{SmartImageView imageview;
 TextView   nickname;
 TextView   sign;
	
}
}
