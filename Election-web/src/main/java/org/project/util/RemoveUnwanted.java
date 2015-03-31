package org.project.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RemoveUnwanted {
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new FileReader("numbers.txt"))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.contains("[1]=")){
					System.out.println(sCurrentLine);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
