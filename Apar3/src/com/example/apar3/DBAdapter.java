package com.example.apar3;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import android.content.Intent;

public class DBAdapter {

	static final String KLUCZG = "_id";
	static final String KLUCZN = "nazwa";
	static final String KLUCZM = "model";
	static final String TAG = "DBAdapter";
	
	static final String DATABASE_NAME = "Baza sprzetow";
	static final String DATABASE_TABLE = "tsprz";
	static final int DATABASE_VERSION = 1;
	
	static final String DATABASE_CREATE =
			"create table tsprz(_id integer primary key autoincrement,"
					+"nazwa text not null, model text not null);";
	
	final Context context;
	
	
	DatabaseHelper DBHelper;
	SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
		
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) 
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try{
				db.execSQL(DATABASE_CREATE);
			} catch(SQLException e){
				e.printStackTrace();
			}
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG,"Upgrading database from version" + oldVersion + "to"
					+ newVersion + ",witch will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS tsprz");
			onCreate(db);
			
		}
		

	}
	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	
	public void close() {

		DBHelper.close();
		}
	
	public long wstawSprzet(String nazwa, String model) 
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KLUCZN, nazwa);
		initialValues.put(KLUCZM, model);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	//public boolean wyrzucSprzet(long rowId) {
		//return db.delete(BAZA_TABELA, KLUCZ_GLOWNY+"=" + rowId , null)>0;	
			//}
	
	public Cursor wezWszystkieSprzety () throws SQLException {
		String[] kolumny = new String[] {KLUCZG, KLUCZN, KLUCZM};
		
		return db.query(DATABASE_TABLE, kolumny, null, null, null, null, null);
			}
	
	
	public Cursor wezJedenSprzet(long rowId) throws SQLException 
	{
		Cursor mCursor = 
				db.query(true, DATABASE_TABLE, new String[] {KLUCZG, 
				KLUCZN, KLUCZM}, KLUCZG+"="+rowId, null, null, null, null, null);
		if(mCursor != null){
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	public boolean aktualizujSprzet(long rowId, String nazwa, String model) {
		ContentValues args = new ContentValues();
		args.put(KLUCZN, nazwa);
		args.put(KLUCZM, model);
		
		return db.update(DATABASE_TABLE, args, KLUCZG +"="+rowId, null)>0;
		
	}
	
	
		
	


}
