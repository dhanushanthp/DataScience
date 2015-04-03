package ele.extraction.drive;

import java.io.IOException;
import java.net.URL;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;

import ele.extraction.domain.Seats;
import ele.extraction.india.conf.Config;

public class RetriveData {

	public static final String SPREADSHEET_URL = "https://spreadsheets.google.com/feeds/spreadsheets/"
			+ Config.getSpreadSheet();

	public Seats reteriveData(String state) throws IOException,
			ServiceException {
		SpreadsheetService service = new SpreadsheetService(
				"Election Result Analysis");

		service.setUserCredentials(Config.getCredentials().getUsername(),
				Config.getCredentials().getPassword());

		URL metafeedUrl = new URL(SPREADSHEET_URL);

		SpreadsheetEntry spreadsheet = service.getEntry(metafeedUrl,
				SpreadsheetEntry.class);

		URL FeedUrlTmp = null;

		for (WorksheetEntry workSheet : spreadsheet.getWorksheets()) {
			if (workSheet.getTitle().getPlainText().contains(state)) {
				FeedUrlTmp = ((WorksheetEntry) workSheet).getCellFeedUrl();
				break;
			}
		}

		URL listFeedUrl = FeedUrlTmp;

		Seats seatsObj = new Seats();
		CellFeed cellFeed = service.getFeed(listFeedUrl, CellFeed.class);
		for (CellEntry entry : cellFeed.getEntries()) {
			switch (entry.getTitle().getPlainText()) {
			case "G2":
				seatsObj.setBjp_seats(Integer.parseInt(entry
						.getPlainTextContent()));
				break;
			case "H2":
				seatsObj.setInc_seats(Integer.parseInt(entry
						.getPlainTextContent()));
				break;
			default:
				break;
			}
		}
		return seatsObj;
	}
}