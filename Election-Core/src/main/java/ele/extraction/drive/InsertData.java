package ele.extraction.drive;

import java.io.IOException;
import java.net.URL;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;

import ele.extraction.domain.Candidate;
import ele.extraction.india.conf.Config;

public class InsertData {

	public static final String SPREADSHEET_URL = "https://spreadsheets.google.com/feeds/spreadsheets/1wNIgVxkaH3u4CLeErBFKJBOAwFdIbpxkdbv7AffKtIo";

	public static void ingestData(Candidate cadidate) throws IOException, ServiceException {
		SpreadsheetService service = new SpreadsheetService("Election Result Analysis");

		service.setUserCredentials(Config.getCredentials().getUsername(), Config.getCredentials().getPassword());

		URL metafeedUrl = new URL(SPREADSHEET_URL);

		SpreadsheetEntry spreadsheet = service.getEntry(metafeedUrl, SpreadsheetEntry.class);

		System.out.println(spreadsheet.getWorksheets().get(0).getTitle().getPlainText());
		URL listFeedUrl = ((WorksheetEntry) spreadsheet.getWorksheets().get(0)).getListFeedUrl();

		// Creating a local representation of the new row.
		ListEntry row = new ListEntry();
		row.getCustomElements().setValueLocal("Name", "Bob");
		row.getCustomElements().setValueLocal("Age", "26");
		row.getCustomElements().setValueLocal("Email", "bob@gmail.com");

		// Sending the new row for insertion into Work sheet.
		row = service.insert(listFeedUrl, row);

		// Printing all entries from Work sheet
		ListFeed feed = (ListFeed) service.getFeed(listFeedUrl, ListFeed.class);
		for (ListEntry entry : feed.getEntries()) {
			System.out.println("new row");
			for (String tag : entry.getCustomElements().getTags()) {
				System.out.println("     " + tag + ": " + entry.getCustomElements().getValue(tag));
			}
		}
	}

}