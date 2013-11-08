package com.migestion.swing.view.frames;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.migestion.swing.controller.IControllerList;
import com.migestion.swing.i18n.buttons.ButtonImagesBundle;
import com.migestion.swing.i18n.buttons.ButtonLabelsBundle;
import com.migestion.swing.search.criteria.UICriteria;
import com.migestion.swing.swingx.custom.StackedBox;
import com.migestion.swing.view.dialogs.DialogMessage;
import com.migestion.swing.view.dialogs.ICriteriaPanel;
import com.migestion.swing.view.exceptions.ViewException;

/**
 * ventana para las operaciones crud de una entidad.
 * 
 * @author bernardo
 *
 */
public abstract class CRUDFrame<T> extends GenericInternalFrameList<T>{

	protected ICriteriaPanel uiCriteriaPanel;
	
	/**
	 * panel para el criterio de búsqueda.
	 * @return
	 */
	public abstract ICriteriaPanel buildUICriteriaPanel();
	
	/**
	 * setea los links que utilizará la ventana.
	 * @param frame
	 */
	public abstract void initLinks();
	

	/**
	 * título para el menú.
	 * @return
	 */
	public abstract String getMenuTitle();

	/**
	 * ícono para la ventana.
	 * @return
	 */
	public abstract URL getIcon();

	/**
	 * footer de la ventana.
	 * @return
	 */
	public abstract JComponent getFooter(); 
	

	public CRUDFrame(String title, IControllerList controller) {
	
		super( title );
		
		//this.icrudFrame = icrudFrame;
		uiCriteriaPanel = buildUICriteriaPanel();
		
		init(title, controller);
		
		menuDefault.setText( getMenuTitle() );
		
		//inicializamos los links.
		initLinks( );
		
		setFrameIcon( new ImageIcon( getIcon() ) );
		
		
		
	}
	
	
	
	@Override
	protected JPanel getNavigation() {
		// criteria de búsqueda.
		
		JPanel buscar = new JPanel();
		Container criteriaPanel = uiCriteriaPanel.getPanel();
		buscar.setLayout(new FlowLayout());
		buscar.add( criteriaPanel  );
		buscar.setPreferredSize( criteriaPanel.getPreferredSize() );
		buscar.setMinimumSize( criteriaPanel.getMinimumSize() );
		
		JButton btnOk = new JButton(ButtonLabelsBundle.btn_Find);
		btnOk.setToolTipText(ButtonLabelsBundle.btn_Find_ToolTipText);
		btnOk.setIcon(new ImageIcon(ButtonImagesBundle.btn_Find));
		btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnOk.setMnemonic(java.awt.event.KeyEvent.VK_ENTER);
		btnOk.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doFind();
			}
		});
		
		//botón para buscar.
		JPanel boton = new JPanel();
		boton.add(btnOk);
		boton.setMaximumSize(new Dimension(100, 35));
		boton.setPreferredSize(new Dimension(100, 35));
		boton.setMinimumSize(new Dimension(100, 35));

		JPanel panelCriteria = new JPanel();
		panelCriteria.setLayout(new BorderLayout());
		panelCriteria.add(buscar, BorderLayout.NORTH);
		panelCriteria.add(boton, BorderLayout.SOUTH);
		
		// panel para el header.
		JPanel panelHeader = new JPanel(new BorderLayout());
		panelHeader.add(super.getNavigation(), BorderLayout.SOUTH);
		panelHeader.add(panelCriteria, BorderLayout.NORTH);		
		StackedBox box = new StackedBox();   
		box.addBox( ButtonLabelsBundle.btn_Find , panelCriteria, true);
		panelHeader.add(box, BorderLayout.NORTH);

		return panelHeader;
	}

	private void doFind() {
		try {
			//criteriaCreated( icrudFrame.getUICriteria());
			criteriaCreated( uiCriteriaPanel.getCriteria() );
		} catch (ViewException e) {
			DialogMessage.showInformationMessage( getTitle(), e.getMessage());
		}		
	}

	public void open(){
		super.open();
		doFind();
	}	
	
	



	public void setUICriteria(UICriteria criteria) {
		uiCriteriaPanel.setCriteria(criteria);
		doFind();
	}

}
