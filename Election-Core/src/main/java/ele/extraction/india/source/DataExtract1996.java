package ele.extraction.india.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import com.google.gdata.util.ServiceException;

import ele.extraction.domain.Candidate;
import ele.extraction.domain.Constituency;
import ele.extraction.domain.Types;
import ele.extraction.drive.InsertData;
import ele.extraction.india.conf.Config;
import ele.extraction.util.ReadUtil;

/**
 * Processing 1996 and below Election dataSet
 * 
 * @author Dhanushanth
 *
 */
public class DataExtract1996 {
	static Scanner sc = new Scanner(System.in);
	static String fileName = Config.getPath() + Config.getYear() + ".pdf";
	static ReadUtil readUtil = new ReadUtil();

	public static void main(String[] args) throws Exception {
		getText("andhra pradesh");
	}

	public static void getText(String state) throws IOException, FileNotFoundException {
		boolean stateChecker = false;
		int countConstituency = 0;

		String constituency = "";
		// To get the count of electors and constituency.
		String ele_cons_st = "";

		int totalElectors = 0;
		int validVotes = 0;

		try {
			String[] lines = readUtil.getRawText(fileName, 1).split("\n");

			// Read line by line
			for (int i = 0; i < lines.length; i++) {
				String lineByLine = lines[i].toLowerCase().trim();

				// Enable when constituency found
				if (lineByLine.startsWith(state.toLowerCase()) && lines[i + 1].toLowerCase().trim().startsWith("constituency")) {
					stateChecker = true;
				}
				if (stateChecker) {

					// Count the constituency
					if (lineByLine.startsWith("constituency")) {
						constituency = getAttributes(lineByLine, Types.CONSTITUENCY);
						int count = 0;
						// This is to get the total votes and electors count.
						// Once it found the constituency the
						// process search through to find total votes and
						// electors.
						while (true) {
							if (lines[i + count].toLowerCase().contains("electors")
									&& lines[i + count].toLowerCase().contains("poll percentage")) {
								ele_cons_st = lines[i + count].toLowerCase();
								break;
							}
							count++;
						}
						validVotes = Integer.parseInt(ele_cons_st.split("\\s\\s")[1].split("\\s")[3].replace("voters", ""));
						countConstituency++;
					}

					if (lineByLine.contains("bjp") || lineByLine.contains("inc")) {
						if (isInteger(lineByLine)) {
							Constituency constitency = new Constituency(constituency);
							constitency.setTotalElectors(totalElectors);
							constitency.setValidVotes(validVotes);
							Candidate c = buildStructure(lineByLine, constitency);
							try {
								// Ingest to google sheet
								InsertData.ingestData(c.getConstituency().getName() + "," + c.getName() + "," + c.getParty() + ","
										+ c.getConstituency().getValidVotes() + "," + c.getVotes(), state);
							} catch (ServiceException e) {
								e.printStackTrace();
							}
						} else {
							throw new RuntimeException();
						}
					}

					if (lineByLine.contains("constituency  : 1 .") && (countConstituency != 1)) {
						stateChecker = false;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * make candidates objects with the parameters.
	 * 
	 * @param input
	 */
	private static Candidate buildStructure(String input, Constituency constituency) {
		String[] array = input.split("\\s");
		String name = getAttributes(input, Types.NAME);
		name = name.replace(",", "");
		int votes = Integer.parseInt(array[array.length - 2]);
		String party = array[array.length - 3].toUpperCase();

		Candidate candidate = new Candidate(name, votes, party, constituency);

		// Additional information
		candidate.setSex(array[array.length - 4].toUpperCase());
		return candidate;
	}

	/**
	 * Depend on each string array location we need to pick each attribute.
	 * 
	 * @param input
	 * @param type
	 * @return
	 */
	private static String getAttributes(String input, Types type) {
		int start = 0;
		int end = 0;

		StringBuffer nameSt = new StringBuffer();
		String[] inputArray = input.split("\\s");
		int size = inputArray.length;

		if (type == Types.NAME) {
			start = 2;
			end = size - 4;
		} else if (type == Types.CONSTITUENCY) {
			start = 5;
			end = size;
		}

		String[] name = Arrays.copyOfRange(inputArray, start, end);

		for (String string : name) {
			nameSt.append(string + " ");
		}

		return nameSt.toString().trim();
	}

	/**
	 * 
	 * @param line
	 * @return
	 */
	private static boolean isInteger(String line) {
		try {
			String[] lists = line.split("\\s");
			Integer.parseInt(lists[0]);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;

	}
}
