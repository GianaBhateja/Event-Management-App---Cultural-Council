package com.example.lnmiitculturalevents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle a) {
		// TODO Auto-generated method stub
		super.onCreate(a);
		setContentView(R.layout.splash);
		Thread timer = new Thread(){
			public void run()
			{
				try{
					sleep(3000);
				}catch(InterruptedException e){
					
				}finally{
					Intent openMainActivity = new Intent(Splash.this,AllEvents.class);//login class change kra hau
					startActivity(openMainActivity);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
