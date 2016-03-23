package com.wikidreams.ui;

import com.wikidreams.properties.PropertiesManager;

public class App {

	public static void main(String[] args) {
		// Load project properties.
		PropertiesManager.loadProperties("resources/config.properties");

		// Create project folders and load files.
		ProjectFoldersManager.create();

		// Create the dialog menu.
		DialogManager.createMenu();
	}

}
