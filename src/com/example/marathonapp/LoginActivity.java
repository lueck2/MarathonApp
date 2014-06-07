package com.example.marathonapp;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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
   

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
        private boolean validate(String user, String password)
        {
        	return true;
        }
        public void onLogin(View view)
        {
        	String user=(((EditText)view.findViewById(R.id.login_email)).getText()).toString();
        	String password=(((EditText)view.findViewById(R.id.login_password)).getText()).toString();
        	//validate login info via webserver
        	if(validate(user, password))
        	{
        		//Intent intent = new Intent(this, MyQRCodeActivity.class);
            	//startActivity(intent);
        	}
        }
        public boolean userCreate(String user, String password)
        {
        	return true;
        	
        }
        public void onRegister(View view)
        {
        	String user=(((EditText)view.findViewById(R.id.register_email)).getText()).toString();
        	String password=(((EditText)view.findViewById(R.id.register_password)).getText()).toString();
        	String passwordConfirm=(((EditText)view.findViewById(R.id.register_password_confirm)).getText()).toString();
        	if(password.equals(passwordConfirm) && userCreate(user, password))
        	{
        		//Intent intent = new Intent(this, MyQRCodeActivity.class);
            	//startActivity(intent);
        	}
        }
    }

}
