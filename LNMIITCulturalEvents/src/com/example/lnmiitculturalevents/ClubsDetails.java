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

public class ClubsDetails extends Activity {
    TextView eventName,e_date,venue,startingTime, endingTime, club, coordinator, subEvents, description, inventory, budget, status;
 
 
    String pid;
 
    // Progress Dialog
    private ProgressDialog pDialog;
 
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
 
    // single product url
    private static final String url_product_detials = "http://172.22.33.49/android_connect/get_clubs_details.php";
 
   
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCT = "product";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";
    private static final String TAG_COORDINATOR = "coordinator";
    private static final String TAG_CONTACT_NO = "contact_no";
    private static final String TAG_MEMBERS = "members";
    private static final String TAG_DESCRIPTION = "description";
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_detail);
 
       
        // getting product details from intent
        Intent i = getIntent();
 
        // getting product id (pid) from intent
        pid = i.getStringExtra(TAG_PID);
 
        // Getting complete product details in background thread
        new GetProductDetails().execute();
 
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
                pDialog = new ProgressDialog(ClubsDetails.this);
                pDialog.setMessage("Loading product details. Please wait...");
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
     
                              /*  // product with this pid found
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
                        	    description=(TextView)findViewById(R.id.setDescription);
                        	    //startingTime=(EditText)findViewById(R.id.editMarks);
                        	    
     
                                // display product data in TextView
                                eventName.setText(product.getString(TAG_NAME));
                                e_date.setText(product.getString(TAG_DATE));
                                venue.setText(product.getString(TAG_VENUE));
                                startingTime.setText(product.getString(TAG_STARTING_TIME));
                                endingTime.setText(product.getString(TAG_ENDING_TIME));
                                club.setText(product.getString(TAG_CLUB));
                                coordinator.setText(product.getString(TAG_COORDINATOR));
                                subEvents.setText(product.getString(TAG_SUB_EVENT));
                                description.setText(product.getString(TAG_DESCRIPTION));
                                inventory.setText(product.getString(TAG_INVENTORY));
                                budget.setText(product.getString(TAG_BUDGET));
     */                           
     
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
     
}
