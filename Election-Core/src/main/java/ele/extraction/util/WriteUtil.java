package ele.extraction.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ele.extraction.india.conf.Config;

public class WriteUtil {
	public static void writeData(String fileName, String data) {
		try {

			File file = new File(Config.getWritePath() + fileName + ".csv");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void writeDataWithoutOverwrite(String fileName, String data) {
		File file = new File(Config.getWritePath() + fileName + ".csv");
		try {
			if (file.exists() == false) {
				System.out.println("We had to make a new file.");
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(new FileWriter(file, true));
			out.append(data + "\n");
			out.close();
		} catch (IOException e) {
			System.out.println("COULD NOT LOG!!");
		}
	}
	
	
}
