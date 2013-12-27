package com.example.triageapplication;

import objectClasses.ER;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class ViewTriageActivity extends Activity implements OnClickListener{
	
	Button saveStateButton;
	Button addPatientButton;
	TextView viewAllPatientsTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_triage);
		
		saveStateButton = (Button) findViewById(R.id.saveStateButton);
		addPatientButton = (Button) findViewById(R.id.addPatientButton);
		viewAllPatientsTextView = (TextView) findViewById(R.id.viewAllPatientsTextView);
		
		saveStateButton.setOnClickListener(this);
		addPatientButton.setOnClickListener(this);
		
		viewAllPatientsTextView.setText(ER.printTriage());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_triage, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent patientClass = new Intent();//the name of the class that will create a patient
		
		switch(v.getId()){
			case R.id.addPatientButton:
				//pressed addPatientButton
				startActivity(patientClass);
				break;
			
			case R.id.saveStateButton:
				//pressed saveStateButton
				ER.loadState();
				break;
		}
	}
}

