package program;
/**
 * File name: ProductsWindow.java
 * ===============================================
 * This class initializes window that allows you to 
 * edit list of products of the chosen product group by 
 * adding, editing, etc. them. It also allows you to 
 * add or reduce the amount of a specific product.
 * Also this window is the main one.
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

public class ProductsWindow {

	private static JFrame frmProductsWindow;
	private static JTable productsTable; // Table where products will be displayed.
	private JScrollPane scrollPaneForTabele; // Scroll pane for the table.
	private static JTextField totalCost; // Text field where total cost of products will be displayed.
	private JLabel lblTotalCostOfProducts; // Label for the total cost of products.
	private static int index; // Index of the product group to which belong products displayed in the table.

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void build(int index, double totalCostOfP) {
		ProductsWindow.index = index;
		initialize(index, totalCostOfP);
		frmProductsWindow.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int index, double totalCostOfP) {
		frmProductsWindow = new JFrame();
		frmProductsWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				GroupsWindow.getFrmGroupsWindow().setVisible(true);
			}
		});
		frmProductsWindow.setTitle("List of products of " + GroupTableModel.getProductGroup(index).getName());
		frmProductsWindow.setBounds(100, 100, 1180, 500);
		frmProductsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProductsWindow.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmProductsWindow.getContentPane().add(panel, BorderLayout.SOUTH);

		/*
		 * By pressing this button you will be able
		 * to add new product. New product will be displayed in
		 * the table and in the array list of products of the
		 * product group. 
		 */
		JButton addButton = new JButton("Add product");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductAdder productAdder = new ProductAdder();
				productAdder.build(index);
				frmProductsWindow.setEnabled(false);
			}
		});
		panel.add(addButton);

		JSeparator separator = new JSeparator();
		panel.add(separator);

		/*
		 * By pressing this button you will be able
		 * to edit a chosen product. Changes will be displayed in
		 * the table and in the array list of products of the
		 * product group. 
		 */
		JButton editButton = new JButton("Edit product");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (productsTable.getSelectionModel().isSelectionEmpty() != true) {
					ProductEditor productEditor = new ProductEditor();
					productEditor.build(index);
					frmProductsWindow.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(frmProductsWindow, "Choose a product first", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel.add(editButton);

		JSeparator separator_1 = new JSeparator();
		panel.add(separator_1);

		/*
		 * By pressing this button you will be able
		 * to remove a chosen product. Changes will be displayed in
		 * the table and in the array list of products of the
		 * product group. 
		 */
		JButton removeButton = new JButton("Remove product");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (productsTable.getSelectionModel().isSelectionEmpty() != true) {
					int result = JOptionPane.showConfirmDialog(frmProductsWindow,
							"Are you sure you want to delete this product?");
					if (result == JOptionPane.OK_OPTION) {
						GroupTableModel.getProductGroup(index).removeProduct(productsTable.getSelectedRow());
						String help = "" + GroupTableModel.converseToString(
								GroupTableModel.showPriceOfWaresInThisGroup(GroupTableModel.getProductGroup(index)));
						ProductsWindow.getTotalCost().setText(help);
					}
				} else {
					JOptionPane.showMessageDialog(frmProductsWindow, "Choose a product first", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel.add(removeButton);

		/*
		 * By pressing this button you will be able
		 * to increase the quantity of a chosen product. Changes will be displayed in
		 * the table and in the array list of products of the
		 * product group. 
		 */
		JButton btnIncreaser = new JButton("Increase quantity of a product");
		btnIncreaser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (productsTable.getSelectionModel().isSelectionEmpty() != true) {
					ProductIncreaser productIncreaser = new ProductIncreaser();
					productIncreaser.build(index);
					frmProductsWindow.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(frmProductsWindow, "Choose a product first", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel.add(btnIncreaser);

		/*
		 * By pressing this button you will be able
		 * to reduce the quantity of a chosen product. Changes will be displayed in
		 * the table and in the array list of products of the
		 * product group. 
		 */
		JButton btnReducer = new JButton("Reduce quantity of a product");
		btnReducer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (productsTable.getSelectionModel().isSelectionEmpty() != true) {
					if ((Integer) GroupTableModel.getProductGroup(index).getValueAt(productsTable.getSelectedRow(),
							3) > 0) {
						ProductReducer productReducer = new ProductReducer();
						productReducer.build(index);
						frmProductsWindow.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(frmProductsWindow, "You can't reduce quantity as it is 0.",
								"Attention", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frmProductsWindow, "Choose a product first", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel.add(btnReducer);

		JSeparator separator_2 = new JSeparator();
		panel.add(separator_2);

		/*
		 * By pressing this button you will close this window.
		 */
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmProductsWindow.dispose();
				GroupsWindow.getFrmGroupsWindow().setVisible(true);
			}
		});
		panel.add(backButton);

		lblTotalCostOfProducts = new JLabel("Total cost of products ");
		panel.add(lblTotalCostOfProducts);

		totalCost = new JTextField();
		String help = "" + GroupTableModel.converseToString(totalCostOfP);
		lblTotalCostOfProducts.setLabelFor(totalCost);
		totalCost.setEditable(false);
		totalCost.setText(help);
		panel.add(totalCost);
		totalCost.setColumns(15);

		productsTable = new JTable(GroupTableModel.getProductGroup(index));
		productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneForTabele = new JScrollPane(productsTable);
		frmProductsWindow.getContentPane().add(scrollPaneForTabele, BorderLayout.CENTER);
	}

	/**
	 * This method returns frame of this window.
	 * @return frame of this window.
	 */
	public static JFrame getFrmProductsWindow() {
		return frmProductsWindow;
	}

	/**
	 * This method returns table used to display products.
	 * @return table used to display products.
	 */
	public static JTable getProductsTable() {
		return productsTable;
	}

	/**
	 * This method returns text field which contains total cost of products.
	 * @return text field which contains total cost of products.
	 */
	public static JTextField getTotalCost() {
		return totalCost;
	}

	/**
	 * This method returns index of the product group to which products displayed in the table belong.
	 * @return index of the product group to which products displayed in the table belong.
	 */
	public static int getIndex() {
		return index;
	}

}
