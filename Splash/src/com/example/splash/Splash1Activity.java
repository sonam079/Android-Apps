package com.example.splash;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Splash1Activity extends Activity {

	MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash1);
        player=MediaPlayer.create(this, R.drawable.bootsound);
        		player.start();
        		Thread t=new Thread()
	        		{ public void run()
		        		{
		        			try
		        			{
		        				sleep(5000);
		        				Intent i=new Intent(Splash1Activity.this, Splash2Activity.class);
		        				startActivity(i);
		        			}
		        			catch(Exception e)
		        			{}
		        		}
        		   };
        		   t.start();
    }
}
