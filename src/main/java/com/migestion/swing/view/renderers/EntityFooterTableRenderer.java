package com.migestion.swing.view.renderers;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;

import com.migestion.swing.model.UICollection;

/**
 * Redefine el EntityTableRenderer teniendo en cuenta que el modelo viene con un footer
 * 
 * @author Bernardo Iribarne
 * @since 24/10/2013
 * 
 */
public class EntityFooterTableRenderer extends EntityTableRenderer {


	@Override
	protected Color getBackground(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Color color;
		
		if ( isLastRow(table, row) ){
			color = getBackgroundFooter();
		}else{
			color = super.getBackground(table, value, isSelected, hasFocus, row, column);
		}
		return color;
	}


	@Override
	protected Color getForeground(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		Color color;
		
		if ( isLastRow(table, row) ){
			color = getForegroundFooter();
		}else{
			color = super.getForeground(table, value, isSelected, hasFocus, row, column);
		}
		return color;
		
	}	

	@Override
	protected Font getFont(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column, Object entity) {
		
		Font font;
		if ( isLastRow(table, row) ){
			font = getFontFooter();
		}else{
			font = super.getFont(table, value, isSelected, hasFocus, row, column, entity);
		}
		return font;

	}


	protected Boolean isLastRow(JTable table, int row){
		
		return row == ((UICollection)table.getModel()).getRealRowCount();
	}
	
	protected Font getFontFooter() {
		
		return new Font("Verdana", Font.BOLD, 11);
	}

	protected Color getBackgroundFooter() {
		
		return new Color(0xEEEEEE);
	}
	
	protected Color getForegroundFooter() {
		
		return Color.BLACK;
	}
}