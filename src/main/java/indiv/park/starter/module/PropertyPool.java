package indiv.park.starter.module;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyPool {

	private static Properties properties;

	private PropertyPool() {}

	public synchronized static void loadProperties(String configPath) throws IOException {
		File file = new File(configPath + File.separator + "application.properties");
		if (!file.exists()) {
			properties = new Properties();
			return;
		}

		try (FileReader fileReader = new FileReader(file)) {
			properties = new Properties();
			properties.load(fileReader);
		}
	}

	public static String getProperty(String property, String defaultValue) {
		return properties.getProperty(property, defaultValue);
	}

	public static String getProperty(String property) {
		return properties.getProperty(property);
	}
}
