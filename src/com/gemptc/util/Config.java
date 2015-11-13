package com.gemptc.util;
public class Config {

	public static final String APP_ID = "com.example.wodangjialayout";
	
	
	public static final String CHARSET = "UTF-8";


	public static final String SERVER_HOST = "http://192.168.191.1:8080";
	public static final String API_URL = SERVER_HOST+"/wdj/api";
	public static final String UPLOAD_URL = SERVER_HOST+"/wdj/upload";

	
	//smssdk 接口
	public static final String SMSSDK_APPKEY="ada9569df712";
	public static final String SMSSDK_APPSECRET="14eceb0fc51f9c80a384988ad105adbd";
	public static final String COUNTRY_CODE_CHINA="86";
	

	public static final String KEY_ACTION = "action";
	public static final String KEY_PHONE = "phone";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_STATUS = "status";
	public static final String KEY_CODE = "code";
	public static final String KEY_PASSWORD_MD5="password_md5";
	public static final String KEY_USER="user";
	public static final String KEY_MSG="msg";
	public static final String KEY_TITLE="title";
	public static final String KEY_SUBTITLE="subtitle";
	public static final String KEY_PRICE="price";
	public static final String KEY_STOCK="stock";
	public static final String KEY_TYPE="type";
	public static final String KEY_ITEM="item";
	public static final String KEY_GOODSLIST="goodslist";
	public static final String KEY_SIZE="size";
	public static final String KEY_KEYWORD="keyword";
	public static final String KEY_PAGE="page";
	public static final String KEY_COMMENTLIST="commentlist";
	public static final String KEY_GOODS_ID="goods_id";
	public static final String KEY_USER_ID="user_id";
	public static final String KEY_SORT="sort";
	
	public static final String ACTION_REGISTER = "register";
	public static final String ACTION_LOGIN = "login";
	public static final String ACTION_LOGIN_FAST = "login_fast";
	public static final String ACTION_SEND_CODE = "send_code";
	public static final String ACTION_ADD_GOOD = "add_goods";
	public static final String ACTION_GET_TYPES = "get_types";
	public static final String ACTION_GET_GOODS = "get_goods";
	public static final String ACTION_GOODS_LIST = "goods_list";
	public static final String ACTION_SEARCH_GOODS = "search_goods";
	public static final String ACTION_GET_GOODS_COMMENT = "get_goods_comment";
	public static final String ACTION_COLLECT_GOODS = "collect_goods";
	public static final String ACTION_CANCLE_COLLECT_GOODS = "cancle_collect_goods";
	public static final String ACTION_GET_GOODS_IS_COLLECT = "get_goods_is_collect";
	public static final String ACTION_GET_SHOW_NAME = "get_show_name";
	
	//public static final String ERROR_NO_IMGS = "至少选择一张商品图片！";
	public static final String ERROR_NO_TITLE = "论坛标题不能为空！";
	public static final String ERROR_TITLE_TOSHORT = "论坛标题长度至少为一个字符！";
	public static final String ERROR_NO_SUBTITLE = "论坛内容不能为空！";
	public static final String ERROR_SUBTITLE_TOSHORT = "论坛描述长度至少为十个字符！";
	public static final String ERROR_SUBTITLE_REPLY = "回复内容长度至少为十个字符！";
	public static final int RESULT_STATUS_SUCCESS = 0;
	public static final int RESULT_STATUS_FAIL_LOGIN = 1;
	public static final int RESULT_STATUS_FAIL = 1;
	public static final int RESULT_STATUS_INVALID_TOKEN = 2;
	public static final int RESULT_STATUS_NOTFOUND = 3;
	public static final int RESULT_STATUS_BAN = -1;//没有访问权限
	

	
	
	
	
	public static final String MSG_NO_MORE = "已全部加载";

	public static final int MSG_WHAT_SHOWTOAST=0;


	public static final int REQUEST_CODE_OPENPICS=11;
	public static final int REQUEST_CODE_CAMERA=12;
	public static final int REQUEST_CODE_ZOOM=13;



	

	


	
	

}
