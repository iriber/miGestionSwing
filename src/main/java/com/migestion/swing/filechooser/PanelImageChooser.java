package com.migestion.swing.filechooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import com.migestion.swing.custom.CustomImageIcon;
import com.migestion.swing.i18n.buttons.ButtonImagesBundle;
import com.migestion.swing.i18n.buttons.ButtonLabelsBundle;
import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.i18n.panels.PanelImageChooserImagesBundle;
import com.migestion.swing.i18n.panels.PanelImageChooserLabelsBundle;
import com.migestion.swing.scrollers.ImageScroller;

/**
 * Panel que nos servir� para elegir y visualizar un archivo de imagen.
 *   
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public class PanelImageChooser extends JPanel{

	//imagen seleccionada.
	private File imageFile;
	
	//bot�n para abrir el filechooser.
	private JButton btnShowDialog;
	
	//bot�n para quitar la imagen.
	private JButton btnCleanImage;
	
	//scroller para mostrar la imagen.
	private ImageScroller imageScroller;
	
	//listener al que se le informar� cuando se seleccione
	//una imagen.
	private IPaneImageChooserListener listener;
	
	public PanelImageChooser(IPaneImageChooserListener listener){		
		super();
		this.listener = listener;
		init();		
	}

	/* 
	 * se inicializa el panel.
	 */
	private void init() {
		
		//seteamos el layout.
		setLayout(new BorderLayout());
		
		//seteamos el panel para mostrar la im�gen.
		imageScroller = new ImageScroller(new CustomImageIcon(PanelImageChooserImagesBundle.chooser_without_image), PanelImageChooserLabelsBundle.chooser_Select_image, Color.WHITE); 
		imageScroller.setPreferredSize(new Dimension(250,250));
		add(imageScroller, BorderLayout.NORTH);
		
		//seteamos el bot�n para abrir el browser.
		JPanel btnPanel = new JPanel(new BorderLayout());
		btnPanel.add(getBtnShowDialog(), BorderLayout.EAST);
		btnPanel.add(getBtnCleanImage(), BorderLayout.WEST);
		add(btnPanel, BorderLayout.SOUTH);		
		
	}

	//se inicializa el bot�n para abrir el filechooser.
	private JButton getBtnShowDialog() {
		if(btnShowDialog==null){
			btnShowDialog = new JButton(createActionBrowser());			
			btnShowDialog.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnShowDialog.setPreferredSize(new Dimension(120,30));
			btnShowDialog.setMinimumSize(new Dimension(120,30));
		}
		return btnShowDialog;
	}

	//se inicializa el bot�n para borrar la imagen.
	private JButton getBtnCleanImage() {
		if(btnCleanImage==null){
			btnCleanImage = new JButton(createActionCleanImage());			
			btnCleanImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnCleanImage.setPreferredSize(new Dimension(120,30));
			btnCleanImage.setMinimumSize(new Dimension(120,30));
			btnCleanImage.setEnabled(false);
		}
		return btnCleanImage;
	}	
	/**
	 * retorna la imagen seleccionada.
	 * @return
	 */
	public File getImage() {
		return imageFile;
	}
	
	/**
	 * setea la imagen seleccionada.
	 * @param image
	 */
	public void setImage(File image){
		this.imageFile = image;
		if(image==null){
			imageScroller.setTexAndImage(PanelImageChooserLabelsBundle.chooser_Select_image, new CustomImageIcon(PanelImageChooserImagesBundle.chooser_without_image));
			btnCleanImage.setEnabled(false);
		}else{
			imageScroller.setImage(new CustomImageIcon(image.getPath()));
			btnCleanImage.setEnabled(true);
		}
		listener.setImageFile(image);
	}
	
	/*
	 * creamos la acci�n para abrir el file chooser.
	 */
	private Action createActionBrowser(){
		Action action = new AbstractAction(ButtonLabelsBundle.btn_Browse, new ImageIcon(ButtonImagesBundle.btn_Browse)) { 
	 	    public void actionPerformed(ActionEvent e) { 
	 		JFileChooser fc = new  JFileChooser(); 
	  
	 		// agregamos un file filter y un file view 
	 		CustomFileFilter filter = new CustomFileFilter( 
	 		    new String[] {"jpg", "gif"}, PanelImageChooserLabelsBundle.chooser_Filter_description); 
	 		 
	 		CustomFileView fileView = new CustomFileView(); 
	 		fileView.putIcon("jpg", new ImageIcon(PanelImageChooserImagesBundle.chooser_icon_jpg)); 
	 		fileView.putIcon("gif", new ImageIcon(PanelImageChooserImagesBundle.chooser_icon_gif)); 
	 		fc.setFileView(fileView); 
	 		fc.addChoosableFileFilter(filter); 
	 		fc.setFileFilter(filter); 
	 		
	 		// agregamos el file image previewer para previsualizar la imagen. 
	 		fc.setAccessory(new FileImagePreviewer(fc)); 
	  
	 		//si tenemos una imagen seleccionada, abrimos en el mismo directorio.
	 		if(getImage()!=null)
	 			fc.setSelectedFile(getImage());
	 		
	 		// mostramos el file chooser
	 		int result = fc.showOpenDialog(null); 
	 		 
	 		// si se seleccion� una imagen, seteamos la
	 		//imagen para retornarla.
	 		if(result == JFileChooser.APPROVE_OPTION) {	 			
	 		    setImage(fc.getSelectedFile()); 
	 		} 
	 	    } 
	 	}; 
	 	
	 	return action;
	}

	/*
	 * creamos la acci�n para borrar la imagen (visualmente).
	 */
	private Action createActionCleanImage(){
		
		Action action = new AbstractAction(LinkLabelsBundle.link_Delete, new ImageIcon(LinkImagesBundle.link_Delete)){

			public void actionPerformed(ActionEvent arg0) {
				setImage(null);				
			}};
		
		return action;
	}
}
