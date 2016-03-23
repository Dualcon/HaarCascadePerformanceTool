package com.wikidreams.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.wikidreams.performance.HaarCascadePerformanceManager;

public class DialogManager {

	public static void createMenu() {
		JTextField testName = new JTextField();
		testName.setToolTipText("Test name");

		JComboBox<String> combo = new JComboBox<>();

		// Add all cascades to the combo.
		for (File file : HaarCascadePerformanceManager.cascadeFiles) {
			String path = new String(file.getAbsolutePath());
			//String[] result = path.split(Pattern.quote(File.separator));
			//combo.addItem(result[result.length - 3] +  "(" + result[result.length - 1] + ")");
			combo.addItem(path);
		}

		JComboBox<String> comboTest = new JComboBox<>();

		// Add cascades to test process.
		JButton addBtn = new JButton("Add Cascade to test");
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboTest.addItem(combo.getItemAt(combo.getSelectedIndex()));
				HaarCascadePerformanceManager.cascadesToTest.add(new File(combo.getItemAt(combo.getSelectedIndex())));
				comboTest.repaint();
			}
		});

		Object[] message = {
				"testName:", testName,
				"combo:", combo,
				"addBtn:", addBtn,
				"comboTest:", comboTest
		};

		int option = JOptionPane.showConfirmDialog(null, message, "Haar Cascade Performance Tool", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			HaarCascadePerformanceManager.testName = testName.getText(); 
			HaarCascadePerformanceManager.start();
		}
	}

}
