package com.example.myfirstapp;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	public final static String EXTRA_DISTANCE = "com.example.myfirstapp.DISTANCE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) 
        {
	        case R.id.action_search:
//	        	openSearch();
	        	return true;
	        case R.id.action_settings:
//	        	openSettings();
	        	return true;
	        default:
	        	return super.onOptionsItemSelected(item);
        }
    }
    
    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
    /** Called when the user clicks the Calculate button */
    public void calculateDistance(View view) {
    	Intent intent = new Intent(this, ConvertDistanceActivity.class);
    	EditText editDistance = (EditText) findViewById(R.id.edit_distance);
    	RadioButton kmhButton = (RadioButton) findViewById(R.id.radio0);
    	RadioButton mileButton = (RadioButton) findViewById(R.id.radio1);
    	
    	if (editDistance.getText().length() == 0)
    	{
    		Toast.makeText(this,  "enter a valid number",  Toast.LENGTH_LONG).show();
    	}
    	else
    	{
    		double inputValue = Double.parseDouble(editDistance.getText().toString());
    		String distance;
            if (mileButton.isChecked()) {  
                distance = (String.valueOf(convertToMiles(inputValue)));  
                // uncheck "to miles" Button  
                mileButton.setChecked(false);  
                // check "to km/h" Button  
                kmhButton.setChecked(true); 
            } else { /* if kmhButton isChecked() */  
                distance = (String.valueOf(convertToKilometers(inputValue)));  
                // uncheck "to km/h" Button  
                kmhButton.setChecked(false);  
                // check "to miles" Button  
                mileButton.setChecked(true);  
            }  
            intent.putExtra(EXTRA_DISTANCE, distance);
            startActivity(intent);
    	}
    }
    
    private double convertToMiles(double inputValue) {
    	return (inputValue * 1.609344);
    }
    
    private double convertToKilometers(double inputValue) {
    	return (inputValue * 0.621372);
    }
}
