package com.migestion.swing.i18n;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class I18n {

	//private static ResourceBundle prop = ResourceBundle.getBundle("i18n");  

	public static Properties prop = getProperties();
	
    public static String buttonImagesBundle = prop.getProperty("button.images");
    public static String buttonLabelsBundle = prop.getProperty("button.labels");
    
    public static String linkImagesBundle = prop.getProperty("link.images");
    public static String linkLabelsBundle = prop.getProperty("link.labels");
    
    public static String exceptionMessagesBundle = prop.getProperty("exception.messages");
    
    public static String criteriaFechaPanelMessagesBundle = prop.getProperty("criteriaFechaPanel.messages");
    
    public static Properties getProperties(){
    	Properties props = new Properties();
    	try {
			props.load(I18n.class.getResourceAsStream("/META-INF/i18n/i18n.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return props;
    };
    
    

    
}
