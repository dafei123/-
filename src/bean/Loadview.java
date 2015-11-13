package bean;

import java.util.zip.Inflater;

import com.gemptc.activities.R;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.text.AlteredCharSequence;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;

public class Loadview {
	public static Dialog builder;
    public static void load(Context context){
        LayoutInflater inflater=LayoutInflater.from(context);
        View view =(LinearLayout) inflater.inflate(R.layout.wo_loading_view,null);
        builder=new Dialog(context,R.style.dialog);
    	builder.show();
    	Window windows=builder.getWindow();
    	android.view.ViewGroup.LayoutParams params=new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
    	windows.addContentView(view, params);
    }
    public static void relase(){
    	builder.dismiss();
    }
}
