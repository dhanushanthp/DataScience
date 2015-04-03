package ele.extraction.drive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gdata.util.ServiceException;

import ele.extraction.domain.Seats;
import ele.extraction.util.ReadUtil;

public class Generate {

	public static void main(String[] args) {
		Generate g = new Generate();
		System.out.println((g.getBjpData("2014")));
	}

	public List<String> getBjpData(String year) {
		List<String> result = ReadUtil.getResultData(year);
		List<String> bjpSeats = new ArrayList<String>();

		for (String line : result) {
			bjpSeats.add(line.split(",")[1]);
		}
		return bjpSeats;
	}

	public List<String> getIncData(String year) {
		List<String> result = ReadUtil.getResultData(year);
		List<String> incSeats = new ArrayList<String>();

		for (String line : result) {
			incSeats.add(line.split(",")[2]);
		}
		return incSeats;
	}
}
