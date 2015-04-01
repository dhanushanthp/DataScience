package ele.extraction.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import ele.extraction.india.source.DataExtract2014_2009;
import ele.extraction.util.ReadUtil;
import ele.extraction.util.WriteUtil;

public class Main {
	public static void main(String[] args) {
		List<String> listOfCon = ReadUtil.geTRawText();
		for (String cons : listOfCon) {
			try {
				System.out
						.println("*****************" + cons + "*************");
				String text = DataExtract2014_2009.getText(cons);
				System.out.println(text);
				WriteUtil.writeData(cons, text);
				System.out.println("\n\n");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
