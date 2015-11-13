package com.gemptc.activities;

import image.SmartImageView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import util.NoScrollListView;
import util.ReboundScrollView;


import beanutil.MyAppalication;

import com.gemptc.adapters.FirstReplyadapter;
import com.gemptc.adapters.NewLuntanAdapter;
import com.gemptc.beans.LunTanBean;
import com.gemptc.beans.Lun_tan;
import com.gemptc.beans.ResponseUserBean;
import com.gemptc.util.Config;
import com.gemptc.util.MyApplication;
import com.gemptc.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wo.applation.SysApplication;
import com.wo.module.Wo_LoginActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;

public class FaX_LunTanLouZhuActivity extends Activity implements
		OnClickListener {
	LunTanBean lunTanBean;
	SmartImageView imageView1, imageView2;
	ImageView imageView3, fx_luntanhuifu2, imageView5, imageView6;
	List<LunTanBean> list = new ArrayList<LunTanBean>();
	TextView textView1, textView2, textView3, textView4, textView5, textView6;
	private String url;
	// ListView listView2;
	Intent intent;
	HttpUtils httpUtils;
	private RequestParams params, params1;
	private static String uploadHost = "http://10.204.1.34:8080/Scholar/jieshou2";
	FirstReplyadapter fReplyadapter;
	List<ResponseUserBean> list3 = new ArrayList<ResponseUserBean>();
	EditText editText1;// 回复内容
	Button tijiaoButton;// 提交回复内容
	String rs_content;// 获取用户回复的内容
	String p_id;// 回复帖子的id
	String be_id;// 回复人的id
	String u_id;// 发帖人的id
	String rs_storey;// 回复的楼层
	String url1 = "http://" + MyAppalication.getIp()
			+ ":8080/Scholar/CollectServlet";
	// 判断是否收藏
	boolean b = true;
	ImageView rightImageview;// 收藏表
	String url2 = "http://" + MyAppalication.getIp()
			+ ":8080/Scholar/PraiseServlet";
	// 判断是否点赞
	boolean z = true;
	private NoScrollListView hui_list;
	private List<Lun_tan> parentList = new ArrayList<Lun_tan>();
	private List<Lun_tan> all_list = new ArrayList<Lun_tan>();
	private NewLuntanAdapter luntanAdapter;
	private Lun_tan huifu_pinglun= new Lun_tan();
	private Lun_tan pinlun;
	private boolean isReply = false;
	ReboundScrollView reboundScrollView;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 10) {
				isReply = true;
				//huifu_pinglun = new Lun_tan();
				pinlun = (Lun_tan) msg.obj;
				onFocusChange(true);
			}
		}
	};

	private void onFocusChange(boolean hasFocus) {
		final boolean isFocus = hasFocus;
		(new Handler()).postDelayed(new Runnable() {
			public void run() {
				InputMethodManager imm = (InputMethodManager) editText1
						.getContext().getSystemService(INPUT_METHOD_SERVICE);
				if (isFocus) {
					// 显示输入法
					imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
				} else {
					// 隐藏输入法
					imm.hideSoftInputFromWindow(editText1.getWindowToken(), 0);
				}
			}
		}, 100);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fa_x__lun_tan_lou_zhu);
		SysApplication.getInstance().addActivity(this);
		// 去掉标题栏
		/*ActionBar actionBar = getActionBar();
		actionBar.hide();*/
		onFocusChange(false);
		initViews();
		initData();
	}

	private void initData() {
		// 判断是否收藏过这种帖子
		getwebcheckshoucang(url1);
		// 判断是否收藏过这种帖子
		getwebcheckdianzan(url2);
		fReplyadapter = new FirstReplyadapter(FaX_LunTanLouZhuActivity.this,
				list3);

		// listView2.setAdapter(fReplyadapter);
		// 此时，集合list中并没有数据，界面是空的
		// 开始使用XUtils框架从服务器上下载大类表数据
		httpUtils = new HttpUtils();
		params = new RequestParams();
		params.addQueryStringParameter("pid", lunTanBean.getP_id());
		// 客户端向服务器端发送请求
		url = "http://" + MyApplication.getIp() + ":8080/Scholar/Replyfirst";
		httpUtils.send(HttpRequest.HttpMethod.POST, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// 访问网络失败调用的方法

					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// 访问网络成功调用的方法
						String result = arg0.result;
						Gson gson = new Gson();
						Type typeOfT = new TypeToken<List<ResponseUserBean>>() {
						}.getType();
						List<ResponseUserBean> lResponseUserBeans = gson
								.fromJson(result, typeOfT);
						list3.addAll(lResponseUserBeans);
						Log.e("wwwwww------------",
								lResponseUserBeans.toString());
						fReplyadapter.notifyDataSetChanged();
					}
				});
	}

	private void initViews() {
		intent = getIntent();
		lunTanBean = (LunTanBean) intent.getSerializableExtra("position");
		Log.e("lunTanBean", lunTanBean.toString());
		imageView1 = (SmartImageView) findViewById(R.id.fx_imageviewtx2);// 用户头像
		imageView2 = (SmartImageView) findViewById(R.id.fx_tiezitupian2);// 帖子图片
		imageView3 = (ImageView) findViewById(R.id.fx_dianzan2);// 点赞的图片
		fx_luntanhuifu2 = (ImageView) findViewById(R.id.fx_luntanhuifu2);// 回复的图片
		imageView5 = (ImageView) findViewById(R.id.fanhui3);
		imageView6 = (ImageView) findViewById(R.id.fx_huabi);
		rightImageview = (ImageView) findViewById(R.id.fx_shoucang);
	    reboundScrollView=(ReboundScrollView) findViewById(R.id.scrollview);
        reboundScrollView.requestChildFocus(hui_list, null);
		textView1 = (TextView) findViewById(R.id.fx_username2);// 用户名
		textView2 = (TextView) findViewById(R.id.fx_time2);// 时间
		textView3 = (TextView) findViewById(R.id.fx_luntan_zannum2);// 赞的数量
		textView4 = (TextView) findViewById(R.id.fx_luntan_huifu2);// 回复
		textView5 = (TextView) findViewById(R.id.fx_shuoshuo3);// 说说
		textView6 = (TextView) findViewById(R.id.fx_tiezineirong3);// 帖子内容

		// 对回复的内容进行初始化
		editText1 = (EditText) findViewById(R.id.fx_huifuneirong1);
		tijiaoButton = (Button) findViewById(R.id.fx_tijiaoneirong);
		// listView2 = (ListView) findViewById(R.id.fx_luntan_firstrepiy);//
		// 下边的回复
		hui_list = (NoScrollListView) findViewById(R.id.fx_luntan_firstrepiy);
		imageView3.setOnClickListener(this);
		imageView5.setOnClickListener(this);
		imageView6.setOnClickListener(this);
		fx_luntanhuifu2.setOnClickListener(this);// 回复的监听事件
		rightImageview.setOnClickListener(this);
		tijiaoButton.setOnClickListener(this);
		textView1.setText(lunTanBean.getU_username());
		textView2.setText(lunTanBean.getP_date());
		textView5.setText(lunTanBean.getP_title());
		textView6.setText(lunTanBean.getP_content());
		p_id = lunTanBean.getP_id();
	//	be_id = MyApplication.u_id;
		//u_id = MyApplication.getU_id();
		u_id=String.valueOf(SysApplication.userbean.getU_id());//修改过110
		String url2 = "http://" + MyApplication.getIp() + ":8080/Scholar/img2/"
				+ lunTanBean.getU_image();
		System.out.println(lunTanBean.getU_image());
		String url3 = "http://" + MyApplication.getIp() + ":8080/Scholar/img2/"
				+ lunTanBean.getP_picture();
	    imageView1.setstyle("circle");
		imageView1.setImageUrl(url2);
		imageView2.setImageUrl(url3);
		// listView2 .setOnItemClickListener(new OnItemClickListener(
		// ) {
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		// // TODO Auto-generated method stub
		// Intent intent=new Intent(FaX_LunTanLouZhuActivity.this,
		// ReplyReplyActivity.class);
		// }
		// });
	}
	public void setListViewHeightBasedOnChildren(ListView listView) {
		
		 ListAdapter listAdapter = listView.getAdapter();
		 if (listAdapter == null) {
		 return;
		 }	
		 int totalHeight = 0;
		 for (int i = 0, len = listAdapter.getCount(); i < len; i++) {	 
		 View listItem = listAdapter.getView(i, null, listView);
		 listItem.measure(0, 0);
		 totalHeight += listItem.getMeasuredHeight();
		 }
		
		 ViewGroup.LayoutParams params = listView.getLayoutParams();
		 params.height = totalHeight+ (listView.getDividerHeight() *
		 (listAdapter.getCount() - 1));
		 listView.setLayoutParams(params);
		 }

	private void Tool() {
		rs_content = editText1.getText().toString();
		
		
		ResponseUserBean responseUserBean = new ResponseUserBean(rs_content,
				p_id);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fanhui3:
			intent = new Intent(FaX_LunTanLouZhuActivity.this,
					MainActivity.class);
			intent.putExtra("faxian", true);
			startActivity(intent);
			break;
		case R.id.fx_huabi:
			if(SysApplication.userbean.getU_id()==0){
				jump();
			}else{
				intent = new Intent(FaX_LunTanLouZhuActivity.this,
						FaX_FuBuLunTanActivity.class);
				intent.putExtra("name", lunTanBean.getU_username());
				startActivity(intent);
			}
		
			break;
		case R.id.fx_tijiaoneirong:
			
				if (checkGoodsParameter()) {
					Tool();
					editText1.setText("");
					params1 = new RequestParams();
					System.out.println(rs_content);
					params1.addBodyParameter("rs_content", rs_content);
					params1.addBodyParameter("u_id", u_id);
					// params1.addBodyParameter("be_id", be_id);
					huifu_pinglun.setContent(rs_content);
					huifu_pinglun.setUserid(Integer.parseInt(u_id));

					if (list3.size() == 0) {
						params1.addBodyParameter("rank", "1");
					} else {
						params1.addBodyParameter("rank",
								String.valueOf(list3.size() + 1));
					}
					params1.addBodyParameter("rootid", lunTanBean.getP_id());
					huifu_pinglun.setRootid(Integer.parseInt(lunTanBean.getP_id()));
					if (isReply) {
						params1.addBodyParameter("p_id", pinlun.getLun_tanid() + "");
						huifu_pinglun.setReplyid(pinlun.getLun_tanid());
						huifu(params1);

					} else {
						params1.addBodyParameter("p_id", 0 + "");
						huifu_pinglun.setReplyid(0);
						huifu(params1);
					}
			}
			
				
				/*intent =new Intent(FaX_LunTanLouZhuActivity.this,FaX_LunTanLouZhuActivity.class);
				intent.putExtra("position", lunTanBean);
				startActivity(intent);*/
			
			break;
		case R.id.fx_shoucang:
			if (b) {
			
				getwebshoucang(url1);
				
			} else {
			
				getwebquxiao(url1);
				
			}
			break;
		case R.id.fx_dianzan2:
			if (z) {
				imageView3.setImageResource(R.drawable.fx_zanhou);
				getwebdianzan(url2);
				z = false;
			} else {
				imageView3.setImageResource(R.drawable.fx_zanqian);
				getwebquxiao1(url2);
				z = true;
			}
			break;
		case R.id.fx_luntanhuifu2:
			isReply = false;
			
			huifu_pinglun = new Lun_tan();
			huifu_pinglun.setUserid(Integer.parseInt(MyApplication.getU_id()));
			onFocusChange(true);
           
			break;
		default:
			break;
		}
	}

	private void huifu(RequestParams params12) {
		onFocusChange(false);
		String url = "http://" + MyApplication.getIp() + ":8080"
				+ "/Scholar/replyluntan";
		httpUtils.send(HttpMethod.POST, url, params12,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						String result = arg0.result;
						// if (result != null && "true".equals(result)) {
						all_list.add(huifu_pinglun);
						parentList.add(huifu_pinglun);
						getHuiFu(Integer.parseInt(lunTanBean.getP_id()));
						Toast.makeText(FaX_LunTanLouZhuActivity.this, "评论成功", 0)
								.show();
						
						// }
                  
					}
				});

	}

	// 检查是否点过赞
	private void getwebcheckdianzan(String url2) {
		HttpUtils httpUtils4 = new HttpUtils();
		RequestParams params4 = new RequestParams();
		params4.addBodyParameter("flg", "2");
		params4.addBodyParameter("u_id", MyApplication.getU_id());
		String p_id = lunTanBean.getP_id();
		params4.addBodyParameter("p_id", p_id);
		httpUtils4.send(HttpMethod.POST, url2, params4,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
						Log.e("pk", "成功");
						String result = responseInfo.result;
						if (result.equals("是")) {
							z = false;
							imageView3.setImageResource(R.drawable.fx_zanhou);
						}

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub
						Log.e("pk", "失败");
					}
				});

	}

	// 取消赞
	private void getwebquxiao1(String url2) {
		HttpUtils httpUtils4 = new HttpUtils();
		RequestParams params4 = new RequestParams();
		params4.addBodyParameter("flg", "3");
		params4.addBodyParameter("u_id", MyApplication.getU_id());
		String p_id = lunTanBean.getP_id();
		params4.addBodyParameter("p_id", p_id.trim());
		httpUtils4.send(HttpMethod.POST, url2, params4,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = responseInfo.result;
						Toast.makeText(FaX_LunTanLouZhuActivity.this, result, 0)
								.show();
					}

					@Override
					public void onFailure(HttpException error, String msg) {

					}
				});

	}

	// 点赞
	private void getwebdianzan(String url2) {
		HttpUtils httpUtils4 = new HttpUtils();
		RequestParams params4 = new RequestParams();
		params4.addBodyParameter("flg", "1");
		params4.addBodyParameter("u_id", MyApplication.getU_id());
		String p_id = lunTanBean.getP_id();
		params4.addBodyParameter("p_id", p_id.trim());
		httpUtils4.send(HttpMethod.POST, url2, params4,
				new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
						Log.e("ZAnPPPPPPPPPPPP", "成功");
						String result = responseInfo.result;
						Toast.makeText(FaX_LunTanLouZhuActivity.this, result, 0)
								.show();
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub
						Log.e("sc", "失败");
					}
				});

	}

	// 判断帖子是否收藏过
	private void getwebcheckshoucang(final String url1) {

		HttpUtils httpUtils1 = new HttpUtils();
		RequestParams params3 = new RequestParams();
		params3.addBodyParameter("flg", "2");
		params3.addBodyParameter("u_id", MyApplication.getU_id());
		String p_id = lunTanBean.getP_id();
		params3.addBodyParameter("p_id", p_id);
		httpUtils1.send(HttpMethod.POST, url1, params3,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
						Log.e("pk", "成功");
						String result = responseInfo.result;
						if (result.equals("是")) {
							b = false;
							rightImageview
									.setImageResource(R.drawable.fx_shoucang1);
						}else{
							b = true;
							rightImageview
									.setImageResource(R.drawable.fx_shoucang2);
						}

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub
						Log.e("pk", "失败");
					}
				});

	}

	// 取消收藏
	private void getwebquxiao(String url1) {
		HttpUtils httpUtils1 = new HttpUtils();
		RequestParams params3 = new RequestParams();
		params3.addBodyParameter("flg", "3");
		params3.addBodyParameter("u_id", MyApplication.getU_id());
		String p_id = lunTanBean.getP_id();
		params3.addBodyParameter("p_id", p_id.trim());
		httpUtils1.send(HttpMethod.POST, url1, params3,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = responseInfo.result;
						rightImageview.setImageResource(R.drawable.fx_shoucang2);
						b = true;
						Toast.makeText(FaX_LunTanLouZhuActivity.this, result, 0)
								.show();
					}

					@Override
					public void onFailure(HttpException error, String msg) {

					}
				});
	}

	// 收藏
	private void getwebshoucang(String url1) {
		HttpUtils httpUtils1 = new HttpUtils();
		RequestParams params3 = new RequestParams();
		params3.addBodyParameter("flg", "1");
		params3.addBodyParameter("u_id", MyApplication.getU_id());
		String p_id = lunTanBean.getP_id();
		params3.addBodyParameter("p_id", p_id.trim());
		httpUtils1.send(HttpMethod.POST, url1, params3,
				new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
						Log.e("sc", "成功");
						rightImageview.setImageResource(R.drawable.fx_shoucang1);
						b = false;
						String result = responseInfo.result;
						Toast.makeText(FaX_LunTanLouZhuActivity.this, result, 0)
								.show();
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub
						Log.e("sc", "失败");
					}
				});

	}

	boolean checkGoodsParameter() {
		// 没有写回复
		if (editText1.getText().length() == 0) {
			StringUtils.showToast(this, Config.ERROR_SUBTITLE_REPLY);
			editText1.requestFocus();
			return false;
		}
		return true;
	}

	public void uploadReply(final RequestParams params1, final String uploadHost) {
		HttpUtils httpUtils1 = new HttpUtils();
		// httpUtils.configSoTimeout(10000);
		httpUtils1.send(HttpMethod.POST, uploadHost, params1,
				new RequestCallBack<String>() {
					@Override
					public void onStart() {

					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						// 上传中
						System.out.println("上传中  ");
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						System.out.println("回复成功111111111  ");
						parentList.clear();
						initData();
						
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {

						System.out.println("回复上传失败======");
					}

				});
	}

	private void getHuiFu(int id) {
		String url = "http://" + MyApplication.getIp()
				+ ":8080/Scholar/huifu?rsid=" + id;
		httpUtils.send(HttpMethod.POST, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result = arg0.result;
				if (result != null) {
					Gson gson = new Gson();
					Type type = new TypeToken<List<Lun_tan>>() {
					}.getType();
					all_list.clear();
					parentList.clear();
					all_list = gson.fromJson(result, type);
					for (Lun_tan lun_tan : all_list) {
						if (lun_tan.getReplyid() == 0) {
							parentList.add(lun_tan);
						}
					}
					luntanAdapter = new NewLuntanAdapter(
							FaX_LunTanLouZhuActivity.this, parentList,
							all_list, handler);
					hui_list.setAdapter(luntanAdapter);
					
				}			
				luntanAdapter.notifyDataSetChanged();					
			}
		});
	}
	/* private void refresh(){
		 finish();
		 intent.setClass(FaX_LunTanLouZhuActivity.this,FaX_LunTanLouZhuActivity.class);
		  Bundle bl=new Bundle();
		    bl.putString("rs_content",rs_content);
		    intent.putExtras(bl);
		     startActivity(intent);
		  }*/

	@Override
	protected void onResume() {
		if (p_id != null && !"".equals(p_id)) {
			getHuiFu(Integer.parseInt(lunTanBean.getP_id()));
		}
		super.onResume();
	
	}
	public void jump(){
		AlertDialog.Builder dialog=new AlertDialog.Builder(this);
		dialog.setTitle("请先登录");
		dialog.setMessage("你还没有登录，是否登录");
		dialog.setPositiveButton("是",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent1=new Intent(FaX_LunTanLouZhuActivity.this, Wo_LoginActivity.class);
				startActivity(intent1);
			}
		});
		dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		});
		dialog.create();
		dialog.show();
		
	}
}
