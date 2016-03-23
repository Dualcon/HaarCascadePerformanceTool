package com.wikidreams.ui;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.wikidreams.filesystem.FileManager;
import com.wikidreams.performance.HaarCascadePerformanceManager;
import com.wikidreams.properties.PropertiesManager;

public class ProjectFoldersManager {

	private static Logger logger = Logger.getLogger(ProjectFoldersManager.class);


	public static void create() {
		// Create workspace folder.
		File file = new File(PropertiesManager.properties.get("WorkSpace"));
		if (! file.exists()) {
			file.mkdir();
		}

		// Create tests folder.
		file = new File(PropertiesManager.properties.get("TestsFolder"));
		if (! file.exists()) {
			file.mkdir();	
		}

		// Create positive test images folder.
		file = new File(PropertiesManager.properties.get("PositiveTestImagesFolder"));
		if (! file.exists()) {
			file.mkdir();	
		}

		// Create negative test images folder.
		file = new File(PropertiesManager.properties.get("NegativeTestImagesFolder"));
		if (! file.exists()) {
			file.mkdir();	
		}

		// Load cascade files from workspace.		
		ArrayList<File> cascades = FileManager.loadFilesFromFolderRecursive(PropertiesManager.properties.get("WorkSpace"));
		for (File f : cascades) {
			if (f.getName().equals("cascade.xml")) {
				HaarCascadePerformanceManager.cascadeFiles.add(f);
			}
		}

		// Load positive test images.
		ArrayList<File> positives = FileManager.loadFilesFromFolderRecursive(PropertiesManager.properties.get("PositiveTestImagesFolder"));
		for (File f : positives) {
			if ((FilenameUtils.getExtension(f.getAbsolutePath())).equals("jpg") || (FilenameUtils.getExtension(f.getAbsolutePath())).equals("JPG")) {
				HaarCascadePerformanceManager.positiveImageFiles.add(f);	
			}
		}

		// Load negative test images.
		ArrayList<File> negatives = FileManager.loadFilesFromFolderRecursive(PropertiesManager.properties.get("NegativeTestImagesFolder"));
		for (File f : negatives) {
			if ((FilenameUtils.getExtension(f.getAbsolutePath())).equals("jpg") || (FilenameUtils.getExtension(f.getAbsolutePath())).equals("JPG")) {
				HaarCascadePerformanceManager.negativeImageFiles.add(f);	
			}
		}

	}

}
