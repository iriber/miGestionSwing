package com.migestion.swing.i18n.panels;

import java.util.ResourceBundle;

import com.migestion.swing.i18n.I18n;
import com.migestion.swing.resources.PropertiesImage;

/**
 * Clase que nos dar� los path de las im�genes para PaneImageChooser de
 * acuerdo al idioma seteado en las properties.
 * 
 * @author Bernardo Iribarne
 */

public class PanelImageChooserImagesBundle {

	
   private static ResourceBundle labels = ResourceBundle.getBundle("com.codnet.swing.i18n.panels.PanelImageChooserImagesBundle");
    
   public static String chooser_icon_jpg = PropertiesImage.PATH_IMAGES + labels.getString("chooser.icon.jpg");
   
   public static String chooser_icon_gif = PropertiesImage.PATH_IMAGES + labels.getString("chooser.icon.gif");
   
   public static String chooser_without_image = PropertiesImage.PATH_IMAGES + labels.getString("chooser.without.image");
}
