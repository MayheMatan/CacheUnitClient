package com.hit.view;

import javax.swing.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hit.util.Message;
import com.hit.util.CacheMemory;
import java.awt.*;
import java.awt.event.ActionEvent;


import java.util.Observable;

public class CacheUnitView extends Observable implements View {

	private JButton fileB = new JButton("Load a file");
	private JButton statsB = new JButton("CacheMemory");
	private JPanel bPanel = new JPanel();
	
	JFrame frame = new JFrame("CacheUnitClient");
	
	
	
	public CacheUnitView () {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				start();
			}
		});
		
	}
	
	
	@SuppressWarnings("serial")
	public void start() {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 300));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		//add components to panel
		
		bPanel.setLayout(new BorderLayout(4,4));
		bPanel.setBackground(new Color(59, 64, 69));

		bPanel.add(fileB, BorderLayout.LINE_START);
		bPanel.add(statsB, BorderLayout.LINE_END);
		
		frame.add(bPanel);//add panel to main frame
		
		
		
		fileB.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setChanged();
	    	 		notifyObservers(new Message("view", "load"));
			}
		}); 
		
		statsB.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setChanged();
				notifyObservers(new Message("view", "stats"));
				
			}
		});
		
		//Display the window.
        frame.pack();
        frame.setVisible(true);
	}
	
	public <T> void updateUIData(T t) {
		
		bPanel.updateUI();
		
		Message tMsg = (Message) t;
        String labelString = null;


        if (tMsg.getSentIdentifier ().equals ("load"))
        {
            String inputString = tMsg.getMessege ();
            labelString = "Failed";

            if (inputString.equals ("true"))
            {
                labelString = "Succeeded";
                bPanel.add(new JLabel(labelString));
                bPanel.validate();
            }else
            {
            		bPanel.add(new JLabel(labelString));
            		bPanel.validate();
            }

        }else if(tMsg.getSentIdentifier ().equals ("stats"))
        {
        		Gson gson = new GsonBuilder ().create ();

            CacheMemory Stats = gson.fromJson (tMsg.getMessege (), CacheMemory.class);

            bPanel.add (new JLabel ("Capacity: "+Stats.getData ().get ("capacity")), BorderLayout.CENTER);
            bPanel.add (new JLabel ("Algorithm: "+Stats.getCacheAlgo ()), BorderLayout.CENTER);
            bPanel.add (new JLabel ("Total Number Of Request: "+Stats.getData ().get ("totalReqs")), BorderLayout.CENTER);
            bPanel.add (new JLabel ("Total Number Of Data Models: "+Stats.getData ().get ("modelReqs")), BorderLayout.CENTER);
            bPanel.add (new JLabel ("Total Number Of Data Models Swaps: "+Stats.getData ().get ("modelSwap")), BorderLayout.CENTER);

            bPanel.validate ();
        }
        
	}
}
