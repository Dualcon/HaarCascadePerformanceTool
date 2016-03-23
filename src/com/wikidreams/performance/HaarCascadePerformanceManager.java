package com.wikidreams.performance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.wikidreams.opencv.HaarCascadeClassifier;
import com.wikidreams.properties.PropertiesManager;

public class HaarCascadePerformanceManager {


	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HaarCascadePerformanceManager.class);

	public static String testName;

	public static ArrayList<File> cascadeFiles = new ArrayList<>();
	public static ArrayList<File> positiveImageFiles = new ArrayList<>();
	public static ArrayList<File> negativeImageFiles = new ArrayList<>();
	public static ArrayList<File> cascadesToTest = new ArrayList<>();

	private static ArrayList<HaarCascadeTest> tests = new ArrayList<>();

	public static void start() {
		HaarCascadePerformanceManager.logger.info("Performing test:");
		if (! HaarCascadePerformanceManager.cascadesToTest.isEmpty()) {

			// Test all choosen cascades.
			for (File cascadeFile : HaarCascadePerformanceManager.cascadesToTest) {

				HaarCascadeTest test = new HaarCascadeTest(cascadeFile);

				// With the positive images.
				for (File positiveImageFile : HaarCascadePerformanceManager.positiveImageFiles) {
					HaarCascadeClassifier posClassifier = new HaarCascadeClassifier(cascadeFile, positiveImageFile);
					test.setTototalPositivesFound(test.getTototalPositivesFound() + posClassifier.getTotalDetections());
					posClassifier = null;
				}

				// With the negative images.
				for (File negativeImageFile : HaarCascadePerformanceManager.negativeImageFiles) {
					HaarCascadeClassifier negClassifier = new HaarCascadeClassifier(cascadeFile, negativeImageFile);
					test.setTototalNegativesFound(test.getTototalNegativesFound() + negClassifier.getTotalDetections());
					negClassifier = null;
				}
				HaarCascadePerformanceManager.tests.add(test);
				test = null;
			}

			HaarCascadePerformanceManager.consoleReport();
			HaarCascadePerformanceManager.fileReport();
		} else {
			HaarCascadePerformanceManager.logger.error("No cascades selected.");			
		}
	}



	private static void consoleReport() {
		HaarCascadePerformanceManager.logger.info("Haar cascades loaded: " + HaarCascadePerformanceManager.cascadesToTest.size());
		HaarCascadePerformanceManager.logger.info("Positive images loaded: " + HaarCascadePerformanceManager.positiveImageFiles.size());
		HaarCascadePerformanceManager.logger.info("Negative images loaded: " + HaarCascadePerformanceManager.negativeImageFiles.size());
		for (HaarCascadeTest test : HaarCascadePerformanceManager.tests) {
			HaarCascadePerformanceManager.logger.info("Cascade: " + test.getCascade().getAbsolutePath() + " Total positives: " + test.getTototalPositivesFound() + " Total negatives " + test.getTototalNegativesFound());
		}
		HaarCascadePerformanceManager.logger.info("Test terminated.");
	}


	private static void fileReport() {
		StringBuilder report = new StringBuilder();
		report.append("Haar cascades loaded: " + HaarCascadePerformanceManager.cascadesToTest.size() + "\n");
		report.append("Positive images loaded: " + HaarCascadePerformanceManager.positiveImageFiles.size() + "\n");
		report.append("Negative images loaded: " + HaarCascadePerformanceManager.negativeImageFiles.size() + "\n\n");
		report.append("Cascade,Positives,Negatives\n");
		for (HaarCascadeTest test : HaarCascadePerformanceManager.tests) {
			report.append(test.getCascade().getAbsolutePath() + "," + test.getTototalPositivesFound() + "," + test.getTototalNegativesFound() + "\n");
		}
		report.append("\n" + "Test terminated.\n");
		try {
			File reportFile = new File(PropertiesManager.properties.get("TestsFolder") + HaarCascadePerformanceManager.testName + ".log");
			FileWriter reportFileWriter = new FileWriter(reportFile);
			BufferedWriter reportBufferedWriter = new BufferedWriter(reportFileWriter);
			reportBufferedWriter.write(report.toString());
			reportBufferedWriter.close();
		} catch (IOException e) {
			HaarCascadePerformanceManager.logger.error("Can not create the report file.");
		}
	}

}
