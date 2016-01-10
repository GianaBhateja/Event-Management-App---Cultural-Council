package com.example.lnmiitculturalevents;

import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EventSubmission extends Activity{

	// Progress Dialog
    private ProgressDialog pDialog;
 
    JSONParser jsonParser = new JSONParser();
	EditText eventName,date, startingTime, endingTime, venue, club, coordinator, inventory, budget, subEvents, description;
	Button submit;
	//DBHelper data=new DBHelper(null);
	  // url to create new product
    private static String url_create_product = "http://172.22.102.92/android_connect/create_product.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//SQLiteDatabase db;
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.event_submission);
	   
	    
	// Initializing controls

	    eventName=(EditText)findViewById(R.id.etEventName);
	    date=(EditText)findViewById(R.id.etDate);
	    startingTime=(EditText)findViewById(R.id.etStartTime);
	    venue=(EditText)findViewById(R.id.etVenue);
	    club=(EditText)findViewById(R.id.etClub);
	    endingTime=(EditText)findViewById(R.id.etEndTime);
	    coordinator=(EditText)findViewById(R.id.etCoordinator);
	    inventory=(EditText)findViewById(R.id.etInventory);
	    budget=(EditText)findViewById(R.id.etBudget);
	    subEvents=(EditText)findViewById(R.id.etSubEvents);
	    description=(EditText)findViewById(R.id.etDescription);
	    //startingTime=(EditText)findViewById(R.id.editMarks);
	    submit=(Button)findViewById(R.id.bGSecApprove);
	    
	// Registering event handlers

	    submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//data.insertEvent(name, Date, Venue, Starting_time, Ending_time, Club, Coordinator, sub_events, Description, Inventory, Budget, "A");
				// creating new product in background thread
                new CreateNewProduct().execute();
				//Intent openMainActivity1 = new Intent(EventSubmission.this,Upcoming1.class);//login class change kra hau
				//startActivity(openMainActivity1);
			}
		});
	   
	}
	/**
     * Background Async Task to Create new product
     * */
    class CreateNewProduct extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(EventSubmission.this);
            pDialog.setMessage("Submitting Event..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
 
        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            
            String name = eventName.getText().toString();
			String Date = date.getText().toString();
			String Venue = venue.getText().toString();
			String Starting_time = startingTime.getText().toString();
			String Ending_time = endingTime.getText().toString();
			String Club = club.getText().toString();
			String Coordinator = coordinator.getText().toString();
			String Inventory = inventory.getText().toString();
			String Budget = budget.getText().toString();
			String sub_events = subEvents.getText().toString();
			String Description = description.getText().toString();
			
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("Date", Date));
            params.add(new BasicNameValuePair("Description", Description));
            params.add(new BasicNameValuePair("Venue", Venue));
            params.add(new BasicNameValuePair("Starting_time", Starting_time));
            params.add(new BasicNameValuePair("Ending_time", Ending_time));
            params.add(new BasicNameValuePair("Club", Club));
            params.add(new BasicNameValuePair("Coordinator", Coordinator));
            params.add(new BasicNameValuePair("Inventory", Inventory));
            params.add(new BasicNameValuePair("Budget", Budget));
            params.add(new BasicNameValuePair("sub_events", sub_events));
            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                    "POST", params);
 
            // check log cat fro response
            Log.d("Create Response", json.toString());
 
            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // successfully created product
                    Intent i = new Intent(getApplicationContext(), CCAllEvents.class);
                    startActivity(i);
 
                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return json.toString();
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }
 
    }
}


