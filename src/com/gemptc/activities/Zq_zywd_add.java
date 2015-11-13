package com.gemptc.activities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import beanutil.DateUtil;
import beanutil.MyAppalication;
import beanutil.MyApplication;

import com.lidroid.xutils.HttpUtils;
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
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

//添加作业问答
public class Zq_zywd_add extends Activity implements OnClickListener {

	private ImageView ivbackzywd1;// 返回作业问答页面
	private ImageView ivsendzywd;// 提交
	private Button qux;// 取消
	private EditText edtQuestion;// 输入的文本内容
	Zq_zywd_add mContext;
	HttpUtils httpUtils;
	MyAppalication myAppalication;
	private ImageView ivcamera;// 图片添加	
	int U_id;
	private String pathImage;// 选择图片路径
	private Bitmap bmp;// 导入临时图片
	private ArrayList<HashMap<String, Object>> imageItem;// ??
	private SimpleAdapter simpleAdapter;// 适配器
	List<String> list = new ArrayList<String>();
	private static final int CAMERA_WITH_DATA = 0;// 拍照

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zq_zywd_add);
		SysApplication.getInstance().addActivity(this);

		initView();

	}

	private void initView() {
		ivbackzywd1 = (ImageView) findViewById(R.id.ivbackzywd1);
		edtQuestion = (EditText) findViewById(R.id.edtQuestion);

		ivsendzywd = (ImageView) findViewById(R.id.ivsendzywd);
		ivsendzywd.setOnClickListener(this);
		qux = (Button) findViewById(R.id.qux);
		qux.setOnClickListener(this);

		ivbackzywd1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Zq_zywd_add.this.finish();
			}
		});

		edtQuestion.setOnClickListener(this);
		// 获取控件对象
		ivcamera =(ImageView) findViewById(R.id.ivcamera);
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.camera);// 加号
		ivcamera.setImageBitmap(bmp);
		
		ivcamera.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 选择图片
				new AlertDialog.Builder(Zq_zywd_add.this)
						.setTitle("拍照")
						.setItems(new String[] { "相机" },
								new DialogInterface.OnClickListener() {

									public void onClick(
											DialogInterface dialog,
											int which) {
										switch (which) {
										case CAMERA_WITH_DATA:
											// 调用系统相机
											Intent camera = new Intent(
													MediaStore.ACTION_IMAGE_CAPTURE);
											startActivityForResult(camera,
													0);
											break;

										default:
											break;
										}

									}
								}).setNegativeButton("取消", null).show();

				
			}
		});
					
	}

	Builder builder;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.qux:
			Zq_zywd_add.this.finish();
			break;
		case R.id.ivsendzywd:
			send();
			break;
		default:
			break;
		}
	}

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

				list.add(pathImage);
				Log.e("list", list.toString());

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
			ivcamera.setImageBitmap(bitmap);
			FileOutputStream fout = null;
			File file = new File("/sdcard/pintu/");
			file.mkdirs();
			pathImage = file.getPath() + name;
			Log.e("filename", pathImage);
			list.add(pathImage);
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

	public void send() {
		httpUtils = new HttpUtils();
		// 帖子包含图片
		String url1 =MyApplication.applicationID+"/Zq_QuestionInsertServlet";
		
		// 获得帖子的主题
		String Q_content = edtQuestion.getEditableText().toString().trim();
		// 获得帖子的内容
		DateUtil dateUtil = new DateUtil();
		String Q_date = dateUtil.getTime();
		RequestParams params = new RequestParams();
		//获取用户Id
		int U_id=SysApplication.userbean.getU_id();
		Log.e("zhi-->", U_id+"");
		params.addBodyParameter("U_id", U_id + "");
		params.addBodyParameter("Q_content", Q_content);
		params.addBodyParameter("Q_date", Q_date);
		// 获取图片的总数i 放到文件内，一并传到web端
		for (int i = 0; i < list.size(); i++) {
			params.addBodyParameter("" + i, new File(list.get(i)));
		}
		httpUtils.send(HttpMethod.POST, url1, params,
				new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						Toast.makeText(Zq_zywd_add.this, "网络连接超时......",
								Toast.LENGTH_SHORT).show();
					}
					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// 帖子发表成功，跳到帖子列表并刷新
						Toast.makeText(Zq_zywd_add.this, "发送成功",
								Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(Zq_zywd_add.this,
								Zq_ZywdActivity.class);

						startActivity(intent);
					}
				});
	}

	
	protected void dialog(final int position) {
		AlertDialog.Builder builder = new Builder(Zq_zywd_add.this);
		builder.setMessage("确认移除已添加图片吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				imageItem.remove(position);
				simpleAdapter.notifyDataSetChanged();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

}
