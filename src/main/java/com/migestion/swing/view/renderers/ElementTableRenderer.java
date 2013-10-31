package com.migestion.swing.view.renderers;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import com.migestion.swing.model.UICollection;
import com.migestion.swing.resources.PropertiesImage;

/**
 * Define la visualización de un objeto en la tabla que se mostrará
 * en los listados
 * 
 * @author Bernardo Iribarne
 * @since 06/05/2012
 *
 */
public class ElementTableRenderer extends JLabel implements TableCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8556558947846476147L;

	public Component getTableCellRendererComponent(JTable table, Object value,
	     boolean isSelected, boolean hasFocus, int row, int column)
	  {

		  JLabel etiqueta = new JLabel();
		  etiqueta.setOpaque(true);

	      
	      etiqueta.setFont( getFont(table, value, isSelected, hasFocus, row, column) );

	      if(isSelected){
	        etiqueta.setBackground(getColorRowSelected());
	        etiqueta.setForeground(getColorFontRowSelected());
	        
	        if(column==0){
	        	etiqueta.setHorizontalAlignment(SwingConstants.RIGHT);
	        	etiqueta.setIcon(new ImageIcon(PropertiesImage.item_list_selection));
	        }
	        
	      }
	       else{	         
	         etiqueta.setBackground(getColorFondo(table, value, isSelected, hasFocus, row, column));
	         etiqueta.setForeground(getColorFont(table, value, isSelected, hasFocus, row, column));
	       }
	      
	      
	      etiqueta.setHorizontalTextPosition(SwingConstants.RIGHT);
	      etiqueta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
         
	      UICollection model = (UICollection)table.getModel();	      
	      
	      etiqueta.setHorizontalAlignment( model.getColumnTextAlign(column));
          
	      setLabel(etiqueta, column, row, model.getValueAt(row, column));
	      
	      return etiqueta;
	  }


	  /**
	   * muestra en el label la propiedad ubicada en fila-columna.
	   * (se deja en un m�todo aparte para que pueda personalizarse).
	   * 
	   * @param label
	   * @param column
	   * @param element
	   */
	  protected void setLabel(JLabel label, int column, int row, Object propertyElement){
		  if(propertyElement!=null)
        	  label.setText(propertyElement.toString());
          else
        	  label.setText("");
	  }
	  
	  /**
	   * retorna el color de las filas pares
	   * @return
	   */
	  protected Color getColorRowPar(){
		  return Color.getHSBColor(10,0,30);
	  }
	  
	  /**
	   * retorna el color de las filas impares
	   * @return
	   */
	  protected Color getColorRowImpar(){
		  return Color.getHSBColor(10,0,10);
	  }
	  
	  /**
	   * retorna el color que tendr� una fila seleccionada.
	   * @return
	   */
	  protected Color getColorRowSelected(){
		  return Color.ORANGE;
	  }
	  
	  /**
	   * retorna el color de la letra
	   * @return
	   */
	  protected Color getColorFont(JTable table, Object value,
			     boolean isSelected, boolean hasFocus, int row, int column){
		  return Color.BLACK;
	  }

	  /*
	   * color para el fondo de la fila
	   */
	  protected Color getColorFondo(JTable table, Object value,
			     boolean isSelected, boolean hasFocus, int row, int column){
	   Color color;

	   if(row % 2 == 0)
	      color = getColorRowPar();
	     else
	      color = getColorRowImpar();

	   return color;

	  }
	  
	  /*
	   * font
	   */
	  protected Font getFont(JTable table, Object value,
			     boolean isSelected, boolean hasFocus, int row, int column){
		  return new Font("Arial", 0, 10);

	  }
	  /**
	   * retorna el color de la letra cuando la fila
	   * est� seleccionada
	   * @return
	   */
	  protected Color getColorFontRowSelected(){
		  return Color.BLACK;
	  }

}
