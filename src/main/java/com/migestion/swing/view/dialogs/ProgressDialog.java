package com.migestion.swing.view.dialogs;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

public class ProgressDialog extends JDialog {

    private JLabel message;
    private JLabel subMessage;
    private JProgressBar progressBar;

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

    public static void showProgress(Component parent, SwingWorker worker, String title,String message, String submessage) {

        ProgressDialog dialog = new ProgressDialog(parent, worker, title, message, submessage);
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