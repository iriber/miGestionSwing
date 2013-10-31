package com.migestion.swing.i18n.links;

import java.net.URL;
import java.util.ResourceBundle;

import com.migestion.swing.i18n.I18n;
import com.migestion.swing.resources.PropertiesImage;

/**
 * Clase que nos dar� los paths de las im�genes para los links de
 * acuerdo al idioma seteado en las properties.
 * 
 * @author Bernardo Iribarne
 *
 */
public class LinkImagesBundle {

	
   private static ResourceBundle labels = ResourceBundle.getBundle( I18n.linkImagesBundle ) ;
    
   public static URL link_Add = LinkImagesBundle.class.getResource( labels.getString("link.add") ) ; 
   
   public static URL link_Update = LinkImagesBundle.class.getResource( labels.getString("link.update") );
   
   public static URL link_Delete = LinkImagesBundle.class.getResource( labels.getString("link.delete") );
   
   public static URL link_View = LinkImagesBundle.class.getResource( labels.getString("link.view") );
   
   public static URL link_Close = LinkImagesBundle.class.getResource( labels.getString("link.close") );
   
   public static URL link_Exit = LinkImagesBundle.class.getResource( labels.getString("link.exit") );
   
   public static URL link_Print = LinkImagesBundle.class.getResource( labels.getString("link.print") );
   
   public static URL link_Print_excel = LinkImagesBundle.class.getResource( labels.getString("link.print.excel") );
   
   public static URL link_CreateCriteria = LinkImagesBundle.class.getResource( labels.getString("link.createCriteria") );

   public static URL link_List = LinkImagesBundle.class.getResource( labels.getString("link.list") );
   
   public static URL link_Open = LinkImagesBundle.class.getResource( labels.getString("link.open") );
   
   public static URL link_FindObject = LinkImagesBundle.class.getResource( labels.getString("link.findObject") );
}
