package com.migestion.swing.view.dialogs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import com.migestion.swing.custom.JImageLabel;

public class ProgressDialog extends JDialog {

    private JLabel message;
    private JLabel subMessage;
    private JImageLabel imageLogo;
    private JProgressBar progressBar;

    public ProgressDialog(Component parent, SwingWorker worker, String strTitle, String strMessage, String strSubmessage, ImageIcon logo, Integer logoWidth) {
    	
    	super(parent == null ? null : SwingUtilities.getWindowAncestor(parent));
        setModal(true);
        
        setTitle(strTitle);

        ((JComponent)getContentPane()).setBorder(new EmptyBorder(8, 8, 8, 8));

        message = new JLabel( strMessage);
        subMessage = new JLabel( strSubmessage);
        progressBar = new JProgressBar();

        imageLogo = new JImageLabel();
        imageLogo.setHorizontalAlignment( JLabel.CENTER );
        imageLogo.setImage(logo, false);
        imageLogo.setPreferredSize(new Dimension(logoWidth,logoWidth));
        imageLogo.setBounds(new Rectangle(new Dimension(logoWidth,logoWidth)));
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth =2;
        add(message, gbc);

        gbc.gridy++;
        add(subMessage, gbc);

        gbc.gridy++;
        gbc.gridwidth =2;
        add(imageLogo, gbc);

        gbc.gridy++;
        gbc.weightx = 1;
        gbc.gridwidth =2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(progressBar, gbc);

        pack();

        worker.addPropertyChangeListener(new PropertyChangeListener() {
			
        	public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("state")) {
                    SwingWorker.StateValue state = (SwingWorker.StateValue) evt.getNewValue();
                    switch (state) {
                        case DONE:
                            dispose();
                            break;
                    }
                } else if (evt.getPropertyName().equals("progress")) {
                    progressBar.setValue((Integer)evt.getNewValue());
                }
            }
		});
        switch (worker.getState()) {
            case PENDING:
                worker.execute();
                break;
        }
    }
    
    public ProgressDialog(Component parent, SwingWorker worker, String strTitle, String strMessage, String strSubmessage) {

        super(parent == null ? null : SwingUtilities.getWindowAncestor(parent));
        setModal(true);
        
        setTitle(strTitle);

        ((JComponent)getContentPane()).setBorder(new EmptyBorder(8, 8, 8, 8));

        message = new JLabel( strMessage);
        subMessage = new JLabel( strSubmessage);
        progressBar = new JProgressBar();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(message, gbc);

        gbc.gridy++;
        add(subMessage, gbc);

        gbc.gridy++;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(progressBar, gbc);

        pack();

        worker.addPropertyChangeListener(new PropertyChangeListener() {
			
        	public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("state")) {
                    SwingWorker.StateValue state = (SwingWorker.StateValue) evt.getNewValue();
                    switch (state) {
                        case DONE:
                            dispose();
                            break;
                    }
                } else if (evt.getPropertyName().equals("progress")) {
                    progressBar.setValue((Integer)evt.getNewValue());
                }
            }
		});
        switch (worker.getState()) {
            case PENDING:
                worker.execute();
                break;
        }

    }

    public static void showProgress(Component parent, SwingWorker worker, String title,String message, String submessage, Image icon, ImageIcon logo, Integer logoWidth) {

        ProgressDialog dialog = new ProgressDialog(parent, worker, title, message, submessage,logo,logoWidth);
        dialog.setIconImage( icon );
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
        
        

    }
/*
    public class PropertyChangeHandler implements PropertyChangeListener {

       
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("state")) {
                SwingWorker.StateValue state = (SwingWorker.StateValue) evt.getNewValue();
                switch (state) {
                    case DONE:
                        dispose();
                        break;
                }
            } else if (evt.getPropertyName().equals("progress")) {
                progressBar.setValue((Integer)evt.getNewValue());
            }
        }

    }*/

}