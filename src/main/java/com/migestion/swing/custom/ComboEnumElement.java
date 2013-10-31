package com.migestion.swing.custom;



/**
 * Element para un combo.
 * 
 * @author Bernardo Iribarne
 *
 */
public class ComboEnumElement{

	private Object item;
	
	private String label;
	
	public ComboEnumElement(Object item, String label){
		this.item = item;
		this.label = label;
	}
	
	/**
	 * @return the item
	 */
	public Object getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(Object item) {
		this.item = item;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String toString(){
		return label;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!obj.getClass().equals(this.getClass()))
			return false;

		ComboEnumElement other = (ComboEnumElement) obj;
		return getItem().equals(other.getItem());
		
	}
}
