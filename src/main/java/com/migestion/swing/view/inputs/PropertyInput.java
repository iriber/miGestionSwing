package com.migestion.swing.view.inputs;

import java.awt.Component;

/**
 * Se definir� un propertyInput para determinar
 * los campos que deben mostrarse en un di�logo
 * para un objeto en particular.
 * 
 * @author Bernardo Iribarne
 *
 */
public class PropertyInput {

	//el nombre deber� ser �nico por ventana, ya
	//que ser� el identificador.
	private String name;
	//descripci�n que se visualizar� en el di�logo.
	private String description;
	//ancho de la descripci�n
	private int descriptionWidth;
	//componente que ser� utilizado para setear la propiedad.
	private Component input;
	
	//CONSTRUCTORES
	public PropertyInput(String name, String description, int descriptionWidth, Component input){
		this.name = name;
		this.description = description;
		this.descriptionWidth = descriptionWidth;
		this.input = input;
	}
	
	public String getDescription() {
		return description;
	}
	public Component getInput() {
		return input;
	}
	public String getName() {
		return name;
	}

	public int getDescriptionWidth() {
		return descriptionWidth;
	}
	public void setDescriptionWidth(int descriptionWidth) {
		this.descriptionWidth = descriptionWidth;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setInput(Component input) {
		this.input = input;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
