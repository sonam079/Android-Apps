package android.pkg.paperlesshospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Thread timer = new Thread() {
			@Override
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent myIntent = new Intent(
							"android.pkg.paperlesshospital.PAPERLESSHOSPITALACTIVITY");
					startActivity(myIntent);
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
