package com.wo.module;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.gemptc.activities.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.BarLineChartBase.BorderPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.YLabels;
import com.github.mikephil.charting.utils.Legend.LegendForm;
import com.github.mikephil.charting.utils.XLabels.XLabelPosition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.bitmap.download.Downloader;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wo.applation.SysApplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class Wo_DreamActivity extends Activity {
  ImageButton mag;
  LineChart chart;
  List<String[]> list=new ArrayList<String[]>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wo__dream);
		Downloader();
		SysApplication.getInstance().addActivity(this);  
		mag=(ImageButton) findViewById(R.id.wo_dream_add);
		mag.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder=new Builder(Wo_DreamActivity.this);
				builder.setSingleChoiceItems(new String[]{"添加理想","修改理想","放弃理想"},0,new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:
							Intent intent=new Intent(Wo_DreamActivity.this,Wo_DreamEditActivity.class);
							startActivity(intent);
							break;
						default:
							break;
						}
					}
				} );
				builder.show();				
			}
		});
	}
	private void initLine() {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		chart=(LineChart) findViewById(R.id.lineview);
		chart.setDescription("学习情况统计图"); //璁剧疆鍥捐〃鎻忚堪淇℃伅
		chart.setScaleEnabled(true); //璁剧疆鍥捐〃鏄惁鍙缉鏀�
		chart.setBackgroundColor(Color.WHITE); //璁剧疆鍥捐〃鑳屾櫙棰滆壊
		chart.setDrawGridBackground(false);  //璁剧疆鏄惁鏄剧ず琛ㄦ牸
		 chart.setDrawYValues(true);//鍦▂杞翠笂鏄剧ず鏁版嵁
		 chart.setDrawBorder(true);//璁剧疆缃戞牸
		chart.setBorderPositions(new BorderPosition[] {
		            BorderPosition.BOTTOM});
		
		//鍦ㄦ姌鐜板浘鍙充笅瑙掓樉绀烘枃瀛�
		 //璁剧疆y杞村崟浣�
		 chart.setUnit("分钟");
		 //璁剧疆閫忔槑搴�
		 chart.setAlpha(0.8f);
		 //璁剧疆缃戞牸搴曠嚎鐨勯鑹�
		 chart.setBorderColor(Color.rgb(213, 216, 214));
		 //璁剧疆y杞村墠鍚庨鍊�		
	      chart.setInvertYAxisEnabled(false);
	    //璁剧疆鏄惁鍙互瑙︽懜锛屽涓篺alse锛屽垯涓嶈兘鎷栧姩锛岀缉鏀剧瓑
	        chart.setTouchEnabled(true);
	      //璁剧疆鏄惁鍙互鎷栨嫿锛岀缉鏀�
	        chart.setDragEnabled(true);
	        chart.setScaleEnabled(true);
	        //璁剧疆鏄惁鑳芥墿澶ф墿灏�
	        chart.setPinchZoom(true);
//	        //璁剧疆瀛椾綋
	        chart.setHighlightIndicatorEnabled(false);
//	        Typeface tf = Typeface.createFromAsset(getAssets(),
//					"OpenSans-Regular.ttf");
		//	chart.setValueTypeface(tf);
			XLabels xl = chart.getXLabels();
			xl.setPosition(XLabelPosition.BOTTOM); // 璁剧疆X杞寸殑鏁版嵁鍦ㄥ簳閮ㄦ樉绀�
			
		//	xl.setTypeface(tf); // 璁剧疆瀛椾綋
			xl.setTextSize(10f); // 璁剧疆瀛椾綋澶у皬
			xl.setSpaceBetweenLabels(3); // 璁剧疆鏁版嵁涔嬮棿鐨勯棿璺�
			YLabels yl = chart.getYLabels();
			//yl.setTypeface(tf); // 璁剧疆瀛椾綋
			yl.setTextSize(10f); // s璁剧疆瀛椾綋澶у皬
			yl.setLabelCount(5); // 璁剧疆Y杞存渶澶氭樉绀虹殑鏁版嵁涓暟
			setData(list.get(1),list.get(0));
			 //浠嶺杞磋繘鍏ョ殑鍔ㄧ敾
				chart.animateX(4000);
				chart.animateY(3000);   //浠嶻杞磋繘鍏ョ殑鍔ㄧ敾
				chart.animateXY(3000, 3000);    //浠嶺Y杞翠竴璧疯繘鍏ョ殑鍔ㄧ敾
				chart.setScaleMinima(0.5f, 1f);
				//璁剧疆瑙嗗彛
				// mChart.centerViewPort(10, 50);

		        // get the legend (only possible after setting data)
		        Legend l = chart.getLegend();
		        l.setForm(LegendForm.LINE);  //璁剧疆鍥炬渶涓嬮潰鏄剧ず鐨勭被鍨�
				//l.setTypeface(tf);  
				l.setTextSize(15);
				l.setTextColor(Color.rgb(104, 241, 175));
				l.setFormSize(30f); // set the size of the legend forms/shapes

		        // 鍒锋柊鍥捐〃
		        chart.invalidate();
	}
	private void setData(String[] aa,String[] bb) {
//		String[] aa = {"12","14","15","17","18","19","20"};
//    	String[] bb = {"122.00","234.34","85.67","117.90","332.33","113.33","120.78"};
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < aa.length; i++) {
            xVals.add(aa[i]);
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < bb.length; i++) {
            yVals.add(new Entry(Float.parseFloat(bb[i]), i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "学习情况统计图");

        set1.setDrawCubic(true);  //璁剧疆鏇茬嚎涓哄渾婊戠殑绾�
		set1.setCubicIntensity(0.2f);
		set1.setDrawFilled(false);  //璁剧疆鍖呮嫭鐨勮寖鍥村尯鍩熷～鍏呴鑹�
		set1.setDrawCircles(true);  //璁剧疆鏈夊渾鐐�
		set1.setLineWidth(2f);    //璁剧疆绾跨殑瀹藉害
		set1.setCircleSize(5f);   //璁剧疆灏忓渾鐨勫ぇ灏�
		set1.setHighLightColor(Color.rgb(244, 117, 117));
		set1.setColor(Color.rgb(104, 241, 175));    //璁剧疆鏇茬嚎鐨勯鑹�

        // create a data object with the datasets
        LineData data = new LineData(xVals, set1);
        // set data
        chart.setData(data);
		
	}
public void Downloader(){
	HttpUtils http=new HttpUtils();
	RequestParams params=new RequestParams();
	String url=SysApplication.httppath+"Wo_Dream";
	params.addBodyParameter("id",String.valueOf(SysApplication.userbean.getU_id()));
	Log.e("id",""+SysApplication.userbean.getU_id());
	http.send(HttpMethod.POST, url,params,new RequestCallBack<String>() {
		@Override
		public void onFailure(HttpException arg0, String arg1) {
			
			
		}

		@Override
		public void onSuccess(ResponseInfo<String> arg0) {
			String result=arg0.result;
			Gson gsom=new Gson();
			Type type=new TypeToken<List<String[]>>(){}.getType();
			List<String[]>list1=gsom.fromJson(result,type);
			list.addAll(list1);
			initLine();
		}
	} );
}
}
