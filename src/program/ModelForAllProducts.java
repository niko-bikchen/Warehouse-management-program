package program;
/**
 * File name: ModelForAllProducts.java
 * =====================================================
 * This class serves as a model for the table that shows
 * all products.
 */
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class ModelForAllProducts extends AbstractTableModel {
	private ArrayList<Product> allProducts = GroupTableModel.showAllStatistics(); // ArrayList with all products.
	// Column names.
	private String[] columnNames = {"Product group", "Name", "Description", "Manufacturer", "Quantity", "Price" };

	/**
	 * This method sets the number of columns of the table.
	 * @return number of columns.
	 */
	@Override
	public int getColumnCount() {
		return 6;
	}

	/**
	 * This method sets number of rows of the table.
	 * @return number of rows.
	 */
	@Override
	public int getRowCount() {
		return allProducts.size();
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
			return allProducts.get(row).getGroupName();
		case 1:
			return allProducts.get(row).getName();
		case 2:
			return allProducts.get(row).getDescription();
		case 3:
			return allProducts.get(row).getManufacturer();
		case 4:
			return allProducts.get(row).getQuantity();
		case 5: 
			return allProducts.get(row).getPrice();
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
	 * This method makes sure that the data in each
	 * cell is displayed correctly, according to its type.
	 */
	public String getColumnName(int col) {
		return columnNames[col];
	}

}
