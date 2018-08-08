package com.example.contact;



import java.util.ArrayList;


import android.app.Activity;

import android.content.Intent;

import android.database.Cursor;

import android.net.Uri;

import android.os.Bundle;

import android.view.ContextMenu;

import android.view.Menu;

import android.view.MenuItem;

import android.view.View;

import android.view.ContextMenu.ContextMenuInfo;

import android.widget.AdapterView;

import android.widget.AdapterView.OnItemClickListener;

import android.widget.ArrayAdapter;

import android.widget.ListView;

import android.widget.TextView;

import android.widget.Toast;



public class MainActivity extends Activity {

	private ListView listView;

	DBHandler db;

	ArrayList list;

	ArrayAdapter arrayAdapter;

	Cursor row;

	String phone;

	

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        db = new DBHandler(getApplicationContext());

		list = db.getAllCotacts();

		arrayAdapter = new ArrayAdapter(this,R.layout.listitem,list);

		listView = (ListView)findViewById(R.id.listView1);

	    listView.setAdapter(arrayAdapter);

	    
	    try {

			listView.setOnItemClickListener(new OnItemClickListener(){

				 @Override

			     public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
 
				 {

			        // TODO Auto-generated method stub

					System.out.println(arg2+arg3);

			        int searchId = arg2 + 1;

			        Bundle dataBundle = new Bundle();

			        dataBundle.putInt("id", searchId);

			        
			        Intent intent = new Intent(getApplicationContext(),DisplayActivity.class);

			        intent.putExtras(dataBundle);

			        startActivity(intent);

			        
				 }

			  });

   // TextView textview=listView.findViewById(android.R.layout.activity_list_item);

			//registerForContextMenu(listView);

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

			Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
 
			
		}
  
	   }
 
   /*  @Override   
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
  
    	
    	
            super.onCreateContextMenu(menu, v, menuInfo);

            menu.setHeaderTitle("Select The Action");
    
            menu.add(0, v.getId(), 0, "Call"); 
 
            menu.add(0, v.getId(), 0, "SMS"); 

            menu.add(0,v.getId(),0, "Email");

          //  int id=v.getId();

        	//row = db.getData(id);

           // phone=row.getString(row.getColumnIndex("mob")).toString();

    }   */


   
    public void addNewContact(View view)
	{

		Bundle dataBundle = new Bundle();

		dataBundle.putInt("id", 0);

        Intent intent = new Intent(getApplicationContext(),AddActivity.class);

        intent.putExtras(dataBundle);

        startActivity(intent);
  
	}


    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
 
       // Inflate the menu; this adds items to the action bar if it is present.
 
       getMenuInflater().inflate(R.menu.main, menu);
 
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
