package program;
/**
 * File name: ProductReducer.java
 * ==================================================
 * This class initializes window which will allow you
 * to reduce the quantity of the chosen product.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProductReducer {

	private JFrame frmProductReducer;
	private int result; // Variable for the quantity of the product after reducing.

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void build(int index) {
		initialize(index);
		frmProductReducer.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int index) {
		frmProductReducer = new JFrame();
		frmProductReducer.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				ProductsWindow.getFrmProductsWindow().setEnabled(true);
			}
		});
		frmProductReducer.setTitle("Reduce number of product");
		frmProductReducer.setBounds(100, 100, 300, 120);
		frmProductReducer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProductReducer.getContentPane()
				.setLayout(new BoxLayout(frmProductReducer.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		frmProductReducer.getContentPane().add(panel);

		Component verticalGlue = Box.createVerticalGlue();
		frmProductReducer.getContentPane().add(verticalGlue);

		JPanel reducerPanel = new JPanel();
		frmProductReducer.getContentPane().add(reducerPanel);

		JLabel lblNumberToReduce = new JLabel("Number to reduce: ");
		reducerPanel.add(lblNumberToReduce);

		/*
		 * JSpinner used to choose a number on which the quantity of the
		 * product will be reduced.
		 */
		JSpinner numberToReduce = new JSpinner();
		numberToReduce.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), (Integer) GroupTableModel.getProductGroup(index)
				.getValueAt(ProductsWindow.getProductsTable().getSelectedRow(), 3), new Integer(1)));
		reducerPanel.add(numberToReduce);

		Component verticalGlue_1 = Box.createVerticalGlue();
		frmProductReducer.getContentPane().add(verticalGlue_1);

		JPanel buttonPanel = new JPanel();
		frmProductReducer.getContentPane().add(buttonPanel);

		/*
		 * By pressing this button the quantity of the product will be reduced
		 * by the number settled in the JSpinner.
		 */
		JButton btnConfirm = new JButton("CONFIRM");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				result = (Integer) GroupTableModel.getProductGroup(index)
						.getValueAt(ProductsWindow.getProductsTable().getSelectedRow(), 3)
						- (Integer) numberToReduce.getValue();
				GroupTableModel.getProductGroup(index).setValueAt(result,
						ProductsWindow.getProductsTable().getSelectedRow(), 3);
				String help = "" + GroupTableModel.converseToString(GroupTableModel
						.showPriceOfWaresInThisGroup(GroupTableModel.getProductGroup(ProductsWindow.getIndex())));
				ProductsWindow.getTotalCost().setText(help);
				ProductsWindow.getFrmProductsWindow().setEnabled(true);
				frmProductReducer.dispose();
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
				frmProductReducer.dispose();
			}
		});
		buttonPanel.add(btnBack);
	}

}
