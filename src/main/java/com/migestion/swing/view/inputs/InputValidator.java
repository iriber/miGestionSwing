package com.migestion.swing.view.inputs;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Colabora con las validaciones de los inputs
 * @author Bernardo Iribarne {Ostrich group}
 *
 */
public abstract class InputValidator {

	private Map<JLabel, JComponent> inputs=new HashMap<JLabel, JComponent>();
	
	//guardamos el color original del texto de los labels
	private Map<JLabel, Color> foregroundLabels =new HashMap<JLabel, Color>();
	
	//guardamos el color original del fondo de los labels
	private Map<JLabel, Color> backgroundLabels =new HashMap<JLabel, Color>();
	
	//guardamos el color original del fondo de los inputs
	private Map<JComponent, Color> backgroundInputs =new HashMap<JComponent, Color>();
		
	private Map<JComponent, InputInspector> inspectors=new HashMap<JComponent, InputInspector>();
	private String marca;
	private String message;
	
	public InputValidator(){
		this.marca = " *";
		this.message = "";
	}
	
	public InputValidator(String marca){
		this.marca = marca;
	}

	
	public void put(JLabel label, JComponent component, InputInspector inspector){
		inputs.put(label, component);
		inspectors.put(component, inspector);
		foregroundLabels.put(label, label.getForeground());
		backgroundLabels.put(label, label.getBackground());
		backgroundInputs.put(component, component.getBackground());
		
	}

	public abstract Boolean validate( InputInspector inspector, JComponent component );
	
	public Boolean validate(){
		Iterator<JLabel> it = inputs.keySet().iterator();
		boolean error=false;
		while (it.hasNext()) {
			JLabel label = (JLabel) it.next();
			JComponent component = inputs.get(label);
			InputInspector inspector = inspectors.get(component);
			
			if(! validate(inspector, component)){
				setError(label, component, inspector);
				error = true;
			}
			
		}
		return !error;
	}
	
	public void clean(){
		Iterator<JLabel> it = inputs.keySet().iterator();
		while (it.hasNext()) {
			JLabel label = (JLabel) it.next();
			JComponent component = inputs.get(label);
			InputInspector inspector = inspectors.get(component);
			cleanError(label, component, inspector);
			
		}
	}
	
	public void initialize(){
		Iterator<JLabel> it = inputs.keySet().iterator();
		while (it.hasNext()) {
			JLabel label = (JLabel) it.next();
			label.setText(label.getText() + this.marca);
		}
	}
	
	public void reset(){
	
		Iterator<JLabel> it = inputs.keySet().iterator();
		while (it.hasNext()) {
			JLabel label = (JLabel) it.next();
			
			//le quitamos la marca.
			int marcaLength =  this.marca.length();
			int textLength = label.getText().length() - marcaLength;
			if (textLength > 0)
				label.setText(label.getText().substring(0,textLength ));
		}
		
		
	}
	
	public void setError(JLabel label, JComponent component, InputInspector inspector){
		label.setForeground(Color.RED);
		inspector.setColor(Color.getHSBColor(10, 0, 80), component);
	}
	
	public void cleanError(JLabel label, JComponent component, InputInspector inspector){
		
		//restauramos los colores originales.
		
		label.setForeground( foregroundLabels.get(label) );
		label.setBackground( backgroundLabels.get(label) );
		inspector.setColor( backgroundInputs.get(component), component);
		
		
	}

	/**
	 * @return the inputs
	 */
	public Map<JLabel, JComponent> getInputs() {
		return inputs;
	}

	/**
	 * @param inputs the inputs to set
	 */
	public void setInputs(Map<JLabel, JComponent> inputs) {
		this.inputs = inputs;
	}

	/**
	 * @return the inspectors
	 */
	public Map<JComponent, InputInspector> getInspectors() {
		return inspectors;
	}

	/**
	 * @param inspectors the inspectors to set
	 */
	public void setInspectors(Map<JComponent, InputInspector> inspectors) {
		this.inspectors = inspectors;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
