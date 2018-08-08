package com.example.contact;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler  extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION=1;
	private static final String DATABASE_NAME="contacts.db";
	private static final String TABALE_NAME="contact";
	private static final String ID="id";
	private static final String NAME="name";
	private static final String MOB="mob";
	private static final String EMAIL="email";
	private HashMap hm;
	
	public DBHandler(Context context) 
	{
		super(context, DATABASE_NAME,null,DATABASE_VERSION);		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table contacts "+"(id integer primary key, name text, mob text, email text)");
		Log.i("DATABASE","Table created successfully");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer ) {
		db.execSQL("drop table if exists contacts");
		Log.i("DATABASE","Old Table is deleted successfully");
		onCreate(db);
	}
	
	public boolean insertContact(String name,String mob, String email)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues content = new ContentValues();
		content.put("name", name);
		content.put("mob", mob);
		content.put("email", email);
		db.insert("contacts", null,content);
		Log.i("DATABASE","Record inserted successfully");
		return true;
	}
	
	public Cursor getData(int id)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor row = db.rawQuery("select * from contacts where id="+id+"", null);
		Log.i("DATABASE","Record selected successfully");
		return row;
	}
	
	public boolean updateContact (Integer id, String name, String mob, String email) 
	{ 
		SQLiteDatabase db = this.getWritableDatabase(); 
		ContentValues content = new ContentValues(); 
		content.put("name", name); 
		content.put("mob",mob); 
		content.put("email", email);
		db.update("contacts", content, "id = ? ", new String[] { Integer.toString(id) } ); 
		Log.i("DATABASE","Record updated successuflly");
		return true; 
	} 
	
	public Integer deleteContact (Integer id) 
	{ 
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("contacts", "id = ? ", new String[] { Integer.toString(id)});
	} 
	
	public ArrayList getAllCotacts() 
	{ 
		ArrayList list = new ArrayList(); 
		hm = new HashMap(); 
		SQLiteDatabase db = this.getReadableDatabase(); 
		Cursor row = db.rawQuery( "select * from contacts", null ); 
		row.moveToFirst(); 
		while(row.isAfterLast() == false)
		{ 
			list.add(row.getString(row.getColumnIndex(NAME)));
			row.moveToNext();
		}
		
		return list;
	}


}
