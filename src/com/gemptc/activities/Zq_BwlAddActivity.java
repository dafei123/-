package com.gemptc.activities;

import java.util.Calendar;

import com.wo.applation.SysApplication;

import beanutil.MyOpenHelper;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Zq_BwlAddActivity extends Activity {

	private EditText edbt, edtime, edcontent;
	private ImageView ivbwl;
	private Button btnedtime;
	private SQLiteDatabase db;
	private String dbName = "scholar.db";
	private MyOpenHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zq_bwl_add);
		SysApplication.getInstance().addActivity(this);

		edbt = (EditText) findViewById(R.id.edbt);
		edtime = (EditText) findViewById(R.id.edtime);
		// 选择时间的控件按钮
		btnedtime = (Button) findViewById(R.id.btnedtime);
		btnedtime.setOnClickListener(new OnClickListener() {
			Calendar c = Calendar.getInstance();

			@Override
			public void onClick(View v) {

				new DatePickerDialog(Zq_BwlAddActivity.this,
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								edtime.setText(year + "-" + (monthOfYear + 1)
										+ "-" + dayOfMonth);
							}
						}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
								.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		edcontent = (EditText) findViewById(R.id.edcontent);

		ivbwl = (ImageView) findViewById(R.id.ivbwl);

		ivbwl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Zq_BwlAddActivity.this.finish();
			}
		});

		helper = new MyOpenHelper(this, dbName, null, 1);
		db = helper.getReadableDatabase();
	}

	public void mySave(View view) {
		String title = edbt.getText().toString();
		String time = edtime.getText().toString();
		String content = edcontent.getText().toString();
		insert(db, title, time, content);
		Toast.makeText(Zq_BwlAddActivity.this, "插入成功", Toast.LENGTH_SHORT)
				.show();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent intent = new Intent(Zq_BwlAddActivity.this, Zq_BwlActivity.class);
		startActivity(intent);
		finish();
	}

	private void insert(SQLiteDatabase db, String title, String time,
			String content) {
		// 使用特定的方法插入数据-----insert("表名","",字段名和值的映射)
		ContentValues values = new ContentValues();
		values.put("title", title);
		values.put("time", time);
		values.put("content", content);
		// 返回值是新插入数据的行ID号
		long id = db.insert("memo", null, values);
	}

}
