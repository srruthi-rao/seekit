package com.example.seekit;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Options extends Activity {
	Button b2;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        b2=(Button)findViewById(R.id.button1);
             b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent i=new Intent(Options.this,passcode.class);
			startActivity(i);
			}
		});
        
	
	}
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
     
            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }


     
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
     
            int id = item.getItemId();
            switch (id) {
                case R.id.action_speech:
                    Intent dialer= new Intent(Options.this,Tabopt.class);
                    startActivity(dialer);
                	
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
		
        }
		
		
}
	

        

