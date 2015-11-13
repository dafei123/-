package beanutil;

import java.util.ArrayList;
import java.util.List;

public class GlobalContants {
	
    public static final String IP="10.204.1.34";
	public static final String SERVLER_URL="http://" +IP+
			":8080/Scholar/P_Netselect";
	public static final String StudyRecord="http://" +IP+
			":8080/Scholar/Stu_select";
	public static final String Record="http://" +IP+
			":8080/Scholar/Re_addservlet";
	public static final String Recordselect="http://" +IP+
			":8080/Scholar/Re_select";
	public static List<RecordBean> R_list2=new ArrayList<RecordBean>();
	
}
