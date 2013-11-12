package com.migestion.swing.view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.migestion.swing.context.IContextListener;
import com.migestion.swing.controller.IControllerList;
import com.migestion.swing.controller.exception.ControllerException;
import com.migestion.swing.factories.JLabelFactory;
import com.migestion.swing.i18n.buttons.ButtonImagesBundle;
import com.migestion.swing.i18n.buttons.ButtonLabelsBundle;
import com.migestion.swing.model.UICollection;
import com.migestion.swing.navigation.LinkOpenDialog;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.navigation.interfaces.ILinkWindowFindObject;
import com.migestion.swing.search.criteria.UICriteria;
import com.migestion.swing.view.exceptions.ViewException;
import com.migestion.swing.view.renderers.ElementTableRenderer;

/**
 * Di�logo que ser� utilizado como base para filtrar
 * y encontrar un objeto en una lista de elementos.
 * 
 * @author Bernardo Iribarne.
 *
 */
public abstract class DialogFindObject<T> extends DialogOkCancel implements ILinkWindowFindObject,
																		 TableModelListener,
																		 ListSelectionListener,
																		 IContextListener<T>{

	//objeto encontrado.
	private Object objectFinded;
	
	/*
	 * objetos necesarios para visualizar la tabla 
	 * con objetos del modelo
	 */
	//tabla para mostrar los objetos
	protected JTable elementsTable;
	//modelo de la tabla a ser mostrada
	protected UICollection elementTableModel;
	//scroll para la tabla
	protected JScrollPane scroll;
	//criteria utilizado para filtrar el listado
	protected UICriteria criteria;

	//label del header
	JLabel labelHeader = new JLabel(); 

	//para la paginaci�n del listado.
	protected int rowCount=10;
	protected int offset;
	protected int page;
	protected int pageSize;
	protected int totalSize;
	
	//botones de navegaci�n para la paginaci�n.
	JButton btnFirst;
	JButton btnPrevious;
	JLabel labelPage;
	JButton btnNext;
	JButton btnLast;
	JPanel navigationPanel;
	JLabel labelRows;
	JButton btnMinusRow;
	JButton btnAddRow;
	
	private LinkOpenDialog linkAdd;
	
	//------------------
	// CONSTRUCTORES
	//------------------
	
	/**
	 * @wbp.parser.constructor
	 * 
	 */
	public DialogFindObject(String title) {
		super(title);
		this.rowCount = 10;
		this.linkAdd = getLinkAdd();
		// Close the dialog when Esc is pressed
        String agregarName = "agregar";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), agregarName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(agregarName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doAdd();
                
            }
			
        });
		
	}
	
	/**
	 * 
	 * @param title
	 * @param pathImage
	 */
	public DialogFindObject(String title, String pathImage) {
		super(title, pathImage);
		this.rowCount = 10;
		this.linkAdd = getLinkAdd();
		// Close the dialog when Esc is pressed
        String agregarName = "agregar";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), agregarName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(agregarName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doAdd();
                
            }
			
        });
		
	}
	
	/**
	 * se setea el renderer para la clase indicada.
	 * @param elementTableRenderer
	 */
	public void setElementTableRenderer(Class classToRenderer, ElementTableRenderer elementTableRenderer) {
		this.elementsTable.setDefaultRenderer(classToRenderer, elementTableRenderer);
	}
	
	/**
	 * se setea el ancho de la columna indicada.
	 * 
	 * @param columnIndex
	 * @param minWidth
	 * @param preferredWith
	 */
	public void setColumnWidth(int columnIndex, int minWidth, int preferredWith){	
		TableColumn column = elementsTable.getColumnModel().getColumn(columnIndex);
		column.setMinWidth(minWidth);
		column.setPreferredWidth(preferredWith);
	}
	

	/**
	 * m�todo invocado al producirse un cambio
	 * en el modelo de la tabla.
	 */
	public void tableChanged(TableModelEvent arg0) {
		//mostramos la descripci�n del criteria, si tiene
		if(criteria!=null)
			labelHeader.setText(criteria.getDescription());
		else{
			labelHeader.setText(getTitle());
		}
	}
	
	/*
	 *  (non-Javadoc)
	 * @see com.ostrich.commonui.navigation.interfaces.ILinkWindowFindObjects#getObjectFinded()
	 */
	public Object getObjectFinded(){
		return objectFinded;
	}

	
	protected void doOk() {
		Object aux = objectFinded;
		this.elementTableModel.setElements(new Vector());
		objectFinded = aux;
		navigationPanel.setVisible(false);
		this.criteria=null;
		this.labelHeader.setText(getTitle());		
		this.accepted = true;
		this.dispose();
		
	}

	/**
	 * retorna el panel donde se mostrar� la informaci�n deseada.
	 * cada subclase definir� qu� informaci�n se mostrar�.
	 */
	protected JPanel createInfoPanel(){
		//inicializamos la tabla
		initializeTable();
		//creamos el panel.
		JPanel panelMain = new JPanel(new BorderLayout());		
		//panel para ingresar el criteria.
		JComponent header = getHeader();
		panelMain.add(header, BorderLayout.NORTH);
		//panel para visualizar la tabla.
		JScrollPane scroll = getScrollWithTable(); 
		panelMain.add(scroll, BorderLayout.CENTER);

		
		panelMain.add(getFooter(), BorderLayout.SOUTH);
		
		
		//seteamos el tama�o
		double height = header.getPreferredSize().getHeight();
		height += scroll.getPreferredSize().getHeight();
		double width = scroll.getPreferredSize().getWidth();
		panelMain.setPreferredSize(new Dimension((int)width,(int)height));
		
		return panelMain;
	}
	
	private Component getFooter() {
		
		if( getLinkAdd() != null )
		
			return new JLabel("F6 - agregar");
		else
			return new JLabel("");	
	}

	/*
	 * retorna el scroll con la tabla que contiene
	 * la colecci�n a mostrar.
	 */
	private JScrollPane getScrollWithTable(){
		//creamos el scroll de la tabla
		scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setViewportView(elementsTable);
		scroll.setPreferredSize(new Dimension(800,450));
		return scroll;
	}

	/**
	 * retorna el panel de header donde se mostrar� 
	 * la descripci�n del listado y para cargar el criteria.
	 */
	protected JComponent getHeader(){
		JPanel header = new JPanel(new BorderLayout());		
		
		//panel para el criteria y el bot�n para buscar.
		JPanel criteriaAndBtn = new JPanel(new BorderLayout());
		criteriaAndBtn.add(createCriteriaPanel(), BorderLayout.NORTH);
		criteriaAndBtn.add(criteriaBtn(), BorderLayout.SOUTH);
		
		header.add(criteriaAndBtn, BorderLayout.NORTH);
		
		labelHeader = JLabelFactory.getJLabel(elementTableModel.getDescription(), 500, SwingConstants.CENTER);
		labelHeader.setOpaque(true);
		labelHeader.setBackground(getHeaderBackground());
		labelHeader.setForeground(getHeaderForeground());
		labelHeader.setFont(new Font("Verdana", 1 , 12));
		labelHeader.setPreferredSize(new Dimension(500,25));
		header.add(labelHeader, BorderLayout.CENTER);
		//agregamos la navegaci�n.
		navigationPanel = getNavigation();
		JPanel contNav = new JPanel(new BorderLayout());
		contNav.add(navigationPanel, BorderLayout.WEST);		
		header.add(contNav, BorderLayout.SOUTH);		
		header.setOpaque(false);
		return header;
		
	}
	
	private JComponent criteriaBtn() {
		JPanel panel = new JPanel(new GridLayout());
		
		JLabel labelEspacio = new JLabel();
		labelEspacio.setPreferredSize(new Dimension(98,25));		
		panel.add(labelEspacio);		

		JButton btn = new JButton(ButtonLabelsBundle.btn_Find);
		btn.setMinimumSize(new Dimension(50, 23));
		btn.setToolTipText(ButtonLabelsBundle.btn_Find_ToolTipText);
		btn.setIcon(new ImageIcon(ButtonImagesBundle.btn_Find));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setMnemonic(java.awt.event.KeyEvent.VK_ENTER);
		btn.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            doSearch();
	          }

	        });
		panel.add(btn);
		labelEspacio = new JLabel();
		labelEspacio.setPreferredSize(new Dimension(98,25));		
		panel.add(labelEspacio);
		
		return panel;
	}

	private void doSearch() {
		try {
			this.criteria = getCriteriaFromUI();

			if(criteria.isPaginable()){
				this.criteria.setPaginable(true);
				this.offset=0;
				this.criteria.setOffset(offset);
				this.criteria.setRowCount(rowCount);
				page=1;			
				navigationPanel.setVisible(true);
			}else{
				navigationPanel.setVisible(false);
			}
			//aplicamos el criteria.
			apply(criteria);
			configurarNavegacion();
		
		} catch (ViewException e) {
			DialogMessage.showErrorMessage(getTitle(),e.getMessage());
		}
		
	}
	

	/**
	 * aplica el criteria y actualiza el listado.
	 * @param criteria
	 */
	private void apply(UICriteria criteria){
		//se aplica el criterio de b�squeda y se actualiza
		//el listado.			
		refreshTable();
	}
	

	/**
	 * retorna el color del fondo del header.
	 * @return
	 */
	public Color getHeaderBackground(){
		return Color.DARK_GRAY;
	}
	
	/**
	 * retorna el color de la fuente del header.
	 * @return
	 */
	public Color getHeaderForeground(){
		return Color.WHITE;
	}

	/**
	 * lo utilizaremos cuando cambie la selecci�n en el listado.
	 */
	public void valueChanged(ListSelectionEvent e) {
		// extra messages.
		if (e.getValueIsAdjusting()) {
			return;
		}

		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (!lsm.isSelectionEmpty()) {
			//tomo el elemento actual.
			this.objectFinded = elementTableModel.getElement(elementsTable.getSelectedRow());
		}else{
			this.objectFinded = null;
		}
				
	}

	/*
	 * se inicializa la tabla donde se visualizar�n
	 * los objetos.
	 */
	private void initializeTable(){

		//inicializamos el modelo de la tabla
			
			//le pedimos al controller los elementos en caso de que
			//no se haya indicado una colecci�n inicial.
			if(elementTableModel==null)
				elementTableModel = getUICollectionDefault();
			
			elementTableModel.addTableModelListener(this);

			//inicializamos la tabla
			elementsTable = new JTable(elementTableModel);
			elementsTable.setAutoscrolls(true);
			elementsTable.setSelectionMode(0);
			elementsTable.setRowHeight(30);		
			elementsTable.getSelectionModel().addListSelectionListener(this);
			elementTableModel.addTableModelListener(elementsTable);
			
			//inicializamos el renderer (hay que setear el renderer para
			//cada una de las clases de las propiedades del objeto).
			TableCellRenderer renderer = elementTableModel.getTableCellRenderer();
			TableCellRenderer headerRenderer = elementTableModel.getTableHeaderRenderer();
			for(int columnIndex=0; columnIndex < elementTableModel.getColumnCount(); columnIndex++){
				elementsTable.setDefaultRenderer(elementTableModel.getColumnClass(columnIndex), renderer);
				elementsTable.getColumnModel().getColumn(columnIndex).setHeaderRenderer( headerRenderer );
			}
			

	}

	/*
	 * retorna los botones de navegaci�n de la tabla (para la paginaci�n).
	 */
	private JPanel getNavigation(){
		JPanel panel = new JPanel(new GridLayout());
		panel.setMinimumSize(new Dimension(150, 25));
		
		//agregamos los botones para la navegaci�n.
		JPanel panelBtn = new JPanel(new GridLayout());
		btnFirst = new JButton();		
		btnFirst.setPreferredSize(new Dimension(23,23));		
		btnFirst.setIcon(new ImageIcon(ButtonImagesBundle.btn_First));
		btnFirst.setToolTipText(ButtonLabelsBundle.btn_First_ToolTipText);
		btnFirst.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnFirst.setEnabled(false);
		btnFirst.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	           first();
	          }
	        });

		
		btnPrevious = new JButton();
		btnPrevious.setPreferredSize(new Dimension(23,23));
		btnPrevious.setIcon(new ImageIcon(ButtonImagesBundle.btn_Previous));
		btnPrevious.setToolTipText(ButtonLabelsBundle.btn_Previous_ToolTipText);
		btnPrevious.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPrevious.setEnabled(false);
		btnPrevious.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            previous();
	          }
	        });

		page = 1;
		labelPage = new JLabel("1");
		Font font = new Font("Arial", 0, 9);
		labelPage.setFont(font);
		labelPage.setHorizontalAlignment(SwingConstants.CENTER);
		labelPage.setPreferredSize(new Dimension(40,23));
		labelPage.setMinimumSize(new Dimension(40,23));
		
		btnNext = new JButton();
		btnNext.setPreferredSize(new Dimension(23,23));
		btnNext.setToolTipText(ButtonLabelsBundle.btn_Next_ToolTipText);
		btnNext.setIcon(new ImageIcon(ButtonImagesBundle.btn_Next));
		btnNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNext.setEnabled(false);
		btnNext.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	           next();
	          }
	        });
		
		btnLast = new JButton();
		btnLast.setPreferredSize(new Dimension(23,23));
		btnLast.setIcon(new ImageIcon(ButtonImagesBundle.btn_Last));
		btnLast.setToolTipText(ButtonLabelsBundle.btn_Last_ToolTipText);
		btnLast.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLast.setEnabled(false);
		btnLast.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	           last();
	          }
	        });
		
		panelBtn.add(btnFirst);
		panelBtn.add(btnPrevious);
		panelBtn.add(labelPage);
		panelBtn.add(btnNext);
		panelBtn.add(btnLast);
		panel.add(panelBtn);
		
		
		//agregamos los botones para setear el n�mero de filas que se desean visualizar.
		JPanel panelSetRows = new JPanel(new GridLayout());
		btnMinusRow = new JButton();
		btnMinusRow.setPreferredSize(new Dimension(23,23));
		btnMinusRow.setIcon(new ImageIcon(ButtonImagesBundle.btn_Down));
		btnMinusRow.setToolTipText(ButtonLabelsBundle.btn_MinusRow_ToolTipText);
		btnMinusRow.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnMinusRow.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	           minusRow();
	          }
	        });
		btnAddRow = new JButton();
		btnAddRow.setPreferredSize(new Dimension(23,23));
		btnAddRow.setIcon(new ImageIcon(ButtonImagesBundle.btn_Up));
		btnAddRow.setToolTipText(ButtonLabelsBundle.btn_AddRow_ToolTipText);
		btnAddRow.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAddRow.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	           addRow();
	          }
	        });
		
		labelRows = new JLabel(String.valueOf(rowCount));
		labelRows.setFont(font);
		labelRows.setHorizontalAlignment(SwingConstants.CENTER);
		labelRows.setPreferredSize(new Dimension(23,23));
		labelRows.setMinimumSize(new Dimension(23,23));
		
		JLabel labelEspacio = new JLabel();
		labelEspacio.setPreferredSize(new Dimension(23,25));		
		panelSetRows.add(labelEspacio);		

		panelSetRows.add(btnMinusRow);
		panelSetRows.add(labelRows);
		panelSetRows.add(btnAddRow);

		labelEspacio = new JLabel();
		labelEspacio.setPreferredSize(new Dimension(23,25));		
		panelSetRows.add(labelEspacio);		
		labelEspacio = new JLabel();
		labelEspacio.setPreferredSize(new Dimension(23,25));		
		panelSetRows.add(labelEspacio);		
		labelEspacio = new JLabel();
		labelEspacio.setPreferredSize(new Dimension(23,25));		
		panelSetRows.add(labelEspacio);		
		labelEspacio = new JLabel();
		labelEspacio.setPreferredSize(new Dimension(23,25));		
		panelSetRows.add(labelEspacio);		
		labelEspacio = new JLabel();
		labelEspacio.setPreferredSize(new Dimension(23,25));		
		panelSetRows.add(labelEspacio);		
		labelEspacio = new JLabel();
		labelEspacio.setPreferredSize(new Dimension(23,25));		
		panelSetRows.add(labelEspacio);		
		labelEspacio = new JLabel();
		labelEspacio.setPreferredSize(new Dimension(23,25));		
		panelSetRows.add(labelEspacio);		
		
		panel.add(panelSetRows);
		panel.setVisible(false);
		
		return panel;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}


	private void configurarNavegacion(){
		btnNext.setEnabled(page<pageSize);
		btnLast.setEnabled(page<pageSize);
		btnPrevious.setEnabled(page>1);
		btnFirst.setEnabled(page>1);
		labelPage.setText(page + " de " + pageSize);
	}
	
	// M�todos de la navegaci�n.
	private void first(){
		this.page=1;
		updateTable(0);		
		configurarNavegacion();
		
	}
	
	private void previous(){
		page--;
		this.offset -= rowCount;
		if(offset<0)
			offset=0;
		updateTable(offset);
		configurarNavegacion();
		
	}
	private void next(){
		page++;
		this.offset += rowCount;
		updateTable(offset);
		configurarNavegacion();
		
	}
	
	private void last(){
		page = pageSize;
		this.offset = ((pageSize-1)*rowCount)+1;
		
		updateTable(offset);
		configurarNavegacion();		
	}

	private void minusRow(){
		if(rowCount>1){
			rowCount -= 1;
			labelRows.setText(String.valueOf(rowCount));
			first();
		}
	}
	
	private void addRow(){
		if(rowCount<50){
			rowCount += 1;
			labelRows.setText(String.valueOf(rowCount));
			first();
		}
	}
		
	/**
	 * se actualiza el listado, modificando el offset
	 * @param offset
	 */
	public void updateTable(int offset){
		this.offset = offset;
		if(this.criteria!=null){
			this.criteria.setOffset(this.offset);
			this.criteria.setRowCount(this.rowCount);			
		}
		refreshTableWithoutFindTotal();
	}
	
	/**
	 * se cierra la ventana
	 * (implementaci�n de interface ILinkWindowClose)
	 */
	public void close() {
		this.elementTableModel.setElements(new Vector());
		navigationPanel.setVisible(false);
		this.criteria=null;
		this.labelHeader.setText(getTitle());
		setVisible(false);
	}
	
	//refresca el listado.
	public void refreshTable(){
		
		try {
			//seteamos el total de elementos.
			setTotalElementSize();
			//seteamos los elementos de la tabla.
			setElements();			
		} catch (ControllerException e) {
			//se informa del error al usuario.
			DialogMessage.showErrorMessage(getTitle(), e.getMessage());			
		}
	}	

	private void setTotalElementSize() throws ControllerException{
		if(criteria!=null)
			totalSize = getController().totalSize(criteria);
		else
			totalSize = getController().totalSize();
	}
	
	private void setElements() throws ControllerException{
		if(criteria!=null){
			this.elementTableModel.setElements(this.getController().list(criteria).getElements(), criteria.getDescription());				
		}else{
			this.elementTableModel.setElements(this.getController().list().getElements(), this.getTitle());
		}		
		pageSize = totalSize / rowCount;
		pageSize += (totalSize % rowCount > 0)?1:0;
	}
	
	/*
	 * para usarlo desde los botones de navegaci�n.
	 */
	public void refreshTableWithoutFindTotal(){
		//actualizamos la vista de la tabla pero a trav�s de los botones de navegaci�n,
		//es por eso que no vamos a buscar el total de registros.
		try {
			//seteamos los elementos de la tabla.
			setElements();
			
		} catch (ControllerException e) {
			//se informa del error al usuario.
			DialogMessage.showErrorMessage(getTitle(), e.getMessage());			
		}
	}	
	
	/**
	 * crea el criteria con la informaci�n cargada desde
	 * la ui.
	 * 
	 * @return
	 */
	protected abstract UICriteria getCriteriaFromUI() throws ViewException;
	
	/**
	 * valida la informaci�n cargada por el usuario. 
	 * 
	 * @return
	 */
	protected abstract void validateInput() throws ViewException;
	
	/**
	 * retorna el panel con los campos de entrada para generar
	 * el criteria.
	 * cada subclase definir� qu� campos deben llenarse.
	 * 
	 * @return
	 */
	protected abstract JPanel createCriteriaPanel();

	/**
	 * retorna el controlador para listar los elementos.
	 * @return
	 */
	protected abstract IControllerList getController();

	/**
	 * retorna el el table model por default a visualizar.
	 * (para la primera, que muestre vac�o)
	 * @return
	 */
	protected abstract UICollection getUICollectionDefault();

	/**
	 * retorna el controlador para agregar un elemento.
	 * @return
	 */
	protected abstract LinkOpenDialog getLinkAdd();
	
	
	/*
	 *  (non-Javadoc)
	 * @see com.ostrich.commonui.view.dialogs.DialogOkCancel#doCancel()
	 */
	protected void doCancel() {
		this.elementTableModel.setElements(new Vector());
		navigationPanel.setVisible(false);
		this.criteria=null;
		this.labelHeader.setText(getTitle());
		super.doCancel();
	}
	
	/**
	 * se abre el di�logo centrado en la pantalla.
	 */
	public void open() {
		doSearch();
		super.open();
	}


	protected void doAdd() {
		
		if( this.linkAdd != null)
			try {
				
				this.linkAdd.doExecute();
				
			} catch (LinkException e) {
				
				DialogMessage.showErrorMessage(this.linkAdd.getDescription(),e.getMessage());
			}
	}

	public void objectCreated(T objectCreated) {
		if( this.linkAdd != null){
			objectFinded = objectCreated;
			doOk();	
		}
		
		
	}


	public void objectDeleted(T objectDeleted) {
		doSearch();
	}


	public void objectUpdated(T objectUpdated) {
		doSearch();
	}

}
