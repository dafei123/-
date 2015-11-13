package com.gemptc.activities;

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
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Zq_zywd_replyActivity extends Activity implements OnClickListener{

	ImageView ivbackhuifu;
	Button btncancelSendReply;
	EditText edtreply;
	Button btnSendReply;
	Zq_zywd_replyActivity mContext;
	HttpUtils httpUtils;
	MyAppalication myAppalication;
	private static final int QUEDING=0;
	String url=MyApplication.applicationID+"/ReplyInsertServlet";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zq_zywd_reply);
		SysApplication.getInstance().addActivity(this);
		
		Intent intent=getIntent();
		
		
		initView();
	}

	private void initView() {
		ivbackhuifu=(ImageView) findViewById(R.id.ivbackhuifu);
		btncancelSendReply=(Button) findViewById(R.id.btncancelSendReply);
		edtreply=(EditText) findViewById(R.id.edtreply);
		btnSendReply=(Button) findViewById(R.id.btnSendReply);
		
		ivbackhuifu.setOnClickListener(this);
		btncancelSendReply.setOnClickListener(this);		
		edtreply.setOnClickListener(this);
		btnSendReply.setOnClickListener(this);	
		
	}

	@Override
	public void onClick(View v) {
		
		ivbackhuifu.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Zq_zywd_replyActivity.this.finish();
			}
		});	
		btncancelSendReply.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(Zq_zywd_replyActivity.this)
				.setTitle("确定要放弃回复提问吗？")
				.setItems(new String[] { "确定"},
						new DialogInterface.OnClickListener() {

							public void onClick(
									DialogInterface dialog,
									int which) {
								switch (which) {
								case QUEDING:									
									Intent queding = new Intent(Zq_zywd_replyActivity.this,Zq_zywd_huifu.class);
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
		
		btnSendReply.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				httpUtils = new HttpUtils();
				// 获得帖子的主题
				String Re_content = edtreply.getEditableText().toString().trim();
				Log.e("sssssssss", edtreply.getEditableText().toString().trim());
				// 获得帖子的内容
				DateUtil dateUtil = new DateUtil();
				String Re_date = dateUtil.getTime();
				RequestParams params = new RequestParams();
				
				params.addBodyParameter("Re_content", Re_content);
				params.addBodyParameter("Re_date", Re_date);
				httpUtils.send(HttpMethod.POST, url, params,
						new RequestCallBack<String>() {

							@Override
							public void onFailure(HttpException arg0, String arg1) {
								Toast.makeText(Zq_zywd_replyActivity.this,
										"网络连接超时......", Toast.LENGTH_SHORT).show();
							}

							@Override
							public void onSuccess(ResponseInfo<String> arg0) {

								// 帖子发表成功，跳到帖子列表并刷新
								Toast.makeText(Zq_zywd_replyActivity.this, "发帖成功",
										Toast.LENGTH_SHORT).show();
								Intent intent = new Intent(
										Zq_zywd_replyActivity.this,
										Zq_zywd_huifu.class);

								startActivity(intent);

							}
						});
			}
		});
		
	}

	
}
