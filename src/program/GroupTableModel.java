package program;
/**
 * File name: GroupTableModel.java
 * ==========================================
 * This class serves as a model for the table
 * that displays product groups in the window
 * which initializes in "GroupWindow" class.
 * Here we also have methods for working with
 * product groups and for working with table.
 */
import java.util.*;
import javax.swing.table.*;

public class GroupTableModel extends AbstractTableModel {

	private static ArrayList<ProductGroup> productGroups = new ArrayList<>(); // ArrayList with all product groups.
	private String[] columnNames = { "Group of products", "Description" }; // Column names.

	/**
	 * Constructor.
	 */
	public GroupTableModel() {
		productGroups = PullFileInfo.takeListOfProductGroups();
	}

	/**
	 * This method sets number of columns in the table.
	 * @return number of columns.
	 */
	@Override
	public int getColumnCount() {
		return 2;
	}

	/**
	 * This method sets names of columns.
	 * @return name of column.
	 */
	public String getColumnName(int col) {
		return columnNames[col];
	}

	/**
	 * This method sets number of rows in the table.
	 * @return number of rows.
	 */
	@Override
	public int getRowCount() {
		return productGroups.size();
	}

	/**
	 * This method sets values for cells of the table,
	 * also it returns value of the specific cell.
	 * @return value of the specific cell.
	 */
	@Override
	public String getValueAt(int row, int column) {
		if (column == 0)
			return ((String) productGroups.get(row).getName());
		if (column == 1)
			return ((String) productGroups.get(row).getDescription());
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
		ProductGroup productGroup = getProductGroup(row);
		switch (column) {
		case 0:
			productGroup.setName((String) value);
			break;
		case 1:
			productGroup.setDescription((String) value);
			break;
		}
		fireTableCellUpdated(row, column);
	}

	/**
	 * This method returns product group, which 
	 * corresponds to the chosen row of the table.
	 * @param row number of chosen row of the table.
	 * @return product group which corresponds to the chosen row.
	 */
	public static ProductGroup getProductGroup(int row) {
		return productGroups.get(row);
	}

	/**
	 * This method adds product group to the ArrayList of all
	 * product groups and, at the same time, to the table
	 * where all product groups are displayed.
	 * @param productGroup product group that you want to add.
	 */
	public void addProductGroup(ProductGroup productGroup) {
		insertProductGroup(getRowCount(), productGroup);
	}

	/**
	 * This method allows to add new product groups to the list of all product groups and to the table.
	 * Used as a part of "addProductGroup" method.
	 * @param row number of row where you want to place your new product group.
	 * @param productGroup product group that you want to add.
	 */
	public void insertProductGroup(int row, ProductGroup productGroup) {
		productGroups.add(row, productGroup);
		fireTableRowsInserted(row, row);
	}

	/**
	 * This method removes product group which 
	 * corresponds to the chosen row of the table and
	 * also it removes this product group from the list 
	 * of all product groups.
	 * @param row number of chosen row of the table.
	 */
	public void removeProductGroup(int row) {
		productGroups.remove(row);
		fireTableRowsDeleted(row, row);
	}

	/**
	 * This method edits product group which corresponds to the chosen row of the table.
	 * @param row number of the chosen row of the table.
	 * @param name new name of the product group which corresponds to the chosen row.
	 * @param description new description of the product group which corresponds to the chosen row.
	 */
	public void editProductGroup(int row, String name, String description) {
		productGroups.get(row).setName(name);
		productGroups.get(row).setDescription(description);
		fireTableRowsUpdated(row, row);
	}

	/**
	 * This method checks whether the product group with the given name
	 * is present in the list of all product groups.
	 * @param name name of the product group
	 * @return true - if product group with such name is present in the list,
	 * otherwise we will return false.
	 */
	public boolean checkName(String name) {
		boolean present = false;
		for (int i = 0; i < productGroups.size(); i++) {
			if (productGroups.get(i).getName().equals(name)) {
				present = true;
				break;
			}
		}
		return present;
	}

	
	/**
	 * This method checks if the product with the given name exists,
	 * since we cannot have two products with similar names.
	 * @param input name of the product
	 * @return null - if such product doesn't exist, otherwise we will return name of the
	 * product group where such product exists.
	 */
	public static String lookForCoincidenceOfNames(String input) {
		String check = null;
		ArrayList<Product> listOfAllProducts = showAllStatistics();
		for (int i = 0; i < listOfAllProducts.size(); i++) {
			if (input.equals(listOfAllProducts.get(i).getName())) {
				check = listOfAllProducts.get(i).getGroupName();
				break;
			}
		}
		return check;
	}

	/**
	 * This method searches for a product with the given name.
	 * @param input name of the product.
	 * @return "Product not found" if a product with given name wasn't found,
	 * otherwise we will return information about it.
	 */
	public static String searchAWare(String input) {
		String answer = "Product not found";
		for (int j = 0; j < productGroups.size(); j++) {
			answer = searchAWareInTheGroup(input, answer, productGroups.get(j).getProductList());
			if (!answer.equals("Product not found")) {
				answer = "Group of products where this product is situated: " + productGroups.get(j).getName() + answer;
				break;
			}
		}
		return answer;
	}

	/**
	 * This method searches for the product with the given name in product groups.
	 * @param input name of the product
	 * @param answer information about this product if it was found.
	 * @param productList list of all product groups
	 * @return information about product with the given name in case if it was found.
	 */
	public static String searchAWareInTheGroup(String input, String answer, ArrayList<Product> productList) {
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getName().equals(input)) {
				answer = "\nFound product has such parameters:\nName: " + productList.get(i).getName()
						+ "\nDescription: " + productList.get(i).getDescription() + "\nManufacturer: "
						+ productList.get(i).getManufacturer() + "\nQuantity: " + productList.get(i).getQuantity()
						+ "\nPrice: " + productList.get(i).getPrice();
				break;
			}
		}
		return answer;
	}

	/**
	 * This method makes a list of all products.
	 * @return ArrayList with all products.
	 */
	public static ArrayList<Product> showAllStatistics() {
		ArrayList<Product> statistics = new ArrayList<Product>();
		for (int i = 0; i < productGroups.size(); i++) {
			ArrayList<Product> list = productGroups.get(i).getProductList();
			for (int j = 0; j < list.size(); j++) {
				Product currentProduct = list.get(j);
				currentProduct.setGroup(productGroups.get(i).getName());
				statistics.add(currentProduct);
			}
		}
		return statistics;
	}

	/**
	 * This method counts total cost of all products.
	 * @return total cost of all products.
	 */
	public static double showPriceOfWaresInThisWarehouse() {
		double sum = 0;
		for (int j = 0; j < productGroups.size(); j++) {
			sum = sum + showPriceOfWaresInThisGroup(productGroups.get(j));
		}
		return sum;
	}

	/**
	 * This method counts total cost of products in the given product group.
	 * @param pg product group whose total cost of products should be counted.
	 * @return total cost of products in the given product group.
	 */
	public static double showPriceOfWaresInThisGroup(ProductGroup pg) {
		double sum = 0;
		ArrayList<Product> productList = pg.getProductList();
		for (int i = 0; i < productList.size(); i++) {
			sum = sum + productList.get(i).getPrice() * productList.get(i).getQuantity();
		}
		return sum;
	}

	/**
	 * This method converts given double number to string
	 * with some changes for better appearance.
	 * @param number number that needs to be converted.
	 * @return given number converted to string.
	 */
	public static String converseToString(double number) {
		String answer = "";
		String stringForWork = (int) number + "";
		for (int i = 1; i < stringForWork.length() + 1; i++) {
			if (i % 3 == 1 && i != 1)
				answer = stringForWork.charAt(stringForWork.length() - i) + "," + answer;
			else
				answer = stringForWork.charAt(stringForWork.length() - i) + answer;
		}
		String fractionalPart = String.format("%.2f", number);
		answer = answer + "." + fractionalPart.charAt(fractionalPart.length() - 2)
				+ fractionalPart.charAt(fractionalPart.length() - 1);

		return answer;
	}
	
	/**
	 * This method returns list of all product groups.
	 * @return ArrayList of all product groups.
	 */
	public static ArrayList<ProductGroup> getProductGroups() {
		return productGroups;
	}

}
