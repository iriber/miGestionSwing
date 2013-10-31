package com.migestion.swing.i18n.buttons;


import java.net.URL;
import java.util.ResourceBundle;

import com.migestion.swing.i18n.I18n;

/**
 * Clase que nos dará los paths de las imágenes para los botones de
 * acuerdo al idioma seteado en las properties.
 * 
 * @author Bernardo Iribarne
 *
 */
public class ButtonImagesBundle {

	
   private static ResourceBundle labels = ResourceBundle.getBundle( I18n.buttonImagesBundle );
    
   public static URL btn_Ok = ButtonImagesBundle.class.getResource( labels.getString("button.ok") ); 
   
   public static URL btn_Cancel = ButtonImagesBundle.class.getResource( labels.getString("button.cancel") );   
   
   public static URL btn_Close = ButtonImagesBundle.class.getResource( labels.getString("button.close") );
   
   public static URL btn_Browse = ButtonImagesBundle.class.getResource( labels.getString("button.browse") );
   
   public static URL btn_First = ButtonImagesBundle.class.getResource( labels.getString("button.first") );
   
   public static URL btn_Next = ButtonImagesBundle.class.getResource( labels.getString("button.next") );
   
   public static URL btn_Previous = ButtonImagesBundle.class.getResource( labels.getString("button.previous") );
   
   public static URL btn_Last = ButtonImagesBundle.class.getResource( labels.getString("button.last") );
   
   public static URL btn_Down = ButtonImagesBundle.class.getResource( labels.getString("button.down") );
   
   public static URL btn_Up = ButtonImagesBundle.class.getResource( labels.getString("button.up") );
   
   public static URL btn_Find = ButtonImagesBundle.class.getResource( labels.getString("button.find") );
   
   
   
}
