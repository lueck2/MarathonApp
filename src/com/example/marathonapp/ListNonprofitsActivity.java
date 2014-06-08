package com.example.marathonapp;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class ListNonprofitsActivity extends ListActivity {
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
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
		String sql="SELECT name, address, phone, skills from organizations";
		Statement st = connection.getConnection().createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		connection.getConnection().close();
		while(rs.next())
		{
			String string1 = rs.getString("name");
			System.out.print(string1);
			String string2 = rs.getString("address");
			System.out.print(string2);
			String string3 = rs.getString("phone");
			System.out.print(string3);
			String string4 = rs.getString("skills");
			System.out.print(string4);
			
		    String[] data={string1, string2, string3, string4};
		    list.add(data);
		}
		rs.close();
		st.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
    return list;
  }
} 