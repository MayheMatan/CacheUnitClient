package com.hit.util;
//utility class to combine the message from view to the controller

public class Message {
	
	private String sentIdentifier;
    private String messege;

    public Message(String sentId, String msg) {
    	
        this.sentIdentifier = sentId;
        this.messege = msg;
    }

    public String getSentIdentifier() {
    	
        return sentIdentifier;
    }

    public String getMessege() {
    	
        return messege;
    }
}
