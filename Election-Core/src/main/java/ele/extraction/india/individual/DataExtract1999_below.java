package ele.extraction.india.individual;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import ele.extraction.domain.Types;

/**
 * Processing 1999 and 1998 and 1996 and 1991 and 1989 and Election dataSet
 * 
 * @author Dhanushanth
 *
 */
public class DataExtract1999_below {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		getText2014("/home/dhanu/Desktop/1991.pdf");
	}

	private static void getText2014(String filePath) throws IOException,
			FileNotFoundException {
		PDFParser parser = new PDFParser(new FileInputStream(filePath));
		parser.parse();

		COSDocument cosDoc = parser.getDocument();
		PDDocument pdDoc = new PDDocument(cosDoc);

		PDFTextStripper pdfStripper = null;
		int number_of_pages = pdDoc.getNumberOfPages();

		try {
			pdfStripper = new PDFTextStripper();
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(number_of_pages);
			String parsedText = pdfStripper.getText(pdDoc);
			String[] lines = parsedText.split("\n");

			for (int i = 0; i < lines.length; i++) {
				String lineTmp = lines[i].toLowerCase().trim();

				if (lineTmp.contains("bjp") || lineTmp.contains("inc")) {
					if (isInteger(lineTmp)) {
						System.out.println(lineTmp);
						buildStructure(lineTmp);
					} else {
						throw new RuntimeException();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			cosDoc.close();
			pdDoc.close();
		}
	}

	/**
	 * make candidates objects with the parameters.
	 * 
	 * @param input
	 */
	private static void buildStructure(String input) {
		String[] array = input.split("\\s");
		System.out.println("Name : " + getAttributes(input, Types.NAME));
		System.out.println("party : " + array[array.length - 3].toUpperCase());
		System.out.println("Sex : " + array[array.length - 4].toUpperCase());
		System.out.println("Total Vote : " + array[array.length - 2]);
		System.out.println("-------------------------------\n\n");
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
		}

		String[] name = Arrays.copyOfRange(inputArray, start, end);

		for (String string : name) {
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
