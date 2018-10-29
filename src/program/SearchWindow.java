package program;
/**
 * File name: SearchWindow.java
 * ========================================
 * This class initializes the window which
 * allows you to search for the product with
 * the given name.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchWindow {

	private JFrame frmSearchWindow;
	private JTextField productToLookFor; // Text field for the name of the product.
	private JScrollPane scrollPane; // Scroll pane for the text area where search result will be displayed.
	private JTextArea result; // Text area where result of the search will be displayed.

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public void build(){
		initialize();
		frmSearchWindow.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSearchWindow = new JFrame();
		frmSearchWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				GroupsWindow.getFrmGroupsWindow().setEnabled(true);
			}
		});
		frmSearchWindow.setTitle("Search");
		frmSearchWindow.setBounds(100, 100, 450, 400);
		frmSearchWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSearchWindow.getContentPane().setLayout(new BoxLayout(frmSearchWindow.getContentPane(), BoxLayout.Y_AXIS));
		
		Component verticalGlue = Box.createVerticalGlue();
		frmSearchWindow.getContentPane().add(verticalGlue);
		
		JPanel panelForSearch = new JPanel();
		frmSearchWindow.getContentPane().add(panelForSearch);
		
		JLabel lblEnterNameOf = new JLabel("Enter name of the product: ");
		panelForSearch.add(lblEnterNameOf);
		
		productToLookFor = new JTextField();
		productToLookFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!productToLookFor.getText().equals(""))
					result.setText(GroupTableModel.searchAWare(productToLookFor.getText()));
				else {
					result.setText("Enter name of the product");
				}
			}
		});
		panelForSearch.add(productToLookFor);
		productToLookFor.setColumns(15);
		
		/*
		 * By pressing this button the program will
		 * search for the product with given name.
		 * If such product exists, in the "Result" text area
		 * will be displayed information about this product.
		 */
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!productToLookFor.getText().equals(""))
					result.setText(GroupTableModel.searchAWare(productToLookFor.getText()));
				else {
					result.setText("Enter name of the product");
				}
			}
		});
		panelForSearch.add(btnSearch);
		
		JPanel panelResult = new JPanel();
		frmSearchWindow.getContentPane().add(panelResult);
		
		JLabel lblResult = new JLabel("Result: ");
		panelResult.add(lblResult);
		
		result = new JTextArea();
		result.setEditable(false);
		result.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		result.setWrapStyleWord(true);
		result.setRows(8);
		result.setColumns(45);
		scrollPane = new JScrollPane(result);
		panelResult.add(scrollPane);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		frmSearchWindow.getContentPane().add(verticalGlue_1);
		
		JPanel buttonPanel = new JPanel();
		frmSearchWindow.getContentPane().add(buttonPanel);
		
		/*
		 * Pressing this button will close the window.
		 */
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmSearchWindow.dispose();
				GroupsWindow.getFrmGroupsWindow().setEnabled(true);
			}
		});
		buttonPanel.add(btnBack);
	}

}
