package com.example.seekit;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class Setting extends Activity{
	EditText ed1,ed2,ed3,ed4;
	Button b1,b2,b3,b4,yes,no;
	SQLiteDatabase db;
	private CharSequence message;
	private CharSequence title;
	Context context=this;
	
	String pwd=" ";
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.settings);
	    ed1=(EditText)findViewById(R.id.editText1);
	    ed2=(EditText)findViewById(R.id.editText2);
	    ed3=(EditText)findViewById(R.id.editText3);
	    ed4=(EditText)findViewById(R.id.editText4);
	    b1=(Button)findViewById(R.id.save);
	    b2=(Button)findViewById(R.id.button2);
	    b3=(Button)findViewById(R.id.button3);
	    b4=(Button)findViewById(R.id.button4);
	   db=openOrCreateDatabase("user", Context.MODE_PRIVATE, null);
	    db.execSQL("CREATE TABLE IF NOT EXISTS password(ring VARCHAR,location VARCHAR,erase VARCHAR,retreive VARCHAR,na VARCHAR);");
            b1.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Cursor c=db.rawQuery("SELECT * FROM password ", null);
		if(c.getCount()==0)
		{
			db.execSQL("INSERT INTO password VALUES('"+ed1.getText()+"','"+ed2.getText()+
    				   "','"+ed3.getText()+"','"+ed4.getText()+"','"+0+"');");
    		showMessage("Success", "Details Stored");
    		 
		}
		else
		{
		db.execSQL("UPDATE password SET ring='"+ed1.getText()+"',location='"+ed2.getText()+"',erase='"+ed3.getText()+
					"',retreive='"+ed4.getText()+"' WHERE na='"+0+"'");
		showMessage("Success", "Details Stored");
		}
	}

	
});
	

	b2.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			pwd="ring:"+ed1.getText().toString()+"\n"+"location:"+ed2.getText().toString();
		
			Intent a=new Intent(Setting.this,email.class);
			a.putExtra("aaa",pwd);
			startActivity(a);
		}
	});
	
	}
	private void clearText() {
		// TODO Auto-generated method stub
		ed1.setText("");
    	ed2.setText("");
    	ed3.setText("");
    	ed4.setText("");
    	
    	ed4.requestFocus();
		
	}
	 public void showMessage(String title,String message)
	    {
	    	Builder builder=new Builder(this);
	    	builder.setCancelable(true);
	    	builder.setTitle(title);
	    	builder.setMessage(message);
	    	builder.show();
		}
	


	  
	
	}