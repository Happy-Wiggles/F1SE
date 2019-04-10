package de.thb.formel1.gui.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.thb.formel1.appl.controller.ControllerFactory;
import de.thb.formel1.appl.controller.RennverwaltungControllerInterface;
import de.thb.formel1.appl.transferObjects.RennenTransferObjekt;



/**
 * Soll den Ablauf von Menus im GUI steuern. (Gibt es jetzt noch nicht.)
 * Greift auf die nächste darunterliegende Schicht zu. (Verbindung von View und Modell)
 * 
 * @author Gabriele Schmidt
 * @version 0.1 
 * @since 09.04.2011
 * 
 */
public class RennverwaltungGUIController {
	
	private RennverwaltungControllerInterface rennController;

	
	public RennverwaltungGUIController() {
		rennController = ControllerFactory.buildController();
	}
	
	public String [] loadRennen(String jahr) {
		String s;
		String [] sArray;
		ArrayList<String> strings = new ArrayList<String>();
		List<RennenTransferObjekt> rennen;

		if(jahr.equals("alle")) {
			 rennen = rennController.getAll();
		}
		else {
			int jahrInt = Integer.parseInt(jahr);  // ohne Fehlererkennung
			rennen = rennController.getBySaison(jahrInt);
		}
		
		System.out.println("rennen ist: " + rennen.toString());
		for(RennenTransferObjekt r : rennen ){	
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");
				s =  (sdf.format(r.datum)).toString() +  ", " + 
							r.rennstrecke + " in " + r.land;
				strings.add(s);
		}
			
		 sArray = new  String[strings.size()];
		 return  strings.toArray(sArray);
	}
	
	
	public String [] loadSaisons() {

		String [] sArray;
		ArrayList<String> strings = new ArrayList<String>();

		List<Integer> saisons = rennController.getSaisons();
		for(Integer jahr : saisons) {
			strings.add(jahr.toString());
		}
		sArray = new  String[strings.size()];
		return  strings.toArray(sArray);

	}
	
} 
