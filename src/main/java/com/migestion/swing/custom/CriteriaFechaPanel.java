package com.migestion.swing.custom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import com.migestion.swing.i18n.panels.CriteriaFechaPanelMessagesBundle;
import com.toedter.calendar.JDateChooser;

public class CriteriaFechaPanel extends JPanel{

	private final ButtonGroup buttonGroup = new ButtonGroup();

	private JRadioButton rdbtnRango ;
	private JRadioButton rdbtnSemanaActual ;
	private JRadioButton rdbtnMesActual ;
	private JRadioButton rdbtnAnioActual ;
	private JDateChooser pickerDesde, pickerHasta;
	
	private PropertyChangeSupport propertySupport;
	
	
	public CriteriaFechaPanel(){
		
		super();
		
		String formatoFecha = "dd/MM/yyyy";
		
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setBounds(51, 35, 360, 67);
		
		this.setLayout(null);
		
		pickerDesde = new JDateChooser();
		pickerDesde.setBounds(110, 12, 112, 19);
		this.add(pickerDesde);
		pickerDesde.setDateFormatString( formatoFecha );
		
		pickerHasta = new JDateChooser();
		pickerHasta.setBounds(234, 12, 112, 19);
		this.add(pickerHasta);
		pickerHasta.setDateFormatString(formatoFecha);
		
		
		rdbtnRango = new JRadioButton( CriteriaFechaPanelMessagesBundle.criteriaFecha_rango );
		buttonGroup.add(rdbtnRango);
		rdbtnRango.setFont(new Font("Dialog", Font.PLAIN, 11));
		rdbtnRango.setBounds(8, 12, 79, 23);
		this.add(rdbtnRango);
		
		
		rdbtnSemanaActual = new JRadioButton(CriteriaFechaPanelMessagesBundle.criteriaFecha_semana_actual);
		buttonGroup.add(rdbtnSemanaActual);
		rdbtnSemanaActual.setFont(new Font("Dialog", Font.PLAIN, 11));
		rdbtnSemanaActual.setBounds(8, 36, 103, 23);
		this.add(rdbtnSemanaActual);
		
		rdbtnMesActual = new JRadioButton(CriteriaFechaPanelMessagesBundle.criteriaFecha_mes_actual);
		buttonGroup.add(rdbtnMesActual);
		rdbtnMesActual.setFont(new Font("Dialog", Font.PLAIN, 11));
		rdbtnMesActual.setBounds(120, 36, 103, 23);
		this.add(rdbtnMesActual);
		
		rdbtnAnioActual = new JRadioButton(CriteriaFechaPanelMessagesBundle.criteriaFecha_anio_actual);
		buttonGroup.add(rdbtnAnioActual);
		rdbtnAnioActual.setFont(new Font("Dialog", Font.PLAIN, 11));
		rdbtnAnioActual.setBounds(229, 35, 103, 23);
		this.add(rdbtnAnioActual);
		
		propertySupport = new PropertyChangeSupport(this);
	}
	
	public CriteriaFechaPanel(String formatoFecha){
		
		super();
		
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setBounds(51, 35, 360, 67);
		
		this.setLayout(null);
		
		pickerDesde = new JDateChooser();
		pickerDesde.setBounds(110, 12, 112, 19);
		this.add(pickerDesde);
		pickerDesde.setDateFormatString( formatoFecha );
		
		pickerHasta = new JDateChooser();
		pickerHasta.setBounds(234, 12, 112, 19);
		this.add(pickerHasta);
		pickerHasta.setDateFormatString(formatoFecha);
		
		
		rdbtnRango = new JRadioButton( CriteriaFechaPanelMessagesBundle.criteriaFecha_rango );
		buttonGroup.add(rdbtnRango);
		rdbtnRango.setFont(new Font("Dialog", Font.PLAIN, 11));
		rdbtnRango.setBounds(8, 12, 79, 23);
		this.add(rdbtnRango);
		
		
		rdbtnSemanaActual = new JRadioButton(CriteriaFechaPanelMessagesBundle.criteriaFecha_semana_actual);
		buttonGroup.add(rdbtnSemanaActual);
		rdbtnSemanaActual.setFont(new Font("Dialog", Font.PLAIN, 11));
		rdbtnSemanaActual.setBounds(8, 36, 103, 23);
		this.add(rdbtnSemanaActual);
		
		rdbtnMesActual = new JRadioButton(CriteriaFechaPanelMessagesBundle.criteriaFecha_mes_actual);
		buttonGroup.add(rdbtnMesActual);
		rdbtnMesActual.setFont(new Font("Dialog", Font.PLAIN, 11));
		rdbtnMesActual.setBounds(120, 36, 103, 23);
		this.add(rdbtnMesActual);
		
		rdbtnAnioActual = new JRadioButton(CriteriaFechaPanelMessagesBundle.criteriaFecha_anio_actual);
		buttonGroup.add(rdbtnAnioActual);
		rdbtnAnioActual.setFont(new Font("Dialog", Font.PLAIN, 11));
		rdbtnAnioActual.setBounds(229, 35, 103, 23);
		this.add(rdbtnAnioActual);
		
		propertySupport = new PropertyChangeSupport(this);
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(300, 250);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
      //  propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
     //   propertySupport.removePropertyChangeListener(listener);
    }
	
	
	public void setMesActual(){
		rdbtnMesActual.setSelected(true);
		pickerDesde.setDate( null );
		pickerHasta.setDate( null );
	}
	
	public void setSemanaActual(){
		rdbtnSemanaActual.setSelected(true);
		pickerDesde.setDate( null );
		pickerHasta.setDate( null );
	}
	
	public void setAnioActual(){
		rdbtnAnioActual.setSelected(true);
		pickerDesde.setDate( null );
		pickerHasta.setDate( null );
	}
	
	public void setFechaDesde(Date fecha){
		pickerDesde.setDate( fecha );
	}
	
	public void setFechaHasta(Date fecha){
		pickerHasta.setDate( fecha );
	}
	
	public Date[] getFechas(){
		
		Date desde = null;
		Date hasta = null;
		
				
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
			
		if(rdbtnSemanaActual.isSelected()){
			calendar1.set( Calendar.DAY_OF_WEEK, 1);
			calendar2.set( Calendar.DAY_OF_WEEK, calendar2.getActualMaximum(Calendar.DAY_OF_WEEK));
		}
			
		if(rdbtnMesActual.isSelected()){
			calendar1.set( Calendar.DAY_OF_MONTH, 1);
			calendar2.set( Calendar.DAY_OF_MONTH, calendar2.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
			
		if(rdbtnAnioActual.isSelected()){
			calendar1.set( Calendar.DAY_OF_YEAR, 1);
			calendar2.set( Calendar.DAY_OF_YEAR, calendar2.getActualMaximum(Calendar.DAY_OF_YEAR));
		}
		desde = calendar1.getTime();
		hasta = calendar2.getTime();
			
		if(rdbtnRango.isSelected()){
			desde = pickerDesde.getDate();
			hasta = pickerHasta.getDate();
		}
		
		Date[] fechas = {desde, hasta}; 
		return fechas;
	}
}
