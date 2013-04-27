package com.qlteam.watermanager;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.qlteam.watermanager.R;

public class SplashActivity extends Activity {
	Boolean isInternetPresent = false;
	

	// MediaPlayer player;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					
						Intent in = new Intent(getApplicationContext(),
								MainSreenActivity.class);
						startActivity(in);
					}
				}
			

		};
		timer.start();
	}

	@Override
	protected void onPause() {

		
		super.onPause();
		finish();
	}

}
