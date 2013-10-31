package com.migestion.swing.factories;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.migestion.swing.view.inputs.PropertyInput;
import com.migestion.swing.view.inputs.PropertyInputCollection;

/**
 * Provee m�todos para generar JPanel a partir de
 * objetos PropertyInput.
 * 
 * @author Bernardo Iribarne
 *
 */
public class PropertyInputFactory {

	/**
	 * se crea un panel para el property input, se arma un par key-value,
	 * de la forma:
	 * 
	 * 			descripci�n: "componente de entrada".
	 * 
	 * @param propertyInput
	 * @return
	 */
	public static JPanel getJPanelProperty(PropertyInput propertyInput){
//		//tomamos las medidas de los campos
//		int descriptionWidth= propertyInput.getDescriptionWidth();
//		int inputWidth = (int)propertyInput.getInput().getMinimumSize().getWidth();
//		
//		//creamos el label de la descripci�n
//		JLabel lblDescription = new JLabel();
//		lblDescription.setMinimumSize(new Dimension(descriptionWidth, 16));
//		lblDescription.setMaximumSize(new Dimension(descriptionWidth, 16));
//		lblDescription.setPreferredSize(new Dimension(descriptionWidth, 16));
//		lblDescription.setText(propertyInput.getDescription());
//		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
//
//		//creamos un panel con el label de la descripci�n y
//		//el componente de entrada
//		JPanel panel = new JPanel();
//		panel.setLayout(new FlowLayout());		
//		panel.add(lblDescription);		
//		panel.add(propertyInput.getInput());
//		panel.setMinimumSize(new Dimension(descriptionWidth+inputWidth+20,30));
//		panel.setPreferredSize(new Dimension(descriptionWidth+inputWidth+20,30));
//		
//		JPanel panelSuperior = new JPanel(new BorderLayout());
//		panelSuperior.add(panel, BorderLayout.CENTER);
//		
		
		//creamos un para key-value, donde key=descripci�n y value=componente de entrada.
		return KeyValueLabelFactory.getJPanelKeyValue(propertyInput.getDescription(), propertyInput.getDescriptionWidth(),propertyInput.getInput());
		
	}
	
	/**
	 * genera un panel donde se mostrar� la
	 * informaci�n definida en la colecci�n de properties
	 * input <code>propertiesInput</code>
	 *
	 */
	public static JPanel getJPanelProperty(PropertyInputCollection propertiesInput){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		//para cada propertyInput creamos un panel
		propertiesInput.init();
		while (propertiesInput.hasNext()) {
			panel.add(getJPanelProperty(propertiesInput.next()));
		}
		
		return panel;
	}

	/**
	 * genera un panel donde se mostrar� la
	 * informaci�n definida en la colecci�n de properties
	 * input <code>propertiesInput</code>
	 * <code>columns</code> definir� el n�mero de columnas
	 * en que se distribuir� la informaci�n.
	 *
	 */
	public static JPanel getJPanelProperty(PropertyInputCollection propertiesInput, int columns){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		int indice=0;
		
		//recorremos las properties
		while (indice<propertiesInput.size()) {
			
			//vamos generando un panel para las properties por fila
			int columnIndex=0;
			JPanel panelRow = new JPanel();
			panelRow.setLayout(new BoxLayout(panelRow,BoxLayout.X_AXIS));
			while((columnIndex<columns) && ((indice+columnIndex)<propertiesInput.size())){
				panelRow.add(getJPanelProperty(propertiesInput.next()));
				columnIndex++;
			}
			indice+=columns;
			panel.add(panelRow);
		}
		return panel;
	}

}
