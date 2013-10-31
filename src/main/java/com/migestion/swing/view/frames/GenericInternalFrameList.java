package com.migestion.swing.view.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.migestion.swing.controller.IControllerList;
import com.migestion.swing.controller.exception.ControllerException;
import com.migestion.swing.factories.JLabelFactory;
import com.migestion.swing.factories.MenuFactory;
import com.migestion.swing.i18n.buttons.ButtonImagesBundle;
import com.migestion.swing.i18n.buttons.ButtonLabelsBundle;
import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.model.UICollection;
import com.migestion.swing.navigation.Link;
import com.migestion.swing.navigation.LinkAddObject;
import com.migestion.swing.navigation.LinkCloseWindow;
import com.migestion.swing.navigation.LinkCreateCriteria;
import com.migestion.swing.navigation.LinkDeleteObject;
import com.migestion.swing.navigation.LinkExportCollectionToExcel;
import com.migestion.swing.navigation.LinkPrintCollection;
import com.migestion.swing.navigation.LinkPrintObject;
import com.migestion.swing.navigation.LinkUpdateObject;
import com.migestion.swing.navigation.LinkViewObject;
import com.migestion.swing.navigation.interfaces.ILinkPrintCollection;
import com.migestion.swing.navigation.interfaces.ILinkPrintObject;
import com.migestion.swing.navigation.interfaces.ILinkWindowClose;
import com.migestion.swing.navigation.interfaces.ILinkWindowList;
import com.migestion.swing.navigation.listeners.LinkAddListener;
import com.migestion.swing.navigation.listeners.LinkCreateCriteriaListener;
import com.migestion.swing.navigation.listeners.LinkDeleteListener;
import com.migestion.swing.navigation.listeners.LinkUpdateListener;
import com.migestion.swing.search.criteria.UICriteria;
import com.migestion.swing.view.dialogs.DialogMessage;
import com.migestion.swing.view.renderers.ElementTableRenderer;

/**
 * Ventana para mostrar una colecci�n de objetos comunes en una
 * tabla (un listado).
 * Se podr�n setearle los links para las operaciones b�sicas sobre los objetos
 * que son:
 *      Agregar un nuevo objeto
 *      Modificar el objeto seleccionado
 *      Eliminar el objeto seleccionado
 *      Visualizar el objeto seleccionado
 *      Imprimir el listado
 *      
 * 
 * @author Bernardo Iribarne {Ostrich}
 * @version 1.0
 */
public class GenericInternalFrameList extends JInternalFrame implements TableModelListener,
																		ListSelectionListener, 
																		LinkAddListener, 
																		LinkUpdateListener,
																		LinkDeleteListener, 
																		LinkCreateCriteriaListener, 
																		ILinkWindowClose,
																		ILinkWindowList, 
																		ILinkPrintCollection,
																		ILinkPrintObject{

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
	//controlador que se encargar� de obtener el listado
	protected IControllerList controller;
	//criteria utilizado para filtrar el listado
	protected UICriteria criteria;
	//listeners a los cuales se les informar� cuando
	//haya cambios en el listado.
	protected List elementsListeners;
	/*
	 * barra de men� por default.
	 * el men� por default contar� con las operaciones:
	 * 			- Add
	 * 			- Update
	 * 			- Delete
	 * 			- View
	 * 			- Print
	 * 			- Close
	 */
	protected JMenuBar menuBar;
	protected JMenu menuDefault;
	protected JMenu menuFilters;
	protected JPopupMenu rightClickPopup;
	/*
	protected JMenuItem menuItemAdd;		
	protected JMenuItem menuItemUpdate;
	protected JMenuItem menuItemDelete;	
	protected JMenuItem menuItemView;
	protected JMenuItem menuItemPrint;
	protected JMenuItem menuItemPrintObject;
	protected JMenuItem menuItemExcel;
	protected JMenuItem menuItemClose;
    */
	/*
	 * links para llamar a las distintas ventanas.
	 * estar�n asociados a los botones, men�es, etc.
	 */
	protected LinkAddObject linkAdd;
	protected LinkUpdateObject linkUpdate;
	protected LinkDeleteObject linkDelete;
	protected LinkViewObject linkView;
	protected LinkPrintCollection linkPrint;
	protected LinkPrintObject linkPrintObject;
	protected LinkExportCollectionToExcel linkExcel;
	protected LinkCloseWindow linkClose;
	protected LinkCreateCriteria linkCreateCriteria;
	
	//label del header
	JLabel labelHeader = new JLabel(); 
	JLabel labelFooter = new JLabel();
	
	//contenedor de la ventana.
	protected JFrameContainer container;
		
	
	//para la paginaci�n del listado.
	protected int rowCount;
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
	protected JPanel navigationPanel;
	JLabel labelRows;
	JButton btnMinusRow;
	JButton btnAddRow;
	
	//----------------
	//CONSTRUCTORES	
	//----------------
	public GenericInternalFrameList( String title ) {
		super(title, true, false, true, true);
	}
	
	/**
	 * 
	 */
	public void init(String title, IControllerList controller){		
		
		this.setFrameIcon(new ImageIcon(LinkImagesBundle.link_List));
		this.controller = controller;
		this.rowCount = 10;
		//creamos la UI
		createUI();
		
	}

	/**
	 * se construye con una colecci�n inicial, no utiliza el controller
	 * para realizar el primer listado.
	 * (pensado para cuando deseamos abrir la ventana sin mostrar
	 * nada en el listado ya que requiere de criterios de b�squeda).
	 * 
	 * @param title
	 * @param controller
	 * @param initCollection
	 */
	public void init(String title, IControllerList controller, UICollection initCollection){		
		//super(title, true, false, true, true);
		this.setFrameIcon(new ImageIcon(LinkImagesBundle.link_List));
		this.controller = controller;
		this.elementTableModel = initCollection;
		this.rowCount = 10;
		//creamos la UI
		createUI();
	}

	
	
	/**
	 * se abre la ventana centrada en la pantalla.
	 */
	public void open() {
		show();
		setVisible(true);
		
		moveToFront();
		
		try {
			setSelected(true);
			setIcon(false);
			setMaximum(true);
		} catch (PropertyVetoException e) {			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * se agrega a un contenedor.
	 */
	public void addToJFrameContainer(JFrameContainer container) {
		 container.addToDesktop(this);		
		 this.container = container;
	}
	
	/**
	 * retorna su contenedor
	 * @return
	 */
	public JFrameContainer getJFrameContainer(){
		return this.container;
	}
	

	/**
	 * se setea el color de fondo del men�.
	 * @param color
	 */
	public void setMenuBackground(Color color){
		menuBar.setBackground(color);
		menuDefault.setBackground(color);
		/*
		menuItemAdd.setBackground(color);
		menuItemUpdate.setBackground(color);
		menuItemDelete.setBackground(color);
		menuItemView.setBackground(color);
		menuItemPrint.setBackground(color);
		menuItemPrintObject.setBackground(color);
		menuItemExcel.setBackground(color);
		menuItemClose.setBackground(color);		
		*/
		menuFilters.setBackground(color);
	}
	
	/**
	 * se setea el link para agregar los objetos. 
	 */
	public void setLinkAdd(LinkAddObject link, int index){
		/*
		//se inicializa el link para agregar
		menuItemAdd.removeActionListener(linkAdd);
		menuItemAdd.setVisible(true);
		linkAdd = link;		
		setMenuWithLink(menuItemAdd, linkAdd);
		linkAdd.addListener(this);
		*/
		
		addLinkToDefaultMenu(link,index);
		link.addListener(this);
		
	}
	
	/**
	 * quita el link add del menu. 
	 */
	public void removeLinkAdd(){
		//menuItemAdd.setVisible(false);
	}
	
	/**
	 * quita el link update del menu. 
	 */
	public void removeLinkUpdate(){
		//menuItemUpdate.setVisible(false);
	}
	
	/**
	 * quita el link view del menu. 
	 */
	public void removeLinkView(){
		//menuItemView.setVisible(false);
	}
	
	/**
	 * quita el link excel del menu. 
	 */
	public void removeLinkExcel(){
		//menuItemExcel.setVisible(false);
	}
	
	/**
	 * quita el link print del menu. 
	 */
	public void removeLinkPrint(){
		//menuItemPrint.setVisible(false);
	}
	
	/**
	 * quita el link print object del menu. 
	 */
	public void removeLinkPrintObject(){
		//menuItemPrintObject.setVisible(false);
	}
	
	/**
	 * quita el link delete del menu. 
	 */
	public void removeLinkDelete(){
		//menuItemDelete.setVisible(false);
	}
	
	
	/**
	 * se setea el link para modificar los objetos. 
	 */
	public void setLinkUpdate(LinkUpdateObject link, int index){
		
		//se inicializa el link para modificar
		/*
		menuItemUpdate.removeActionListener(linkUpdate);
		menuItemUpdate.setVisible(true);
		this.elementsListeners.remove(linkUpdate);		
		linkUpdate = link;		
		setMenuWithLink(menuItemUpdate, linkUpdate);		
		addElementsListener(linkUpdate);
		linkUpdate.addListener(this);
		*/
		
		addLinkToDefaultMenu(link,index);
		addElementsListener(link);
		link.addListener(this);
	}

	/**
	 * se setea el link para eliminar los objetos. 
	 */
	public void setLinkDelete(LinkDeleteObject link, int index){
		//se inicializa el link para eliminar
		/*
		menuItemDelete.removeActionListener(linkDelete);
		menuItemDelete.setVisible(true);
		this.elementsListeners.remove(linkDelete);		
		linkDelete = link;
		setMenuWithLink(menuItemDelete, linkDelete);
		linkDelete.addListener(this);
		addElementsListener(linkDelete);
		*/
		addLinkToDefaultMenu(link,index);
		addElementsListener(link);
		link.addListener(this);
	}

	/**
	 * se setea el link para visualizar los objetos. 
	 */
	public void setLinkView(LinkViewObject link, int index){
		/*
		//se inicializa el link para visualizar
		menuItemView.removeActionListener(linkView);
		this.elementsListeners.remove(linkView);
		menuItemView.setVisible(true);
		linkView = link;
		setMenuWithLink(menuItemView, linkView);		
		addElementsListener(linkView);
		*/
		
		addLinkToDefaultMenu(link,index);
		addElementsListener(link);
	}
	
	/**
	 * se setea el link para imprimir los objetos. 
	 */
	public void setLinkPrint(LinkPrintCollection link, int index){
		/*
		//se inicializa el link para imprimir la colecci�n
		menuItemPrint.removeActionListener(linkPrint);
		menuItemPrint.setVisible(true);
		linkPrint = link;
		setMenuWithLink(menuItemPrint, linkPrint);
		linkPrint.setILinkPrintCollection(this);
		*/
		addLinkToDefaultMenu(link, index);
		link.setILinkPrintCollection(this);
	}

	/**
	 * se setea el link para imprimir el objeto seleccionado. 
	 */
	public void setLinkPrintObject(LinkPrintObject link, int index){
		/*
		//se inicializa el link para imprimir el objeto.
		menuItemPrintObject.removeActionListener(linkPrintObject);
		linkPrintObject = link;
		setMenuWithLink(menuItemPrintObject, linkPrintObject);
		linkPrintObject.setILinkPrintObject(this);
		menuItemPrintObject.setVisible(true);
		*/
		addLinkToDefaultMenu(link, index);
		link.setILinkPrintObject(this);
	}
	
	
	/**
	 * se setea el link para imprimir los objetos en excel. 
	 */
	public void setLinkExportToExcel(LinkExportCollectionToExcel link, int index){
		/*
		//se inicializa el link para exportar a excel la coleccci�n.
		menuItemExcel.removeActionListener(linkExcel);
		linkExcel = link;
		setMenuWithLink(menuItemExcel, linkExcel);
		linkExcel.setILinkPrintCollection(this);
		menuItemExcel.setVisible(true);
		
		*/
		
		addLinkToDefaultMenu(link, index);
		link.setILinkPrintCollection(this);
	}

	/**
	 * se setea el link para cerrar la ventana. 
	 */
	public void setLinkClose(LinkCloseWindow link){
		/*
		//se inicializa el link para visualizar
		menuItemClose.removeActionListener(linkClose);
		linkClose = link;		
		setMenuWithLink(menuItemClose, linkClose);
		*/
		addLinkToDefaultMenu(link);
		
	}

	/*
	 * se setea el menuItem con propiedades del link
	 */
	private void setMenuWithLink(JMenuItem menuItem, Link link){
		
		MenuFactory.setJMenuItem(menuItem, link);
		
	}


	/**
	 * se setea el renderer para la clase indicada.
	 * @param elementTableRenderer
	 */
	public void setElementTableRenderer(Class classToRenderer, ElementTableRenderer elementTableRenderer) {
		this.elementsTable.setDefaultRenderer(classToRenderer, elementTableRenderer);
	}
	
	/**
	 * se setea el renderer para el header de la clase indicada.
	 * @param elementTableRenderer
	 */
	public void setTableHeaderRenderer(int columnIndex, TableCellRenderer tableHeaderRenderer) {
		
		TableColumn col = this.elementsTable.getColumnModel().getColumn(columnIndex);
	    col.setHeaderRenderer( tableHeaderRenderer );
	    
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
			labelHeader.setText(title);
		}
		//actualizamos el footer.
		labelFooter.setText("Totales " + elementTableModel.getElements().size());
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
			Object selectedObject = elementTableModel.getElement(elementsTable.getSelectedRow());
			//avisamos a los listeners.
			alertListenersSelectionChange(selectedObject);
		}else{
			//avisamos a los listeners.
			alertListenersSelectionChange(null);
		}
				
	}
		
	//===============================
	//M�TODOS PRIVADOS.
	//===============================
	
	/*
	 * se crea la ventana.
	 */
	private void createUI(){
		/*
		 * vamos a crear tres secciones:
		 *    
		 *     -----------------------------------------------
		 *    |     JPanel para la descripci�n del listado    |
		 *    |-----------------------------------------------|
		 *    |                                               |
		 *    |  JScroll que contendr� el JTable con la       |
		 *    |  colecci�n a mostrar                          |       
		 *    |                                               | 
		 *    |-----------------------------------------------|
		 *    |  JPanel para un res�men del listado,          | 
		 *    |  anotaciones, totales,etc, etc.               |
		 *     -----------------------------------------------
		 * 
		 */
		
		//inicializamos los listeners.
		this.elementsListeners = new ArrayList();
		//inicializamos la tabla
		initializeTable();
		//inicializamos el men�.
		initializeMenuBar();
		//inicializamos los links.		
		initializeLinks();		

		//creamos el panel.
		//JPanel panelMain = new JPanel(new BorderLayout());
		JPanel panelMain = new JPanel(new BorderLayout());		
		panelMain.add(getHeader(), BorderLayout.NORTH);
		//panelMain.add(getTableNavigation(), BorderLayout.CENTER);
		panelMain.add(getScrollWithTable(), BorderLayout.CENTER);
		panelMain.add(getFooter(), BorderLayout.SOUTH);		
		this.setContentPane(panelMain);
		
		//seteamos el tama�o
		setSize(new Dimension(800,600));
	}
	
	/**
	 * retorna el panel de header donde se mostrar� 
	 * la descripci�n del listado.
	 */
	protected JComponent getHeader(){
		
		JPanel header = new JPanel(new BorderLayout());		
		labelHeader = JLabelFactory.getJLabel(elementTableModel.getDescription(), 500, SwingConstants.CENTER);
		labelHeader.setOpaque(true);
		labelHeader.setBackground(getHeaderBackground());
		labelHeader.setForeground(getHeaderForeground());
		labelHeader.setFont(new Font("Verdana", 1 , 12));
		labelHeader.setPreferredSize(new Dimension(500,25));
		header.add(labelHeader, BorderLayout.CENTER);
		//agregamos la navegaci�n.
		navigationPanel = getNavigation();
//		JPanel contNav = new JPanel(new BorderLayout());
//		contNav.add(navigationPanel, BorderLayout.WEST);		
//		header.add(contNav, BorderLayout.SOUTH);		
		header.add(navigationPanel, BorderLayout.SOUTH);
		//header.setOpaque(false);
		return header;
		
	}
	
	/*
	 * retorna los botones de navegaci�n de la tabla (para la paginaci�n).
	 */
	protected JPanel getNavigation(){
		JPanel panel = new JPanel(new GridLayout());
		panel.setMinimumSize(new Dimension(150, 25));
		
		//agregamos los botones para la navegaci�n.
		JPanel panelBtn = new JPanel(new GridLayout());
		btnFirst = new JButton();		
		btnFirst.setPreferredSize(new Dimension(10,10));		
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
		labelEspacio = new JLabel();
		labelEspacio.setPreferredSize(new Dimension(23,25));		
		panelSetRows.add(labelEspacio);		
		labelEspacio = new JLabel();
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
		
		panel.add(panelSetRows);
		panel.setVisible(true);
		
		JPanel contNav = new JPanel(new BorderLayout());
		contNav.add(panel, BorderLayout.WEST);		
		
		return contNav;
	}
	
	/**
	 * retorna el scroll con la tabla que contiene
	 * la colecci�n a mostrar.
	 */
	protected JScrollPane getScrollWithTable(){
		//creamos el scroll de la tabla
		scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//scroll.setBounds(new Rectangle(15, 44, 760, 426));
		scroll.getViewport().add(elementsTable, null);		
		//scroll.setPreferredSize(new Dimension(500,500));
		return scroll;
	}
	
	/**
	 * retorna el panel de footer. 
	 */
	public JComponent getFooter(){
		JPanel footer = new JPanel(new BorderLayout());		
		labelFooter = JLabelFactory.getJLabel("Totales " + elementTableModel.getElements().size(), 300, SwingConstants.CENTER);
		footer.add(labelFooter, BorderLayout.CENTER);
		footer.setPreferredSize(new Dimension(500,50));
		return footer;
	}
	
	/*
	 * se inicializa la tabla donde se visualizar�n
	 * los objetos.
	 */
	protected void initializeTable(){
		//inicializamos el modelo de la tabla
		try {
			
			//le pedimos al controller los elementos en caso de que
			//no se haya indicado una colecci�n inicial.
			if(elementTableModel==null)
				elementTableModel = controller.list();
			
			elementTableModel.addTableModelListener(this);

			//inicializamos la tabla
			elementsTable = new JTable(elementTableModel);
			elementsTable.setAutoscrolls(true);
			elementsTable.setSelectionMode(0);
			elementsTable.setRowHeight(30);		
			elementsTable.getSelectionModel().addListSelectionListener(this);
			elementTableModel.addTableModelListener(elementsTable);
			setTableSorter();
			
			//inicializamos el renderer (hay que setear el renderer para
			//cada una de las clases de las propiedades del objeto).
			
			elementTableModel.initHeaderRenderers(elementsTable);
			elementTableModel.initCellRenderers(elementsTable);
			elementTableModel.initCellEditorsRenderers(elementsTable);
			elementTableModel.initColumnsWidth(elementsTable);
			
			/*
			TableCellRenderer renderer = elementTableModel.getTableCellRenderer();
			TableCellRenderer headerRenderer = elementTableModel.getTableHeaderRenderer();
			for(int columnIndex=0; columnIndex < elementTableModel.getColumnCount(); columnIndex++){
				elementsTable.setDefaultRenderer(elementTableModel.getColumnClass(columnIndex), renderer);
				elementsTable.getColumnModel().getColumn(columnIndex).setHeaderRenderer( headerRenderer );
			}*/		
			
		
		} catch (ControllerException e) {
			//problemas al recuperar el listado.
			DialogMessage.showErrorMessage(getTitle(),e.getMessage());
			//inicializamos la tabla sin modelo.
			elementsTable = new JTable();
		}
	}

	public void setTableSorter() {
		if(elementTableModel.getTableSorter()!=null)
			elementsTable.setRowSorter(elementTableModel.getTableSorter());
	}

	
	/*
	 * se inicializan los links inhabilit�ndolos.
	 */
	private void initializeLinks(){
		/*
		setLinkAdd(new LinkAddObject(null));
		setLinkUpdate(new LinkUpdateObject(null));
		setLinkDelete(new LinkDeleteObject(null));
		setLinkView(new LinkViewObject(null));

		setLinkPrint(new LinkPrintCollection());
		setLinkPrintObject(new LinkPrintObject(null));
		setLinkExportToExcel(new LinkExportCollectionToExcel());

		setLinkClose(new LinkCloseWindow(this));
		
		
		removeLinkAdd();
		removeLinkDelete();
		removeLinkView();
		removeLinkUpdate();
		removeLinkPrint();
		removeLinkPrintObject();
		*/
		setLinkClose(new LinkCloseWindow(this));
	}

	/*
	 * inicializa la barra men�.
	 * por default contar� con las operaciones:
	 * 			- Add
	 * 			- Update
	 * 			- Delete
	 * 			- View
	 * 			- Close
	 */
	private void initializeMenuBar(){

		/*
		//item de men� Add
		menuItemAdd = new JMenuItem(LinkLabelsBundle.link_Add);
		
		//item de men� Update
		menuItemUpdate = new JMenuItem(LinkLabelsBundle.link_Update);
		
		//item de men� Delete		
		menuItemDelete = new JMenuItem(LinkLabelsBundle.link_Delete);
		
		//item de men� View
		menuItemView = new JMenuItem(LinkLabelsBundle.link_View);
		
		//item de men� Print
		menuItemPrint = new JMenuItem(LinkLabelsBundle.link_Print);

		//item de men� PrintObject
		menuItemPrintObject = new JMenuItem(LinkLabelsBundle.link_PrintObject);

		//item de men� exportar a excel
		menuItemExcel = new JMenuItem(LinkLabelsBundle.link_Print_excel);

		//item de men� View
		menuItemClose = new JMenuItem(LinkLabelsBundle.link_Close);
		*/
		
		//construimos el men� por default con los �tems creados
		menuDefault = new JMenu(elementTableModel.getDescription());
		/*
		menuDefault.add(menuItemAdd);
		menuDefault.add(menuItemUpdate);
		menuDefault.add(menuItemDelete);
		menuDefault.add(menuItemView);
		menuDefault.addSeparator();
		menuDefault.add(menuItemExcel);
		menuDefault.add(menuItemPrint);
		menuDefault.add(menuItemPrintObject);
		menuDefault.addSeparator();
		menuDefault.add(menuItemClose);
		*/	
		//agregamos a la barra de men� el men� por default
		menuBar = new JMenuBar();
		menuBar.add(menuDefault);
		
		//agregamos a la ventana la barra de men�
		this.setJMenuBar(menuBar);
				
	}
	
	public void addMenu(JMenu menu){
		menuBar.add(menu);
	}
	
	
	/**
	 * se agrega un link para crear criterios de b�squeda.
	 * @param linkCreateCriteria
	 */
	public void addLinkCreateCriteria(LinkCreateCriteria linkCreateCriteria){
		//si es el primero, agregamos a la barra de men� el
		// men� para filtros.		
		if(menuFilters==null){
			menuFilters = new JMenu("Filtros");
			menuBar.add(menuFilters);
		}
		//agregamos el link al men�.
		menuFilters.add(linkCreateCriteria);
		linkCreateCriteria.addListener(this);
	}

	public LinkCreateCriteria getLinkCreateCriteria() {
		return linkCreateCriteria;
	}

	/**
	 * se agrega un grupo de links de filtros en la barra 
	 * de men� con el nombre dado por title.
	 * 
	 * @param links
	 * @param title 
	 */
	public void addLinkCreateCriteriaGroup(Collection links, String title){

		//construimos el men� donde se agrupar�n los links.
		JMenu menu = new JMenu(title);
		menu.setBackground(getJMenuBar().getBackground());
		
		Iterator iter = links.iterator();
		//agregamos cada uno de los links.
		while (iter.hasNext()) {			
			LinkCreateCriteria link = (LinkCreateCriteria) iter.next();
			menu.add(link);
			//seteamos a la ventana como listener para que escuche los cambios.
			link.addListener(this);
		}
		//agregamos el men� a la barra de men�.
		menuBar.add(menu);
	}
	
	/**
	 * este m�todo se ejecutar� cuando el linkAdd avise
	 * que un objeto fue creado.
	 */
	public void objectCreated(Object objectCreated) {
		refreshTable();		
	}

	/**
	 * este m�todo se ejecutar� cuando el linkUpdate avise
	 * que el objeto fue eliminado.
	 */
	public void objectUpdated(Object objectUpdated) {
		refreshTable();		
	}
	
	/**
	 * este m�todo se ejecutar� cuando el linkDelete avise
	 * que el objeto fue eliminado.
	 */
	public void objectDeleted(Object object) {
		refreshTable();
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

	/*
	 *  (non-Javadoc)
	 * @see com.ostrich.commonui.navigation.listeners.LinkCreateCriteriaListener#criteriaCreated(com.ostrich.commonui.search.criteria.UICriteria)
	 */
	public void criteriaCreated(UICriteria criteriaCreated) {
		
		this.criteria = criteriaCreated;
		if(criteriaCreated.isPaginable()){
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
		//navigationPanel.setVisible(false);
		this.criteria=null;
		this.labelHeader.setText(getTitle());
		setVisible(false);
	}

	
	
	//refresca el listado.
	public void refreshTable(){
		
		try {
			//seteamos los elementos de la tabla.
			setElements();			
		} catch (ControllerException e) {
			//se informa del error al usuario.
			DialogMessage.showErrorMessage(getTitle(), e.getMessage());			
		}
	}	

	@Deprecated
	private void setTotalElementSize() throws ControllerException{
		
		/*
		if(criteria!=null)
			totalSize = controller.totalSize(criteria);
		else
			totalSize = controller.totalSize();
		*/
		
		
	}
	
	protected void setElements() throws ControllerException{
		UICollection items = null;
		String description = this.getTitle();
		
		if(criteria!=null){
			items = this.controller.list(criteria);
			//this.elementTableModel = this.controller.list(criteria);
			//this.elementTableModel.setDescription(criteria.getDescription());
		}else{
			//this.elementTableModel.setElements(this.controller.list().getElements(), this.getTitle());
			items = this.controller.list();
			//this.elementTableModel =  this.controller.list();
			//this.elementTableModel.setDescription( this.getTitle() );
		}
		
		this.elementTableModel.setElements(items);
		totalSize = items.getTotalSize();
		
		this.elementTableModel.setTotalSize(totalSize);
		//this.elementTableModel = items;
		//initializeTable();
		pageSize =  totalSize / rowCount;
		pageSize += ( totalSize % rowCount > 0)?1:0;
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
	 * agregamos un listener que desea escuchar cambios en 
	 * el listado.
	 * 
	 * @param listener
	 */
	public void addElementsListener(ListCollectionListener listener){
		this.elementsListeners.add(listener);
	}
	
	/*
	 *  (non-Javadoc)
	 * @see com.ostrich.commonui.navigation.interfaces.ILinkPrintCollection#getUICollectionToPrint()
	 */
	public UICollection getUICollectionToPrint() {
		//retornamos la colecci�n a imprimir.
		UICollection print = null;
		try {
			//le quitamos la paginaci�n as� se imprimen todos			
			if(criteria!=null){
				boolean paginable = criteria.isPaginable();
				criteria.setPaginable(false);
				print = this.controller.list(criteria);
				print.setDescription(criteria.getDescription());
				//dejamos la paginaci�n como antes.
				criteria.setPaginable(paginable);
			}else{
				print = this.controller.list();
				print.setDescription(this.getTitle());
			}
			
			
		} catch (ControllerException e) {
			//se informa del error al usuario.
			DialogMessage.showErrorMessage(getTitle(), e.getMessage());			

		}
		return print;
	}

	/*
	 * se avisa a los listener que cambió el objeto seleccionado.  
	 */
	private void alertListenersSelectionChange(Object selectedObject){
		Iterator iter = elementsListeners.iterator();
		while (iter.hasNext()) {
			//le avisamos a cada listener
			ListCollectionListener element = (ListCollectionListener) iter.next();
			element.valueSelectedChange(selectedObject);			
		}
	}

	//GETTERS
	public LinkAddObject getLinkAdd() {
		return linkAdd;
	}

	public LinkDeleteObject getLinkDelete() {
		return linkDelete;
	}

	public LinkExportCollectionToExcel getLinkExcel() {
		return linkExcel;
	}

	public LinkPrintCollection getLinkPrint() {
		return linkPrint;
	}

	public LinkPrintObject getLinkPrintObject() {
		return linkPrintObject;
	}
	
	public LinkUpdateObject getLinkUpdate() {
		return linkUpdate;
	}

	public LinkViewObject getLinkView() {
		return linkView;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public IControllerList getController(){
		return controller;
	}

	/*
	 * se configura el men� de navegaci�n.
	 */
	protected void configurarNavegacion(){
		btnNext.setEnabled(page<pageSize);
		btnLast.setEnabled(page<pageSize);
		btnPrevious.setEnabled(page>1);
		btnFirst.setEnabled(page>1);
		
		if(pageSize==0)
			page=0;
		
		labelPage.setText(page + " de " + pageSize);
	}
	
	// M�todos de la navegaci�n.

	/*
	 * posiciona el listado en la primera p�gina.
	 */
	private void first(){
		this.page=1;
		updateTable(0);		
		configurarNavegacion();
		
	}
	
	/*
	 * posiciona el listado en la p�gina anterior.
	 */
	private void previous(){
		page--;
		this.offset -= rowCount;
		if(offset<0)
			offset=0;
		updateTable(offset);
		configurarNavegacion();
		
	}

	/*
	 * posiciona el listado en la p�gina siguiente.
	 */
	private void next(){
		page++;
		this.offset += rowCount;
		updateTable(offset);
		configurarNavegacion();
		
	}
	
	/*
	 * posiciona el listado en la �ltima p�gina disponible.
	 */
	private void last(){
		page = pageSize;
		this.offset = ((pageSize-1)*rowCount)+1;
		
		updateTable(offset);
		configurarNavegacion();		
	}

	/*
	 * se decrementa la cantidad de filas a visualizar
	 * en el listado (m�nimo 1 fila).
	 */
	private void minusRow(){
		if(rowCount>1){
			rowCount -= 1;
			labelRows.setText(String.valueOf(rowCount));
			first();
		}
	}
	
	/*
	 * se incrementa la cantidad de filas a visualizar
	 * en el listado (m�ximo de 50 filas).
	 */
	private void addRow(){
		if(rowCount<50){
			rowCount += 1;
			labelRows.setText(String.valueOf(rowCount));
			first();
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see com.ostrich.commonui.navigation.interfaces.ILinkPrintObject#getObjectToPrint()
	 */
	public Object getObjectToPrint() {
		//tomo el elemento actual.
		Object selectedObject = elementTableModel.getElement(elementsTable.getSelectedRow());
		return selectedObject;
	}

	/**
	 * agrega un link en el men� por default.
	 * 
	 * @param link link que se agregar� en el men�.
	 */
	public void addLinkToDefaultMenu(Link link){	
		//se agrega el link en el men� default, al final.		
		//int index = menuDefault.getItemCount()-2;
		//menuDefault.insert(link,menuDefault.getItemCount());
		
		menuDefault.insert(MenuFactory.getJMenuItem(link),menuDefault.getItemCount());
	}
	
	public void addLinkToDefaultMenu(Link link, int index){	
		//se agrega el link en el men� default, al final.
		
		//if(index < menuDefault.getItemCount())
		//	menuDefault.remove(index);
		
		JMenuItem menuItem = MenuFactory.getJMenuItem(link);
		
		//menuDefault.insert(link,index);
		menuDefault.insert(menuItem,index);
	}
	
	public void setMenuDefaultTitle(String title){
		menuDefault.setText(title);
	}
	
	public void initRightClickMenu(){
		
		elementsTable.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseReleased(MouseEvent e) {
	        	
	            int r = elementsTable.rowAtPoint(e.getPoint());
	            if (r >= 0 && r < ((UICollection)elementsTable.getModel()).getRealRowCount()) {
	            	elementsTable.setRowSelectionInterval(r, r);
	            } else {
	            	elementsTable.clearSelection();
	            }

	            int rowindex = elementsTable.getSelectedRow();
	            if (rowindex < 0)
	                return;
	            Component c = e.getComponent();
	            if (e.getButton() == 3 && e.getComponent() instanceof JTable ) {
	                JPopupMenu popup = getRightClickPopup();
	                popup.show(e.getComponent(), e.getX(), e.getY());
	            }
	        	
	        }
		});
	}

	/**
	 * @return the rightClickPopup
	 */
	public JPopupMenu getRightClickPopup() {
		return rightClickPopup;
	}

	/**
	 * @param rightClickPopup the rightClickPopup to set
	 */
	public void setRightClickPopup(JPopupMenu rightClickPopup) {
		this.rightClickPopup = rightClickPopup;
		initRightClickMenu();
	}
	
	
}