package sharedPreferencesUtil;

import java.util.HashMap;
import java.util.Map;

import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class SharedHelper {

    private Context mContext;

    public SharedHelper() {
    }

    public SharedHelper(Context mContext) {
        this.mContext = mContext;
    }


    //定义一个保存数据的方法
    public void save(String starttime,String settime) {
        SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("starttime", starttime);
        editor.putString("settime",settime);
        editor.commit();
        Toast.makeText(mContext, "信息已写入SharedPreference中", Toast.LENGTH_SHORT).show();
    }

    //定义一个读取SP文件的方法
    public Map<String, String> read() {
        Map<String, String> data = new HashMap<String, String>();
        SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        data.put("starttime", sp.getString("starttime",""));
      data.put("settime", sp.getString("settime",""));
        return data;
    }
}
