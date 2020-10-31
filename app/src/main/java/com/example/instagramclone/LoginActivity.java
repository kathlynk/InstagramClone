package com.example.instagramclone;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

	public static final String TAG = "LoginActivity";
	private EditText etUsername;
	private EditText etPassword;
	private Button btnLogin;
	private Button btnSignup;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		if(ParseUser.getCurrentUser() != null) {
			goMainActivity();
		}

		etUsername = findViewById(R.id.etUserName);
		etPassword = findViewById(R.id.etPassword);
		btnLogin = findViewById(R.id.btnLogin);
		btnSignup = findViewById(R.id.btnSignup);


		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "onCLick login button");
				String username = etUsername.getText().toString();
				String password = etPassword.getText().toString();
				loginUser(username, password);
			}
		});

		btnSignup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String username = etUsername.getText().toString();
				String password = etPassword.getText().toString();
				createUser(username, password);
				loginUser(username, password);

			}
		});
	}

	private void createUser(String username, String password) {
		ParseUser user = new ParseUser();

		user.setUsername(username);
		user.setPassword(password);

		user.signUpInBackground(new SignUpCallback() {
			@Override
			public void done(ParseException e) {
				if (e != null) {
					Log.e(TAG, "Sign up failure", e);
					Toast.makeText(LoginActivity.this, "Sign up unsuccessful. Make sure to use a valid username and/or password.", Toast.LENGTH_SHORT);
				} else {
					Toast.makeText(LoginActivity.this, "Sign up successful!", Toast.LENGTH_SHORT);
				}
			}
		});
	}

	private void loginUser(String username, String password) {
		Log.i(TAG, "Attempting to login user " + username);
		ParseUser.logInInBackground(username, password, new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException e) {
				if (e !=null) {
					Log.e(TAG, "Issue with login", e);
					Toast.makeText(LoginActivity.this, "Issue with Login!", Toast.LENGTH_SHORT);
					return;
				}
				goMainActivity();
			}
		});
	}

	private void goMainActivity() {
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		finish();
	}
}
