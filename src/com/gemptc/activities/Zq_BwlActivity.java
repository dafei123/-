package com.gemptc.activities;

import com.wo.applation.SysApplication;

import beanutil.MyOpenHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Zq_BwlActivity extends Activity {

	private ImageView ivbwl, ivAdd;
	private ListView lvlist;
	private Button btnbwladd;

	private SQLiteDatabase db;
	private String dbName = "scholar.db";
	private MyOpenHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zq_bwl);
		SysApplication.getInstance().addActivity(this);

		ivbwl = (ImageView) findViewById(R.id.ivbwl);
		ivAdd = (ImageView) findViewById(R.id.ivAdd);
		lvlist = (ListView) findViewById(R.id.lvlist);
		btnbwladd = (Button) findViewById(R.id.btnbwladd);

		click();
		setOnItemLongClickListener();

		helper = new MyOpenHelper(this, dbName, null, 1);
		db = helper.getReadableDatabase();
		// 在ListView中放入数据库中的数据
		Cursor cursor = db.rawQuery("select * from memo", null);
		refresh(cursor);
	}

	public void click() {
		ivbwl.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Zq_BwlActivity.this.finish();
			}
		});

		ivAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Zq_BwlActivity.this,
						Zq_BwlAddActivity.class);
				startActivity(intent);
				finish();
			}
		});
		btnbwladd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Zq_BwlActivity.this,
						Zq_BwlAddActivity.class);
				startActivity(intent);
				finish();
			}
		});

	}

	// 长按删除信息
	public void setOnItemLongClickListener() {
		lvlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView tv = (TextView) view.findViewById(R.id.tvbid);
				// 获取列表中的某行的id值
				int i = Integer.parseInt(tv.getText().toString());
				db.delete("memo", "_id=?", new String[] { id + "" });
				Toast.makeText(Zq_BwlActivity.this, "长按删除", Toast.LENGTH_SHORT)
						.show();
				// 更新数据
				String sql = "select * from memo";
				// 查询出来的数据
				Cursor cursor = db.rawQuery(sql, null);
				// 更新数据
				refresh(cursor);
				return true;
			}
		});
	}

	// 刷新ListView中的数据
	public void refresh(Cursor cursor) {
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.zq_bwlitem, cursor, new String[] { "_id", "title",
						"content", "time" }, new int[] { R.id.tvbid,
						R.id.tvbtitle, R.id.tvcontent1, R.id.tvbtime },
				SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		lvlist.setAdapter(adapter);
	}

}
