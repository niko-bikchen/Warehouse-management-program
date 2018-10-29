package program;
/**
 * File name: AllProductsWindow.java
 * ==================================================
 * This class initializes the window where the list
 * of all existing products will be displayed. In this
 * window will be also displayed total cost of
 * all products.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AllProductsWindow {

	private JFrame frmListOfAllProducts;
	private JTable tableOfAllProducts;
	private JTextField totalCost;
	private ModelForAllProducts allProductsModel;
	private JScrollPane scrollForTable;
	private JButton btnBack;
	private Component horizontalGlue;

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public void build() {
		initialize();
		frmListOfAllProducts.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListOfAllProducts = new JFrame();
		frmListOfAllProducts.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				GroupsWindow.getFrmGroupsWindow().setEnabled(true);
			}
		});
		frmListOfAllProducts.setTitle("List of all products");
		frmListOfAllProducts.setBounds(100, 100, 1150, 500);
		frmListOfAllProducts.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		allProductsModel = new ModelForAllProducts();
		tableOfAllProducts = new JTable(allProductsModel);
		scrollForTable = new JScrollPane(tableOfAllProducts);
		frmListOfAllProducts.getContentPane().add(scrollForTable, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frmListOfAllProducts.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JLabel lblTotalCostOf = new JLabel("Total cost of all products in the warehouse: ");
		panel.add(lblTotalCostOf);
		
		totalCost = new JTextField();
		String help = GroupTableModel.converseToString(GroupTableModel.showPriceOfWaresInThisWarehouse());
		totalCost.setText(help);
		lblTotalCostOf.setLabelFor(totalCost);
		totalCost.setEditable(false);
		panel.add(totalCost);
		totalCost.setColumns(20);
		
		/*
		 * Pressing this button will close this window.
		 */
		btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmListOfAllProducts.dispose();
				GroupsWindow.getFrmGroupsWindow().setEnabled(true);
			}
		});
		
		horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		panel.add(btnBack);
	}

}
