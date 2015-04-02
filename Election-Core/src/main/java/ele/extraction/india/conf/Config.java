package ele.extraction.india.conf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ele.extraction.domain.Credentials;

public class Config {

	static Properties prop = new Properties();
	static InputStream input;
	static {
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getPath() {
		String env = "lin";
		switch (env) {
		case "mac":
			return "/Users/Dhanushanth/Google Drive/MSc Uni/CS5617 Data Science/Projects/Data Files/Extracted LOK Shaba Results/";
		case "lin":
			return "/opt/election-data/";
		default:
			break;
		}
		return new String();
	}

	public static String getWritePath() {
		return "/Users/Dhanushanth/Google Drive/MSc Uni/CS5617 Data Science/Projects/Data Files/Csv/";
	}

	public static Credentials getCredentials() {
		return new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}

	public static String getYear() {
		return String.valueOf(prop.getProperty("year"));
	}

	public static String getSpreadSheet() {
		return prop.getProperty("workbook");
	}

}
