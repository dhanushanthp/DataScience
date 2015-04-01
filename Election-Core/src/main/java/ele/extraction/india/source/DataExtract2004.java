package ele.extraction.india.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import ele.extraction.domain.Candidate;
import ele.extraction.domain.Constituency;
import ele.extraction.domain.Types;
import ele.extraction.india.conf.Config;
import ele.extraction.util.ReadUtil;

/**
 * Processing 2004 and Election dataSet
 * 
 * @author Dhanushanth
 *
 */
public class DataExtract2004 {
	static Scanner sc = new Scanner(System.in);
	static String fileName = Config.getPath() + "2004.pdf";
	static ReadUtil readUtil = new ReadUtil();

	public static void main(String[] args) throws Exception {
		getText(fileName, "tamil nadu");
	}

	private static void getText(String filePat, String state) throws IOException, FileNotFoundException {
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
				if (lineByLine.contains(state.toLowerCase())) {
					stateChecker = true;
				}
				if (stateChecker) {

					// Count the constituency
					if (lineByLine.contains("PC No. & Name :".toLowerCase())) {
						constituency = getAttributes(lineByLine, Types.CONSTITUENCY);
						int count = 0;
						// This is to get the total votes and electors count.
						// Once it found the constituency the
						// process search through to find total votes and
						// electors.
						while (true) {
							if (lines[i + count].toLowerCase().contains("Total Valid Votes for the PC:".toLowerCase())) {
								ele_cons_st = lines[i + count].toLowerCase();
								break;
							}
							count++;
						}
						String votes = ele_cons_st.replace("Total Valid Votes for the PC: ".toLowerCase(), "").split("-")[0];
						validVotes = Integer.parseInt(votes.substring(0, votes.length() - String.valueOf(countConstituency).length()));
						countConstituency++;
					}

					if (lineByLine.contains("bjp") || lineByLine.contains("inc")) {
						if (isInteger(lineByLine)) {
							Constituency constitency = new Constituency(constituency);
							constitency.setValidVotes(validVotes);
							Candidate c = buildStructure(lineByLine, constitency);
							System.out.println(c.getConstituency().getName() + ", " + c.getName() + ", " + c.getParty() + ", "
									+ c.getConstituency().getValidVotes() + ", " + c.getVotes());
						} else {
							System.out.println("This is : Valid Postal Ballots for each candidate in the PC");
//							throw new RuntimeException();
						}
					}
				}

				if (lineByLine.contains("state-ut code & name :".toLowerCase()) && (countConstituency != 0)) {
					stateChecker = false;
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
		//TODO This need to be fixed.
		int votes = Integer.parseInt(array[0]);
		String party = array[array.length - 1].toUpperCase();

		Candidate candidate = new Candidate(name, votes, party, constituency);

		// Additional information
		candidate.setSex("Not Assigned");
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
			start = 1;
			end = size - 1;
		} else if (type == Types.CONSTITUENCY) {
			start = 0;
			end = size-4;
		}

		String[] name = Arrays.copyOfRange(inputArray, start, end);

		for (String string : name) {
			if(string.contains("-")){
				nameSt.append(string.split("-")[1] + " ");
			}else if(string.contains("pc")){
				nameSt.append(string.replace("pc", "") + " ");
			}else{
			nameSt.append(string + " ");
			}
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
