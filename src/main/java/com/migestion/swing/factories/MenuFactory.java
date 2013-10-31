package com.migestion.swing.factories;

import java.awt.Cursor;

import javax.swing.JMenuItem;

import com.migestion.swing.navigation.Link;

/**
 * Proporciona m�todos para crear men�es con las
 * propiedades m�s utilizadas.
 * 
 * @author Bernardo Iribarne
 *
 */
public class MenuFactory {
	
	/**
	 * se crea un menuItem a partir de un link
	 * @param link
	 * @return
	 */
	public static JMenuItem getJMenuItem(Link link){
		JMenuItem menuItem = new JMenuItem(link);				
		menuItem.setText(link.getDescription());
		menuItem.setIcon(link.getIcon());
		//menuItem.setVisible(link.isEnabled());
		menuItem.setEnabled(link.isEnabled());
		menuItem.setAccelerator(link.getKeyStroke());
		menuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//menuItem.addActionListener(link);
		return menuItem;
	}

	/**
	 * setea un menuItem a partir de un link
	 * @param link
	 * @return
	 */
	public static void setJMenuItem(JMenuItem menuItem, Link link){		
		menuItem.setText(link.getDescription());
		menuItem.setIcon(link.getIcon());
		//menuItem.setVisible(link.isEnabled());
		menuItem.setEnabled(link.isEnabled());
		menuItem.setAccelerator(link.getKeyStroke());
		//menuItem.addActionListener(link);		
	}


}
