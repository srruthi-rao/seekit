package com.example.seekit;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class create_account extends Activity{
	SQLiteDatabase db;
	Context context=this;
public static final String PREFS_NAME="MyPrefsFile";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        db=openOrCreateDatabase("pass", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS pass(code VARCHAR, na VARCHAR);");
SharedPreferences settings=getSharedPreferences(PREFS_NAME, 0);
       boolean firstRun=settings.getBoolean("firstRun",true);
       if(firstRun)
       {
    	   Log.w("activity","first time");
    	   setContentView(R.layout.createac);
    	   LayoutInflater li = LayoutInflater.from(context);
			View promptsView = li.inflate(R.layout.set_pass, null);

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);

			// set prompts.xml to alertdialog builder
			alertDialogBuilder.setView(promptsView);

			final EditText userInput = (EditText) promptsView
					.findViewById(R.id.ippass);

			// set dialog message
			alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK",
				  new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog,int id) {
					// get user input and set it to result
					// edit text
					//result.setText(userInput.getText());
					Cursor c=db.rawQuery("SELECT * FROM pass ", null);
					if(c.getCount()==0)
		    		{
						db.execSQL("INSERT INTO pass VALUES('"+userInput.getText()+"','"+0+"');");
						startActivity(new Intent(getBaseContext(),Options.class)); 
						
		    		}
				    }
				  })
				
				.setNegativeButton("Cancel",
				  new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
				    }
				  });

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
    	   SharedPreferences.Editor editor=settings.edit();
    	   editor.putBoolean("firstRun",false);
    	   editor.commit();
       
       }
       else
       {
    	   Log.w("activity","second time");
    	   //setContentView(R.layout.login);
    	   startActivity(new Intent(getBaseContext(),Login.class));
       }
    }

}

