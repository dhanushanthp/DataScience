package ele.extraction.india.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import ele.extraction.domain.Candidate;
import ele.extraction.domain.Constituency;
import ele.extraction.domain.Types;
import ele.extraction.util.ReadUtil;

/**
 * Processing 2014 and 2009 Election dataSet
 * 
 * @author Dhanushanth
 *
 */
public class DataExtract2014_2009 {
	static Scanner sc = new Scanner(System.in);
	static String fileName = "/home/dhanu/Desktop/2014.pdf";
	static ReadUtil readUtil = new ReadUtil();

	public static void main(String[] args) throws Exception {
		getText2014(fileName, "tamil nadu");
	}

	private static void getText2014(String filePath, String constituency)
			throws IOException, FileNotFoundException {
		boolean consChecker = false;
		int countConsti = 0;
		// To get the count of electors and constituency.
		String ele_cons_st = "";

		try {
			String[] lines = readUtil.getRawText(fileName, 1).split("\n");

			// Read line by line.
			for (int i = 0; i < lines.length; i++) {
				String lineByLine = lines[i].toLowerCase().trim();

				/**
				 * Enable cons checker when it found constituency. To find
				 * constituency specific data.
				 */
				if (lineByLine.contains(constituency.toLowerCase())) {
					consChecker = true;
				}

				/**
				 * If consCheck enable start to read line by line. If disables
				 * stop the process.
				 */
				if (consChecker) {
					// System.out.println(lineByLine);
					// Count the cons
					if (lineByLine.contains("constituency :")) {
						ele_cons_st = lineByLine;
						countConsti++;
					}

					if (lineByLine.contains("bjp")
							|| lineByLine.contains("inc")) {
						if (isInteger(lineByLine)) {

							Constituency cons = new Constituency(getAttributes(
									ele_cons_st.split(":")[0].trim(),
									Types.CONSTITUENCY));

							if (fileName.contains("2014")) {
								cons.setTotalElectors(Integer
										.parseInt(ele_cons_st.split("\\s")[ele_cons_st
												.split("\\s").length - 1]));
							} else {
								cons.setTotalElectors(Integer
										.parseInt(ele_cons_st.split("\\s")[ele_cons_st
												.split("\\s").length - 5]));

							}
							Candidate c = buildStructure(lineByLine, cons);
							System.out.println(c.getConstituency().getName()
									+ ", " + c.getName() + ", " + c.getParty()
									+ ", "
									+ c.getConstituency().getTotalElectors()
									+ ", " + c.getVotes());
						} else {
							Constituency cons = new Constituency(getAttributes(
									ele_cons_st.split(":")[0].trim(),
									Types.CONSTITUENCY));

							if (fileName.contains("2014")) {
								cons.setTotalElectors(Integer
										.parseInt(ele_cons_st.split("\\s")[ele_cons_st
												.split("\\s").length - 1]));
							} else {
								cons.setTotalElectors(Integer
										.parseInt(ele_cons_st.split("\\s")[ele_cons_st
												.split("\\s").length - 5]));

							}
							String lin = (lines[i - 2].toLowerCase().trim()
									+ " " + lines[i - 1].toLowerCase() + " " + lineByLine)
									.trim();
							Candidate c = buildStructure(lin, cons);
							System.out.println(c.getConstituency().getName()
									+ ", " + c.getName() + ", " + c.getParty()
									+ ", "
									+ c.getConstituency().getTotalElectors()
									+ ", " + c.getVotes());
						}
					}

				}

				/**
				 * 1) first char of the line should be 0 . But at the same time
				 * it should ignore our constituency first line. 2) It should
				 * contain < constituency : >
				 */
				try {
					if (lineByLine.split("\\s")[0].equals("1")
							&& lineByLine.contains("constituency :")
							&& countConsti != 1) {
						consChecker = false;
					}
				} catch (Exception e) {
					throw e;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Make candidates objects with the parameters.
	 * 
	 * @param input
	 */
	private static Candidate buildStructure(String input,
			Constituency constituency) {
		String[] array = input.split("\\s\\s");

		String name = getAttributes(array[array.length - 7], Types.NAME);
		// remove , from name.
		name = name.replace(",", "");
		int votes = Integer.parseInt(array[array.length - 4]);
		String party = getAttributes(array[array.length - 6], Types.PARTY)
				.toUpperCase();

		Candidate candidate = new Candidate(name, votes, party, constituency);

		/**
		 * Additional informations.
		 */
		candidate.setAge(Integer.parseInt(getAttributes(
				array[array.length - 6], Types.AGE)));
		candidate.setGeneralVotes(Integer.parseInt(array[array.length - 3]));
		candidate.setPostalVotes(Integer.parseInt(array[array.length - 5]));
		candidate.setSex(getAttributes(array[array.length - 7], Types.SEX)
				.toUpperCase());
		candidate.setAge(Integer.parseInt(getAttributes(
				array[array.length - 6], Types.AGE)));
		candidate.setCategory(getAttributes(array[array.length - 6],
				Types.CATEGORY).toUpperCase());

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
		} else if (type == Types.SEX) {
			return inputArray[size - 1];
		} else if (type == Types.AGE) {
			return inputArray[0];
		} else if (type == Types.CATEGORY) {
			return inputArray[1];
		} else if (type == Types.PARTY) {
			return inputArray[2];
		} else if (type == Types.CONSTITUENCY) {
			start = 1;
			end = size - 1;
		}

		String[] result = Arrays.copyOfRange(inputArray, start, end);

		for (String string : result) {
			nameSt.append(string + " ");
		}

		return nameSt.toString().trim();
	}

	/**
	 * <h3>
	 * 3 kuppu ramu.d m 57 gen bjp 465 171082 170617 17.09 11.75 lotus</h3>
	 * Check the first char of each line start with number. because the line
	 * start with number only having full length.
	 * 
	 * @param line
	 *            input line.
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
