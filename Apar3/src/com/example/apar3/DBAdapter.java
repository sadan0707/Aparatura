package com.example.apar3;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

	private static final String KLUCZ_GLOWNY = "_id";
	private static final String KLUCZ_NAZWA = "nazwa";
	private static final String KLUCZ_MODEL = "model";
	private static final String TAG = "DBAdapter";
	
	private static final String NAZWA_BAZY = "Baza sprzetow";
	private static final String BAZA_TABELA = "Tabela sprzetow";
	private static final int WERSJA_BAZY = 1;
	
	private static final String STWORZ_BAZE =
			"create table Tabela sprzetow(_id integer primary key autoincrement"
					+"nazwa text not null"
					+"model text not null);";
	
	final Context context;
	
	SQLiteDatabase db;
	DatabaseHelper DBHelper;
	

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
		
	}
	
	public class DatabaseHelper extends SQLiteOpenHelper{
		DatabaseHelper(Context context) 
		{
			super(context, NAZWA_BAZY, null, WERSJA_BAZY);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try{
				db.execSQL(STWORZ_BAZE);
			} catch(SQLException e){
				e.printStackTrace();
			}
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG,"Upgrading database from version"+oldVersion+"to"
					+ newVersion+",witch will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS Tabela sprzetow");
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
	
	public long wstawSprzet(String nazwa, String model) {
		ContentValues initialValueas = new ContentValues();
		initialValueas.put(KLUCZ_NAZWA, nazwa);
		initialValueas.put(KLUCZ_MODEL, model);
		return db.insert(BAZA_TABELA, null, initialValueas);
	}
	
	public boolean wyrzucSprzet(long rowId) {
		return db.delete(BAZA_TABELA, KLUCZ_GLOWNY+"=" + rowId , null)>0;	
			}
	
	public Cursor wezWszystkieSprzety () {
		return db.query(BAZA_TABELA, new String[] {KLUCZ_GLOWNY, 
				KLUCZ_NAZWA, KLUCZ_MODEL}, null, null, null, null, null);
			}
	
	public Cursor wezJedenSprzet(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, BAZA_TABELA, new String[] {KLUCZ_GLOWNY, 
				KLUCZ_NAZWA, KLUCZ_MODEL}, KLUCZ_GLOWNY+"="+rowId, null, null, null, null, null);
		if(mCursor !=null){
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	public boolean aktualizujSprzet(long rowId, String nazwa, String model) {
		ContentValues args = new ContentValues();
		args.put(KLUCZ_NAZWA, nazwa);
		args.put(KLUCZ_MODEL, model);
		
		return db.update(BAZA_TABELA, args, KLUCZ_GLOWNY +"="+rowId, null)>0;
		
	}


}
