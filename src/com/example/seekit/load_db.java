package com.example.seekit;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class load_db extends Service {
SQLiteDatabase db;

@Override
public IBinder onBind(Intent arg0) {
	// TODO Auto-generated method stub
	return null;
}
public void onStart(Intent intent, int startId) {
	// TODO Auto-generated method stub
	Toast.makeText(getBaseContext(), "service started",Toast.LENGTH_LONG).show();
}

}
