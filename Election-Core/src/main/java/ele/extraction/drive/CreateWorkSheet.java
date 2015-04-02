package ele.extraction.drive;

import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;

import ele.extraction.india.conf.Config;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class CreateWorkSheet {
	public static void main(String[] args) throws AuthenticationException,
			MalformedURLException, IOException, ServiceException {

		SpreadsheetService service = new SpreadsheetService(
				"Indian Election Analysis");

		service.setUserCredentials(Config.getCredentials().getUsername(),
				Config.getCredentials().getPassword());

		// Define the URL to request. This should never change.
		URL SPREADSHEET_FEED_URL = new URL(
				"https://spreadsheets.google.com/feeds/spreadsheets/private/full");

		// Make a request to the API and get all spreadsheets.
		SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,
				SpreadsheetFeed.class);
		List<SpreadsheetEntry> spreadsheets = feed.getEntries();

		SpreadsheetEntry spreadsheet = null;
		for (SpreadsheetEntry spread : spreadsheets) {
			System.out.println(spread.getTitle().getPlainText());
			if (spread.getTitle().getPlainText().contains("2014")) {
				spreadsheet = spread;
			}
		}

		for (int i = 0; i < 5; i++) {
			WorksheetEntry worksheet = new WorksheetEntry();
			worksheet.setTitle(new PlainTextConstruct("dhanu e" + i));
			worksheet.setColCount(100);
			worksheet.setRowCount(100);

			URL worksheetFeedUrl = spreadsheet.getWorksheetFeedUrl();
			WorksheetEntry wSheet = service.insert(worksheetFeedUrl, worksheet);

			CellFeed cellFeed = service.getFeed(wSheet.getCellFeedUrl(),
					CellFeed.class);

			CellEntry cellEntry = new CellEntry(1, 1, "headline1");
			cellFeed.insert(cellEntry);
			cellEntry = new CellEntry(1, 2, "headline2");
			cellFeed.insert(cellEntry);
			cellEntry = new CellEntry(1, 3, "headline2");
			cellFeed.insert(cellEntry);
			cellEntry = new CellEntry(1, 4, "headline2");
			cellFeed.insert(cellEntry);
		}

	}
}