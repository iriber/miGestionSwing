package com.migestion.swing.view.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import com.migestion.swing.controller.IControllerList;
import com.migestion.swing.controller.exception.ControllerException;
import com.migestion.swing.factories.JLabelFactory;
import com.migestion.swing.factories.MenuFactory;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.model.UICollection;
import com.migestion.swing.navigation.Link;
import com.migestion.swing.navigation.LinkAddObject;
import com.migestion.swing.navigation.LinkCloseWindow;
import com.migestion.swing.navigation.LinkCreateCriteria;
import com.migestion.swing.navigation.LinkDeleteObject;
import com.migestion.swing.navigation.LinkPrintCollection;
import com.migestion.swing.navigation.LinkUpdateObject;
import com.migestion.swing.navigation.LinkViewObject;
import com.migestion.swing.navigation.interfaces.ILinkWindowClose;
import com.migestion.swing.navigation.interfaces.ILinkWindowList;
import com.migestion.swing.navigation.listeners.LinkAddListener;
import com.migestion.swing.navigation.listeners.LinkCreateCriteriaListener;
import com.migestion.swing.navigation.listeners.LinkDeleteListener;
import com.migestion.swing.search.criteria.UICriteria;
import com.migestion.swing.utils.UbicacionVentana;
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
 *      Imprimir el listado en formato html
 *      
 * 
 * @author Bernardo Iribarne {Ostrich}
 * @version 1.0
 */

public class GenericFrameList extends JFrame implements TableModelListener, 
														ListSelectionListener, 
														LinkAddListener, 
														LinkDeleteListener, 
														LinkCreateCriteriaListener, 
														ILinkWindowClose,
														ILinkWindowList{

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
	protected JMenuItem menuItemAdd;		
	protected JMenuItem menuItemUpdate;
	protected JMenuItem menuItemDelete;	
	protected JMenuItem menuItemView;
	protected JMenuItem menuItemPrint;
	protected JMenuItem menuItemClose;

	/*
	 * links para llamar a las distintas ventanas.
	 * estar�n asociados a los botones, men�es, etc.
	 */
	protected LinkAddObject linkAdd;
	protected LinkUpdateObject linkUpdate;
	protected LinkDeleteObject linkDelete;
	protected LinkViewObject linkView;
	protected LinkPrintCollection linkPrint;
	protected LinkCloseWindow linkClose;
	protected LinkCreateCriteria linkCreateCriteria;
	
	//label del header
	JLabel labelHeader; 
	JLabel labelFooter;
	String description;
	
		
	//CONSTRUCTORES	
	public GenericFrameList(String title, IControllerList controller){		
		super(title);
		this.description = title;
		this.controller = controller;
		//creamos la UI
		createUI();
	}

	
	
	/**
	 * se abre la ventana centrada en la pantalla.
	 */
	public void open() {
		UbicacionVentana.centrar(this, true);
		this.setVisible(true);
	}
	
	public void addToJFrameContainer(JFrameContainer container) {
		 //container.addToDesktop(this);		
	}
	

	/**
	 * se setea el color de fondo del men�.
	 * @param color
	 */
	public void setMenuBackground(Color color){
		menuBar.setBackground(color);
		menuDefault.setBackground(color);
		menuItemAdd.setBackground(color);
		menuItemUpdate.setBackground(color);
		menuItemDelete.setBackground(color);
		menuItemView.setBackground(color);
		menuItemPrint.setBackground(color);
		menuItemClose.setBackground(color);		
		
		menuFilters.setBackground(color);
	}
	
	/**
	 * se setea el link para agregar los objetos. 
	 */
	public void setLinkAdd(LinkAddObject link){
		//se inicializa el link para agregar
		menuItemAdd.removeActionListener(linkAdd);
		linkAdd = link;		
		setMenuWithLink(menuItemAdd, linkAdd);
		linkAdd.addListener(this);
		//menuItemAdd.setMnemonic('A');
		
	}
	
	/**
	 * se setea el link para modificar los objetos. 
	 */
	public void setLinkUpdate(LinkUpdateObject link){
		//se inicializa el link para modificar
		menuItemUpdate.removeActionListener(linkUpdate);
		linkUpdate = link;		
		setMenuWithLink(menuItemUpdate, linkUpdate);
	}

	/**
	 * se setea el link para eliminar los objetos. 
	 */
	public void setLinkDelete(LinkDeleteObject link){
		//se inicializa el link para eliminar
		menuItemDelete.removeActionListener(linkDelete);
		linkDelete = link;
		setMenuWithLink(menuItemDelete, linkDelete);
		linkDelete.addListener(this);
	}

	/**
	 * se setea el link para visualizar los objetos. 
	 */
	public void setLinkView(LinkViewObject link){
		//se inicializa el link para visualizar
		menuItemView.removeActionListener(linkView);
		linkView = link;
		setMenuWithLink(menuItemView, linkView);
	}
	
	/**
	 * se setea el link para imprimir los objetos. 
	 */
	public void setLinkPrint(LinkPrintCollection link){
		//se inicializa el link para visualizar
		menuItemPrint.removeActionListener(linkPrint);
		linkPrint = link;
		setMenuWithLink(menuItemPrint, linkPrint);
		//seteamos la colecci�n al link, la que deber� imprimir
		linkPrint.setUICollectionToPrint(elementTableModel);
	}

	/**
	 * se setea el link para cerrar la ventana. 
	 */
	public void setLinkClose(LinkCloseWindow link){
		//se inicializa el link para visualizar
		menuItemClose.removeActionListener(linkClose);
		linkClose = link;		
		setMenuWithLink(menuItemClose, linkClose);
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
		//mostramos la descripci�n del criteria.
		labelHeader.setText(description);
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
			//tomo el elemento actual y lo seteo en los links.
			Object selectedObject = elementTableModel.getElement(elementsTable.getSelectedRow());
			linkUpdate.setObjectToUpdate(selectedObject);
			linkDelete.setObjectToDelete(selectedObject);
			linkView.setObjectToView(selectedObject);
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
		labelHeader.setOpaque(false);
		header.add(labelHeader, BorderLayout.CENTER);
		header.setOpaque(false);
		return header;
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
		scroll.setBounds(new Rectangle(15, 44, 760, 426));
		scroll.getViewport().add(elementsTable, null);
		
		return scroll;
	}
	
	/**
	 * retorna el panel de footer. 
	 */
	protected JComponent getFooter(){
		JPanel footer = new JPanel(new BorderLayout());		
		labelFooter = JLabelFactory.getJLabel("Totales " + elementTableModel.getElements().size(), 300, SwingConstants.CENTER);
		footer.add(labelFooter, BorderLayout.CENTER);		
		return footer;
	}
	
	/*
	 * se inicializa la tabla donde se visualizar�n
	 * los objetos.
	 */
	private void initializeTable(){
		//inicializamos el modelo de la tabla
		try {
			elementTableModel = controller.list();
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
			ElementTableRenderer renderer = new ElementTableRenderer();
			for(int columnIndex=0; columnIndex < elementTableModel.getColumnCount(); columnIndex++){
				elementsTable.setDefaultRenderer(elementTableModel.getColumnClass(columnIndex), renderer);
			}		
		
		} catch (ControllerException e) {
			//problemas al recuperar el listado.
			DialogMessage.showErrorMessage(getTitle(),e.getMessage());
			//inicializamos la tabla sin modelo.
			elementsTable = new JTable();
		}
	}

	
	/*
	 * se inicializan los links inhabilit�ndolos.
	 */
	private void initializeLinks(){
		setLinkAdd(new LinkAddObject(null));
		setLinkUpdate(new LinkUpdateObject(null));
		setLinkDelete(new LinkDeleteObject(null));
		setLinkView(new LinkViewObject(null));
		setLinkPrint(new LinkPrintCollection());
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

		//item de men� View
		menuItemClose = new JMenuItem(LinkLabelsBundle.link_Close);
		
		//construimos el men� por default con los �tems creados
		menuDefault = new JMenu(elementTableModel.getDescription());
		menuDefault.add(menuItemAdd);
		menuDefault.add(menuItemUpdate);
		menuDefault.add(menuItemDelete);
		menuDefault.add(menuItemView);
		menuDefault.add(menuItemPrint);
		menuDefault.addSeparator();
		menuDefault.add(menuItemClose);
				
		//agregamos a la barra de men� el men� por default
		menuBar = new JMenuBar();
		menuBar.add(menuDefault);
		
		//agregamos a la ventana la barra de men�
		this.setJMenuBar(menuBar);
				
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


	/**
	 * este m�todo se ejecutar� cuando el linkAdd avise
	 * que un objeto fue creado.
	 */
	public void objectCreated(Object objectCreated) {
		this.elementTableModel.addElement(objectCreated);		
	}

	/**
	 * este m�todo se ejecutar� cuando el linkDelete avise
	 * que el objeto fue eliminado.
	 */
	public void objectDeleted(Object object) {
		this.elementTableModel.deleteElement(object);
	}

	/**
	 * aplica el criteria y actualiza el listado.
	 * @param criteria
	 */
	private void apply(UICriteria criteria){
		//se aplica el criterio de b�squeda y se actualiza
		//el listado.			
		this.criteria = criteria;
		refreshTable();
	}


	public void criteriaCreated(UICriteria criteriaCreated) {
		//aplicamos el criteria.
		apply(criteriaCreated);
	}


	/**
	 * se cierra la ventana
	 * (implementaci�n de interface ILinkWindowClose)
	 */
	public void close() {
		this.dispose();
	}
	
	//refresca el listado.
	public void refreshTable(){
		
		try {
			//se actualiza el listado.
			if(criteria!=null){
				this.elementTableModel.setElements(this.controller.list(criteria).getElements());
				this.description = criteria.getDescription();
			}else{
				this.elementTableModel.setElements(this.controller.list().getElements());
				this.description = this.getTitle();
			}
			
		} catch (ControllerException e) {
			//se informa del error al usuario.
			DialogMessage.showErrorMessage(getTitle(), e.getMessage());			
		}
	}	

}