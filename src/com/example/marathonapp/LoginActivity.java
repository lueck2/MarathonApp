package com.example.marathonapp;

import java.sql.ResultSet;
import java.sql.Statement;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

	DBConnection connection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.marathon_app_login_screen);
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

	private boolean validate(String user, String password) throws SQLException{
		String sql = "SELECT count(*) AS count from users where username=\'"+user+"\' and  password = \'"+password+"\'";
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		connection = new DBConnection(networkInfo);

		try {
			Statement st = connection.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			int size=0;
			
			rs.next();
			size=rs.getInt("count");
			rs.close();
			st.close();
			connection.getConnection().close();
			if(size>=1)
			{
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean validate(String user){
		String sql = "SELECT count(*) AS count from users where username=\'"+user+"\'";
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		connection = new DBConnection(networkInfo);

		try {
			Statement st = connection.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			int size=0;
			
			rs.next();
			size=rs.getInt("count");
			rs.close();
			st.close();
			connection.getConnection().close();
			if(size>=1)
			{
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void openHome()
	{
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		this.finish();
	}
	public void onLogin(View view) {
		View rootView = findViewById(android.R.id.content);
		String user = ((EditText) rootView.findViewById(R.id.login_email))
				.getText().toString();
		String password = ((EditText) rootView
				.findViewById(R.id.login_password)).getText().toString();
		// validate login info via webserver
		if (validate(user, password)) {
			openHome();
		}
	}

	public boolean userCreate(String user, String password, String passwordConfirm, String name, String email, String phone) {
		boolean result=false;
		if(passwordConfirm.equals(password))
		{
			ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

			NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
			connection = new DBConnection(networkInfo);

			try {
				String sql = "INSERT INTO users (username, password, name, email, phone)"+
						" VALUES (\'"+user+"\', \'"+password+"\', \'"+name+"\', \'"+email+"\', \'"+phone+"\')"+
						"RETURNING username, password";
				System.out.println(sql);
				Statement st = connection.getConnection().createStatement();
				ResultSet rs = st.executeQuery(sql);
				String uname = "";
				while (rs.next()) {
					 uname = rs.getString("username");
				 }
				
				rs.close();
				st.close();
				connection.getConnection().close();

				if(validate(uname))
				{
					result = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void onRegister(View view) {
		View rootView = findViewById(android.R.id.content);
		String user = ((EditText) rootView.findViewById(R.id.register_email))
				.getText().toString();
		String password = ((EditText) rootView
				.findViewById(R.id.register_password)).getText().toString();
		String passwordConfirm = ((EditText) rootView
				.findViewById(R.id.register_password_confirm)).getText()
				.toString();
		if ( userCreate(user, password, passwordConfirm, "","","")) {
			openHome();
		}
	}
}