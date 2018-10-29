package program;

/**
 * File name: FileSaver.java
 * This class initializes the window that allows
 * you to save list of all product groups and 
 * list of all products to the files with the
 * entered names.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FileSaver {

	private JFrame frmFileSaver;
	private JTextField prodGrFileName; // Text field for the name of the file for list of product groups.
	private JTextField prodFileName; // Text field for the name of the file for list of products.
	private String fileNameForPrGroups;  // Variable for the name of the file for list of product groups.
	private String fileNameForProducts; // Variable for the name of the file for list of products.
	private JButton btnConfirm; // Confirm button.

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void build() {
		initialize();
		frmFileSaver.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFileSaver = new JFrame();
		frmFileSaver.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				GroupsWindow.getFrmGroupsWindow().setEnabled(true);
			}
		});
		frmFileSaver.setTitle("Save data");
		frmFileSaver.setBounds(100, 100, 550, 250);
		frmFileSaver.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmFileSaver.getContentPane().setLayout(new BoxLayout(frmFileSaver.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panelForProdGrFile = new JPanel();
		frmFileSaver.getContentPane().add(panelForProdGrFile);

		JLabel lblEnterNameOf = new JLabel("File name where all product groups will be saved: ");
		panelForProdGrFile.add(lblEnterNameOf);

		prodGrFileName = new JTextField();
		prodGrFileName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prodFileName.requestFocus();
			}
		});
		panelForProdGrFile.add(prodGrFileName);
		prodGrFileName.setColumns(15);

		Component verticalGlue = Box.createVerticalGlue();
		frmFileSaver.getContentPane().add(verticalGlue);

		JPanel panelForProdListFile = new JPanel();
		frmFileSaver.getContentPane().add(panelForProdListFile);

		JLabel lblFileNameWhere = new JLabel("File name where all products will be saved: ");
		panelForProdListFile.add(lblFileNameWhere);

		prodFileName = new JTextField();
		prodFileName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnConfirm.requestFocus();
			}
		});
		panelForProdListFile.add(prodFileName);
		prodFileName.setColumns(15);

		Component verticalGlue_1 = Box.createVerticalGlue();
		frmFileSaver.getContentPane().add(verticalGlue_1);

		JPanel buttonPanel = new JPanel();
		frmFileSaver.getContentPane().add(buttonPanel);

		/*
		 * After pressing this button there will be created two files:
		 * one with product groups, other with the list of all products.
		 * These files will have ".txt" format and names the user have entered.
		 */
		btnConfirm = new JButton("CONFIRM");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileNameForPrGroups = prodGrFileName.getText();
				fileNameForProducts = prodFileName.getText();
				if ((fileNameForPrGroups.trim().length() > 0 && !fileNameForPrGroups.equals(""))) {
					if (fileNameForProducts.trim().length() > 0 && !fileNameForProducts.equals("")) {
						if (!fileNameForPrGroups.equals(fileNameForProducts)) {
							try {
								PushFileInfo.writeToFile(fileNameForPrGroups + ".txt",
										GroupTableModel.getProductGroups());
								PushFileInfo.writeToFile(fileNameForProducts + ".txt",
										GroupTableModel.showAllStatistics());
								JOptionPane.showMessageDialog(frmFileSaver, "Operation successfull", "Attention",
										JOptionPane.INFORMATION_MESSAGE);
								frmFileSaver.dispose();
								GroupsWindow.getFrmGroupsWindow().setEnabled(true);
							} catch (FileNotFoundException e) {
								JOptionPane.showMessageDialog(frmFileSaver, "File wasn't found", "Attention",
										JOptionPane.WARNING_MESSAGE);
								e.printStackTrace();
							} catch (IOException e) {
								JOptionPane.showMessageDialog(frmFileSaver, "Erro occured while writing files",
										"Attention", JOptionPane.ERROR_MESSAGE);
								e.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(frmFileSaver, "File names cannot be equal", "Attention",
									JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(frmFileSaver, "Invalid input for file name for products",
								"Attention", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frmFileSaver, "Invalid input for file name for product groups",
							"Attention", JOptionPane.WARNING_MESSAGE);
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
				frmFileSaver.dispose();
				GroupsWindow.getFrmGroupsWindow().setEnabled(true);
			}
		});
		buttonPanel.add(btnCancel);
	}

}
