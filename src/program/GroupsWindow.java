package program;
/**
 * File name: GroupsWindow.java
 * ============================================
 * This class initializes the window where all 
 * product groups will be displayed and where you
 * will be able to add, edit, etc. these groups.
 * On this window also available such functions
 * as "Search", "Statistics", "About" and "File"
 * situated in the menu bar.
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

public class GroupsWindow {

	private static JFrame frmGroupsWindow;
	private static JTable groupsTable; // Table where groups of products will be displayed.
	private static GroupTableModel groupsTableModel; // Model for "groupsTable".
	private JScrollPane scrollPaneForTable; // Scroll pane for "groupsTable"

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void build() {
		initialize();
		frmGroupsWindow.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGroupsWindow = new JFrame();
		frmGroupsWindow.setTitle("Groups of products");
		frmGroupsWindow.setBounds(100, 100, 800, 500);
		frmGroupsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGroupsWindow.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmGroupsWindow.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		/*
		 * Pressing this button will allow you to add new product group to array list of all product groups.
		 * New group will be also displayed in the table.
		 */
		JButton btnAddGroup = new JButton("Add product group");
		btnAddGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GroupAdder groupAdder = new GroupAdder();
				groupAdder.build();
				frmGroupsWindow.setEnabled(false);
			}
		});
		buttonPanel.add(btnAddGroup);

		/*
		 * Pressing this button will allow you to edit a chosen product group.
		 * Changes will be displayed in the table and in the array list of all product groups.
		 */
		JButton btnEditGroup = new JButton("Edit product group");
		btnEditGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (groupsTable.getSelectionModel().isSelectionEmpty() != true) {
					GroupEditor groupEditor = new GroupEditor();
					groupEditor.build();
					frmGroupsWindow.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(frmGroupsWindow, "Choose a group first", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		buttonPanel.add(btnEditGroup);

		/*
		 * Pressing this button will allow you to remove a chosen product group from the array list of all product groups.
		 * Changes will be displayed in the table.
		 */
		JButton btnRemoveGroup = new JButton("Remove product group");
		btnRemoveGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (groupsTable.getSelectionModel().isSelectionEmpty() != true) {
					int result = JOptionPane.showConfirmDialog(frmGroupsWindow,
							"Are you sure you want to delete this group?");
					if (result == JOptionPane.OK_OPTION)
						groupsTableModel.removeProductGroup(groupsTable.getSelectedRow());
				} else {
					JOptionPane.showMessageDialog(frmGroupsWindow, "Choose a group first", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		buttonPanel.add(btnRemoveGroup);

		/*
		 * Pressing this button will allow you to manage the 
		 * list of products of a chosen product group.
		 * Changes will be also displayed in the array list of all
		 * product groups.
		 */
		JButton btnManageProducts = new JButton("Manage products in product group");
		btnManageProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (groupsTable.getSelectionModel().isSelectionEmpty() != true) {
					ProductsWindow productsWindow = new ProductsWindow();
					productsWindow.build(groupsTable.getSelectedRow(), GroupTableModel.showPriceOfWaresInThisGroup(
							GroupTableModel.getProductGroup(groupsTable.getSelectedRow())));
					frmGroupsWindow.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(frmGroupsWindow, "Choose a group first", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		buttonPanel.add(btnManageProducts);

		/*
		 * Pressing this button closes whole program.
		 */
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(frmGroupsWindow, "Are you sure you want to exit?");
				if (result == JOptionPane.OK_OPTION)
					frmGroupsWindow.dispatchEvent(new WindowEvent(frmGroupsWindow, WindowEvent.WINDOW_CLOSING));
			}
		});
		buttonPanel.add(btnExit);

		groupsTableModel = new GroupTableModel();
		groupsTable = new JTable(groupsTableModel);
		groupsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneForTable = new JScrollPane(groupsTable);
		frmGroupsWindow.getContentPane().add(scrollPaneForTable, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		frmGroupsWindow.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		/*
		 * Pressing this menu item allows you to save your list of all product groups
		 * and list of all products to the files with the entered names.
		 */
		JMenuItem mntmSaveListOf = new JMenuItem("Save list of product groups and products to files");
		mntmSaveListOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileSaver fileSaver = new FileSaver();
				fileSaver.build();
				frmGroupsWindow.setEnabled(false);
			}
		});
		mnFile.add(mntmSaveListOf);

		JMenu mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);

		/*
		 * Pressing this menu item will allow you to search
		 * for the product with given name.
		 */
		JMenuItem mntmFindStuff = new JMenuItem("Find product");
		mntmFindStuff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchWindow searchWindow = new SearchWindow();
				searchWindow.build();
				frmGroupsWindow.setEnabled(false);
			}
		});
		mnSearch.add(mntmFindStuff);

		JMenu mnStatitistics = new JMenu("Statitistics");
		menuBar.add(mnStatitistics);

		/*
		 * Pressing this menu item will show you the list of all existing products
		 * together with the total cost of all products.
		 */
		JMenuItem mntmListOfAll = new JMenuItem("List of all products");
		mntmListOfAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AllProductsWindow listOfAllProducts = new AllProductsWindow();
				listOfAllProducts.build();
				frmGroupsWindow.setEnabled(false);
			}
		});
		mnStatitistics.add(mntmListOfAll);

		JMenu mnHelp = new JMenu("About");
		menuBar.add(mnHelp);

		/*
		 * Pressing this menu item will show you short informaton
		 * about program.
		 */
		JMenuItem mntmProgramGuide = new JMenuItem("About program");
		mntmProgramGuide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frmGroupsWindow, "This is the program for warehouse menagement."
						+ "\nDeveloped by: Mykola Bikchentaev, Mykola Pryjmytsch.\nv.0.99\nAll rights reserved.", "About program",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnHelp.add(mntmProgramGuide);
	}

	/**
	 * This method returns model used for the table of product groups.
	 * @return  model used for the table of product groups.
	 */
	public static GroupTableModel getGroupsTableModel() {
		return groupsTableModel;
	}

	/**
	 * This method returns frame of this window.
	 * @return frame of this window.
	 */
	public static JFrame getFrmGroupsWindow() {
		return frmGroupsWindow;
	}

	/**
	 * This method returns table used to display groups of products.
	 * @return table used to display groups of products.
	 */
	public static JTable getGroupsTable() {
		return groupsTable;
	}

}
