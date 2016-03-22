package com.wikidreams.ui;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DialogManager {

	public static void createMenu() {
		JTextField processName = new JTextField();
		processName.setToolTipText("Process Name");

				Object[] message = {
				"processName:", processName
		};

		int option = JOptionPane.showConfirmDialog(null, message, "Create samples.", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
		
		}
}

}
