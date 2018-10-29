package program;
/**
 * File name: InitProdList.java
 * ===================================================
 * This class creates window that allows you to choose
 * a file where the list of all products is stored.
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class InitProdList {

	private static JFrame frmProductListFileChooser; 
	private static JTextField filePathField; // Text field where path to the file will be displayed.
	private static String productListFilePath; // Variable for file's path.

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public void build() {
		initialize();
		frmProductListFileChooser.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
		frmProductListFileChooser = new JFrame();
		frmProductListFileChooser.setTitle("File with list of products");
		frmProductListFileChooser.setBounds(100, 100, 500, 150);
		frmProductListFileChooser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProductListFileChooser.getContentPane()
				.setLayout(new BoxLayout(frmProductListFileChooser.getContentPane(), BoxLayout.Y_AXIS));

		JPanel infoPanel = new JPanel();
		frmProductListFileChooser.getContentPane().add(infoPanel);

		JLabel infoLabel = new JLabel("Select file where the list of products is stored");
		infoLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		infoPanel.add(infoLabel);

		Component verticalGlue = Box.createVerticalGlue();
		frmProductListFileChooser.getContentPane().add(verticalGlue);

		JPanel filePathPanel = new JPanel();
		frmProductListFileChooser.getContentPane().add(filePathPanel);

		JLabel lblNewLabel_1 = new JLabel("Path: ");
		filePathPanel.add(lblNewLabel_1);

		/*
		 * Text field where will be displayed path to the file
		 * that you have chosen by using "File..." button.
		 */
		filePathField = new JTextField();
		lblNewLabel_1.setLabelFor(filePathField);
		filePathField.setToolTipText("FIile path displays here");
		filePathPanel.add(filePathField);
		filePathField.setColumns(20);

		/*
		 * Pressing this button will open up file chooser
		 * where you can navigate and choose file where
		 * list of all products is stored.
		 */
		JButton btnNewButton = new JButton("File...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				int action = fileChooser.showDialog(frmProductListFileChooser, "Open file");
				if (action == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					filePathField.setText(file.getPath());
				}
			}
		});
		filePathPanel.add(btnNewButton);

		Component verticalGlue_1 = Box.createVerticalGlue();
		frmProductListFileChooser.getContentPane().add(verticalGlue_1);

		JPanel confirmButtonPanel = new JPanel();
		frmProductListFileChooser.getContentPane().add(confirmButtonPanel);

		/*
		 * By pressing this button, this window will be closed and
		 * the other, where the list of all products is displayed, will be opened.
		 */
		JButton confirmButton = new JButton("CONFIRM");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!filePathField.getText().equals("")) {
					productListFilePath = filePathField.getText();
					try {
						PullFileInfo.pullProductsLists(productListFilePath);
						GroupsWindow groupsWindow = new GroupsWindow();
						groupsWindow.build();
						frmProductListFileChooser.dispose();
					} catch (IOException e) {
						if (e instanceof FileNotFoundException) {
							JOptionPane.showMessageDialog(frmProductListFileChooser, "File wasn't found", "Attention",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} else
					JOptionPane.showMessageDialog(frmProductListFileChooser, "You didn't chose a file", "Attention",
							JOptionPane.ERROR_MESSAGE);
				confirmButton.setEnabled(false);
			}
		});
		confirmButtonPanel.add(confirmButton);
	}

	public JFrame getFrmProductListFileChooser() {
		return frmProductListFileChooser;
	}

	public String getProductListFilePath() {
		return productListFilePath;
	}

}
