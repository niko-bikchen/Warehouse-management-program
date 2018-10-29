package program;
/**
 * File name: ProductEditor.java
 * ====================================================
 * This class initializes the window that  
 * allows you to edit a chosen product.
 */
import javax.swing.*;
import java.awt.event.*;

public class ProductEditor {

	private JButton btnConfirm; // Confirm button
	private JFrame frmProductEditor;
	private JTextField productNameField; // Text field for product name.
	private JTextField producrDescField; // Text field for product description.
	private JTextField productManufField; // Text field for product manufacturer.
	private JTextField productPrField; // Text field for product's price.
	private String name, desc, manufacturer, help, help_2; // Variables for product's name, manufacturer and some helping variables.
	private double price, num_2; // Variable for product's price and a helping variable.

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void build(int index) {
		initialize(index);
		frmProductEditor.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int index) {
		frmProductEditor = new JFrame();
		frmProductEditor.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				ProductsWindow.getFrmProductsWindow().setEnabled(true);
			}
		});
		frmProductEditor.setTitle("Edit product");
		frmProductEditor.setBounds(100, 100, 600, 300);
		frmProductEditor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProductEditor.getContentPane().setLayout(new BoxLayout(frmProductEditor.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panelForName = new JPanel();
		frmProductEditor.getContentPane().add(panelForName);

		JLabel lblNameOfThe = new JLabel("Name of the new product: ");
		panelForName.add(lblNameOfThe);

		productNameField = new JTextField();
		productNameField.setText((String) GroupTableModel.getProductGroup(index)
				.getValueAt(ProductsWindow.getProductsTable().getSelectedRow(), 0));
		help = productNameField.getText();
		productNameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				producrDescField.requestFocus();
			}
		});
		panelForName.add(productNameField);
		productNameField.setColumns(20);

		JPanel panelForDesc = new JPanel();
		frmProductEditor.getContentPane().add(panelForDesc);

		JLabel lblDescriptionOfThe = new JLabel("Description of the name product: ");
		panelForDesc.add(lblDescriptionOfThe);

		producrDescField = new JTextField();
		producrDescField.setText((String) GroupTableModel.getProductGroup(index)
				.getValueAt(ProductsWindow.getProductsTable().getSelectedRow(), 1));
		producrDescField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				productManufField.requestFocus();
			}
		});
		panelForDesc.add(producrDescField);
		producrDescField.setColumns(30);

		JPanel panelForManufact = new JPanel();
		frmProductEditor.getContentPane().add(panelForManufact);

		JLabel lblManufacturerOfThe = new JLabel("Manufacturer of the new product: ");
		panelForManufact.add(lblManufacturerOfThe);

		productManufField = new JTextField();
		productManufField.setText((String) GroupTableModel.getProductGroup(index)
				.getValueAt(ProductsWindow.getProductsTable().getSelectedRow(), 2));
		productManufField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				productPrField.requestFocus();
			}
		});
		panelForManufact.add(productManufField);
		productManufField.setColumns(20);
		
		JPanel panelForPrice = new JPanel();
		frmProductEditor.getContentPane().add(panelForPrice);

		JLabel lblPriceOfThe = new JLabel("Price of the new product");
		panelForPrice.add(lblPriceOfThe);

		productPrField = new JTextField();
		num_2 = (Double) GroupTableModel.getProductGroup(index)
				.getValueAt(ProductsWindow.getProductsTable().getSelectedRow(), 4);
		help_2 = "" + num_2;
		productPrField.setText(help_2);
		productPrField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnConfirm.requestFocus();
			}
		});
		panelForPrice.add(productPrField);
		productPrField.setColumns(10);

		JPanel buttonPanel = new JPanel();
		frmProductEditor.getContentPane().add(buttonPanel);

		/*
		 * In case if all the entered data is correct, by pressing this button you 
		 * will edit chosen product. Changes will be added to the table and to
		 * the array list of all product of the product group.
		 */
		btnConfirm = new JButton("CONFIRM");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				name = productNameField.getText();
				if (!name.equals("") && name.trim().length() > 0) {
					if (name.equals(help)) {
						desc = producrDescField.getText();
						manufacturer = productManufField.getText();
						try {
							price = Double.parseDouble(productPrField.getText());
							if (price > 0) {
								GroupTableModel.getProductGroup(index).editProduct(
										ProductsWindow.getProductsTable().getSelectedRow(), name, desc, manufacturer, price);
								String help = ""
										+ GroupTableModel.converseToString(GroupTableModel.showPriceOfWaresInThisGroup(
												GroupTableModel.getProductGroup(ProductsWindow.getIndex())));
								ProductsWindow.getTotalCost().setText(help);
								frmProductEditor.dispose();
								ProductsWindow.getFrmProductsWindow().setEnabled(true);
							} else {
								JOptionPane.showMessageDialog(frmProductEditor,
										"Invalid input for quantity or price.\n" + "Please, check this text fields.",
										"Attention", JOptionPane.ERROR_MESSAGE);
							}

						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(frmProductEditor,
									"Invalid input for price.\n" + "Please, check this text field.",
									"Attention", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						if (GroupTableModel.lookForCoincidenceOfNames(name) == null) {
							desc = producrDescField.getText();
							manufacturer = productManufField.getText();
							try {
								price = Double.parseDouble(productPrField.getText());
								if (price > 0) {
									GroupTableModel.getProductGroup(index).editProduct(
											ProductsWindow.getProductsTable().getSelectedRow(), name, desc,
											manufacturer, price);
									String help = "" + GroupTableModel
											.converseToString(GroupTableModel.showPriceOfWaresInThisGroup(
													GroupTableModel.getProductGroup(ProductsWindow.getIndex())));
									ProductsWindow.getTotalCost().setText(help);
									frmProductEditor.dispose();
									ProductsWindow.getFrmProductsWindow().setEnabled(true);
								} else {
									JOptionPane.showMessageDialog(frmProductEditor,
											"Invalid input for price.\n"
													+ "Please, check this text field.",
											"Attention", JOptionPane.ERROR_MESSAGE);
								}
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(frmProductEditor,
										"Invalid input for quantity or price.\n" + "Please, check this text fields.",
										"Attention", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(frmProductEditor,
									"Product with such name alredy exists in the group "
											+ GroupTableModel.lookForCoincidenceOfNames(name) + ".\n"
											+ "Enter different name.",
									"Attention", JOptionPane.ERROR_MESSAGE);
						}
					}
				} else {
					JOptionPane.showMessageDialog(frmProductEditor, "Invalid input for name.", "Attention",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonPanel.add(btnConfirm);
		
		/*
		 * Pressing this button will close this window.
		 */
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmProductEditor.dispose();
				ProductsWindow.getFrmProductsWindow().setEnabled(true);
			}
		});
		buttonPanel.add(btnCancel);
	}

}
