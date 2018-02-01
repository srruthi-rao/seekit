package com.example.seekit;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Tabopt extends TabActivity {
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabopt);
        Resources ressources = getResources(); 
		TabHost tabHost = getTabHost(); 

		Intent intentcontact = new Intent().setClass(this,contact.class);
		TabSpec tabSpeccontact = tabHost
		  .newTabSpec("Choose contacts..")
		    .setIndicator("", ressources.getDrawable(R.drawable.unnamed))
		  .setContent(intentcontact);

		Intent intentsecret = new Intent().setClass(this,Setting.class);
		TabSpec tabSpecSecret = tabHost
		  .newTabSpec("Save your secret code..")
		  .setIndicator("", ressources.getDrawable(R.drawable.secret))
		  .setContent(intentsecret);
		
		Intent intentFile = new Intent().setClass(this,File.class);
		TabSpec tabSpecFile = tabHost
		  .newTabSpec("Choose important files..")
		  .setIndicator("", ressources.getDrawable(R.drawable.file))
		  .setContent(intentFile);
	
		tabHost.addTab(tabSpeccontact);
		tabHost.addTab(tabSpecSecret);
		tabHost.addTab(tabSpecFile);
		
tabHost.setCurrentTab(2);
}
}
