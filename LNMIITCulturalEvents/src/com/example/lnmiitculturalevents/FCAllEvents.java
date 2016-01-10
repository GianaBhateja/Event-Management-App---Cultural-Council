package com.example.lnmiitculturalevents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
 
public class FCAllEvents extends ListActivity {
 
    // Progress Dialog
    private ProgressDialog pDialog;
    Button button1, approve;
    ImageView icon;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
 
    ArrayList<HashMap<String, String>> productsList;
 
    // url to get all products list
    private static String url_all_products = "http://172.22.102.92/android_connect/get_all_products.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";
 
    // products JSONArray
    JSONArray products = null;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fc_upcoming);
        icon = (ImageView) findViewById(R.id.ivNavigation);
        icon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Creating the instance of PopupMenu  
		          PopupMenu popup = new PopupMenu(FCAllEvents.this, icon);  
		          //Inflating the Popup using xml file  
		          popup.getMenuInflater().inflate(R.menu.fc_navigation, popup.getMenu());  
		         
		          //registering popup with OnMenuItemClickListener  
		          popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
		           public boolean onMenuItemClick(MenuItem item) {  
		            Toast.makeText(FCAllEvents.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();  
		            if(item.getTitle().equals("Home"))
		            {
		            	Intent openMainActivity = new Intent(FCAllEvents.this,FCAllEvents.class);
						startActivity(openMainActivity);
		            }
		            else if(item.getTitle().equals("Approve Events"))
		            {
		            	Intent openMainActivity = new Intent(FCAllEvents.this,FCApproveEvents.class);
						startActivity(openMainActivity);
		            }
		            else if(item.getTitle().equals("Logout"))
		            {
		            	Intent openMainActivity = new Intent(FCAllEvents.this,AllEvents.class);
						startActivity(openMainActivity);
		            }
		            return true;  
		           }  
		          });  

		          popup.show();//showing popup menu  
		         } 
		});
        approve = (Button) findViewById(R.id.bapprove);
        approve.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openMainActivity1 = new Intent(FCAllEvents.this,FCApproveEvents.class);//login class change kra hau
				startActivity(openMainActivity1);
			}
        	
        });
        button1 = (Button) findViewById(R.id.blogout);
        button1.setOnClickListener(new OnClickListener() {  
         
         @Override  
         public void onClick(View v) {  
        	 Intent openMainActivity = new Intent(FCAllEvents.this,AllEvents.class);//login class change kra hau
				startActivity(openMainActivity);
         } 
         });//closing the setOnClickListener method  
     // To handle the click on List View Item
        // Hashmap for ListView
        productsList = new ArrayList<HashMap<String, String>>();
 
        // Loading products in Background Thread
        new LoadAllProducts().execute();
 
        // Get listview
        ListView lv = getListView();
 
        // on seleting single product
        // launching Edit Product Screen
        lv.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // getting values from selected ListItem
                String pid = ((TextView) view.findViewById(R.id.pid)).getText()
                        .toString();
 
                // Starting new intent
                Intent in = new Intent(getApplicationContext(),
                        FCApproval.class);
                // sending pid to next activity
                in.putExtra(TAG_PID, pid);
 
                // starting new activity and expecting some response back
                startActivityForResult(in, 100);
            }
        });
 
    }
 
    // Response from Edit Product Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted product
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
 
    }
 
    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllProducts extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(FCAllEvents.this);
            pDialog.setMessage("Loading events. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);
 
            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());
 
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray(TAG_PRODUCTS);
 
                    // looping through All Products
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);
 
                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String name = c.getString(TAG_NAME);
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_NAME, name);
 
                        // adding HashList to ArrayList
                        productsList.add(map);
                    }
                } else {
                    // no products found
                    // Launch Add New product Activity
                    Intent i = new Intent(getApplicationContext(),
                            EventSubmission.class);
                    // Closing all previous activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
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
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            FCAllEvents.this, productsList,
                            R.layout.activity_listview, new String[] { TAG_PID,
                                    TAG_NAME},
                            new int[] { R.id.pid, R.id.name });
                    // updating listview
                    setListAdapter(adapter);
                }
            });
 
        }
 
    }
}