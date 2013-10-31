package com.migestion.swing.view.renderers;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import com.migestion.swing.model.UICollection;

/**
 * Customizamos el header de la tabla
 * 
 * @author Bernardo Iribarne
 * @since 08/10/2013
 *
 */
public class TableHeaderRenderer extends JLabel implements TableCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8556558947846476147L;

	public Component getTableCellRendererComponent(JTable table, Object value,
	     boolean isSelected, boolean hasFocus, int row, int column)
	  {

		  JLabel etiqueta = new JLabel();
		  etiqueta.setOpaque(true);
		  Font font = new Font("Verdana", 1, 11);
	      etiqueta.setFont(font);

	      if(isSelected){
	        etiqueta.setBackground(getColorRowSelected());
	        etiqueta.setForeground(getColorFontRowSelected());
	       	       
	      }
	       else{	         
	         etiqueta.setBackground(getColorFondo(row));
	         etiqueta.setForeground(getColorFont());
	       }
	      
	      

	      UICollection model = (UICollection)table.getModel();	      
	      
	      etiqueta.setHorizontalAlignment( model.getColumnTextAlign(column));
          
	      //TODO agregar para ordenar.
	      
	      setLabel(etiqueta, column, row, model.getColumnName(column));
	      
	      return etiqueta;
	  }

	  /*
	   * color para el fondo de la fila
	   */
	  private Color getColorFondo(int row){
	   Color color;

	   if(row % 2 == 0)
	      color = getColorRow();
	     else
	      color = getColorRow();

	   return color;

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
		  label.setHorizontalTextPosition(SwingConstants.RIGHT);
		  label.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		  if(propertyElement!=null)
        	  label.setText(propertyElement.toString());
          else
        	  label.setText("");
	  }
	  
	  
	  /**
	   * retorna el color de las filas impares
	   * @return
	   */
	  protected Color getColorRow(){
		  //return  Color.decode("42B0FF");//Color.getHSBColor(205, 74, 100);
		  return new Color(0x42B0FF);
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
	  protected Color getColorFont(){
		  return Color.WHITE;
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
