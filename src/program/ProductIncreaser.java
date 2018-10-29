package program;
/**
 * File name: ProductIncreaser.java
 * ==================================================
 * This class initializes window which will allow you
 * to increase the quantity of the chosen product.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProductIncreaser {

	private JFrame frmProductIncr;
	private int result; // Variable for the quantity of the product after increasing. 
	
	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void build(int index) {
		initialize(index);
		frmProductIncr.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int index) {
		frmProductIncr = new JFrame();
		frmProductIncr.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				ProductsWindow.getFrmProductsWindow().setEnabled(true);
			}
		});
		frmProductIncr.setTitle("Increase product");
		frmProductIncr.setBounds(100, 100, 300, 120);
		frmProductIncr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProductIncr.getContentPane().setLayout(new BoxLayout(frmProductIncr.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		frmProductIncr.getContentPane().add(panel);

		Component verticalGlue = Box.createVerticalGlue();
		frmProductIncr.getContentPane().add(verticalGlue);

		JPanel adderPanel = new JPanel();
		frmProductIncr.getContentPane().add(adderPanel);

		JLabel lblNumberToAdd = new JLabel("Number to add: ");
		adderPanel.add(lblNumberToAdd);

		/*
		 * JSpinner used to choose a number on which the quantity of the
		 * product will be increased.
		 */
		JSpinner numberToAdd = new JSpinner();
		numberToAdd.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		adderPanel.add(numberToAdd);

		Component verticalGlue_1 = Box.createVerticalGlue();
		frmProductIncr.getContentPane().add(verticalGlue_1);

		JPanel buttonPanel = new JPanel();
		frmProductIncr.getContentPane().add(buttonPanel);

		/*
		 * By pressing this button the quantity of the product will be increased
		 * by the number settled in the JSpinner.
		 */
		JButton btnConfirm = new JButton("CONFIRM");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				result = (Integer) numberToAdd.getValue() + (Integer) GroupTableModel.getProductGroup(index)
						.getValueAt(ProductsWindow.getProductsTable().getSelectedRow(), 3);
				GroupTableModel.getProductGroup(index).setValueAt(result,
						ProductsWindow.getProductsTable().getSelectedRow(), 3);
				String help = "" + GroupTableModel.converseToString(GroupTableModel
						.showPriceOfWaresInThisGroup(GroupTableModel.getProductGroup(ProductsWindow.getIndex())));
				ProductsWindow.getTotalCost().setText(help);
				ProductsWindow.getFrmProductsWindow().setEnabled(true);
				frmProductIncr.dispose();
			}
		});
		buttonPanel.add(btnConfirm);

		/*
		 * Pressing this button will close this window.
		 */
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductsWindow.getFrmProductsWindow().setEnabled(true);
				frmProductIncr.dispose();
			}
		});
		buttonPanel.add(btnBack);
	}

}
