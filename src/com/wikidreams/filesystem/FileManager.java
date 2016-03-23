package com.wikidreams.filesystem;

import java.io.File;
import java.util.ArrayList;

public class FileManager {

	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FileManager.class);


	private static void displayDirectoryContents(File dir, ArrayList<File> result) {
		try {
			File[] files = dir.listFiles();

			for (File file : files) {
				if (file.isDirectory()) {
					//System.out.println("directory:" + file.getAbsolutePath());
					displayDirectoryContents(file, result);
				} else {
					//System.out.println("     file:" + file.getAbsolutePath());
					result.add(new File(file.getAbsolutePath()));
				}		
			}

		} catch (Exception e) {
			FileManager.logger.error(e.getMessage());
		}
	}

	public static ArrayList<File> loadFilesFromFolderRecursive(String path) {
		ArrayList<File> files = new ArrayList<>();
		displayDirectoryContents(new File(path), files);
		return files;
	}


	public static File[] loadFilesFromFolder(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		return listOfFiles;
	}

}
