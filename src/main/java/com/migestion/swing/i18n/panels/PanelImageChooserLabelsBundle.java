package com.migestion.swing.i18n.panels;

import java.util.ResourceBundle;

import com.migestion.swing.i18n.I18n;

/**
 * Clase que nos dar� los labels para PaneImageChooser de
 * acuerdo al idioma seteado en las properties.
 * 
 * @author Bernardo Iribarne
 */

public class PanelImageChooserLabelsBundle {

	
   private static ResourceBundle labels = ResourceBundle.getBundle("com.codnet.swing.i18n.panels.PanelImageChooserLabelsBundle");
    
   public static String chooser_Filter_description = labels.getString("chooser.filter.description");
   
   public static String chooser_Select_image = labels.getString("chooser.select.image");
   
}
