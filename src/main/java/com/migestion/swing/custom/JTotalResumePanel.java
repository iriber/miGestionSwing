package com.migestion.swing.custom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.migestion.swing.factories.JLabelFactory;
import com.migestion.swing.factories.KeyValueLabelFactory;
import com.migestion.swing.utils.StringUtils;


/**
 * Panel pensado para mostrar un resumen de totales, de
 * la forma:
 * 
 * 		|---------------------|
 *      |       T�tulo	      |	
 * 		|---------------------|
 *      |  key 1   |  value 1 |
 *      |   ...    |   ...    |
 *      |  key n   |  value n |
 *      -----------------------
 * 
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public class JTotalResumePanel extends JPanel{

	//t�tulo de los totales.
	private String title;
	
	//background del t�tulo.
	private Color titleBackground = Color.WHITE;
	
	//background del key.
	private Color keyBackground = Color.LIGHT_GRAY;
	
	//background del value.
	private Color valueBackground = Color.WHITE;
	
	//panel donde se ir�n agregando los pares key-value.
	private JPanel totales;
	
	//label del t�tulo.
	private JLabel lblTitle;
	
	//se guardan los pares key-values.
	private HashMap keysValues;
	
	//---------------
	//CONSTRUCTORES
	//---------------
	public JTotalResumePanel(String title){		
		this.title = title;
		create();
	}
	
	//-----------------
	//M�TODOS PRIVADOS
	//-----------------
	private void create(){
		keysValues = new HashMap();
		
		//creamos el label para el t�tulo.
		lblTitle = JLabelFactory.getJLabel(title, 150);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBackground(getTitleBackground());
		lblTitle.setBorder(BorderFactory.createLineBorder(Color.black));
		lblTitle.setOpaque(true);
		Font font = new Font(lblTitle.getFont().getFontName(),1,lblTitle.getFont().getSize());
		lblTitle.setFont(font);
		
		//creamos el panel para los totales
		totales = new JPanel();
		//layout para agregar por fila.
		totales.setLayout(new BoxLayout(totales,BoxLayout.Y_AXIS));
		
		//formamos el panel.
		this.setLayout(new BorderLayout());		
		this.add(lblTitle, BorderLayout.NORTH);
		this.add(totales, BorderLayout.SOUTH);
	}

	//------------------
	//M�TODOS P�BLICOS
	//------------------

	/**
	 * agrega a los totales el par key-value. 
	 * @param key
	 * @param value
	 */
	public void add(String key, String value){
		add(key, value, 100, 100);
	}

	/**
	 * agrega a los totales el par key-value. 
	 * @param key
	 * @param value
	 */
	public void add(String key, String value, String toolTipText){
		add(key, value, 100, 100, toolTipText);
	}

	/**
	 * agrega a los totales el par key-value. 
	 * @param key
	 * @param value
	 */
	public void add(String key, String value, int keyWidth, int valueWidth){
		add(key, value, keyWidth, valueWidth,"");
	}
	/**
	 * agrega a los totales el par key-value. 
	 * @param key
	 * @param value
	 */
	public void add(String key, String value, int keyWidth, int valueWidth, String toolTipText){
		//componente para el key.
		JLabel keyComponent = JLabelFactory.getJLabel(key, keyWidth);
		keyComponent.setHorizontalAlignment(SwingConstants.RIGHT);
		keyComponent.setBackground(getKeyBackground());
		//si no tiene valores lo ocultamos.
		if(StringUtils.isEmpty(key))
			keyComponent.setOpaque(false);
		else{
			keyComponent.setBorder(BorderFactory.createLineBorder(Color.black));
			keyComponent.setOpaque(true);
		}
		
 	    //componente para el value.
		JLabel valueComponent = JLabelFactory.getJLabel(value, valueWidth);
		valueComponent.setHorizontalAlignment(SwingConstants.CENTER);
		valueComponent.setBackground(getValueBackground());		
		valueComponent.setOpaque(true);
		//si no tiene valores lo ocultamos.
		if(StringUtils.isEmpty(value))
			valueComponent.setOpaque(false);
		else{
			valueComponent.setBorder(BorderFactory.createLineBorder(Color.black));
			valueComponent.setOpaque(true);
		}
		
		//agregamos a la hash el par.
		keysValues.put(key, valueComponent);
		
		//creamos un panel key-value.
		JPanel panelKeyValue = KeyValueLabelFactory.getJPanelKeyValue(keyComponent, valueComponent,23);
		//lo agregamos al panel de totales.
		panelKeyValue.setToolTipText(toolTipText);
		totales.add(panelKeyValue);
		
		
		
		repaint();
		
	}

	public Color getKeyBackground() {
		return keyBackground;
	}

	public Color getTitleBackground() {
		return titleBackground;
	}

	public Color getValueBackground() {
		return valueBackground;
	}

	public void setKeyBackground(Color keyBackground) {
		this.keyBackground = keyBackground;
	}

	public void setTitleBackground(Color titleBackground) {
		this.titleBackground = titleBackground;
		lblTitle.setBackground(titleBackground);
	}

	public void setValueBackground(Color valueBackground) {
		this.valueBackground = valueBackground;
	}

	/**
	 * se setea el value de la key.
	 *  
	 * @param key
	 * @param value
	 */
	public void set(String key, String value){
		if(keysValues.containsKey(key)){
		 ((JLabel)keysValues.get(key)).setText(value);
		 repaint();
		}
	}
}
