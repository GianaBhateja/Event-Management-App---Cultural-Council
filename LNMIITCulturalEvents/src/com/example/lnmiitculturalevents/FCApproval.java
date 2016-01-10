package com.example.lnmiitculturalevents;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FCApproval extends Activity {
    TextView eventName,e_date,venue,startingTime, endingTime, club, coordinator, subEvents, inventory, budget;
    EditText e_status;
    Button btnSave;
    Button btnDelete;
    String pid;
    String status="B";
    
 
    // Progress Dialog
    private ProgressDialog pDialog;
 
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
 
    // single product url
    private static final String url_product_detials = "http://172.22.102.92/android_connect/get_product_details.php";
    // url to update product
    private static final String url_update_product = "http://172.22.102.92/android_connect/update_product.php";
 
    // url to delete product
    private static final String url_delete_product = "http://172.22.102.92/android_connect/delete_product.php";

   
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCT = "product";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";
    private static final String TAG_DATE = "Date";
    private static final String TAG_VENUE = "Venue";
    private static final String TAG_STARTING_TIME = "Starting_time";
    private static final String TAG_ENDING_TIME = "Ending_time";
    private static final String TAG_CLUB = "Club";
    private static final String TAG_COORDINATOR = "Coordinator";
    private static final String TAG_SUB_EVENT = "sub_events";
    private static final String TAG_INVENTORY = "Inventory";
    private static final String TAG_BUDGET = "Budget";
    private static final String TAG_STATUS = "status";
   // private static final String TAG_DESCRIPTION = "Description";
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fc_approve_event);
        btnSave = (Button) findViewById(R.id.bSave);
        btnDelete = (Button) findViewById(R.id.bDelete);
 
       
        // getting product details from intent
        Intent i = getIntent();
 
        // getting product id (pid) from intent
        pid = i.getStringExtra(TAG_PID);
 
        // Getting complete product details in background thread
        new GetProductDetails().execute();
     // save button click event
        btnSave.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                // starting background task to update product
            	new SaveProductDetails().execute();
            }
        });
 
        // Delete button click event
        btnDelete.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                // deleting product in background thread
                new DeleteProduct().execute();
            }
        });
        
 
    }
        /**
         * Background Async Task to Get complete product details
         * */
        class GetProductDetails extends AsyncTask<String, String, String> {
     
            /**
             * Before starting background thread Show Progress Dialog
             * */
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(FCApproval.this);
                pDialog.setMessage("Loading event details. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            }
     
            /**
             * Getting product details in background thread
             * */
            protected String doInBackground(String... args) {
            	List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("pid", pid));

                // getting product details by making HTTP request
                // Note that product details url will use GET request
                final JSONObject json = jsonParser.makeHttpRequest(
                        url_product_detials, "GET", params);
                // updating UI from Background Thread
                runOnUiThread(new Runnable() {
                    public void run() {
                        // Check for success tag
                        int success;
                        try {
                            // Building Parameters
                            
     
                            // check your log for json response
                            Log.d("Single Product Details", json.toString());
     
                            // json success tag
                            success = json.getInt(TAG_SUCCESS);
                            if (success == 1) {
                                // successfully received product details
                                JSONArray productObj = json
                                        .getJSONArray(TAG_PRODUCT); // JSON Array
     
                                // get first product object from JSON Array
                                JSONObject product = productObj.getJSONObject(0);
     
                                // product with this pid found
                                eventName=(TextView)findViewById(R.id.tveventName);
                        	    e_date=(TextView)findViewById(R.id.tvDate);
                        	    startingTime=(TextView)findViewById(R.id.tvStartTime);
                        	    venue=(TextView)findViewById(R.id.tvVenue);
                        	    club=(TextView)findViewById(R.id.setClub);
                        	    endingTime=(TextView)findViewById(R.id.tvEndTime);
                        	    coordinator=(TextView)findViewById(R.id.setCoordinator);
                        	    inventory=(TextView)findViewById(R.id.setInventory);
                        	    budget=(TextView)findViewById(R.id.setBudget);
                        	    subEvents=(TextView)findViewById(R.id.setSubEvents);
                        	  //  description=(TextView)findViewById(R.id.setDescription);
                        	    //e_status=(EditText)findViewById(R.id.etstatus);
                        	   
                        	    System.out.println(product.getString(TAG_NAME));
     
                                // display product data in TextView
                                eventName.setText(product.getString(TAG_NAME));
                                e_date.setText(product.getString(TAG_DATE));
                                venue.setText(product.getString(TAG_VENUE));
                                startingTime.setText(product.getString(TAG_STARTING_TIME));
                                endingTime.setText(product.getString(TAG_ENDING_TIME));
                                club.setText(product.getString(TAG_CLUB));
                                coordinator.setText(product.getString(TAG_COORDINATOR));
                                subEvents.setText(product.getString(TAG_SUB_EVENT));
                             //   description.setText(product.getString(TAG_DESCRIPTION));
                                inventory.setText(product.getString(TAG_INVENTORY));
                                budget.setText(product.getString(TAG_BUDGET));
                                e_status.setText(product.getString(TAG_STATUS));
                                
     
                            }else{
                                // product with pid not found
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
     
                return json.toString();
            }
     
            /**
             * After completing background task Dismiss the progress dialog
             * **/
            protected void onPostExecute(String file_url) {
                // dismiss the dialog once got all details
                pDialog.dismiss();
            }
        }
        class SaveProductDetails extends AsyncTask<String, String, String> {
        	 
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(FCApproval.this);
                pDialog.setMessage("Approving Event ...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            }
     
           
            protected String doInBackground(String... args) {
     
                // getting updated data from EditTexts
                
                
                String name = eventName.getText().toString();
    			/*String Date = e_date.getText().toString();
    			String Venue = venue.getText().toString();
    			String Starting_time = startingTime.getText().toString();
    			String Ending_time = endingTime.getText().toString();
    			String Club = club.getText().toString();
    			String Coordinator = coordinator.getText().toString();
    			String Inventory = inventory.getText().toString();
    			String Budget = budget.getText().toString();
    			String sub_events = subEvents.getText().toString();
    		//	String Description = description.getText().toString();*/
    			//String status = e_status.getText().toString();
     
                // Building Parameters
    			List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair(TAG_PID, pid));
                params.add(new BasicNameValuePair(TAG_STATUS, status));
     
                // sending modified data through http request
                // Notice that update product url accepts POST method
                JSONObject json = jsonParser.makeHttpRequest(url_update_product,
                        "POST", params);
     
                // check json success tag
                try {
                    int success = json.getInt(TAG_SUCCESS);
     
                    if (success == 1) {
                        // successfully updated
                        Intent i = getIntent();
                        // send result code 100 to notify about product update
                        setResult(100, i);
                        finish();
                    } else {
                        // failed to update product
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
     
                return null;
            }
     
            
            protected void onPostExecute(String file_url) {
                // dismiss the dialog once product updated
                pDialog.dismiss();
            }
        }
            
            class DeleteProduct extends AsyncTask<String, String, String> {
         
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    pDialog = new ProgressDialog(FCApproval.this);
                    pDialog.setMessage("Rejecting Event...");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(true);
                    pDialog.show();
                }
         
                
                protected String doInBackground(String... args) {
         
                    // Check for success tag
                    int success;
                    try {
                        // Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("pid", pid));
         
                        // getting product details by making HTTP request
                        JSONObject json = jsonParser.makeHttpRequest(
                                url_delete_product, "POST", params);
         
                        // check your log for json response
                        Log.d("Reject Event", json.toString());
         
                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {
                            // product successfully deleted
                            // notify previous activity by sending code 100
                            Intent i = getIntent();
                            // send result code 100 to notify about product deletion
                            setResult(100, i);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
         
                    return null;
                }
         
                protected void onPostExecute(String file_url) {
                    // dismiss the dialog once product deleted
                    pDialog.dismiss();
         
                }
         
            }
        
        }
     

