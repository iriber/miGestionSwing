package com.migestion.swing.view.renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.migestion.swing.model.UICollection;

/**
 * Define la visualización de un objeto en la tabla que se mostrará en los
 * listados
 * 
 * @author Bernardo Iribarne
 * @since 23/10/2013
 * 
 */
public class EntityTableRenderer implements TableCellRenderer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax
	 * .swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		//armamos un label para mostrar la property de la entity.
		
		JLabel entityLbl = new JLabel();

		UICollection model = (UICollection) table.getModel();

		Object entity = model.getElement(row);
		
		//escribimos la property
		setText(entityLbl, table, column, row, entity,
				model.getValueAtShow(row, column));

		//decoramos el label.
		decorate(table, value, isSelected, hasFocus, row, column, entityLbl, entity);

		return entityLbl;
	}

	/**
	 * muestra en el label la propiedad ubicada en fila-columna. (se deja en un
	 * método aparte para que pueda personalizarse).
	 * 
	 * @param label
	 * @param column
	 * @param element
	 */
	protected void decorate(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column, JLabel entityLbl, Object entity) {

		entityLbl.setOpaque(true);

		entityLbl.setFont(getFont(table, value, isSelected, hasFocus, row,
				column, entity));

		if (isSelected) {
			entityLbl.setBackground(getBackgroundSelected());
			entityLbl.setForeground(getForegroundSelected());
		} else {
			entityLbl.setBackground(getBackground(table, value, isSelected,
					hasFocus, row, column));
			entityLbl.setForeground(getForeground(table, value, isSelected,
					hasFocus, row, column));
		}

		// entityLbl.setHorizontalTextPosition(SwingConstants.RIGHT);
		entityLbl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		UICollection model = (UICollection) table.getModel();

		entityLbl.setHorizontalAlignment(model.getColumnTextAlign(column));

	}

	protected void setText(JLabel label, JTable table, int column, int row,
			Object entity, Object property) {
		if (property != null)
			label.setText(property.toString());
		else
			label.setText("");
	}

	/**
	 * retorna el color de las filas pares
	 * 
	 * @return
	 */
	protected Color getBackgroundPar() {
		return Color.getHSBColor(10, 0, 30);
	}

	/**
	 * retorna el color de las filas impares
	 * 
	 * @return
	 */
	protected Color getBackgroundImpar() {
		return Color.getHSBColor(10, 0, 10);
	}

	/**
	 * retorna el color que tendr� una fila seleccionada.
	 * 
	 * @return
	 */
	protected Color getBackgroundSelected() {
		return Color.ORANGE;
	}

	/**
	 * retorna el color de la letra
	 * 
	 * @return
	 */
	protected Color getForeground(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		return Color.BLACK;
	}

	/*
	 * color para el fondo de la fila
	 */
	protected Color getBackground(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Color color;

		if (row % 2 == 0)
			color = getBackgroundPar();
		else
			color = getBackgroundImpar();

		return color;

	}

	/*
	 * font
	 */
	protected Font getFont(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column, Object entity) {
		
		Font font = new Font("Arial", 0, 10);
		return font;

	}


	/**
	 * retorna el color de la letra cuando la fila est� seleccionada
	 * 
	 * @return
	 */
	protected Color getForegroundSelected() {
		return Color.BLACK;
	}

}
