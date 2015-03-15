package ele.extraction.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class ReadUtil {
	public String getRawText(String filePath, int startPage)
			throws FileNotFoundException, IOException {
		PDFParser parser = new PDFParser(new FileInputStream(filePath));
		parser.parse();

		COSDocument cosDoc = parser.getDocument();
		PDDocument pdDoc = new PDDocument(cosDoc);

		PDFTextStripper pdfStripper = null;
		int number_of_pages = pdDoc.getNumberOfPages();

		pdfStripper = new PDFTextStripper();
		pdfStripper.setStartPage(startPage);
		pdfStripper.setEndPage(number_of_pages);
		String parsedText = pdfStripper.getText(pdDoc).toLowerCase();
		return parsedText;
	}
}
