package com.ishibiki.temple;

import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

/**
 * SA41 Androidサンプル　Chapter04 Ex01 データベース
 *
 * データベース上のデータを処理するクラス
 *
 * @author ohs05032
 */
public class DataAccess {
	/**
	 * 主キーーによる内容検索
	 * @param context コンテキスト
	 * @param id 主キー値
	 * @param return 主キーに対応するcontentカラムの値
	 */
	public static HashMap<String, String> findContentByPk(Context context,int id){
		DatabaseHelper helper = new DatabaseHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = null;
		//全部のデータこれにいれて送り返す
		HashMap<String,String> result = new HashMap<String,String>();

		String sql = "SELECT * FROM memo WHERE _id ="+id;

		db.beginTransaction();
		try{
			cursor = db.rawQuery(sql, null);
			db.setTransactionSuccessful();
			if(cursor != null && cursor.moveToFirst()){
				//本尊
				int idexContent = cursor.getColumnIndex("honson");
				result.put("honson",  cursor.getString(idexContent));

				//宗旨
				idexContent = cursor.getColumnIndex("syusi");
				result.put("syusi",  cursor.getString(idexContent));

				//所在地
				idexContent = cursor.getColumnIndex("syozai");
				result.put("syozai",  cursor.getString(idexContent));
				//URL
				idexContent = cursor.getColumnIndex("url");
				result.put("url",  cursor.getString(idexContent));

				//感想
				idexContent = cursor.getColumnIndex("content");
				result.put("content",  cursor.getString(idexContent));
			}
		}	catch(Exception ex){
			Log.e("ERROR",ex.toString());
		}finally{
			db.endTransaction();
			db.close();
		}
		return result;
	}

/**
 * 主キーによるレコード存在チェックメソッド
 * @param contect コンテキスト
 * @param id 主キー値
 * @return 主キーに対応するcontentカラムの値
 */
	public static boolean findRowByPK(Context context,int id){
		DatabaseHelper helper = new DatabaseHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = null;
		boolean result =false;
		String sql = "SELECT  COUNT(*) AS count FROM memo WHERE _id="+id;

		db.beginTransaction();
		try{
			cursor = db.rawQuery(sql, null);
			db.setTransactionSuccessful();
			if(cursor != null && cursor.moveToFirst()){
				int idxCount = cursor.getColumnIndex("count");
				int count = cursor.getInt(idxCount);
				if(count >= 1){
					result = true;
				}
			}
		}catch(Exception ex){
					Log.e("ERROR",ex.toString());
		}finally{
					db.endTransaction();
					db.close();
		}
		return result;
	}

/**
 * メモ情報を更新するメソッド
 * @param context コンテキスト
 * @param id 主キー値
 * @param name 都道府県名
 * @param content メモ内容
 */
	public static void update(Context context,int id, String name,HashMap<String, String> inputdata){

		DatabaseHelper helper = new DatabaseHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();



		String sql = "UPDATE memo SET name = ? , honson = ? ,  syusi = ? , syozai = ? ,  url = ? ,  content =? WHERE  _id = ? ";
		SQLiteStatement stmt = db.compileStatement(sql);
		stmt.bindLong(7,id);
		stmt.bindString(1,name);
		stmt.bindString(2,inputdata.get("honson"));
		stmt.bindString(3,inputdata.get("syusi"));
		stmt.bindString(4,inputdata.get("syozai"));
		stmt.bindString(5,inputdata.get("url"));
		stmt.bindString(6,inputdata.get("content"));

		db.beginTransaction();
		try{
			stmt.executeInsert();
			db.setTransactionSuccessful();
		}catch(Exception ex){
			Log.e("ERROR",ex.toString());
		}finally{
				db.endTransaction();
				db.close();
		}
 	}

	/**
	 * メモ情報を新規登録するメソッド
	 * @param context コンテキスト
	 * @param id 主キー値
	 * @param name 都道府県名
	 * @param content メモ内容
	 */
 public static void insert(Context context,int id, String name,HashMap<String, String> inputdata){
		DatabaseHelper helper = new DatabaseHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();

		String sql = "INSERT INTO memo (_id,name,honson,syusi,syozai,url,content) VALUES (?,?,?,?,?,?,?);";
		SQLiteStatement stmt = db.compileStatement(sql);
		stmt.bindLong(1,id);
		stmt.bindString(2,name);
		stmt.bindString(3,inputdata.get("honson"));
		stmt.bindString(4,inputdata.get("syusi"));
		stmt.bindString(5,inputdata.get("syozai"));
		stmt.bindString(6,inputdata.get("url"));
		stmt.bindString(7,inputdata.get("content"));

		db.beginTransaction();
		try{
			stmt.executeInsert();
			db.setTransactionSuccessful();
		}catch(Exception ex){
			Log.e("ERROR",ex.toString());
		}finally{
				db.endTransaction();
				db.close();
		}
 	}
  }