package com.gemptc.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Yang_fangqiActivity extends Activity implements OnClickListener {
TextView textView;
Intent intent;
Button fangqibt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yang_fangqi);
		initview();
	}
	private void initview() {
		textView=(TextView) findViewById(R.id.yang_fqwancheng);
		fangqibt=(Button) findViewById(R.id.yang_fqfenxiang);
		textView.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.yang_fqwancheng:
			  intent=new Intent(this,MainActivity.class);
			     startActivity(intent);
			break;
		case R.id.yang_fqfenxiang:
			 // showShare();
			share();
			break;
		default:
			break;
		}
		
	}
	private void share() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "不好好学习的下场是悲惨的"
				);
		startActivity(Intent.createChooser(intent, "学霸养成记分享！"));

	}
	/*private void showShare() {
		 ShareSDK.initSDK(this);
		 OnekeyShare oks = new OnekeyShare();
		 //关闭sso授权
		 oks.disableSSOWhenAuthorize(); 

		// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
		 //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
		 // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		 oks.setTitle(getString(R.string.share));
		 // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		 oks.setTitleUrl("http://sharesdk.cn");
		 // text是分享文本，所有平台都需要这个字段
		 oks.setText("我是分享文本");
		 // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		 //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		 // url仅在微信（包括好友和朋友圈）中使用
		 oks.setUrl("http://sharesdk.cn");
		 // comment是我对这条分享的评论，仅在人人网和QQ空间使用
		 oks.setComment("我是测试评论文本");
		 // site是分享此内容的网站名称，仅在QQ空间使用
		 oks.setSite(getString(R.string.app_name));
		 // siteUrl是分享此内容的网站地址，仅在QQ空间使用
		 oks.setSiteUrl("http://sharesdk.cn");

		// 启动分享GUI
		 oks.show(this);
		 }*/
}
