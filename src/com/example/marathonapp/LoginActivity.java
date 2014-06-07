package com.example.marathonapp;

import java.sql.ResultSet;
import java.sql.Statement;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class LoginActivity extends ActionBarActivity {

	DBConnection connection;
	private View fragmentView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		connection = new DBConnection(networkInfo);

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

	private boolean validate(String user, String password) {
		String sql = "SELECT username, password from users where username ="
				+ user;
		
		try {
			Statement st = connection.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String uname = rs.getString("username");
				String pword = rs.getString("password");
			}
			rs.close();
			st.close();
	//		return true;
		} catch (Exception e) {
			e.printStackTrace();
//			return false;
		} 
		return true;
	}

	public void onLogin(View view) {
		View rootView = getSupportFragmentManager().findFragmentById(
				R.id.container).getView();
		String user = ((EditText) rootView.findViewById(R.id.login_email))
				.getText().toString();
		String password = ((EditText) rootView
				.findViewById(R.id.login_password)).getText().toString();
		// validate login info via webserver
		if (validate(user, password)) {
			Intent intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
		}
	}

	public boolean userCreate(String user, String password) {
		return true;

	}

	public void onRegister(View view) {
		View rootView = getSupportFragmentManager().findFragmentById(
				R.id.container).getView();
		String user = ((EditText) rootView.findViewById(R.id.register_email))
				.getText().toString();
		String password = ((EditText) rootView
				.findViewById(R.id.register_password)).getText().toString();
		String passwordConfirm = ((EditText) rootView
				.findViewById(R.id.register_password_confirm)).getText()
				.toString();
		if (password.equals(passwordConfirm) && userCreate(user, password)) {
			Intent intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
}