package com.example.lnmiitculturalevents;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	   public static final String DATABASE_NAME = "LNMIITEvents.db";
	   public static final String EVENTS_TABLE_NAME = "events";
	// events Table Columns names
		private static final String ID = "id";
		private static final String EVENT_NAME = "name";
		private static final String DATE = "date";
		private static final String VENUE = "venue";
		private static final String STARTING_TIME = "starting_time";
		private static final String ENDING_TIME = "ending_time";
		private static final String CLUB_ASSOCIATED = "club";
		private static final String EVENT_COORDINATOR = "coordinator";
		private static final String SUB_EVENT = "sub_event";
		private static final String DESCRIPTION = "description";
		private static final String INVENTORY = "inventory";
		private static final String BUDGET = "budget";
		private static final String STATUS = "status";

	  // private HashMap hp;

	   public DBHelper(Context context)
	   {
	      super(context, DATABASE_NAME , null, 1);
	   }

	   @Override
	   public void onCreate(SQLiteDatabase db) {
	      // TODO Auto-generated method stub
	      db.execSQL(
	      "create table events " +
	      "(id integer primary key, name text,date text,venue text, starting_time text,ending_time text, club text,coordinator text,sub_event text, description text,inventory text,budget text,status text)"
	      );
	   }

	   @Override
	   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	      // TODO Auto-generated method stub
	      db.execSQL("DROP TABLE IF EXISTS events");
	      onCreate(db);
	   }

	   public boolean insertEvent  (String name, String date, String venue, String starting_time,String ending_time,String club, String coordinator, String sub_event, String description,String inventory,String budget, String status)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();
	      contentValues.put("name", name);
	      contentValues.put("date", date);
	      contentValues.put("venue", venue);	
	      contentValues.put("starting_time", starting_time);
	      contentValues.put("ending_time", ending_time);
	      contentValues.put("club", club);
	      contentValues.put("coordinator", coordinator);
	      contentValues.put("sub_event", sub_event);	
	      contentValues.put("description", description);
	      contentValues.put("inventory", inventory);
	      contentValues.put("budget", budget);
	      contentValues.put("status", status);
	      db.insert("events", null, contentValues);
	      return true;
	   }
	   
	   public Cursor getData(int id){
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from events where id="+id+"", null );
	      return res;
	   }
	   
	   public int numberOfRows(){
	      SQLiteDatabase db = this.getReadableDatabase();
	      int numRows = (int) DatabaseUtils.queryNumEntries(db, EVENTS_TABLE_NAME);
	      return numRows;
	   }
	   
	   public boolean updateEvent (Integer id, String name, String date, String venue, String starting_time,String ending_time,String club, String coordinator, String sub_event, String description,String inventory,String budget, String status)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();
	      contentValues.put("name", name);
	      contentValues.put("date", date);
	      contentValues.put("venue", venue);	
	      contentValues.put("starting_time", starting_time);
	      contentValues.put("ending_time", ending_time);
	      contentValues.put("club", club);
	      contentValues.put("coordinator", coordinator);
	      contentValues.put("sub_event", sub_event);	
	      contentValues.put("description", description);
	      contentValues.put("inventory", inventory);
	      contentValues.put("budget", budget);
	      contentValues.put("status", status);
	      db.update("events", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
	      return true;
	   }

	   public Integer deleteEvent (Integer id)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      return db.delete("events", 
	      "id = ? ", 
	      new String[] { Integer.toString(id) });
	   }
	   
	   public ArrayList<String> getAllEvents()
	   {
	      ArrayList<String> array_list = new ArrayList<String>();
	      
	      //hp = new HashMap();
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from events", null );
	      res.moveToFirst();
	      
	      while(res.isAfterLast() == false){
	         array_list.add(res.getString(res.getColumnIndex(EVENT_NAME)));
	         res.moveToNext();
	      }
	   return array_list;
	   }
}
