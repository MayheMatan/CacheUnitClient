package com.hit.controller;

import com.hit.model.Model;
import com.hit.util.Message;
import com.hit.view.View;


public class CacheUnitController implements Controller{
	
	private View theView;
	private Model theModel;
	private static String id;
	
	public CacheUnitController(Model theModel, View theView) {
		super();
		this.theView = theView;
		this.theModel = theModel;
	}
	
	public void update(java.util.Observable obs, java.lang.Object obj)
    {
		id = null;
        Message update = (Message) obj;

        if(update.getSentIdentifier ().equals ("view"))
        {
            if(update.getMessege ().equals ("load"))
            {
                theModel.updateModelData ("load");
            }else
            {
                theModel.updateModelData ("stats");
            }
        }

        if(update.getSentIdentifier ().equals ("model-load"))
        {
            theView.updateUIData(new Message("load","true"));
        }

        if(update.getSentIdentifier ().equals ("stats"))
        {
            theView.updateUIData (update);
        }

        if(update.getSentIdentifier().equals("net-crash"))
        {
            theView.updateUIData(new Message("load","false"));
        }

	}
}


