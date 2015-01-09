package com.smu.funapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	private static final int DB_VERSION = 1;//数据库版本
	
	private static final String DB_NAME = "funapp.db"; //数据库名称
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table bd_customer ( ID TEXT PRIMARY KEY DESC," +
				" NAME TEXT ," +
				" CODE INT ," +
				" PHONE NUM ," +
				" ADDRESS TEXT ," +
				" SEX INT )");
		
		db.execSQL("create table bd_product ( ID TEXT PRIMARY KEY ASC," +
				" NAME TEXT ," +
				" CODE INT ," +
				" PRODUCE_DATE ," +
				" PRICE )");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}

}
