package com.example.lnmiitculturalevents;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "eventsManager";

	// events table name
	private static final String EVENTS = "events";

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

	public DatabaseHandler(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
	    String CREATE_EVENTS_TABLE = "CREATE TABLE " + EVENTS + "("
	            + ID + " INTEGER PRIMARY KEY," + EVENT_NAME + " TEXT,"
	            + DATE + " TEXT," + VENUE + " TEXT," + STARTING_TIME + " TEXT,"
	            + ENDING_TIME + " TEXT," + CLUB_ASSOCIATED + " TEXT," + EVENT_COORDINATOR + " TEXT,"
	            + SUB_EVENT + " TEXT" + DESCRIPTION + " TEXT," + INVENTORY + " TEXT,"
	            + BUDGET + " TEXT" + STATUS + " TEXT,"+ ")";
	    db.execSQL(CREATE_EVENTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    // Drop older table if existed
	    db.execSQL("DROP TABLE IF EXISTS " + EVENTS);

	    // Create tables again
	    onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new event
	void addEvent(Event event) {
	    SQLiteDatabase db = this.getWritableDatabase();

	    ContentValues values = new ContentValues();
	    values.put(EVENT_NAME, event.getName()); // Event Name
	    values.put(DATE, event.getDate()); // event date
	    values.put(VENUE, event.getVenue()); // event venue
	    values.put(STARTING_TIME, event.getStarting_time()); // event starting time
	    values.put(ENDING_TIME, event.getEnding_time()); // event ending time
	    values.put(CLUB_ASSOCIATED, event.getClub()); // event club
	    values.put(EVENT_COORDINATOR, event.getCoordinator()); // event coordinator
	    values.put(SUB_EVENT, event.getSub_event()); // event subevents
	    values.put(DESCRIPTION, event.getDescription()); // event desription
	    values.put(INVENTORY, event.getInventory()); // event inventory
	    values.put(BUDGET, event.getBudget()); // event budget
	    values.put(STATUS, event.getStatus()); // event status

	    // Inserting Row
	    db.insert(EVENTS, null, values);
	    db.close(); // Closing database connection
	}

	// Getting single event
	Event getEvent(int id) {
	    SQLiteDatabase db = this.getReadableDatabase();

	    Cursor cursor = db.query(EVENTS, new String[] { ID,EVENT_NAME, DATE, VENUE, STARTING_TIME, ENDING_TIME, CLUB_ASSOCIATED, EVENT_COORDINATOR, SUB_EVENT, DESCRIPTION, INVENTORY, BUDGET, STATUS }, ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
	    if (cursor != null)
	        cursor.moveToFirst();

	    Event event = new Event(Integer.parseInt(cursor.getString(0)),
	            cursor.getString(1), cursor.getString(2), null, null, null, null, null, null, null, null, null, null);
	    // return event
	    return event;
	}

	// Getting All events
	public List<Event> getAllEvents() {
	    List<Event> eventList = new ArrayList<Event>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + EVENTS;

	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);

	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            Event event = new Event();
	            event.setId(Integer.parseInt(cursor.getString(0)));
	            event.setName(cursor.getString(1));
	            event.setDate(cursor.getString(2));
	            event.setVenue(cursor.getString(3));
	            event.setStarting_time(cursor.getString(4));
	            event.setEnding_time(cursor.getString(5));
	            event.setClub(cursor.getString(6));
	            event.setCoordinator(cursor.getString(7));
	            event.setSub_event(cursor.getString(8));
	            event.setDescription(cursor.getString(9));
	            event.setInventory(cursor.getString(10));
	            event.setBudget(cursor.getString(11));
	            event.setStatus(cursor.getString(12));
	            // Adding event to list
	            eventList.add(event);
	        } while (cursor.moveToNext());
	    }

	    // return event list
	    return eventList;
	}
	public List<String> getAllEventsName() {
	    List<String> eventName = new ArrayList<String>();
	    // Select All Query
	    String selectQuery = "SELECT  EVENT_NAME FROM " + EVENTS;

	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);

	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            //Event event = new Event();
	            //event.setId(Integer.parseInt(cursor.getString(0)));
	            //event.setName(cursor.getString(1));
	        	String str=cursor.getString(1);
	                   // Adding event to list
	            eventName.add(str);
	        } while (cursor.moveToNext());
	    }

	    // return event list
	    return eventName;
	}

	// Updating single event
	public int updateEvent(Event event) {
	    SQLiteDatabase db = this.getWritableDatabase();

	    ContentValues values = new ContentValues();
	    values.put(EVENT_NAME, event.getName()); // Event Name
	    values.put(DATE, event.getDate()); // event date
	    values.put(VENUE, event.getVenue()); // event venue
	    values.put(STARTING_TIME, event.getStarting_time()); // event starting time
	    values.put(ENDING_TIME, event.getEnding_time()); // event ending time
	    values.put(CLUB_ASSOCIATED, event.getClub()); // event club
	    values.put(EVENT_COORDINATOR, event.getCoordinator()); // event coordinator
	    values.put(SUB_EVENT, event.getSub_event()); // event subevents
	    values.put(DESCRIPTION, event.getDescription()); // event desription
	    values.put(INVENTORY, event.getInventory()); // event inventory
	    values.put(BUDGET, event.getBudget()); // event budget
	    values.put(STATUS, event.getStatus()); // event status

	    // updating row
	    return db.update(EVENTS, values, ID + " = ?",
	            new String[] { String.valueOf(event.getId()) });
	}

	// Deleting single event
	public void deleteEvent(Event event) {
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(EVENTS, ID + " = ?",
	            new String[] { String.valueOf(event.getId()) });
	    db.close();
	}


	// Getting Events Count
	public int getEventsCount() {
	    String countQuery = "SELECT  * FROM " + EVENTS;
	    SQLiteDatabase db = this.getReadableDatabase();
	    Cursor cursor = db.rawQuery(countQuery, null);
	    cursor.close();

	    // return count
	    return cursor.getCount();
	}

	}
