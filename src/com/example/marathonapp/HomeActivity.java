package com.example.marathonapp;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class HomeActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ArrayList<String[]> list = buildData();
	  StringAdapter adapter=new StringAdapter(this, list, R.layout.list_four_strings);
	   setListAdapter(adapter);
	}

	private ArrayList<String[]> buildData() {
	    ArrayList<String[]> list = new ArrayList<String[]>();
	    String[] data={"Android", "Mobile", "Phone","111-111-1111"};
	    list.add(data);
	    String[] data2={"Windows7", "Windows7", "PC","222-222-2222"};
	    list.add(data2);
	    String[] data3={"iPhone", "iPhone","POS","333-333-3333"};
	    list.add(data3);
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
