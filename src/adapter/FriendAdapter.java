package adapter;

import java.util.ArrayList;
import java.util.List;

import com.gemptc.activities.R;

import bean.Friendbean;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FriendAdapter extends BaseAdapter {
	Friendbean friend;
	Context context;
	LayoutInflater inflater;
	List<String>list =new ArrayList<String>();
	List<String>list1=new ArrayList<String>();
   public FriendAdapter(Friendbean friend,Context context)
   { 
	 this.friend=friend;
	 this.context=context;
	 inflater=LayoutInflater.from(context);
	 list.add("个性签名：");
	 list.add("昵称：");	
	 list.add("性别：");
	 list.add("年龄：");
	 list.add("年纪：");
	 list.add("学校：");
	 list1.add(friend.getFriend_signator());
	 list1.add(friend.getFriend_username());	
	 list1.add(friend.getFriend_sex());
	 list1.add(""+friend.getFriend_age());
	 list1.add(friend.getFriend_class());
	 list1.add(friend.getFriend_school());
	
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
	
		return position;
	}
  Handers handers=new Handers();
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
		    convertView= inflater.inflate(R.layout.frienditem, null);
		    handers.name=(TextView) convertView.findViewById(R.id.name);
		    handers.text=(TextView) convertView.findViewById(R.id.texts);
		    convertView.setTag(handers);
		} 
		else{
		   handers=(Handers) convertView.getTag();
		}
		handers.name.setText(list.get(position));
		handers.text.setText(list1.get(position));
		return convertView;
	}
	class Handers{
		TextView name;
		TextView text;
	}

}
