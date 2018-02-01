package com.example.seekit;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class email extends Activity{
	TextView txt1,txt2,txt3,txt4,txt5;
	EditText ed1,ed2,ed3,ed4;
	Button b1;
	RadioGroup rg;
	RadioButton rb;
	String s1,s2,s3,s4;
	protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.email);
	       
	    	ed1=(EditText)findViewById(R.id.username);
	        ed2=(EditText)findViewById(R.id.editText2);
	        ed3=(EditText)findViewById(R.id.editText3);
	        ed4=(EditText)findViewById(R.id.editText4);
	        b1=(Button)findViewById(R.id.button1);
	        s1=ed1.getText().toString();
	        s2=ed2.getText().toString();
	        s3=ed3.getText().toString();
	        Intent x=getIntent();
	        s4= x.getStringExtra("aaa");
	        b1.setOnClickListener(new View.OnClickListener() {
	           public void onClick(View view) {
	              sendEmail();
	           }
	        });
	       
	     }
	     protected void sendEmail() {
	        Log.i("Send email", "");
	        String TO = ed1.getText().toString();//s1.toString();
	        String CC =ed2.getText().toString();
	        String SUB=ed3.getText().toString();
	        String TEXT=s4.toString();
	        //Intent emailIntent = new Intent(Intent.ACTION_SEND);
	        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
	        emailIntent.setData(Uri.parse("mailto:"));
	        emailIntent.setType("text/plain");
	        emailIntent.putExtra(Intent.EXTRA_EMAIL,TO);
	        emailIntent.putExtra(Intent.EXTRA_CC,CC);
	        emailIntent.putExtra(Intent.EXTRA_SUBJECT,SUB);
	        emailIntent.putExtra(Intent.EXTRA_TEXT, TEXT);
	        
	        try {
	           startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	           finish();
	           Log.i("Finished sending email...", "");
	        }
	        catch (android.content.ActivityNotFoundException ex) {
	           Toast.makeText(email.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
	        }
	     }
}


