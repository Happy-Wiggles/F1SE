package de.thb.formel1.gui.view;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import de.thb.formel1.gui.controller.RennverwaltungGUIController;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.JComboBox;

/**
 * Zeigt die Liste von Musikern an
 * Es ist eine Auswahl nach Instrumententyp möglich.
 * 
 * @author Susanne Busse
 * @version 0.1
 * @since 03.04.2019
 * 
 */
public class RennverwaltungGUIContentView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelListeRennen = null;
	private JLabel labelRennen = null;
	private JComboBox comboSaison = null;
	private JList listRennenErgebnisse = null;
	private JScrollPane scrollPaneErgebnisse = null;
	private RennverwaltungGUIController controller;

	private GridBagLayout gbl;

	/**
	 * Default-Konstruktor
	 */
	public RennverwaltungGUIContentView() {
		super();

		initialize();
		loadSaisons();
	}

	/**
	 * Diese Methode initialisiert ein Objekt der Klasse.
	 * Aufruf über den Default-Konstruktor
	 * 
	 * @return void
	 */
	private void initialize() {

		this.controller = new RennverwaltungGUIController();

		gbl = new GridBagLayout();

		this.setLayout(gbl);
		
		labelListeRennen = new JLabel();
		labelListeRennen.setText("Liste der Rennen");


		labelRennen = new JLabel();
		labelRennen.setText("Saison:");
		
		addComponent(labelListeRennen,  0, 0, 1, 1, 0, 0);

		addComponent(labelRennen,  0, 1, 1, 1, 0, 0);

		addComponent(getComboBoxSaison(), 2, 1, 1, 1, 0, 0);

		addComponent(getScrollPaneErgebnisse(),  0, 2, 4, 4, 1.0, 1.0);
		
		this.setSize(500, 500);
	}

	/**
	 * Diese Methode initialisiert comboSaison
	 * Bei der Auswahl eines anderen Elements der Combobox wird die listRennenErgebnisse
	 * neu gefüllt (s. loadText(String)
	 * 
	 * @return javax.swing.JComboBox 
	 */
	private JComboBox getComboBoxSaison() {
		if (comboSaison == null) {
			comboSaison = new JComboBox();
			comboSaison.setBounds(new Rectangle(90, 16, 301, 30));
			comboSaison
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							loadText((String) comboSaison
									.getSelectedItem());
						}
					});
		}
		return comboSaison;
	}
	
	/**
	 * Diese Methode initialisiert listRennenErgebnisse
	 * 
	 * @return javax.swing.JList
	 */
	private JList getListErgebnisse() {
		if (listRennenErgebnisse == null) {
			listRennenErgebnisse = new JList();
		}
		listRennenErgebnisse.setSize(300, 300);
		return listRennenErgebnisse;
	}


	/**
	 * Diese Methode initialisiert scrollPaneErgebnisse. Das sind die Scrollbalken für listRennenErgebnisse.
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JScrollPane getScrollPaneErgebnisse() {
		if (scrollPaneErgebnisse == null) {
			scrollPaneErgebnisse = new JScrollPane(getListErgebnisse());
			scrollPaneErgebnisse
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneErgebnisse
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		}
		return scrollPaneErgebnisse;
	}

	/**
	 * Füllt die Combobox mit Saisons einmal am Anfang
	 * 
	 */
	private void loadSaisons() {
		String[] ausgabe = controller.loadSaisons();
		comboSaison.removeAllItems();

		comboSaison.addItem("alle");
		for (String s : ausgabe) {
			comboSaison.addItem(s);
		}
	}
	
	/**
	 * Füllt die Liste am Anfang und immer wenn in der Combobox wieder eine andere Saison 
	 * ausgewählt wurde (s. ActionListener() in getComboBoxWarengruppe().
	 * 
	 */
	private void loadText(String saison) {
		String[] ausgabe = controller.loadRennen(saison);
		getListErgebnisse().setListData(ausgabe);
	}
	
	
	/**
	 * Hilfsmethode, um Contraints bei GridBagLayout zusetzen.
	 * 
	 */
	private void addComponent(Component c,  int x, int y, int width, int height,
			double weightx, double weighty) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.insets = new Insets(2, 2, 2, 2);
		gbl.setConstraints(c, gbc);
		this.add(c);
	}
}
