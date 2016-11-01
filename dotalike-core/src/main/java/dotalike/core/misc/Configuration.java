package dotalike.core.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

	public static String pathFromSteamToDota = "SteamApps\\common\\dota 2 beta\\game\\dota";

	public static final String CONFIG_FILE = "dotalike.properties";

	private static Configuration instance;

	public static Configuration getInstance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
	}

	public String read(ConfigurationKey key) {
		String systemProperty = System.getProperties().getProperty(key.getKey());
		if (systemProperty != null) {
			return systemProperty;
		}

		Properties properties = new Properties();
		File file = new File(CONFIG_FILE);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			properties.load(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (String) properties.getOrDefault(key.getKey(), key.getDefaultValue());
	}

	public static enum ConfigurationKey {

		PROXY("proxy.http", ""), PROXY_PORT("proxy.http_port", ""), ENV("env", "prod");

		private String key;

		private String defaultValue;

		private ConfigurationKey(String key, String defaultValue) {
			this.key = key;
			this.defaultValue = defaultValue;
		}

		public String getDefaultValue() {
			return defaultValue;
		}

		public String getKey() {
			return key;
		}

	}

}
