package com.hit.util;
//class to handle and calculate the statistics

import java.io.Serializable;
import java.util.Hashtable;

public class CacheMemory implements Serializable {
	

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String cacheAlgo;
	    private Hashtable<String,Integer> data;

	    private static CacheMemory instance = null;

	    private CacheMemory() {
	    	
	    	data = new Hashtable<> ();
	    	data.put ("capacity",0);
	   		data.put("totalReqs",0);
	   		data.put ("modelReqs",0);
	      	data.put ("modelSwap",0);

	    }
	    public static CacheMemory getInstance() {
	    	
	        if(instance == null) {
	            instance = new CacheMemory ();
	        }
	        return instance;
	    }

	    public synchronized void addRequest() {
	    	
	        int temp = data.get ("totalReqs");
	        data.put ("totalReqs",temp+1);
	    }

	    public synchronized void addModelsRequest() {
	    	
	        int temp = data.get ("modelReqs");
	        data.put ("modelReqs",temp+1);
	    }

	    public synchronized void addModelSwap() {
	    	
	        int temp = data.get ("modelSwap");
	        data.put ("modelSwap",temp+1);
	    }

	    public synchronized void setCapacity(int capacity) {
	    	
	        data.put ("capacity",capacity);
	    }


	    public String getCacheAlgo() {
	        return cacheAlgo;
	    }

	    public void setCacheAlgo(String cacheAlgo) {
	        this.cacheAlgo = cacheAlgo;
	    }

	    public Hashtable<String, Integer> getData() {
	        return data;
	    }

	    public void setData(Hashtable<String, Integer> data) {
	        this.data = data;
	    }

	}