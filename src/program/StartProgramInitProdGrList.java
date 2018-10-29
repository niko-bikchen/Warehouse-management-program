package program;
/**
 * File name: StartProgram_InitProdGrList.java
 * ===================================================
 * This class creates window that allows you to choose
 * a file where the list of all product groups is stored.
 * It also starts the whole program.
 */
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;

public class StartProgramInitProdGrList {

	private JFrame frmProductsGroupFileChoose;
	private JTextField filePathField;// Text field where path to the file will be displayed. 
	private String productGroupsFilePath; // Variable for file's path.

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartProgramInitProdGrList window = new StartProgramInitProdGrList();
					window.frmProductsGroupFileChoose.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the application.
	 */
	public StartProgramInitProdGrList() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProductsGroupFileChoose = new JFrame();
		frmProductsGroupFileChoose.setTitle("File with product groups");
		frmProductsGroupFileChoose.setBounds(100, 100, 500, 150);
		frmProductsGroupFileChoose.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProductsGroupFileChoose.getContentPane()
				.setLayout(new BoxLayout(frmProductsGroupFileChoose.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panelForInfoLabel = new JPanel();
		frmProductsGroupFileChoose.getContentPane().add(panelForInfoLabel);
		panelForInfoLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel infoLabel = new JLabel("Select file where the list with product groups is stored");
		infoLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panelForInfoLabel.add(infoLabel);

		Component verticalGlue_1 = Box.createVerticalGlue();
		frmProductsGroupFileChoose.getContentPane().add(verticalGlue_1);

		JPanel panelForFileChoosing = new JPanel();
		frmProductsGroupFileChoose.getContentPane().add(panelForFileChoosing);

		JLabel pathLebel = new JLabel("Path: ");
		panelForFileChoosing.add(pathLebel);

		/*
		 * Text field where will be displayed path to the file
		 * that you have chosen by using "File..." button.
		 */
		filePathField = new JTextField();
		pathLebel.setLabelFor(filePathField);
		filePathField.setToolTipText("FIile path displays here");
		panelForFileChoosing.add(filePathField);
		filePathField.setColumns(20);
		filePathField.setEditable(true);

		/*
		 * Pressing this button will open up file chooser
		 * where you can navigate and choose file where
		 * list of all groups is stored.
		 */
		JButton fileButton = new JButton("File...");
		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				int action = fileChooser.showDialog(frmProductsGroupFileChoose, "Open file");
				if (action == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					filePathField.setText(file.getPath());
				}
			}
		});
		panelForFileChoosing.add(fileButton);

		Component verticalGlue = Box.createVerticalGlue();
		frmProductsGroupFileChoose.getContentPane().add(verticalGlue);

		JPanel panelForConfirmButton = new JPanel();
		frmProductsGroupFileChoose.getContentPane().add(panelForConfirmButton);

		/*
		 * By pressing this button, this window will be closed and
		 * the other, where you choose file where list of all products
		 * is stored, will be opened.
		 */
		JButton confirmButton = new JButton("CONFIRM");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!filePathField.getText().equals("")) {
					productGroupsFilePath = filePathField.getText();
					try {
						PullFileInfo.pullProductGroupList(productGroupsFilePath);
						InitProdList productsListFileChooser = new InitProdList();
						productsListFileChooser.build();
						frmProductsGroupFileChoose.dispose();
					} catch (IOException e) {
						if (e instanceof FileNotFoundException) {
							JOptionPane.showMessageDialog(frmProductsGroupFileChoose, "File wasn't found",
									"Attention", JOptionPane.ERROR_MESSAGE);
						}	
					}
				} else
					JOptionPane.showMessageDialog(frmProductsGroupFileChoose, "You didn't chose a file", "Attention",
							JOptionPane.ERROR_MESSAGE);
				confirmButton.setEnabled(false);
			}
		});
		panelForConfirmButton.add(confirmButton);
	}

	public String getProductGroupsFilePath() {
		return productGroupsFilePath;
	}

	public JFrame getFrmProductsGroupFileChoose() {
		return frmProductsGroupFileChoose;
	}
	
	

}
