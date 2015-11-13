package http;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.sax.StartElementListener;
import android.util.Log;
import android.widget.Toast;
import bean.Wo_Userbean;

import com.gemptc.activities.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.wo.applation.SysApplication;

public class HttpDown {
  public void  down( final int s,Context context,String a,String b){ 
	  down(s,null,null,context,a,b);
  } 
  public <T> void down(final int s,final List<T> list,final Handler hander,final Context context){
	 down(s,list,hander,context,"1","1"); 
  }
  public static <T> void down(final int s,final List<T> list,final Handler hander,final Context context,String phone,String password){
	  RequestParams params=new RequestParams();
	  params.addBodyParameter("userphone",phone);
	  params.addBodyParameter("password", password);
	  String url = null;
	  HttpUtils http=new HttpUtils();
	  List<T> lists=new ArrayList<T>();
	  Log.e("asdf", ""+s);
	  switch (s) {
	case 1:
		 url=SysApplication.httppath+"Netclass";
		 Log.e("url",url);
		break;
    case 2:
    	 url=SysApplication.httppath+"Gerenxinxi";
		break;
    case 3:
    	 url=SysApplication.httppath+"xuigai";
    	  Log.e("url",url);
		break;
    case 4:
  	    url=SysApplication.httppath+"Zhuce";
  	Log.e("url",url);
		break;
	default:
		break;
	}
	  http.send(HttpMethod.POST,url,params,new RequestCallBack<T>() {
		@Override
		public void onFailure(HttpException arg0, String arg1) {
		    Toast.makeText(context, "网络错误。", 0).show();			
		}

		@Override
		public void onSuccess(ResponseInfo<T> arg0) {			
			if(s==1){			 
			 list.add(arg0.result);
			 Log.e("sdf",""+arg0.result);
			 String b=(String) list.get(0);
			 Message msg=new Message();
			 msg.arg1=3;
			 hander.sendMessage(msg);
			}
			else if(s==2){				
//			String a=(String)arg0.result;
//			Gson gson=new Gson();
//		//	Type type = new TypeToken<List<Wo_Userbean>>(){}.getType();
//			List<T> list1=gson.fromJson(a, type);
//			list.addAll(list1);
//			System.out.println("s==2"+list.get(0).toString());
//			System.out.println("s==2--------"+list.size());
//		    Message msg=new Message();
//			 msg.arg1=3;
//			 hander.sendMessage(msg);
			}else if(s==3){
				Intent intent=new Intent(context,MainActivity.class);
				context.startActivity(intent);
			}else if(s==4){
				Intent intent=new Intent(context, MainActivity.class);
				context.startActivity(intent);
			}
		}
	});
  }
}
