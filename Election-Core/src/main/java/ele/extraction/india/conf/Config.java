package ele.extraction.india.conf;

import ele.extraction.domain.Credentials;

public class Config {
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
		return "/Users/Dhanushanth/Google Drive/MSc Uni/CS5617 Data Science/Projects/Data Files/Csv/";
	}

	public static Credentials getCredentials() {
		return new Credentials("xxxxx@gmail.com", "xxxxxx");
	}
}
