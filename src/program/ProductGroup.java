package program;
/**
 * File name: ProductGroup.java
 * ===========================================================
 * This class describes product group and serves as a model
 * for the table where all products of a specific product group
 * are shown. Here we also have methods for working with the fields
 * of product group.
 */
import java.util.*;
import javax.swing.table.AbstractTableModel;

public class ProductGroup extends AbstractTableModel {
	private ArrayList<Product> productList = new ArrayList<>(); // ArrayList of all products of the product group.
	// Column names
	private String[] columnNames = { "Name", "Description", "Manufacturer", "Quantity", "Price" };
	private String name, description;

	public ProductGroup(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	/**
	 * This method adds product to the list of products. 
	 * @param product product you want to add.
	 */
	public void addProduct(Product product) {
		productList.add(product);
	}

	/**
	 * This method makes sure that the data in each
	 * cell is displayed correctly, according to its type.
	 */
	public String getColumnName(int col) {
		return columnNames[col];
	}

	/**
	 * Getter for the name of the product group.
	 * @return name of the product group.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the name of the product group.
	 * @param name new name of the product group.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the description of the product group.
	 * @return description of the product group.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter for the description of the product group
	 * @param description new description of the product group.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This method sets the number of columns of the table.
	 * @return number of columns.
	 */
	@Override
	public int getColumnCount() {
		return 5;
	}

	/**
	 * This method sets number of rows of the table.
	 * @return number of rows.
	 */
	@Override
	public int getRowCount() {
		return productList.size();
	}

	/**
	 * This method sets values for cells of the table,
	 * also it returns value of the specific cell.
	 * @return value of the specific cell.
	 */
	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return productList.get(row).getName();
		case 1:
			return productList.get(row).getDescription();
		case 2:
			return productList.get(row).getManufacturer();
		case 3:
			return productList.get(row).getQuantity();
		case 4:
			return productList.get(row).getPrice();
		}
		return null;
	}

	/**
	 * This method makes sure that the data in each
	 * cell is displayed correctly, according to its type.
	 */
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/**
	 * This method sets new value for the given cell of the table.
	 */
	@Override
	public void setValueAt(Object value, int row, int column) {
		Product product = getProduct(row);
		switch (column) {
		case 0:
			product.setName((String) value);
			break;
		case 1:
			product.setDescription((String) value);
			break;
		case 2:
			product.setManufacturer((String) value);
			break;
		case 3:
			product.setQuantity((Integer) value);
			break;
		case 4:
			product.setPrice((Double) value);
			break;
		}
		fireTableCellUpdated(row, column);
	}

	/**
	 * This method returns product, which 
	 * corresponds to the chosen row of the table.
	 * @param row number of chosen row of the table.
	 * @return product which corresponds to the chosen row.
	 */
	public Product getProduct(int row) {
		return productList.get(row);
	}

	/**
	 * This method adds product to the ArrayList of all
	 * product groups and, at the same time, to the table
	 * where all products are displayed.
	 * @param product product that you want to add.
	 */
	public void addProductToTable(Product product) {
		insertProduct(getRowCount(), product);
	}

	/**
	 * This method allows to add new products to the list of products and to the table. 
	 * Used as a part of "addProductToTable" method.
	 * @param row number of row where you want to place your new product group.
	 * @param product product that you want to add.
	 */
	public void insertProduct(int row, Product product) {
		productList.add(row, product);
		fireTableRowsInserted(row, row);
	}

	/**
	 * This method removes product which 
	 * corresponds to the chosen row of the table and
	 * also it removes this product from the list 
	 * of products.
	 * @param row number of chosen row of the table.
	 */
	public void removeProduct(int row) {
		productList.remove(row);
		fireTableRowsDeleted(row, row);
	}

	/**
	 * This method edits product which corresponds to the chosen row of the table.
	 * @param row number of the chosen row of the table.
	 * @param name new name of the product group which corresponds to the chosen row.
	 * @param description new description of the product group which corresponds to the chosen row.
	 * @param manufacturer new manufacturer of the product.
	 * @param price new price of the product.
	 */
	public void editProduct(int row, String name, String description, String manufacturer, double price) {
		productList.get(row).setName(name);
		productList.get(row).setDescription(description);
		productList.get(row).setManufacturer(manufacturer);
		productList.get(row).setPrice(price);
		fireTableRowsUpdated(row, row);
	}

	/**
	 * This method returns list of product of the product group.
	 * @return list of products
	 */
	public ArrayList<Product> getProductList() {
		return productList;
	}

	/**
	 * toString for printing out information about product group.
	 */
	@Override
	public String toString() {
		return name + ", " + description;
	}
}
