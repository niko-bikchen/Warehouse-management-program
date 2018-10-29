package program;
/**
 * File name: ProductAdder.java
 * ====================================================
 * This class initializes the window that  
 * allows you to add a new product.
 */
import javax.swing.*;
import java.awt.event.*;

public class ProductAdder {

	private JButton btnConfirm; // Confirm button
	private JFrame frmProductAdder;
	private JTextField productNameField; // Text field for product name. 
	private JTextField producrDescField; // Text field for product description.
	private JTextField productManufField; // Text field for product manufacturer.
	private JTextField productQuantField; // Text field for product initial quantity.
	private JTextField productPrField; // Text field for product's price.
	private String name, desc, manufacturer; // Variables for product's name, manufacturer and description.
	private int quantity; // Variable for product's quantity.
	private double price; // Variable for product's price.

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void build(int index) {
		initialize(index);
		frmProductAdder.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int index) {
		frmProductAdder = new JFrame();
		frmProductAdder.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				ProductsWindow.getFrmProductsWindow().setEnabled(true);
			}
		});
		frmProductAdder.setTitle("Add product");
		frmProductAdder.setBounds(100, 100, 600, 300);
		frmProductAdder.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProductAdder.getContentPane().setLayout(new BoxLayout(frmProductAdder.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panelForName = new JPanel();
		frmProductAdder.getContentPane().add(panelForName);

		JLabel lblNameOfThe = new JLabel("Name of the new product: ");
		panelForName.add(lblNameOfThe);

		productNameField = new JTextField();
		productNameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				producrDescField.requestFocus();
			}
		});
		panelForName.add(productNameField);
		productNameField.setColumns(20);

		JPanel panelForDescr = new JPanel();
		frmProductAdder.getContentPane().add(panelForDescr);

		JLabel lblDescriptionOfThe = new JLabel("Description of the name product: ");
		panelForDescr.add(lblDescriptionOfThe);

		producrDescField = new JTextField();
		producrDescField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				productManufField.requestFocus();
			}
		});
		panelForDescr.add(producrDescField);
		producrDescField.setColumns(30);

		JPanel panelForManufact = new JPanel();
		frmProductAdder.getContentPane().add(panelForManufact);

		JLabel lblManufacturerOfThe = new JLabel("Manufacturer of the new product: ");
		panelForManufact.add(lblManufacturerOfThe);

		productManufField = new JTextField();
		productManufField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				productQuantField.requestFocus();
			}
		});
		panelForManufact.add(productManufField);
		productManufField.setColumns(20);

		JPanel panelForQuant = new JPanel();
		frmProductAdder.getContentPane().add(panelForQuant);

		JLabel lblQuantityOfThe = new JLabel("Quantity of the new product: ");
		panelForQuant.add(lblQuantityOfThe);

		productQuantField = new JTextField();
		productQuantField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				productPrField.requestFocus();
			}
		});
		panelForQuant.add(productQuantField);
		productQuantField.setColumns(10);

		JPanel panelForPrice = new JPanel();
		frmProductAdder.getContentPane().add(panelForPrice);

		JLabel lblPriceOfThe = new JLabel("Price of the new product");
		panelForPrice.add(lblPriceOfThe);

		productPrField = new JTextField();
		productPrField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnConfirm.requestFocus();
			}
		});
		panelForPrice.add(productPrField);
		productPrField.setColumns(10);

		JPanel buttonPanel = new JPanel();
		frmProductAdder.getContentPane().add(buttonPanel);

		/*
		 * In case if all the entered data is correct, by pressing this button you 
		 * will create new product, which will be also added to the table and to
		 * the array list of products of the product group.
		 */
		btnConfirm = new JButton("CONFIRM");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				name = productNameField.getText();
				desc = producrDescField.getText();
				manufacturer = productManufField.getText();
				if (!name.equals("") && name.trim().length() > 0) {
					try {
						quantity = Integer.parseInt(productQuantField.getText());
						price = Double.parseDouble(productPrField.getText());
						if (quantity > 0 && price > 0) {
							if (GroupTableModel.lookForCoincidenceOfNames(name) == null) {
								GroupTableModel.getProductGroup(index)
										.addProductToTable(new Product(name, desc, manufacturer, quantity, price));
								String help = ""
										+ GroupTableModel.converseToString(GroupTableModel.showPriceOfWaresInThisGroup(
												GroupTableModel.getProductGroup(ProductsWindow.getIndex())));
								ProductsWindow.getTotalCost().setText(help);
								frmProductAdder.dispose();
								ProductsWindow.getFrmProductsWindow().setEnabled(true);
							} else {
								JOptionPane.showMessageDialog(frmProductAdder,
										"Product with such name alredy exists in the group "
												+ GroupTableModel.lookForCoincidenceOfNames(name) + ".\n"
												+ "Enter different name.",
										"Attention", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(frmProductAdder,
									"Invalid input for quantity or price.\n" + "Please, check this text fields.",
									"Attention", JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(frmProductAdder,
								"Invalid input for quantity or price.\n" + "Please, check this text fields.",
								"Attention", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frmProductAdder, "Invalid input for name.", "Attention",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonPanel.add(btnConfirm);
		
		/*
		 * By pressing this button you will close this window.
		 */
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmProductAdder.dispose();
				ProductsWindow.getFrmProductsWindow().setEnabled(true);
			}
		});
		buttonPanel.add(btnCancel);
	}

}
