package com.migestion.swing.i18n.buttons;

import java.util.ResourceBundle;

import com.migestion.swing.i18n.I18n;

/**
 * Clase que nos dará los labels de los botones de acuerdo
 * al idioma seteado en las properties.
 * 
 * @author Bernardo Iribarne
 *
 */
public class ButtonLabelsBundle {

	
   private static ResourceBundle messages = ResourceBundle.getBundle( I18n.buttonLabelsBundle );
    
   
   public static String btn_Ok = messages.getString("button.ok"); 
   public static String btn_Ok_ToolTipText = messages.getString("button.ok.toolTipText");
   
   public static String btn_Cancel = messages.getString("button.cancel");
   public static String btn_Cancel_ToolTipText = messages.getString("button.cancel.toolTipText");
   
   public static String btn_Close = messages.getString("button.close"); 
   public static String btn_Close_ToolTipText = messages.getString("button.close.toolTipText");   

   public static String btn_Browse = messages.getString("button.browse"); 
   public static String btn_Browse_ToolTipText = messages.getString("button.browse.toolTipText");
   
   public static String btn_First_ToolTipText = messages.getString("button.first.toolTipText");
   public static String btn_Next_ToolTipText = messages.getString("button.next.toolTipText");
   public static String btn_Previous_ToolTipText = messages.getString("button.previous.toolTipText");
   public static String btn_Last_ToolTipText = messages.getString("button.last.toolTipText");
   
   public static String btn_AddRow_ToolTipText = messages.getString("button.addRow.toolTipText");
   public static String btn_MinusRow_ToolTipText = messages.getString("button.minusRow.toolTipText");

   public static String btn_Find = messages.getString("button.find"); 
   public static String btn_Find_ToolTipText = messages.getString("button.find.toolTipText");
}
