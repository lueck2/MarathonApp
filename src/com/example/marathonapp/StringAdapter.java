package com.example.marathonapp;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;

public class StringAdapter extends ArrayAdapter<String[]> {
  private final Context context;
  private final  ArrayList<String[]> values;
  private int layout;
  private boolean[] selected;
  public StringAdapter(Context context, ArrayList<String[]> values, int layout) {
    super(context, R.layout.list_three_strings, values);
    this.context = context;
    this.values = values;
    this.layout = R.layout.list_four_strings;
    this.selected=new boolean[values.size()];
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(layout, parent, false);
    for(int i=0; i<values.get(position).length; i++)
    {	
    	
    	TextView textView = (TextView) rowView.findViewById(context.getResources().getIdentifier("string"+(i+1),"id", context.getPackageName()));
    	if(textView==null)
    		System.out.println("textView is null");
    	System.out.println(""+i+values.get(position)[i]);
    	textView.setText(values.get(position)[i]);
    	textView = (TextView) rowView.findViewById(R.id.string2);
    }
    /*rw.checkbox
    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

      @Override
      public void onCheckedChanged(CompoundButton buttonView,
          boolean isChecked) {
        if(isChecked)
        	selected[position]=true;
        else
        	selected[position]=false;

      }
    });*/
    return rowView;
  }
} 