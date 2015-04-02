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
		switch (Integer.parseInt(prop.getProperty("year"))) {
		case 1991:
			return  "1eMyNnPnE0OQaP5tUeb4y7GMcdfxJUxlyxqVaT3SbQOk";
		case 1989:
			return  "1jM1zIyL-bh39k6yD3CVgSwKYVlyn4V08mISXXMgK3es";
		case 1984:
			return  "10b7WUKjpIEijwdXVowpoGJ6KqRkF3uTZ7dU1DOoDxhk";
		case 1980:
			return  "1JvXRiwASjNYdFbl3GT9ntpKp-70ocLaZo8bXEjl_kYM";
		case 1977:
			return  "1BMxkadSI_KeQvGQnSMjrFZdoqrfoapZtzkmdYywBo8I";
		case 1971:
			return  "1e4WfkBt1W9BOKS7r6cjtfxUi_OCsxU1CjHzSvPHPn_o";
		default:
			break;
		}
		return prop.getProperty("workbook");
	}

}
