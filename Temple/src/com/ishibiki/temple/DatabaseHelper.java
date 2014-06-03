package com.ishibiki.temple;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	/**
	 * データベースファイル名の定数フィールド
	 */
	private static final String DATABASE_NAME = "temple.db";
	/**
	 * バージョン情報の定数フィールド
	 */
	private static final int DATABASE_VERSION =1;

	/**
	 * コンストラクタ
	 * @param context コンテキスト
	 */
	public DatabaseHelper(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		db.beginTransaction();

		StringBuffer sb = new StringBuffer();
		sb.append("CREATE TABLE memo (");
		sb.append("_id INTEGER,");
		sb.append("name TEXT,");
		sb.append("honson TEXT,");
		sb.append("syusi TEXT, ");
		sb.append("syozai TEXT,");
		sb.append("url TEXT,");
		sb.append("content TEXT, ");
		sb.append("PRIMARY KEY (_id)");
		sb.append(");");
		String sql = sb.toString();

		db.execSQL(sql);

		db.setTransactionSuccessful();
		db.endTransaction();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
	}
}