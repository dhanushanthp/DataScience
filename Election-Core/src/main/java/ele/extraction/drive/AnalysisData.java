package ele.extraction.drive;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;

import ele.extraction.domain.Seats;
import ele.extraction.india.conf.Config;

public class AnalysisData {

	public static final String SPREADSHEET_URL = "https://spreadsheets.google.com/feeds/spreadsheets/"
			+ Config.getSpreadSheet();

	public void reteriveData(String state) throws IOException,
			ServiceException {
		SpreadsheetService service = new SpreadsheetService(
				"Election Result Analysis");

		service.setUserCredentials(Config.getCredentials().getUsername(),
				Config.getCredentials().getPassword());

		URL metafeedUrl = new URL(SPREADSHEET_URL);

		SpreadsheetEntry spreadsheet = service.getEntry(metafeedUrl,
				SpreadsheetEntry.class);

		URL FeedUrlTmp = null;
		URL CellFeedUrlTmp = null;

		for (WorksheetEntry workSheet : spreadsheet.getWorksheets()) {
			if (workSheet.getTitle().getPlainText().contains(state)) {
				FeedUrlTmp = ((WorksheetEntry) workSheet).getListFeedUrl();
				CellFeedUrlTmp = ((WorksheetEntry) workSheet).getCellFeedUrl();
				break;
			}
		}

		URL listFeedUrl = FeedUrlTmp;
		URL CelllistFeedUrl = CellFeedUrlTmp;

		Seats seatsObj = new Seats();
		CellFeed cellFeed = service.getFeed(CelllistFeedUrl, CellFeed.class);
		
		//		 Printing all entries from Work sheet
		ListFeed feed = (ListFeed) service.getFeed(listFeedUrl, ListFeed.class);
		
		List<ListEntry> listOfEntries = feed.getEntries();
		int totalRowSize = listOfEntries.size();
		try{
		for (int i = 0; i <= totalRowSize; i++) {
			System.out.println("processing " + i +  " th raw  ");
			String cons1 = listOfEntries.get(i).getCustomElements().getValue("Constituency");
			String cons2 = listOfEntries.get(i+1).getCustomElements().getValue("Constituency");
			if(cons1.equals(cons2)){
				int v1 = Integer.parseInt(listOfEntries.get(i).getCustomElements().getValue("CandidateVotes"));
				int v2 = Integer.parseInt(listOfEntries.get(i+1).getCustomElements().getValue("CandidateVotes"));
				if(v1 > v2){
					CellEntry cellEntry = new CellEntry(i+2, 6, listOfEntries.get(i).getCustomElements().getValue("Party"));
					cellFeed.insert(cellEntry);	
					
				}else{
					CellEntry cellEntry = new CellEntry(i+3, 6, listOfEntries.get(i+1).getCustomElements().getValue("Party"));
					cellFeed.insert(cellEntry);
				}
				i = i+1;
			}else{
				CellEntry cellEntry = new CellEntry(i+2, 6, listOfEntries.get(i).getCustomElements().getValue("Party"));
				cellFeed.insert(cellEntry);
			}
		}
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public static void main(String[] args) {
		AnalysisData an = new AnalysisData();
		try {
			an.reteriveData("jammu & kashmir");
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}