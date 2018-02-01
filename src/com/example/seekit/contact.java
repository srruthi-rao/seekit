package com.example.seekit;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class contact extends Activity {
	SQLiteDatabase db;
	 public static final int PICK_CONTACT = 1;
		static final int PICK_CONTACT_REQUEST = 1; 
	Button b1,b2,b3,b4,b5,b6;
	EditText ed1,ed2,ed3,ed4,ed5;
	String s1;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5);
        b6=(Button)findViewById(R.id.button6);
        ed1=(EditText)findViewById(R.id.editText1);
        ed2=(EditText)findViewById(R.id.editText2);
        ed3=(EditText)findViewById(R.id.editText3);
        ed4=(EditText)findViewById(R.id.editText4);
        ed5=(EditText)findViewById(R.id.editText5);
        db=openOrCreateDatabase("user", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS impcontacts(cntct1 NUMBER,cntct2 NUMBER,cntct3 NUMBER,cntct4 NUMBER,cntct5 NUMBER,na VARCHAR);");
        b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pickContact();
			}
		});
b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pickContact();
			}
		});

b3.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		pickContact();
	}
});

b4.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		pickContact();
	}
});

b5.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		pickContact();
	}
});
b6.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(ed1.getText().toString().length()==0 && ed2.getText().toString().length()==0 
				&& ed3.getText().toString().length()==0
				&& ed4.getText().toString().length()==0
				&& ed5.getText().toString().length()==0)
		{
			Toast.makeText(contact.this,"Please enter 5 contacts..",Toast.LENGTH_SHORT).show();
		}
		else
		{
		Cursor c=db.rawQuery("SELECT * FROM impcontacts ", null);
		if(c.getCount()==0)
		{
			db.execSQL("INSERT INTO impcontacts VALUES('"+ed1.getText()+"','"+ed2.getText()+
    				   "','"+ed3.getText()+"','"+ed4.getText()+"','"+ed5.getText()+"','"+0+"');");
    		showMessage("Success", "Details Stored");
    	
		}
		
		else
		{
		db.execSQL("UPDATE impcontacts SET cntct1='"+ed1.getText()+"',cntct2='"+ed2.getText()+"',cntct3='"+ed3.getText()+
				"',cntct4='"+ed4.getText()+"',cntct5='"+ed5.getText()+"' WHERE na='"+0+"'");
		Toast.makeText(contact.this,"Contacts saved successfully",Toast.LENGTH_SHORT).show();
			
			//Intent j=new Intent(create_account.this,Options.class);
	    	//startActivity(j);
		}

	}
	}
});

        
	}
	private void pickContact() {
	    Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
	    pickContactIntent.setType(Phone.CONTENT_TYPE); 
	    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == PICK_CONTACT_REQUEST) {
	        if (resultCode == RESULT_OK) {
	            Uri contactUri = data.getData();
	            String[] projection = {Phone.NUMBER,Phone.DISPLAY_NAME};
	            Cursor cursor = getContentResolver()
	                    .query(contactUri, projection, null, null, null);
	            cursor.moveToFirst();
	            int column = cursor.getColumnIndex(Phone.NUMBER);
	            String number = cursor.getString(column);
	             s1=String.valueOf(number);
	             set();
	        }
}
	}
	private void set() {
		// TODO Auto-generated method stub
		if(ed1.getText().toString().trim().length() == 0)
		{ 
         	ed1.setText(s1);
		}
		else if(ed2.getText().toString().trim().length() == 0)//&&(ed2.getText().toString().equals(null)))
         {
         	ed2.setText(s1);
         }
         else if (ed3.getText().toString().trim().length() == 0)
         {
         	ed3.setText(s1);
         }
         else if(ed4.getText().toString().trim().length() == 0)
         {
         	ed4.setText(s1);
         }
         else
         {
         	ed5.setText(s1);
         }

	}
	 public void showMessage(String title,String message)
	    {
	    	Builder builder=new Builder(contact.this);
	    	builder.setCancelable(true);
	    	builder.setTitle(title);
	    	builder.setMessage(message);
	    	builder.show();
		}
	 
	}
	

