package ele.extraction.drive;

import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;

import ele.extraction.india.conf.Config;
import ele.extraction.util.ReadUtil;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class CreateWorkSheet {
	
	public static void createWorkSheet() throws AuthenticationException, MalformedURLException, IOException, ServiceException {

		SpreadsheetService service = new SpreadsheetService("Indian Election Analysis");

		service.setUserCredentials(Config.getCredentials().getUsername(), Config.getCredentials().getPassword());

		// Define the URL to request. This should never change.
		URL SPREADSHEET_FEED_URL = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");

		// Make a request to the API and get all spreadsheets.
		SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL, SpreadsheetFeed.class);
		List<SpreadsheetEntry> spreadsheets = feed.getEntries();

		SpreadsheetEntry spreadsheet = null;
		//Get each individual spread sheets.
		for (SpreadsheetEntry spread : spreadsheets) {
			if (spread.getTitle().getPlainText().contains(Config.getYear())) {
				spreadsheet = spread;
			}
		}

		List<String> constituencies = ReadUtil.getConstituencies();
		for (String constituency : constituencies) {
			WorksheetEntry worksheet = new WorksheetEntry();
			worksheet.setTitle(new PlainTextConstruct(constituency));
			worksheet.setColCount(100);
			worksheet.setRowCount(100);

			System.out.println("Creating " + constituency + " Spread Sheet ...");
			URL worksheetFeedUrl = spreadsheet.getWorksheetFeedUrl();
			WorksheetEntry wSheet = service.insert(worksheetFeedUrl, worksheet);

			CellFeed cellFeed = service.getFeed(wSheet.getCellFeedUrl(), CellFeed.class);

			CellEntry cellEntry = new CellEntry(1, 1, "Constituency");
			cellFeed.insert(cellEntry);
			cellEntry = new CellEntry(1, 2, "Candidate");
			cellFeed.insert(cellEntry);
			cellEntry = new CellEntry(1, 3, "Party");
			cellFeed.insert(cellEntry);
			cellEntry = new CellEntry(1, 4, "ValidVotes");
			cellFeed.insert(cellEntry);
			cellEntry = new CellEntry(1, 5, "CandidateVotes");
			cellFeed.insert(cellEntry);
		}

	}
}