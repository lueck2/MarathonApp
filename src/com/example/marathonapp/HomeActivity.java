package com.example.marathonapp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class HomeActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.marathon_homepage);
		ArrayList<String[]> list = buildData();
	  StringAdapter adapter=new StringAdapter(this, list, R.layout.list_four_strings);
	   setListAdapter(adapter);
	}

	private ArrayList<String[]> buildData() {   
	    ArrayList<String[]> list = new ArrayList<String[]>();
	    
	    
	    ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		DBConnection connection = new DBConnection(networkInfo);

		try {
			String sql="SELECT eve.event_name, eve.latitude, eve.longitude,"+
					"org.name, org.website from events eve, organizations org where eve.ordid = org.id ORDER BY start_date";
			Statement st = connection.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			connection.getConnection().close();
			while(rs.next())
			{
				String string1 = rs.getString("event_name");
				System.out.print(string1);
				String string2 = rs.getString("latitude");
				System.out.print(string2);
				String string3 = rs.getString("longitude");
				System.out.print(string3);
				String string4 = rs.getString("name");
				System.out.print(string4);
				String string5= rs.getString("website");
			    String[] data={string1, string2 +", "+ string3, string4, string5};
			    list.add(data);
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return list;
	}
		  

	public void gotoUserInfo(View view)
    {
		Intent intent = new Intent(this, UserInfoActivity.class);
    	startActivity(intent);
    }
	public void gotoSkills(View view)
    {
		Intent intent = new Intent(this, SkillsActivity.class);
    	startActivity(intent);
    }
	public void gotoNonprofs(View view)
    {
		Intent intent = new Intent(this, ListNonprofitsActivity.class);
    	startActivity(intent);
    }
}
