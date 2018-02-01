package com.example.seekit;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class passcode extends Activity {
	public static final int PICK_CONTACT = 1;
	static final int PICK_CONTACT_REQUEST = 1; 
	String s;
	EditText ed1,ed2;
	Button send,b1;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passcode);
        ed1=(EditText)findViewById(R.id.editText1);
        ed2=(EditText)findViewById(R.id.editText2);
        send=(Button)findViewById(R.id.button1);
        b1=(Button)findViewById(R.id.button2);
      
        Intent x=getIntent();
       s= x.getStringExtra("zz");
       ed1.setText(s);
    
		 b1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					pickContact();
			}

				private void pickContact() {
					// TODO Auto-generated method stub
					Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
				    pickContactIntent.setType(Phone.CONTENT_TYPE); 
				    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
				}
		});
		 
       send.setOnClickListener(new View.OnClickListener() 
       {
           public void onClick(View v) 
           {                
               String phoneNo = ed1.getText().toString();
               String message = ed2.getText().toString();                 
               if (phoneNo.length()>0 && message.length()>0)                
                   sendSMS(phoneNo, message);                
               else
                   Toast.makeText(getBaseContext(), 
                       "Please enter both phone number and message.", 
                       Toast.LENGTH_SHORT).show();
           }
       });        
   }    
   private void sendSMS(String phoneNumber, String message)
   {        
       String SENT = "SMS_SENT";
       String DELIVERED = "SMS_DELIVERED";
       
       PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
           new Intent(SENT), 0);
       
       PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
           new Intent(DELIVERED), 0);
       
       //---when the SMS has been sent---
       registerReceiver(new BroadcastReceiver(){
           @Override
           public void onReceive(Context arg0, Intent arg1) {
               switch (getResultCode())
               {
                   case Activity.RESULT_OK:
                       Toast.makeText(getBaseContext(), "SMS sent", 
                               Toast.LENGTH_SHORT).show();
                       break;
                   case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                       Toast.makeText(getBaseContext(), "Generic failure", 
                               Toast.LENGTH_SHORT).show();
                       break;
                   case SmsManager.RESULT_ERROR_NO_SERVICE:
                       Toast.makeText(getBaseContext(), "No service", 
                               Toast.LENGTH_SHORT).show();
                       break;
                   case SmsManager.RESULT_ERROR_NULL_PDU:
                       Toast.makeText(getBaseContext(), "Null PDU", 
                               Toast.LENGTH_SHORT).show();
                       break;
                   case SmsManager.RESULT_ERROR_RADIO_OFF:
                       Toast.makeText(getBaseContext(), "Radio off", 
                               Toast.LENGTH_SHORT).show();
                       break;
               }
           }
       }, new IntentFilter(SENT));
       
       //---when the SMS has been delivered---
       registerReceiver(new BroadcastReceiver(){
           @Override
           public void onReceive(Context arg0, Intent arg1) {
               switch (getResultCode())
               {
                   case Activity.RESULT_OK:
                       Toast.makeText(getBaseContext(), "SMS delivered", 
                               Toast.LENGTH_SHORT).show();
                       break;
                   case Activity.RESULT_CANCELED:
                       Toast.makeText(getBaseContext(), "SMS not delivered", 
                               Toast.LENGTH_SHORT).show();
                       break;                        
               }
           }
       }, new IntentFilter(DELIVERED));        
       
       SmsManager sms = SmsManager.getDefault();
       sms.sendTextMessage(phoneNumber,null, message, sentPI, deliveredPI);        
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
	            int col=cursor.getColumnIndex(Phone.DISPLAY_NAME);
	            String number = cursor.getString(column);
	            String s1=String.valueOf(number);
               ed1.setText(s1);
           // String name1=cursor.getString(col);
           //   String s2=String.valueOf(name1);
            //   ed2.setText(s2);
	        }
	    
	    }
	}
  
	
}


