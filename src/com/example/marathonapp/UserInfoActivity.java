package com.example.marathonapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class UserInfoActivity extends Activity {
	private static String userFile="user_file";
	private Map<String, String> allSkills;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
	}
	
}
