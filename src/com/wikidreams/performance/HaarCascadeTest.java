package com.wikidreams.performance;

import java.io.File;

public class HaarCascadeTest {

	private File cascade;
	private int tototalPositivesFound;
	private int tototalNegativesFound;

	public HaarCascadeTest(File cascade) {
		super();
		this.cascade = cascade;
		this.tototalPositivesFound = 0;
		this.tototalNegativesFound = 0;
	}

	public File getCascade() {
		return cascade;
	}

	public void setCascade(File cascade) {
		this.cascade = cascade;
	}

	public int getTototalPositivesFound() {
		return tototalPositivesFound;
	}

	public void setTototalPositivesFound(int tototalPositivesFound) {
		this.tototalPositivesFound = tototalPositivesFound;
	}

	public int getTototalNegativesFound() {
		return tototalNegativesFound;
	}

	public void setTototalNegativesFound(int tototalNegativesFound) {
		this.tototalNegativesFound = tototalNegativesFound;
	}

}
