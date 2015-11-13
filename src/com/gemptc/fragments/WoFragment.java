package com.gemptc.fragments;

import image.SmartImageView;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import view.Circleimage;

import bean.Loadview;
import bean.Wo_Userbean;
import com.gemptc.activities.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hexiaochun.utils.ZoomBitmap;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wo.applation.SysApplication;
import com.wo.module.PersonZiLiaoActivity;
import com.wo.module.Wo_CollectActivity;
import com.wo.module.Wo_DreamActivity;
import com.wo.module.Wo_FriendmsgActivity;
import com.wo.module.Wo_SetActivity;
import com.wo.module.Wo_rankingActivity;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WoFragment extends Fragment implements OnClickListener {
	SharedPreferences p;
	SharedPreferences.Editor editor;
	SmartImageView image;
	View view;
	Intent intent;
	List<Wo_Userbean> list;
	TextView personalitysing;
	TextView nickname, personalitysign;
	private String filename = "xiaochu" + System.currentTimeMillis() + ".jpg";
	String string;
	final int xiangji = 1;
	final int xiangce = 2;
	final int caijian = 3;
	Uri uri;
	LinearLayout personlayout, friendmsglayout, rankinglayout, dreamlayout,
			collectlayout, setLayout;
@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	//Loadview.load(getActivity());
}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.wo, null);
		nickname = (TextView) view.findViewById(R.id.wo_nickname);
		personalitysign = (TextView) view.findViewById(R.id.wo_personalitysign);
		image = (SmartImageView) view.findViewById(R.id.wo_personimage);
		personalitysing = (TextView) view.findViewById(R.id.wo_personalitysign);
		personlayout = (LinearLayout) view
				.findViewById(R.id.wo_personalitylayout);
		friendmsglayout = (LinearLayout) view
				.findViewById(R.id.friendmessagelayout);
		rankinglayout = (LinearLayout) view.findViewById(R.id.wo_rankinglayout);
		dreamlayout = (LinearLayout) view.findViewById(R.id.dreamlayout);
		collectlayout = (LinearLayout) view.findViewById(R.id.wo_collectlayout);
		setLayout = (LinearLayout) view.findViewById(R.id.wo_setlayout);
		// 下载图片
		downimage();
		// 设置监听
		lear();
		return view;
	}

	// 下部对话框设置及监听
	private void showDiglog() {
		final AlertDialog dialog = new AlertDialog.Builder(view.getContext())
				.create();
		dialog.show();
		// 获得对话框的window对象
		Window window = dialog.getWindow();
		// 给布局分配空间
		window.setContentView(R.layout.luntanalertdialog);
		// 布局设置(一般是子布局不应父布局)
		window.setLayout(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		// 设置动画
		window.setWindowAnimations(R.style.AnimBottom);
		Button take = (Button) window.findViewById(R.id.btn_take);
		Button bendi = (Button) window.findViewById(R.id.btn_bendi);
		Button quxiao = (Button) window.findViewById(R.id.btn_cancel);
		take.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.cancel();
				// 调用系统相机，
				intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				System.out.println(filename);
				uri = Uri.fromFile(new File(Environment
						.getExternalStorageDirectory(), filename));
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
						Environment.getExternalStorageDirectory(), filename)));
				Log.e("urits", uri.toString());
				startActivityForResult(intent, xiangji);
			}
		});
		bendi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.cancel();// 调用本地相册
				intent = new Intent(Intent.ACTION_PICK, null);
				intent.setDataAndType(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(intent, xiangce);
			}
		});
		quxiao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
	}
	@Override
	public void onStart() {
		image.setImageBitmap(SysApplication.bitmap);
		if (SysApplication.userbean.getUsername()==null) {
			nickname.setText("你没设昵称");
		} else {
			nickname.setText(SysApplication.userbean.getUsername());
		}
		if (SysApplication.userbean.getSignator()==null) {
			personalitysign.setText("没有签名");
		} else {
			personalitysign.setText(SysApplication.userbean.getSignator());
		}
		super.onStart();
	}
	private void downimage() {
		if (SysApplication.userbean.getU_id() != 0) {
			list = new ArrayList<Wo_Userbean>();
			String a = String.valueOf(SysApplication.userbean.getU_id());
			Log.e("u_id",a);
			// HttpDown.down(2,list,mHandler,getActivity(),a,"a");}
			final HttpUtils http = new HttpUtils();
			RequestParams params = new RequestParams();
			params.addBodyParameter("userphone", a);
			http.send(HttpMethod.POST, SysApplication.httppath + "Gerenxinxi",params,
					new RequestCallBack<String>() {
						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {							
							String result = responseInfo.result;
							Log.e("result",result);
							Gson gson = new Gson();
							Type type = new TypeToken<List<Wo_Userbean>>() {
							}.getType();
							list = gson.fromJson(result, type);
							Log.e("list---->",list.get(0).toString());
							Log.e("list",list.toString());
							SysApplication.userbean = list.get(0);
							Log.e("login", list.get(0).toString());
							Log.e("下载list", list.toString());
							if (list.get(0).getUsername().equals("0")) {
								nickname.setText("你没设昵称");
							} else {
								nickname.setText(list.get(0).getUsername());
							}
							if (list.get(0).getSignator().equals("0")) {
								personalitysign.setText("没有签名");
							} else {
								personalitysign.setText(list.get(0)
										.getSignator());
							}
							System.out.println(list.get(0).getU_image());
							if (list.get(0).getU_image().equals("0")) {
								image.setImageResource(R.drawable.zhuyuanzhang);
							} else {
								String url = SysApplication.httppath
										+ "img2/"
										+ list.get(0).getU_image();
								System.out.println(url);
								image.setstyle("circle");
								image.setImageUrl(url);
								 http.configSoTimeout(3000);
						//		Loadview.relase();
							}

						}

						@Override
						public void onFailure(HttpException error, String msg) {
                         http.configTimeout(5000);
							Loadview.relase();
						}
					});
		}

	}

	private void lear() {
		image.setOnClickListener(this);
		personlayout.setOnClickListener(this);
		friendmsglayout.setOnClickListener(this);
		rankinglayout.setOnClickListener(this);
		dreamlayout.setOnClickListener(this);
		collectlayout.setOnClickListener(this);
		setLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.wo_personalitylayout:
			intent = new Intent(v.getContext(), PersonZiLiaoActivity.class);
			startActivity(intent);
			break;
		case R.id.friendmessagelayout:
			intent = new Intent(v.getContext(), Wo_FriendmsgActivity.class);
			startActivity(intent);
			break;
		case R.id.wo_rankinglayout:
			intent = new Intent(v.getContext(), Wo_rankingActivity.class);
			startActivity(intent);
			break;
		case R.id.dreamlayout:
			intent = new Intent(v.getContext(), Wo_DreamActivity.class);
			startActivity(intent);
			break;
		case R.id.wo_collectlayout:
			intent = new Intent(v.getContext(), Wo_CollectActivity.class);
			startActivity(intent);
			break;
		case R.id.wo_setlayout:
			intent = new Intent(v.getContext(), Wo_SetActivity.class);
			startActivity(intent);
			break;
		case R.id.wo_personimage:
			// 上传图片
			showDiglog();
		default:
			break;
		}

	}

	// intent返回结果；
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
			// 图片剪切
			startPhotoZoom(uri);
			break;
		case xiangce:
			Uri uri = data.getData();
			startPhotoZoom(uri);
			break;
		case caijian:
			Bitmap bitmap;
			string = Environment.getExternalStorageDirectory().getPath() + "/"
					+ filename;
			bitmap = BitmapFactory.decodeFile(Environment
					.getExternalStorageDirectory().getPath() + "/" + filename);
			Circleimage image1 = new Circleimage();
			image.setImageBitmap(image1.toRoundBitmap(bitmap));
			Log.e("asdf", "asdf");
			upload();
		}
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
		intent.putExtra("output", Uri.fromFile(new File(Environment
				.getExternalStorageDirectory(), filename)));
		startActivityForResult(intent, caijian);
	}

	public void upload() {
		String uploadHost = SysApplication.httppath + "imageservlet"; // 服务器接收地址
		RequestParams params = new RequestParams();
		params.addBodyParameter("u_id",
				String.valueOf(SysApplication.userbean.getU_id()));
		params.addBodyParameter("img1", new File(string)); // filePath是手机获取的图片地址
		uploadMethod(params, uploadHost);
	}

	private void uploadMethod(RequestParams params, String uploadHost) {
		HttpUtils http = new HttpUtils();
		http.send(HttpMethod.POST, uploadHost, params,
				new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
					}
				});
	}

}
