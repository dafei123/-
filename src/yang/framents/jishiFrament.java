package yang.framents;

import java.util.ArrayList;
import java.util.Calendar;

import sharedPreferencesUtil.SharedHelper;
import sharedPreferencesUtil.TimesGet;

import com.gemptc.activities.R;
import com.gemptc.activities.Yang_jishiActivity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class jishiFrament extends Fragment {
	Button startButton;
	EditText minuteText, secondText;
	View view;
	int minute;
	int second;
	ArrayList<Integer> list = new ArrayList<Integer>();
	SharedHelper sp;
	SharedPreferences.Editor editor = null;	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.y_jishi, null);
		initview();
		Log.e("WWWWWWWWWWWWWWWWWWWWWWWWWWW", 22222222+"");
		return view;
	}

	private void initview() {

		startButton = (Button) view.findViewById(R.id.yang_start);
		minuteText = (EditText) view.findViewById(R.id.yang_minute);
		secondText = (EditText) view.findViewById(R.id.yang_sencond);
		minuteText.setText("45");
		secondText.setText("0");
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
					System.out.println(minute + ":" + second);

					list.add(minute);
					list.add(second);
					
					AlertDialog.Builder builder = new Builder(getActivity());					
					builder.setTitle("你确定要开始吗？");
					builder.setMessage("放弃会有严厉的惩罚");
					builder.setPositiveButton("是",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									
									// TODO Auto-generated method stub
									sp = new SharedHelper(v
											.getContext());
									String starttime = TimesGet
											.getCurrentTime();
									Log.e("hua",starttime);
									int settime = minute * 60 + second;
									Log.e("hua",settime+"");
									sp.save(starttime, String.valueOf(settime));
									Intent intent = new Intent(getActivity(),
											Yang_jishiActivity.class);
									intent.putIntegerArrayListExtra("times",
											list);
									Log.e("刘邦王凯互爆",list.toString());
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
