package ele.extraction.main;

import java.io.IOException;
import java.util.List;

import com.google.gdata.util.ServiceException;

import ele.extraction.drive.AnalysisData;
import ele.extraction.util.ReadUtil;

public class RunAlgorithm {
	public static void main(String[] args) {

		List<String> listOfCon = ReadUtil.getConstituencies();
		for (String cons : listOfCon) {
			AnalysisData an = new AnalysisData();
			try {
				an.reteriveData(cons);
			} catch (IOException | ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
