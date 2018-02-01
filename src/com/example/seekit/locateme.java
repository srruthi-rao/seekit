package com.example.seekit;
import java.util.List;



import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.telephony.gsm.SmsManager;
import android.widget.TextView;
import android.widget.Toast;

public class locateme extends Activity{
	
	
	String string;

	
	 public class OpenCellID {
		  String mcc;  //Mobile Country Code
		  String mnc;  //mobile network code
		  String cellid; //Cell ID
		  String lac;  //Location Area Code
		   
		  Boolean error;
		  String strURLSent;
		  String GetOpenCellID_fullresult;
		   
		  String latitude;
		  String longitude;
		   
		  public Boolean isError(){
		   return error;
		  }
		   
		  public void setMcc(String value){
		   mcc = value;
		  }
		   
		  public void setMnc(String value){
		   mnc = value;
		  }
		   
		  public void setCallID(int value){
		   cellid = String.valueOf(value);
		  }
		   
		  public void setCallLac(int value){
		   lac = String.valueOf(value);
		  }
		   
		  public String getLocation(){
		   return(latitude + " : " + longitude);
		  }
		   
		  public void groupURLSent(){
		   strURLSent =
		    "http://www.opencellid.org/cell/get?mcc=" + mcc
		    +"&mnc=" + mnc
		    +"&cellid=" + cellid
		    +"&lac=" + lac
		    +"&fmt=txt";
		  }
		   
		  public String getstrURLSent(){
		   return strURLSent;
		  }
		   
		  public String getGetOpenCellID_fullresult(){
		   return GetOpenCellID_fullresult;
		  }
		   
		  public void GetOpenCellID() throws Exception {
		   groupURLSent();
		   HttpClient client = new DefaultHttpClient();
		   HttpGet request = new HttpGet(strURLSent);
		   HttpResponse response = client.execute(request);
		   GetOpenCellID_fullresult = EntityUtils.toString(response.getEntity()); 
		   spliteResult();
		  }
		   
		  private void spliteResult(){
		   if(GetOpenCellID_fullresult.equalsIgnoreCase("err")){
		    error = true;
		   }else{
		    error = false;
		    String[] tResult = GetOpenCellID_fullresult.split(",");
		    latitude = tResult[0];
		    longitude = tResult[1];
		    
		    String str="mcc:"+mcc+"mnc:"+mnc+"cellid:"+cellid+"lac:"+lac;
		    
		    Toast.makeText(getApplicationContext(), str,Toast.LENGTH_LONG).show(); 
		    
		   }
		  }
		  
	 }
	 int myLatitude, myLongitude;
	 OpenCellID openCellID;
	 
	  
	   /** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.passcode);
	      
	      
	      // TextView txt1 = (TextView)findViewById(R.id.textView1);
	     //TextView textGeo=(TextView)findViewById(R.id.textView2);
	       
	       //retrieve a reference to an instance of TelephonyManager
	       TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
	       GsmCellLocation cellLocation = (GsmCellLocation)telephonyManager.getCellLocation();
	       
	       String networkOperator = telephonyManager.getNetworkOperator();
	       String mcc = networkOperator.substring(0, 3);
	       String mnc = networkOperator.substring(3);
	   
	       
	       int cid = cellLocation.getCid();
	       int lac = cellLocation.getLac();
	    
	       String str1=String.valueOf(cid);
	       String str2=String.valueOf(lac);
	       String string="mcc: "+ mcc+"\n"+"mnc:" +mnc+"\n"+"cid:"+str1+"\n"+"lac:"+str2;
	       //txt1.setText(string);
	      // Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG).show();
	       Bundle extras = getIntent().getExtras(); 
	       String num = extras.getString("num");
		   // String msg = extras.getString("msg");
		    sendreturnmsg(num,string);
	       
	  }


	private void sendreturnmsg(String num, String string) {
		// TODO Auto-generated method stub
		String num1=num;
		 String msg1=string;
		 if (num1.length()>0 && msg1.length()>0)                
           sendSMS(num1,msg1);                
       else
           Toast.makeText(getBaseContext(), 
               "Please enter both phone number and message.", 
               Toast.LENGTH_SHORT).show();
		
	}

	private void sendSMS(String num1, String msg1) {
		// TODO Auto-generated method stub
		 PendingIntent pi = PendingIntent.getActivity(this, 0,
		            new Intent(), 0);                
		        SmsManager sms = SmsManager.getDefault();
		        sms.sendTextMessage(num1, null, msg1, pi, null); 
		
	
	} 
		
	}  
		
	


