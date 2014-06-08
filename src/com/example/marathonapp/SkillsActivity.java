package com.example.marathonapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SimpleAdapter;

public class SkillsActivity extends ListActivity {
	private static String userFile="user_file";
	private ArrayList<Map<String, String>> allSkills;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skills);
		String[] from = { "key", "value" };
	    int[] to = { R.layout.skill_list, R.id.checkBox1 };
	    
	   allSkills = loadAllSkills();
	    SimpleAdapter adapter = new SimpleAdapter(this, allSkills,
	        R.layout.skill_list, from, to);
	    setListAdapter(adapter);
		
		markUserSkills(allSkills);
	}
	private ArrayList<Map<String, String>> loadAllSkills()
	{
		ArrayList<Map<String, String>> allSkills=new ArrayList<Map<String, String>>();
		//database shit here
		allSkills.add(putData("hi", "hello"));
		allSkills.add(putData("bye", "goodbye"));
		
		return allSkills;
		
	}
	private HashMap<String, String> putData(String name, String purpose) {
	    HashMap<String, String> item = new HashMap<String, String>();
	    item.put("key", name);
	    item.put("value", purpose);
	    return item;
	  }
	private void markUserSkills(ArrayList<Map<String, String>> allSkills)
	{
		try{
			FileInputStream is = this.openFileInput(userFile);
			String[] keys= FileManager.convertStreamToString(is);
			Set<String> keySkills=new HashSet<String>();
			for (Map<String, String> row: allSkills) {
				keySkills.addAll( row.keySet());
				}
			
			for (int i=0; i<keys.length; i++)
			{
				if(keySkills.contains(keys[i]))
				{
					CheckBox checkbox=(CheckBox)findViewById(this.getResources().getIdentifier(keys[i],"id", this.getPackageName()));
					if(checkbox!=null)
					{
						checkbox.setChecked(true);
					}
				}
			}
		}
		catch(FileNotFoundException e)
		{
			try{
				this.openFileOutput(userFile, Context.MODE_PRIVATE);
				e.printStackTrace();
				
			}catch(FileNotFoundException f)
			{
				f.printStackTrace();
			}
		}
		
	}
	
}
