package ele.extraction.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.google.gdata.util.ServiceException;

import ele.extraction.drive.CreateWorkSheet;
import ele.extraction.drive.InsertData;
import ele.extraction.india.conf.Config;
import ele.extraction.india.source.DataExtract1996;
import ele.extraction.india.source.DataExtract1998;
import ele.extraction.india.source.DataExtract2004;
import ele.extraction.india.source.DataExtract2014_2009;
import ele.extraction.util.ReadUtil;
import ele.extraction.util.WriteUtil;

public class Main {
	public static void main(String[] args) {
//		try {
//			CreateWorkSheet.createWorkSheet();
//		} catch (IOException | ServiceException e1) {
//			e1.printStackTrace();
//		}
		
		List<String> listOfCon = ReadUtil.getConstituencies();
		for (String cons : listOfCon) {
			try {
				System.out.println("*****************" + cons + "*************");
				if (Config.getYear().equals("2014") || Config.getYear().equals("2009")) {
					DataExtract2014_2009.getText(cons);
				} else if (Config.getYear().equals("1998")) {
					DataExtract1998.getText(cons);
				} else if (Config.getYear().equals("2004")) {
					DataExtract2004.getText(cons);
				} else {
					DataExtract1996.getText(cons);
				}
				// WriteUtil.writeData(cons, text);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
