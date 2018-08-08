package com.example.contact;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DisplayActivity extends Activity implements OnClickListener  {
	DBHandler db;
	EditText t,t1,t2;
	Button b,b1;
	Bundle bundle;
	Cursor row;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		t=(EditText)findViewById(R.id.name);
		t1=(EditText)findViewById(R.id.mob);
		t2=(EditText)findViewById(R.id.email);
		b=(Button)findViewById(R.id.edit);
		b1=(Button)findViewById(R.id.delete);
		b.setOnClickListener(this);
		b1.setOnClickListener(this);
		db=new DBHandler(this);
		bundle = getIntent().getExtras();
		int value=bundle.getInt("id");
		if(value>0)
		{
			System.out.println("inside if");
			row = db.getData(value);
			//updateId=value;
			row.moveToFirst();
			t.setText(row.getString(row.getColumnIndex("name")).toString());
			t1.setText(row.getString(row.getColumnIndex("mob")).toString());
			t2.setText(row.getString(row.getColumnIndex("email")).toString());
		}
		registerForContextMenu(t);
	}
	@Override   
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)  
    {  
            super.onCreateContextMenu(menu, v, menuInfo);  
            menu.setHeaderTitle("Select The Action");    
            menu.add(0, v.getId(), 0, "Call");
            menu.add(0, v.getId(), 0, "SMS");  
            menu.add(0,v.getId(),0, "Email");
    }   
	 @Override    
	    public boolean onContextItemSelected(MenuItem item){ 
	    	
	            if(item.getTitle()=="Call"){ 
	            	Intent in=new Intent(Intent.ACTION_CALL);
	            	in.setData(Uri.parse("tel:"+t1.getText().toString()));
	            	try{
	            	startActivity(in);
	            	} 
	                catch (android.content.ActivityNotFoundException ex){
	                    Toast.makeText(getApplicationContext(),"yourActivity is not founded",Toast.LENGTH_SHORT).show();
	                 }
	                Toast.makeText(getApplicationContext(),"calling code",Toast.LENGTH_LONG).show();  
	            }    
	            else if(item.getTitle()=="SMS"){  
	            	Intent i = new Intent(getApplicationContext(),Message.class);
	            	i.putExtra("phone",t1.getText().toString());
	            	 startActivity(i); 
	                Toast.makeText(getApplicationContext(),"sending sms code",Toast.LENGTH_LONG).show();  
	            }
	            else if(item.getTitle()=="Email"){  
	            	Intent i = new Intent(getApplicationContext(),Email.class);
	            	i.putExtra("email",t2.getText().toString());
	            	startActivity(i);
	            	
	                Toast.makeText(getApplicationContext(),"sending email code",Toast.LENGTH_LONG).show();  
	            }else{  
	               return false;  
	            }    
	          return true;    
	      }    
	@Override
	public void onClick(View arg0) {
	
		int value=bundle.getInt("id");
		if(arg0==b)
		{
			boolean flag=db.updateContact(value,t.getText().toString(),t1.getText().toString(),t2.getText().toString());
			if(flag)
				Toast.makeText(this, "Contact Updated Successfully",Toast.LENGTH_LONG).show();
			else
				Toast.makeText(this, "Contact Not Updated",Toast.LENGTH_LONG).show();
		}
		else
		{
			int r=db.deleteContact(value);
			if(r==0)
				Toast.makeText(this, "Contact Not Deleted",Toast.LENGTH_LONG).show();
			else
				Toast.makeText(this, "Contact Deleted Successfully",Toast.LENGTH_LONG).show();
		}
		Intent i = new Intent(this,MainActivity.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display, menu);
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
