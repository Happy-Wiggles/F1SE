package de.thb.formel1.appl.controller;

/**
 * 
 * Schnittstelle zum GUI (Controller)
 * Die ControllerFactory erzeugt Controller, so dass nur die Interfaces der Controller
 * der Applikation genutzt werden müssen.
 * 
 * GGf. muss die Factory noch so erweitert werden, dass verschiedene 
 * Controller der Applikation erzeugt werden können.
 * 
 * @author Gabriele Schmidt
 * @version 0.1 
 * @since 08.04.2011
 * 
 */

public class ControllerFactory {

	public static RennverwaltungControllerInterface buildController() {
        return new RennverwaltungControllerImpl();
    }

}
