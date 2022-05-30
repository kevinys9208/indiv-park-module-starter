package indiv.park.starter.module;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyPool {

	private static Properties properties;

	public synchronized static void loadProperties(String configPath) throws IOException {
		File file = new File(configPath + File.separator + "application.properties");
		if (!file.exists()) {
			properties = new Properties();
			return;
		}
		
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
			
			properties = new Properties();
			properties.load(fileReader);

		} finally {
			if (fileReader != null) fileReader.close();
		}
	}

	public static String getProperty(String property, String defaultValue) {
		return properties.getProperty(property, defaultValue);
	}

	public static String getProperty(String property) {
		return properties.getProperty(property);
	}
}
