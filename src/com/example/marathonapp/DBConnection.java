package com.example.marathonapp;

import java.sql.Connection;
import java.sql.DriverManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class DBConnection {
	private static String USER;
	private static String PASSWORD;
	private static Connection connection;
	protected DBConnection(NetworkInfo networkInfo) {
		USER = "nfswnhayngokev";
		PASSWORD = "AwX4zjzVJyMoxo5eVoLlXYhcKr";

		if (networkInfo != null && networkInfo.isConnected()) {

			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String url;
			url = "jdbc:postgresql://ec2-107-20-234-127.compute-1.amazonaws.com:5432/d3sbq4b41pjrid"
					+ "?sslfactory=org.postgresql.ssl.NonValidatingFactory"
					+ "&ssl=true";
			try {
				connection = DriverManager.getConnection(url, USER, PASSWORD);

				System.out.println(" Made a connection with the database");

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public Connection getConnection()
	{
		return DBConnection.connection;
	}
	
}
