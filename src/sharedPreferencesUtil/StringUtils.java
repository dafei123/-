package sharedPreferencesUtil;

public class StringUtils {
	public static String[] getTimeSplit(String time) {
	
		String[] 	timeTemp=time.split("[-:]");
		return timeTemp;

	}
}
