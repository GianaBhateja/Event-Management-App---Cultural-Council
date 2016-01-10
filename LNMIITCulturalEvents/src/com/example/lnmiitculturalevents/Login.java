package com.example.lnmiitculturalevents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

	Button bLogin;
	EditText etName, etPswrd;
	TextView tvOutput;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
        
		
		
		bLogin=(Button) findViewById(R.id.bLogin);
        etName=(EditText)findViewById(R.id.etUserName);
        etPswrd=(EditText)findViewById(R.id.etPassword);
        
        
        
        bLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

			
				String name = etName.getText().toString();
				String password = etPswrd.getText().toString();

				
				if (name.equals("") && password.equals("")) {
					Toast.makeText(Login.this, "Pleae Enter Credentials",Toast.LENGTH_SHORT).show(); 
					
							
				}
				else if(name.equals("Diksha") && password.equals("lnmiit"))
				{
					Intent openMainActivity = new Intent(Login.this,CCAllEvents.class);//login class change kra hau
					startActivity(openMainActivity);
				}
				else if(name.equals("Dhiyavasu") && password.equals("lnmiit"))
				{
					Intent openMainActivity = new Intent(Login.this,FCAllEvents.class);//login class change kra hau
					startActivity(openMainActivity);
				}
				else if(name.equals("GSec") && password.equals("lnmiit"))
				{
					Intent openMainActivity = new Intent(Login.this,PinEntryView.class);//login class change kra hau
					startActivity(openMainActivity);
				}
				
			}
		});
	}

}
