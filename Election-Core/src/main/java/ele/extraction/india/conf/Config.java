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
			input = new FileInputStream("/Users/Dhanushanth/git/election-india-analysis/Election-Core/src/main/resources/config.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getPath() {
		String env = "mac";
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
		return "/Users/Dhanushanth/Google Drive/MSc Uni/CS5617 Data Science/Projects/Data Files/Csv/Result/";
	}
	
	public static String getResultPath() {
		return "/Users/Dhanushanth/Google Drive/MSc Uni/CS5617 Data Science/Projects/Data Files/Csv/Result/";
	}

	public static Credentials getCredentials() {
		return new Credentials(prop.getProperty("username"),
				prop.getProperty("password"));
	}

	public static String getYear() {
		return String.valueOf(prop.getProperty("year"));
	}

	// 04 and 99 pending
	public static String getSpreadSheet() {
		switch (Integer.parseInt(prop.getProperty("year"))) {
		case 2014:
			return "1-6Jc0gwqaWzsRtFw0r6q_cQP_OeLXGLbHDKR1Yp8v6o";
		case 2009:
			return "1Kn20y4RZ_JdaSmzTEsTRRl2yHYU_1X4h8dhREGhRAYI";
		case 2004:
			return "18Dvox3zg_ZdJOn-kuseCC0Q_DxO1RX_rK-dE7kj_JxE";
		case 1999:
			return "1ZtrSg_UcRM_tIww5jRIUU5a1K40VN6mUC4dhU5nQbv0";
		case 1998:
			return "19fQz7V7WG_Jpu-8Dyx7w5_ofj9Ib4WQWFUUoGfE5oiY";
		case 1996:
			return "18Vc7pnVGGZAI3Mn868-zMGH9BARK6prHeVblJY9Zn5Y";
		case 1991:
			return "1h2TnwT-IyOzepE39WA0vt-007L-dh1TkRLV4RFOVwtc";
		case 1989:
			return "1TXo2JkpJxYbQvde6wo-MivYmrRC8DJpXDfhLyhahV30";
		case 1984:
			return "1q47hDCad7gY9rOgm7wlERylrY-RL5eEjjtT5_2EHEpg";
		default:
			break;
		}
		return prop.getProperty("workbook");
	}

}
