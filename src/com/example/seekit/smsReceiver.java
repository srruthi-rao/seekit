package com.example.seekit;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.gsm.SmsManager;

public class smsReceiver extends BroadcastReceiver{
	
	 final SmsManager sms = SmsManager.getDefault();
	    @Override
	    public void onReceive(Context context, Intent intent) {
	    	 if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
	        {   
	    		 intent=new Intent(context,load_db.class);
	    		 context.startService(intent);
	    		
	    }
}
}
	  