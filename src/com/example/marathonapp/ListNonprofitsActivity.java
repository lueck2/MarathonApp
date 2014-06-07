package com.example.marathonapp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class ListNonprofitsActivity extends ListActivity {
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ArrayList<String[]> list = buildData();
    StringAdapter adapter=new StringAdapter(this, list, R.layout.list_three_strings);
    setListAdapter(adapter);
  }

  private ArrayList<String[]> buildData() {
    ArrayList<String[]> list = new ArrayList<String[]>();
    String[] data={"Android", "Mobile", "Phone"};
    list.add(data);
    String[] data2={"Windows7", "Windows7", "PC"};
    list.add(data2);
    String[] data3={"iPhone", "iPhone","POS"};
    list.add(data3);
    return list;
  }
} 