package yang.framents;

import java.util.ArrayList;

import sharedPreferencesUtil.SharedHelper;
import sharedPreferencesUtil.TimesGet;

import com.gemptc.activities.R;
import com.gemptc.activities.Yang_fanqieActivity;
import com.gemptc.activities.Yang_jishiActivity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class fanqieFrament extends Fragment{
	Button startButton;
	EditText minuteText,secondText;
	View view;
	int minute;  
    int second;
    LinearLayout layout;
    ArrayList<Integer> list = new ArrayList<Integer>();  
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view =inflater.inflate(R.layout.y_fanqie, null);
		initview();
		return view;
	}
	private void initview() {
		startButton=(Button) view.findViewById(R.id.yang_fqstart);
		minuteText=(EditText) view.findViewById(R.id.yang_fqminute);
		secondText=(EditText) view.findViewById(R.id.yang_fqsencond);
        layout=(LinearLayout) view.findViewById(R.id.yang_fanqietime);
		//layout=(LinearLayout) view.findViewById(R.layout.y_fanqie);
		minuteText.setText("25"); 
		secondText.setText("0");
		minuteText.setFocusable(false);
		secondText.setFocusable(false);
		minuteText.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "请输入0-59的整数", 1).show();
			
			}
		});
		secondText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "请输入0-59的整数", 1).show();
				
			}
		});
		layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder=new Builder(getActivity());
				builder.setTitle("番茄学习");
				builder.setIcon(R.drawable.ic_study_tomato_medium);
				builder.setSingleChoiceItems(new String[]{"25分钟","30分钟","35分钟","40分钟"}, 0, 
						new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								switch (which) {
								case 0:
									minuteText.setText("25");
									break;
								case 1:
									minuteText.setText("30");
									break;
								case 2:
									minuteText.setText("35");
									break;
								case 3:
									minuteText.setText("40");
									break;

								default:
									break;
								}
								
							}
						});
				builder.setNegativeButton("确定",null);
				  builder.show();
				
			}
		});
		startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				
				
				 if (!minuteText.getText().toString().equals("")) {  
	                    minute = Integer.parseInt(minuteText.getText().toString());  
	                }  
	                if (!secondText.getText().toString().equals("")) {  
	                    second = Integer.parseInt(secondText.getText().toString());  
	                }  
	                if (minute != 0 || second != 0) {  
	                    System.out.println(minute+":"+second);   
	                    list.add(minute);  
	                    list.add(second);  
	                    AlertDialog.Builder builder=new Builder(getActivity());
	    				builder.setTitle("你确定要开始吗？");
	    				builder.setMessage("放弃会有严厉的惩罚");
	    				builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
	    					
	    					@Override
	    					public void onClick(DialogInterface dialog, int which) {
	    						// TODO Auto-generated method stub
	    						SharedHelper sp= new SharedHelper(v.getContext());
	    						  String  starttime=TimesGet.getCurrentTime();
	    						     int settime= minute*60+second;	    						
	    						  sp.save(starttime, String.valueOf(settime));	    		                   
	    						  Intent intent = new Intent(getActivity(),Yang_fanqieActivity.class); 
	    		                    intent.putIntegerArrayListExtra("times2", list);  
	    		                    intent.putExtra("flag", 1);
	    		                    startActivity(intent);  
	    					}
	    				});
	    				builder.setNegativeButton("否", null);
	    				builder.show();
	                     
			}
	         }
		});
		
	}
	@Override
	public void onResume() {  
        // TODO Auto-generated method stub  
        minute = 0;  
        second = 0;  
        super.onResume();  
    } 
}
