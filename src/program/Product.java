package program;
/**
 * File name: Product.java
 * ========================================================================
 * This class describes product and has methods for working with its fields.
 */
public class Product {
	private String name, description, manufacturer, groupName;
	private int quantity;
	private double price;

	public Product(String name, String description, String manufacturer, int quantity, double price) {
		this.name = name;
		this.description = description;
		this.manufacturer = manufacturer;
		this.quantity = quantity;
		this.price = price;
	}

	/**
	 * Getter for the name.
	 * @return name of the product.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the name.
	 * @param name new name of the product.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the description.
	 * @return description of the product.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter for the description.
	 * @param description new description of the product.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter for the manufacturer.
	 * @return name of the manufacturer.
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Setter for the manufacturer.
	 * @param manufacturer new name of the manufacturer.
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Getter for the quantity of the product.
	 * @return quantity of the product.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Setter for the quantity of the product.
	 * @param quantity new quantity of the product.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Getter for the price of the product.
	 * @return price of the product.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Setter for the price of the product.
	 * @param price new price of the product.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Setter for the product group to which this product belongs.
	 * @param name2 name of the product group.
	 */
	public void setGroup(String name2) {
		groupName = name2;
	}

	/**
	 * Getter for the name of the product group to which this product belongs.
	 * @return name of the product group.
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * toString method for printing out information about the product.
	 */
	@Override
	public String toString() {
		return groupName + ", " + name + ", " + description + ", " + manufacturer + ", " + quantity + ", " + price;
	}

}
