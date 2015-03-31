package org.project.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlExtractor {
	public static void main(String argv[]) {

		try {

			
			File fXmlFile = new File("file.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("path");

			StringBuilder sb = new StringBuilder();

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

//				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					sb.append(eElement.getAttribute("d") + " ");
//					System.out.println("Staff id : " + eElement.getAttribute("title"));
//					System.out.println("Staff id : " + eElement.getAttribute("d"));

				}
			}
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
