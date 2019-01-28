package com.hit.model;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.Observable;
import java.util.Scanner;

import javax.swing.JFileChooser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;
import com.hit.server.Request;
import com.hit.util.Message;

public class CacheUnitModel extends Observable implements Model {
	
	public static String fileChose()
	{
	    JFileChooser fc= new JFileChooser();
	    int ret = fc.showOpenDialog(null);
	    String file = "";
	    if (ret== JFileChooser.APPROVE_OPTION) 
	    {
			try {
				file = new String(Files.readAllBytes(fc.getSelectedFile().toPath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
	        return file;
	    }
	    else
	        return null;
	}
	CacheUnitClient cacheUnitClient;
	
	public CacheUnitModel() {
		this.cacheUnitClient = new CacheUnitClient();
	}
	
	public <T> void updateModelData(T t) {
		System.out.println("got it");
		String requst = (String) t;
		
		if(requst.equals ("load")) {
			
            ObjectInputStream inputStream = null;

            String request = this.fileChose();
			request = request.replaceAll(System.lineSeparator(), "");
			System.out.println(request);
			System.out.println("try to send request");
			String response = cacheUnitClient.send(request);
			System.out.println("~Got the response");
			//System.out.println(response);

                if (response.equals("net-crash")) {
                		notifyObservers(new Message("net-crash", "Failed"));
				}
                
                notifyObservers(new Message("model-load", response));
        }else if(requst.equals ("stats")) {
        	
            String dataFromServer = cacheUnitClient.send ("stats");
            setChanged ();
            notifyObservers (new Message ("stats",dataFromServer));

        }
	}
}
