package com.gemptc.activities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import beanutil.DateUtil;
import beanutil.MyAppalication;
import beanutil.MyApplication;
import beanutil.Question;
import beanutil.Reply;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wo.applation.SysApplication;
import com.zq.adapter.ReplyListViewAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Zq_zywd_huifu extends Activity {

	private ImageView ivbackzywd2, ivRcamera;
	private Button btnSendReply;
	private ListView lvhflist;
	private EditText edtreply;
	private ReplyListViewAdapter adapter;
	List<Reply> list = new ArrayList<Reply>();
	HttpUtils httpUtils;
	MyApplication application;
	private static final int QUEDING = 0;
	int Q_id;
	int U_id;
	private String pathImage;// 选择图片路径
	private Bitmap bmp;// 导入临时图片
	private ArrayList<HashMap<String, Object>> imageItem;// ??
	private SimpleAdapter simpleAdapter;// 适配器
	List<String> list1 = new ArrayList<String>();
	private static final int CAMERA_WITH_DATA = 0;// 拍照

	TextView usernameTextView, timeTextView, contentTextView;
	ImageView questionImageView;
	String url = application.applicationID + "/Zq_RUSelectServlet";
	String url1 = MyApplication.applicationID + "/Zq_ReplyInsertServlet";
	Question question;

	Handler handler=new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				list.clear();
				downloadData();
				lvhflist.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				break;

			default:
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zq_zywd_huifu);
		SysApplication.getInstance().addActivity(this);

		initView();
		downloadData();
	}

	public void initView() {
		// 获取上一页传入的数据
		Intent intent = getIntent();
		Q_id = intent.getIntExtra("Q_id", Q_id);
		String U_username = intent.getStringExtra("U_username");
		String Q_time = intent.getStringExtra("Q_time");
		String Q_content = intent.getStringExtra("Q_content");
		String Q_picture = intent.getStringExtra("Q_picture");
		// 图片Id
		String path = "http://10.204.1.34:8080/Scholar/img2/";
		// Log.e("ssssa", path + Q_picture);

		ivbackzywd2 = (ImageView) findViewById(R.id.ivbackzywd2);
		btnSendReply = (Button) findViewById(R.id.btnSendReply);
		edtreply = (EditText) findViewById(R.id.edtreply);
		ivRcamera = (ImageView) findViewById(R.id.ivRcamera);

		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.camera);// 加号
		ivRcamera.setImageBitmap(bmp);

		lvhflist = (ListView) findViewById(R.id.lvhflist);

		usernameTextView = (TextView) findViewById(R.id.tvusername);
		timeTextView = (TextView) findViewById(R.id.tvdate);
		contentTextView = (TextView) findViewById(R.id.tvcontent);
		questionImageView = (ImageView) findViewById(R.id.ivimage);
		// 数据与控件绑定
		MyAppalication.bitmapUtils.display(questionImageView, path + Q_picture);
		usernameTextView.setText(U_username);
		timeTextView.setText(Q_time);
		contentTextView.setText(Q_content);

		adapter = new ReplyListViewAdapter(list, Zq_zywd_huifu.this);
		lvhflist.setAdapter(adapter);
		// 返回上级页面的判断
		ivbackzywd2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(Zq_zywd_huifu.this)
						.setTitle("确定要放弃回复吗？")
						.setItems(new String[] { "确定" },
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										switch (which) {
										case QUEDING:
											Intent queding = new Intent(
													Zq_zywd_huifu.this,
													Zq_ZywdActivity.class);
											startActivity(queding);
											finish();
											break;
										default:
											break;
										}

									}
								}).setNegativeButton("取消", null).show();
			}
		});

		// 相机控件的事件处理
		ivRcamera.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 选择图片
				new AlertDialog.Builder(Zq_zywd_huifu.this)
						.setTitle("拍照")
						.setItems(new String[] { "相机" },
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										switch (which) {
										case CAMERA_WITH_DATA:
											// 调用系统相机
											Intent camera = new Intent(
													MediaStore.ACTION_IMAGE_CAPTURE);
											startActivityForResult(camera, 0);
											break;

										default:
											break;
										}

									}
								}).setNegativeButton("取消", null).show();

			}
		});

		// 对问题回复
		btnSendReply.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				send();
				edtreply.setText("");
				ivRcamera.setImageBitmap(bmp);
			}
		});
	}

	public void send(){
		httpUtils = new HttpUtils();
		
		// 获得帖子的主题
		String Re_content = edtreply.getEditableText().toString()
				.trim();
		Intent intent = getIntent();
		// 获取问题编号，将数据传到指定的问题的回复中。
		U_id = intent.getIntExtra("U_id", U_id);
		Q_id = intent.getIntExtra("Q_id", Q_id);

		// 获得帖子的内容
		DateUtil dateUtil = new DateUtil();
		String Re_date = dateUtil.getTime();
		RequestParams params = new RequestParams();

		params.addBodyParameter("U_id", U_id + "");
		params.addBodyParameter("Q_id", Q_id + "");
		params.addBodyParameter("Re_content", Re_content);
		params.addBodyParameter("Re_date", Re_date);
		//获取图片的总数i放到文件内，并传到web端
		for (int i = 0; i < list1.size(); i++) {
			params.addBodyParameter(""+i,new File(list1.get(i)));
		}			
		
		httpUtils.send(HttpMethod.POST, url1, params,
				new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0,
							String arg1) {
						Toast.makeText(Zq_zywd_huifu.this,
								"网络连接超时......", Toast.LENGTH_SHORT)
								.show();
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {

						// 帖子发表成功
						Toast.makeText(Zq_zywd_huifu.this, "发送成功",
								Toast.LENGTH_SHORT).show();
						//接收到服务器发送的信息接收成功的消息，然后刷新ListView
								String result = arg0.result;
								if (result.equals("success")) {
									Message msg = new Message();
									msg.what = 1;
									handler.sendMessage(msg);
								}
					}
				});				
	}
	
	Builder builder;

	// 获取图片路径 响应startActivityForResult
	@SuppressWarnings("unused")
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 打开图片
		if (resultCode == RESULT_OK && requestCode == 1) {
			File file = new File(this.getExternalCacheDir(), pathImage);

			Uri uri = data.getData();
			if (!TextUtils.isEmpty(uri.getAuthority())) {
				// 查询选择图片
				Cursor cursor = getContentResolver().query(uri,
						new String[] { MediaStore.Images.Media.DATA }, null,
						null, null);
				// 返回 没找到选择图片
				if (null == cursor) {
					return;
				}

				// 光标移动至开头 获取图片路径
				cursor.moveToFirst();
				pathImage = cursor.getString(cursor
						.getColumnIndex(MediaStore.Images.Media.DATA));

				list1.add(pathImage);
				// Log.e("list", list1.toString());

			}
		} else if (resultCode == RESULT_OK && requestCode == 0) {
			String sdState = Environment.getExternalStorageState();

			new DateFormat();
			String name = DateFormat.format("yyyyMMdd_hhmmss",
					Calendar.getInstance(Locale.CHINA))
					+ ".jpg";
			Bundle bundle = data.getExtras();

			// 获取相机返回的数据，并转换为图片格式
			Bitmap bitmap = (Bitmap) bundle.get("data");
			ivRcamera.setImageBitmap(bitmap);
			FileOutputStream fout = null;
			File file = new File("/sdcard/pintu/");
			file.mkdirs();
			pathImage = file.getPath() + name;
			Log.e("filename", pathImage);
			list1.add(pathImage);
			try {
				fout = new FileOutputStream(pathImage);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fout);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					fout.flush();
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}// end if 打开图片

	}

	private void downloadData() {
		// 从数据库下载数据
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();

		params.addBodyParameter("Q_id", Q_id + "");
		// 向服务器发送请求
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// 获取服务器端返回的数据结果
				String result = arg0.result;
				// 使用GSon框架进行json解析
				Gson gson = new Gson();
				Type typeOfT = new TypeToken<List<Reply>>() {
				}.getType();
				List<Reply> list2 = gson.fromJson(result, typeOfT);
				list.addAll(list2);
				Log.e("list", list.toString());
				adapter.notifyDataSetChanged();
			}

		});
	}

}
