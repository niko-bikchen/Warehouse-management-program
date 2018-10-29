package program;
/**
 * File name: GroupAdder.java
 * ====================================
 * This class initializes window which 
 * allows you to add new product groups.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GroupAdder {

	private JFrame frmGroupAdder;
	private JTextField nameTextField; // Text field for the name of the group.
	private JTextField descTextField; // Text field for description of the group.
	private String groupName, groupDesc; // Variables for the name of the group and for the description.
	private JButton btnConfirm; // Confirm button.

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void build() {
		initialize();
		frmGroupAdder.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGroupAdder = new JFrame();
		frmGroupAdder.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				GroupsWindow.getFrmGroupsWindow().setEnabled(true);
			}
		});
		frmGroupAdder.setTitle("Add product group");
		frmGroupAdder.setBounds(100, 100, 600, 140);
		frmGroupAdder.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGroupAdder.getContentPane().setLayout(new BoxLayout(frmGroupAdder.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		frmGroupAdder.getContentPane().add(panel);

		JLabel labelGroupName = new JLabel("New group name: ");
		panel.add(labelGroupName);

		nameTextField = new JTextField();
		nameTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				descTextField.requestFocus();
			}
		});
		panel.add(nameTextField);
		nameTextField.setColumns(20);

		Component verticalGlue = Box.createVerticalGlue();
		frmGroupAdder.getContentPane().add(verticalGlue);

		JPanel panel_1 = new JPanel();
		frmGroupAdder.getContentPane().add(panel_1);

		JLabel labelGroupDescription = new JLabel("New group description: ");
		panel_1.add(labelGroupDescription);

		descTextField = new JTextField();
		descTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnConfirm.requestFocus();
			}
		});
		panel_1.add(descTextField);
		descTextField.setColumns(30);

		Component verticalGlue_1 = Box.createVerticalGlue();
		frmGroupAdder.getContentPane().add(verticalGlue_1);

		JPanel panel_2 = new JPanel();
		frmGroupAdder.getContentPane().add(panel_2);

		/*
		 * In case if all the entered data is correct, by pressing this button you 
		 * will create new product group, which will be also added to the table and to
		 * the array list of all product groups.
		 */
		btnConfirm = new JButton("CONFIRM");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				groupName = nameTextField.getText();
				groupDesc = descTextField.getText();
				if (!groupName.equals("") && groupName.trim().length() > 0)
					if (!GroupsWindow.getGroupsTableModel().checkName(groupName)) {
						GroupsWindow.getGroupsTableModel().addProductGroup(new ProductGroup(groupName, groupDesc));
						frmGroupAdder.dispose();
						GroupsWindow.getFrmGroupsWindow().setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(frmGroupAdder,
								"Product group with such name alredy exists.\n" + "Enter different name.", "Attention",
								JOptionPane.ERROR_MESSAGE);
					}
				else {
					JOptionPane.showMessageDialog(frmGroupAdder, "Invalid input for name.", "Attention",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_2.add(btnConfirm);

		JSeparator separator = new JSeparator();
		panel_2.add(separator);

		/*
		 * Pressing this button will close this window.
		 */
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmGroupAdder.dispose();
				GroupsWindow.getFrmGroupsWindow().setEnabled(true);
			}
		});
		panel_2.add(btnCancel);
	}
}
