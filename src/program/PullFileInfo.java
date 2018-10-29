package program;
/**
 * File name: PullFileInfo.java
 * ==========================================
 * This class pulls information about product
 * groups and products from files so that we
 * could use it in the program.
 */
import java.util.*;
import java.io.*;

public class PullFileInfo {
	// List of product groups that we make of information from files.
	private static ArrayList<ProductGroup> productGroups = new ArrayList<>(); 

	/**
	 * This method receives path to the file with information about product groups
	 * and uses it to fill the "productGroups" array with "ProductGroup" objects.
	 * @param filePath path to the file with information about groups.
	 * @throws IOException
	 */
	public static void pullProductGroupList(String filePath) throws IOException {
		String name, description, help;
		String[] words;
		BufferedReader bReader = openFile(filePath);
		while (true) {
			help = bReader.readLine();
			if (help == null)
				break;
			words = help.split(", ");
			name = words[0];
			description = words[1];
			if (checkGroupNameInTheInput(name) == false)
				productGroups.add(new ProductGroup(name, description));
		}
	}

	/**
	 * This method receives path to the file with information about products
	 * and uses it to fill the list of products of each product group
	 * from "productGroups".
	 * @param filePath path to the file with information about products.
	 * @throws IOException
	 */
	public static void pullProductsLists(String filePath) throws IOException {
		String name, description, manufacturer, help;
		int quantity;
		double price;
		String[] words;
		BufferedReader bReader = openFile(filePath);
		while (true) {
			help = bReader.readLine();
			if (help == null)
				break;
			words = help.split(", ");
			name = words[1];
			description = words[2];
			manufacturer = words[3];
			quantity = Integer.parseInt(words[4]);
			price = Double.parseDouble(words[5]);
			if (checkNameOfProductInTheInput(name) == false)
				if(findProductGroup(words[0]) != -1)
					productGroups.get(findProductGroup(words[0]))
							.addProduct(new Product(name, description, manufacturer, quantity, price));

		}
	}

	/**
	 * This method returns "productGroups" list filled with product groups and products objects
	 * made from the information from files.
	 * @return "productGroups" list filled with product groups and products objects
	 * made from the information from files.
	 */
	public static ArrayList<ProductGroup> takeListOfProductGroups() {
		return productGroups;
	}

	/**
	 * This method "opens" file, so we could work with it
	 * @param filePath path to the file that needs to be opened.
	 * @return BufferedReader object that we use to work with the file.
	 */
	private static BufferedReader openFile(String filePath) {
		BufferedReader br = null;
		while (br == null) {
			try {
				br = new BufferedReader(new FileReader(filePath));
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			}
		}
		return br;
	}

	/**
	 * This method looks for the product group in the "productGroups"
	 * and returns index of this group if it's present in the list.
	 * @param name name of the product group
	 * @return index of the product group if such group is present in the list,
	 * otherwise, we will return -1;
	 */
	private static int findProductGroup(String name) {
		for (int i = 0; i < productGroups.size(); i++) {
			if (productGroups.get(i).getName().equals(name))
				return i;
		}
		return -1;
	}

	/**
	 * Checks whether the product group with the given name 
	 * is present in "productGroups".
	 * @param inputName name of the product group
	 * @return true - if such group is present in the list,
	 * otherwise, we will return false.
	 */
	public static boolean checkGroupNameInTheInput(String inputName) {
		boolean result = false;
		for (int i = 0; i < productGroups.size(); i++) {
			if (inputName.equals(productGroups.get(i).getName())) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * This method checks whether the product with the given name
	 * already exists in one of the product groups.
	 * @param inputName name of the product
	 * @return true - if product with such name exists, otherwise,
	 * we will return false.
	 */
	public static boolean checkNameOfProductInTheInput(String inputName) {
		boolean result = false;
		for (int j = 0; j < productGroups.size(); j++) {
			if (result == true)
				break;
			ArrayList<Product> list = productGroups.get(j).getProductList();
			result = checkNameOfProductInTheGroup(result, list, inputName);

		}
		return result;
	}

	/**
	 * This method looks through the list of products of the product group
	 * and checks whether the product with the given name is present in this list.
	 * @param result indicates whether the product with the given name is present in the list 
	 * of products of the product group.
	 * @param list list of products of a specific product group.
	 * @param inputName name of the product.
	 * @return true - if product with such name is already present in the list, otherwise,
	 * we will return false.
	 */
	public static boolean checkNameOfProductInTheGroup(boolean result, ArrayList<Product> list, String inputName) {
		for (int i = 0; i < list.size(); i++) {
			if (inputName.equals(list.get(i).getName())) {
				result = true;
				break;
			}
		}
		return result;
	}

}