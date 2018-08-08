package com.example.contact;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends Activity implements OnClickListener {

	DBHandler db;
	Button b,b1;
	EditText t,t1,t2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		db=new DBHandler(this);
		t=(EditText) findViewById(R.id.newname);
		t1=(EditText) findViewById(R.id.newmob);
		t2=(EditText) findViewById(R.id.newemail);
		b=(Button) findViewById(R.id.addnew);
		b1=(Button) findViewById(R.id.reset);
		b.setOnClickListener(this);
		b1.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
	
		if(arg0==b)
		{
			boolean flag=db.insertContact(t.getText().toString(), t1.getText().toString(), t2.getText().toString());
			if(flag)
				Toast.makeText(getApplicationContext(),"Contact Save",Toast.LENGTH_LONG).show();
			else
				Toast.makeText(getApplicationContext(),"Contact Not Save",Toast.LENGTH_LONG).show();
			Intent i = new Intent(getApplicationContext(),MainActivity.class);
			startActivity(i);
		}
		else
		{
			t.setText("");
			t1.setText("");
			t2.setText("");
			t.requestFocus();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
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
