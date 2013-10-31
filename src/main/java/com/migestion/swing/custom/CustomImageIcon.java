package com.migestion.swing.custom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;

/**
 * image icon donde se redefine c�mo pintar la imagen.
 * 
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public class CustomImageIcon extends ImageIcon {

	/**
	 * construye un image icon a partir del filename.
	 * 
	 * @param filename
	 */
	public CustomImageIcon(String filename) {
		super(filename);
	};

	/**
	 * redefinimos c�mo pintar la imagen.
	 */
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(Color.white);
		g.fillRect(0, 0, c.getWidth(), c.getHeight());
		if (getImageObserver() == null) {
			g.drawImage(getImage(), c.getWidth() / 2 - getIconWidth() / 2, c
					.getHeight()
					/ 2 - getIconHeight() / 2, c);
		} else {
			g.drawImage(getImage(), c.getWidth() / 2 - getIconWidth() / 2, c
					.getHeight()
					/ 2 - getIconHeight() / 2, getImageObserver());
		}
	}
}
