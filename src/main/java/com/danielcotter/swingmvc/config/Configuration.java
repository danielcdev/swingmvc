package com.danielcotter.swingmvc.config;

import java.util.Properties;

public class Configuration {

	private static final Properties properties = loadProperties();

	public static String getRequiredSetting(String key) throws ConfigurationException {
		String answer = null;

		if (!properties.containsKey(key) || (answer = properties.getProperty(key)) == null || answer.isEmpty())
			throw new ConfigurationException("Configuration file expecting \"" + key + "\", none exist");

		return answer;
	}

	public static String getOptionalSetting(String key) {
		return properties.getProperty(key);
	}

	private static Properties loadProperties() {
		Properties properties = new Properties();

		try {
			properties.loadFromXML(Configuration.class.getResourceAsStream("/swingmvc.xml"));
		} catch (Exception e) {
			System.out.println("Make sure swingmvc.xml exists and is formatted correctly");
			e.printStackTrace();
		}

		return properties;
	}
}
