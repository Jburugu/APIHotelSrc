package com.APIHotels.framework;

import java.io.IOException;         

public final class TestLauncher{

	private final ConfigFileReader configFileReader;

	private TestLauncher() {
		configFileReader = new ConfigFileReader();
	}

	public static void main(final String args[]) throws Exception {
		new TestLauncher().execute();
	}

	private void execute() throws IOException {
		configFileReader.readConfigFile();
	}
	
	
}
