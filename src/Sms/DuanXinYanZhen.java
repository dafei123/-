package Sms;

import com.wo.module.Wo_RegisterActivity;
import com.wo.module.Wo_XuiGaiActivity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class DuanXinYanZhen {
	int flag;
	private static String APPKEY = "b81ddc34b4b4";
	private static String APPSECRET = "58512bd000f13002c13a9c8303825ae2";
	public String phString;
//	Wo_XuiGaiActivity context;
Context context;
	public DuanXinYanZhen(Context context) {
		this.context = context;
	}
	public void huidiao() {
		SMSSDK.initSDK(context, APPKEY, APPSECRET);
		EventHandler eh = new EventHandler() {
			@Override
			public void afterEvent(int arg0, int arg1, Object arg2) {
				// TODO Auto-generated method stub
				Message msg = new Message();
				msg.arg1 = arg0;
				msg.arg2 = arg1;
				msg.obj = arg2;
				hander.sendMessage(msg);
			}
		};
		SMSSDK.registerEventHandler(eh);
	}

	Handler hander = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			int envent = msg.arg1;
			int result = msg.arg2;
			Object data = msg.obj;
			Log.e("envent", "" + envent);
			Log.e("result", "" + result);
			if (result == SMSSDK.RESULT_COMPLETE) {
				if (envent == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
					Toast.makeText(context, "请稍等...", Toast.LENGTH_SHORT)
							.show();
					if(flag==1){Wo_XuiGaiActivity wo_tivity=(Wo_XuiGaiActivity)context;
					wo_tivity.upusermessage();}else if(flag==2){
						Wo_RegisterActivity wo_tivity=(Wo_RegisterActivity) context;
						wo_tivity.upusermessage();
					}
					
					
				} else if (envent == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
					Toast.makeText(context, "验证码已经发送", Toast.LENGTH_SHORT)
							.show();
				}
			}

		}
	};
	public void code(String a,int b) {
		if (!TextUtils.isEmpty(a)) {
			flag=b;
			SMSSDK.getVerificationCode("86", a);
			phString = a;
		} else {
			Toast.makeText(context, "电话不能为空", 1).show();
		}
	}

	public void upload(String b) {
		if (!TextUtils.isEmpty(b)) {
			SMSSDK.submitVerificationCode("86", phString, b);
		} else {
			Toast.makeText(context, "验证码不能为空", 1).show();
		}

	}

	public void clear() {
		SMSSDK.unregisterAllEventHandler();
	}
}
