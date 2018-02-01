package com.example.seekit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class password extends Activity{
	SQLiteDatabase db;
	String na,pa;
	Button b1;
	EditText ed1;
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.password);
	    b1=(Button)findViewById(R.id.button1);
	    ed1=(EditText)findViewById(R.id.editText1);
	    db=openOrCreateDatabase("pass", Context.MODE_PRIVATE, null);
	    b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Cursor c=db.rawQuery("SELECT * FROM pass", null);
	    		if(c.moveToFirst())
	    		{
	    			na=c.getString(0);
	    			
	    		}
	    		
	    		if(ed1.getText().toString().equals(na))
	    		{
				Intent i=new Intent(password.this,Options.class);
				startActivity(i);
	    		}
	    		else
	    		{
	    			Toast.makeText(password.this,"please enter correct password",Toast.LENGTH_LONG).show();
	    		}
			}
		});
	}
}