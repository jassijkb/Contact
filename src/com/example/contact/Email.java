package com.example.contact;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Email extends ActionBarActivity {
	String email;
	EditText msg,sub;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email);
		Intent i1=getIntent();
		email=i1.getStringExtra("email");
		msg=(EditText) (findViewById(R.id.editText3));
		sub=(EditText) (findViewById(R.id.editText2));
	      
	}
	public void send(View v){
		sendEmail();
	}
	protected void sendEmail() {
	    
	      String[] TO = {""};
	      String[] CC = {""};
	      Intent emailIntent = new Intent(Intent.ACTION_SEND);
	      
	      emailIntent.setData(Uri.parse("email"));
	      emailIntent.setType("text/plain");
	      emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{TO});
	      emailIntent.putExtra(Intent.EXTRA_CC, CC);
	      emailIntent.putExtra(Intent.EXTRA_SUBJECT,sub.getText().toString() );
	      emailIntent.putExtra(Intent.EXTRA_TEXT, msg.getText().toString());
	      
	      try {
	         startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	         finish();
	        
	      }
	      catch (android.content.ActivityNotFoundException ex) {
	         Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
	      }
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
