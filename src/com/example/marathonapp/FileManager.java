package com.example.marathonapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.util.Log;

public class FileManager {

	public static String[] convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        int stringCount=0;
        String[] tempArray=new String[10000];
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
            	tempArray[stringCount]=line;
            	stringCount++;
            }
        } catch (IOException e) {
            Log.w("LOG", e.getMessage());
            return null;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.w("LOG", e.getMessage());
            }
        }
        String[] returnArray=new String[stringCount];
        for(int i=0; i<stringCount; i++)
        {
        	returnArray[i]=tempArray[i];
        }
        return returnArray;
	}
	
	public static void stringToFile(String[] data, String filename, Activity activity)
	{
		
		
		
	}
}
