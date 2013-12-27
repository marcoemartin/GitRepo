package com.example.triageapplication;

import objectClasses.ER;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener{
	
	EditText usernameEdit;
	EditText passwordEdit;
	TextView errorMessage;
	Button login;
	Button register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		login = (Button) findViewById(R.id.sign_in_button);
		register = (Button) findViewById(R.id.register_button);
		usernameEdit = (EditText) findViewById(R.id.username);
		passwordEdit = (EditText) findViewById(R.id.password);
		errorMessage = (TextView) findViewById(R.id.err_msg);
		
		//set button listeners
		login.setOnClickListener(this);
		register.setOnClickListener(this);

	} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.sign_in_button:
			//clicked on login
			attemptLogin();
			break;
		case R.id.register_button:
			register();
			break;
			
		default:
			break;
		}
	}
	
	/***
	 * will register a new nurse to the system
	 */
	private void register() {
		//creates a nurse adds its username and password to the constructor and adds it to the ER nurse map
		ER.addRegisteredNurse(usernameEdit.getText().toString(), passwordEdit.getText().toString());
		
	}

	/***
	 * will look up ER to see if the credentials are valid
	 */
	private void attemptLogin() {
		
		//if the credentials are correct login 
		if(ER.checkCredentials(usernameEdit.getText().toString(), passwordEdit.getText().toString())){
			Intent loginSuccessful = new Intent(this, ViewTriageActivity.class);
			startActivity(loginSuccessful);
		}else{
			//credentials are invalid
			errorMessage.setText("Invalid Username or Passowrd");
		}
	}

}
