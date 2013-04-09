package com.example.swt.witgets;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;

public class FirstSwtApplication {
	private static Text firstName;
	private static Text lastName;
	private static Text text;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display();

	    Shell shell = new Shell(display);
	    shell.setLayout(new FillLayout(SWT.VERTICAL));
	    
	    Composite composite = new Composite(shell, SWT.NONE);
	    composite.setLayout(new RowLayout(SWT.HORIZONTAL));
	    
	    Label lblFirstName = new Label(composite, SWT.NONE);
	    lblFirstName.setText("Vorname\n"); //$NON-NLS-1$
	    
	    firstName = new Text(composite, SWT.BORDER);
	    
	    Composite composite_1 = new Composite(shell, SWT.NONE);
	    composite_1.setLayout(new RowLayout(SWT.HORIZONTAL));
	    
	    Label lblLastName = new Label(composite_1, SWT.NONE);
	    lblLastName.setText(Messages.getString("LastNameLastName")); //$NON-NLS-1$
	    
	    lastName = new Text(composite_1, SWT.BORDER);
	    
	    Composite composite_2 = new Composite(shell, SWT.NONE);
	    composite_2.setLayout(new RowLayout(SWT.HORIZONTAL));
	    
	    Button btnOk = new Button(composite_2, SWT.NONE);
	    btnOk.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseUp(MouseEvent e) {
	    		text.setText(firstName.getText() + " " + lastName.getText()); //$NON-NLS-1$
	    	}
	    });
	    btnOk.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		text.setText(firstName.getText() + " " + lastName.getText()); //$NON-NLS-1$
	    	}
	    });
	    btnOk.setText("OK"); //$NON-NLS-1$
	    
	    Button btnCancel = new Button(composite_2, SWT.NONE);
	    btnCancel.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseUp(MouseEvent e) {
	    		text.setText(""); //$NON-NLS-1$
	    		firstName.setText(""); //$NON-NLS-1$
	    		lastName.setText(""); //$NON-NLS-1$
	    	}
	    });
	    btnCancel.setText("Cancel"); //$NON-NLS-1$
	    
	    Composite composite_3 = new Composite(shell, SWT.NONE);
	    composite_3.setLayout(new RowLayout(SWT.HORIZONTAL));
	    
	    text = new Text(composite_3, SWT.BORDER);
	    
	    
	    //TODO add some widgets to the Shell
	    shell.open();
	    while (!shell.isDisposed()) {
	      if (!display.readAndDispatch())
	        display.sleep();
	    }
	    display.dispose();
	  }
	}


