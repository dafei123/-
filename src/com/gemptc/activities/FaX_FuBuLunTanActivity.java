package com.gemptc.activities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gemptc.beans.LunTanBean;
import com.gemptc.util.Config;
import com.gemptc.util.MyApplication;
import com.gemptc.util.StringUtils;

import com.gemptc.util.ZoomBitmap;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wo.applation.SysApplication;
import com.wo.module.Wo_LoginActivity;

import android.widget.LinearLayout.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.AlteredCharSequence;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class FaX_FuBuLunTanActivity extends Activity implements OnClickListener {
	String usernameString;
	String string;
	String type = "1";
	EditText editText1, editText2;// 1：论坛标题；2：论坛内容
	List<String> img_path;// 上传图片的路径
	TextView textView;// 发表的那个字
	ImageView imageView1, imageView2, imageView3;// 返回按钮，图片上传按钮,上传的图片存放的位置
	Intent intent;
	private RequestParams params;
	String p_title, p_content;// 获取用户写的标题，内容
	// 文件名
	// 上传的bitmap
	private Bitmap upbitmap;
	// 代表三个按钮;学习，生活，娱乐
	RadioButton radioButton1, radioButton2, radioButton3;
	// 多线程通信
	private Handler myHandler;
	private ProgressDialog myDialog;
	private static String HOST = "http://10.204.1.34:8080/Scholar/jieshou";
	LunTanBean lunTanBean;
	Uri uri;
	String p_picture;// 用户的图片
	String p_type;// 用户所选的类型
	RadioGroup group;// 多选按钮
	String u_username, u_image;
	private String picName;
	private File file;
	private HttpUtils httpUtils;
	private String	filename = "xiaochun" + System.currentTimeMillis() + ".jpg";
	private Bitmap bitmap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_fa_x__fu_bu_lun_tan);
		SysApplication.getInstance().addActivity(this);
		Intent intent1 = getIntent();
		usernameString = intent1.getStringExtra("name");
		initData();
		initViews();
	}

	private void initData() {
		img_path = new ArrayList<String>();// 初始化图片路径 定义成一个集合
	}

	private void Tools() {
		p_title = editText1.getText().toString();
		p_content = editText2.getText().toString();
		p_type = type;
		u_username = usernameString;
		LunTanBean lunTanBean = new LunTanBean(p_title, p_content, p_picture,
				p_type);
	}

	private void initViews() {
		textView = (TextView) findViewById(R.id.faxian_fabiao);
		imageView1 = (ImageView) findViewById(R.id.fanhui2);
		imageView2 = (ImageView) findViewById(R.id.fx_imageview);
		imageView3 = (ImageView) findViewById(R.id.fx_showimageview);
		// 对标题栏和文本进行初始化
		editText1 = (EditText) findViewById(R.id.wenben);
		editText2 = (EditText) findViewById(R.id.nerrong);
		textView.setOnClickListener(this);
		imageView1.setOnClickListener(this);
		imageView2.setOnClickListener(this);
		// 对学习，生活，娱乐按钮进行初始化
		group = (RadioGroup) findViewById(R.id.radiobuttonzu);
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.fx_xuexi:
					type = "1";
					break;
				case R.id.fx_yule:
					type = "2";
					break;
				case R.id.fx_shenghuo:
					type = "3";

					break;
				default:
					break;
				}

			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.faxian_fabiao:
			if(SysApplication.userbean.getU_id()==0){
				jump();
			}else{
				if (checkGoodsParameter()) {
					Tools();
					params = new RequestParams();//修改过110
					String u_id=String.valueOf(SysApplication.userbean.getU_id());
					params.addBodyParameter("p_title", p_title);
					params.addBodyParameter("p_content", p_content);
					params.addBodyParameter("p_type", p_type);				
					params.addBodyParameter("file",new File(string));
					params.addBodyParameter("u_id", u_id);
//					params.setContentType("multipart/form-data");
					uploadMethod(params, HOST);
				}
			}
			
			break;
		case R.id.fanhui2:
			FaX_FuBuLunTanActivity.this.finish();
			break;
		case R.id.fx_imageview:
			showDialog();
		default:
			break;
		}
	}

	// 下拉框的拍照，从手机相册中取
	private void showDialog() {
		// 生成一个dialog
		final AlertDialog dialog = new AlertDialog.Builder(
				FaX_FuBuLunTanActivity.this).create();
		dialog.show();
		// 获得对话框的window对象
		Window window = dialog.getWindow();
		// 给布局分配空间
		window.setContentView(R.layout.luntanalertdialog);
		// 布局设置(一般是子布局不应父布局)
		window.setLayout(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		// 设置动画
		window.setWindowAnimations(R.style.AnimBottom);
		// 设置监听事件
		Button takephoto = (Button) window.findViewById(R.id.btn_take);
		Button bendiButton = (Button) window.findViewById(R.id.btn_bendi);
		Button cancelButton = (Button) window.findViewById(R.id.btn_cancel);

		takephoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.cancel();
				intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			
				System.out.println(filename);
				// 下面这句指定调用相机拍照后的照片存储的路径
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
						Environment.getExternalStorageDirectory(), filename)));
				uri=Uri.fromFile(new File(
						Environment.getExternalStorageDirectory(), filename));
				startActivityForResult(intent, 1);
			}
		});
		bendiButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.cancel();
				intent = new Intent(Intent.ACTION_PICK, null);
				intent.setDataAndType(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(intent, 2);

			}
		});
		cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
	}
	boolean checkGoodsParameter() {
		// 没有选择论坛图片的
		if (editText1.getText().length() == 0) {
			StringUtils.showToast(this, Config.ERROR_NO_TITLE);
			editText1.requestFocus();
			return false;
		} else if (editText1.getText().length() < 1) {
			StringUtils.showToast(this, Config.ERROR_TITLE_TOSHORT);
			editText1.requestFocus();
			return false;
		} else if (editText2.getText().length() == 0) {
			StringUtils.showToast(this, Config.ERROR_NO_SUBTITLE);
			editText2.requestFocus();
			return false;
		} else if (editText2.getText().length() < 10) {
			StringUtils.showToast(this, Config.ERROR_SUBTITLE_TOSHORT);
			editText2.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			if (resultCode == RESULT_OK) {
				startPhotoZoom(uri);
//				bitmap=BitmapFactory.decodeFile(uri.getPath());
//				ZoomBitmap.zoomImage(bitmap, 200, 200);
//				imageView3.setImageBitmap(bitmap);
			}
			break;
		case 2:			
			if (data != null) {
				imageView3.setImageURI(data.getData());
				upbitmap = BitmapFactory.decodeFile(getAbsoluteImagePath(data
						.getData()));
				string=getAbsoluteImagePath(data.getData());
				file = new File(getAbsoluteImagePath(data.getData()));
				// 剪一下，防止测试的时候上传的文件太大
				upbitmap = ZoomBitmap.zoomImage(upbitmap,
						upbitmap.getWidth() / 8, upbitmap.getHeight() / 8);
			}
			break;
		case 3:
			Bitmap bitmap;
			string=Environment.getExternalStorageDirectory().getPath()+"/"+filename;
			bitmap = BitmapFactory.decodeFile(string);
			imageView3.setImageBitmap(bitmap);			
			break;
		}
	}

	// 取到绝对路径
	protected String getAbsoluteImagePath(Uri uri) {
		// can post image
		String[] proj = { MediaStore.Images.Media.DATA };
		@SuppressWarnings("deprecation")
		Cursor cursor = managedQuery(uri, proj, // Which columns to return
				null, // WHERE clause; which rows to return (all rows)
				null, // WHERE clause selection arguments (none)
				null); // Order-by clause (ascending by name)

		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	public void saveBitmap() {
		picName = "" + new Date().toString() + ".png";
		File f = new File(getExternalCacheDir(), picName);
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			upbitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void uploadMethod(final RequestParams params, final String uploadHost) {
		httpUtils = new HttpUtils();
		httpUtils.configSoTimeout(3000);
		httpUtils.send(HttpMethod.POST, uploadHost, params,
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
						System.out.println("图片成功111111111  ");
						intent = new Intent(FaX_FuBuLunTanActivity.this,
								MainActivity.class);
						intent.putExtra("faxian", true);
						startActivity(intent);
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {

						System.out.println("图片上传失败======");
					}

				});

	}

	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 300);
		intent.putExtra("outputY", 300);
		intent.putExtra("return-data", true);
		intent.putExtra("output", Uri.fromFile(new File(Environment.getExternalStorageDirectory(),filename)));
		startActivityForResult(intent, 3);
	}
	public void jump(){
		
		
	}
}
