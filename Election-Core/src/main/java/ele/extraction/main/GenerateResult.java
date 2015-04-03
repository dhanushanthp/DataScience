package ele.extraction.main;

import java.io.IOException;
import java.util.List;

import com.google.gdata.util.ServiceException;

import ele.extraction.domain.Seats;
import ele.extraction.drive.AnalysisData;
import ele.extraction.drive.RetriveData;
import ele.extraction.india.conf.Config;
import ele.extraction.util.ReadUtil;
import ele.extraction.util.WriteUtil;

public class GenerateResult {
	public static void main(String[] args) {

		List<String> listOfCon = ReadUtil.getConstituencies();
		for (String cons : listOfCon) {
			RetriveData rd = new RetriveData();
			try {
				Seats result = rd.reteriveData(cons);
				WriteUtil.writeDataWithoutOverwrite(Config.getYear(), cons + ","
						+ result.getBjp_seats() + ", " + result.getInc_seats());
			} catch (IOException | ServiceException e) {
				e.printStackTrace();
			}
		}
	}
}
