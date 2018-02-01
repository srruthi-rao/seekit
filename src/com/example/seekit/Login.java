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

public  class Login extends Activity  {
EditText password;
Button lg;
SQLiteDatabase db;
String check_pass;
public void onCreate(Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login);
    lg=(Button)findViewById(R.id.login);
   
   password=(EditText)findViewById(R.id.editText2);
  
  
    db=openOrCreateDatabase("pass", Context.MODE_PRIVATE, null);
    lg.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Cursor c=db.rawQuery("SELECT * FROM pass", null);
    		if(c.moveToFirst())
    		{
    			check_pass=c.getString(0);
    			
    		}
    		
    		if(password.getText().toString().equals(check_pass))
    		{
			Intent i=new Intent(Login.this,Options.class);
			startActivity(i);
    		}
    		else
    		{
    			Toast.makeText(Login.this,"user name passwd incorrect",Toast.LENGTH_LONG).show();
    		}
    		}
		
	});
  
    		
}
}