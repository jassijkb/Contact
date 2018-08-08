package com.example.contact;

import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Message extends ActionBarActivity {
	 EditText txtMessage,num;
	 Button send;
	 String phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		num=(EditText)findViewById(R.id.editText2);
		txtMessage=(EditText)findViewById(R.id.editText1);
		Intent i1=getIntent();
		phone=i1.getStringExtra("phone");
		num.setText(phone);
		}
	public void send(View v){
		 sendSMSMessage();
	}
	protected void sendSMSMessage() {
	 
	     
	      String message = txtMessage.getText().toString();
	      
	      try {
	         SmsManager smsManager = SmsManager.getDefault();
	         smsManager.sendTextMessage(phone, null, message, null, null);
	         Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
	      } 
	      
	      catch (Exception e) {
	         Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
	         e.printStackTrace();
	      }
	   }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
