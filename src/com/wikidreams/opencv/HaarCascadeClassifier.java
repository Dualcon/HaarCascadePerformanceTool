package com.wikidreams.opencv;

import java.io.File;

import org.apache.log4j.Logger;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class HaarCascadeClassifier {


	private Logger logger = Logger.getLogger(HaarCascadeClassifier.class);

	static{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	private File cascade;
	private CascadeClassifier classifier;

	private File image;
	private Mat mat;

	private MatOfRect detections;
	private int totalDetections;

	public HaarCascadeClassifier(File cascade, File image) {
		this.cascade = cascade;
		this.image = image;
		this.totalDetections = 0;

		// Create cascade classifier.
		this.classifier = new CascadeClassifier(this.cascade.getAbsolutePath());
		if (! this.classifier.empty()) {
			// Perform test.
			this.mat = this.fileToMat(this.image.getAbsolutePath());
			if (! this.mat.empty()) {
				this.classifyImage();
			} else {
				this.logger.error("Empty image.");
			}
		} else {
			this.logger.error("Problems with classifier: " + this.cascade.getAbsolutePath());	
		}
	}


	private Mat fileToMat(String imageFilePath) {
		Mat image = Imgcodecs.imread(imageFilePath); 
		if (image.empty()) {
			this.logger.error("Empty image.");
			return null;
		}
		return image;
	}



	private void classifyImage() {
		Mat mRgba = new Mat();  
		Mat mGrey=new Mat();  
		this.mat.copyTo(mRgba);  
		this.mat.copyTo(mGrey);
		// Grayscale conversion
		Imgproc.cvtColor( mRgba, mGrey, Imgproc.COLOR_BGR2GRAY);
		// Equalizes the image for better recognition
		Imgproc.equalizeHist( mGrey, mGrey );
		// Detect how many faces are in the loaded image
		this.detections = new MatOfRect();
		this.classifier.detectMultiScale(mGrey, detections);
		this.totalDetections = this.detections.toArray().length;
	}



	public int getTotalDetections() {
		return totalDetections;
	}

}
