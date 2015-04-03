package org.project.main;

import java.util.ArrayList;
import java.util.List;

import ele.extraction.util.ReadUtil;

public class Generate {	
	public List<String> getBjpData(String year) {
		List<String> listOfCons = ReadUtil.getConstituencies("web");
		List<String> bjpSeats = new ArrayList<String>();
		bjpSeats.add("1");
		bjpSeats.add("2");
		bjpSeats.add("3");
		bjpSeats.add("4");
		bjpSeats.add("5");
		bjpSeats.add("6");
		bjpSeats.add("7");
		bjpSeats.add("8");
		bjpSeats.add("9");
		bjpSeats.add("10");
		bjpSeats.add("11");
		bjpSeats.add("12");
		bjpSeats.add("13");
		bjpSeats.add("14");
		bjpSeats.add("15");
		bjpSeats.add("16");
		bjpSeats.add("17");
		bjpSeats.add("18");
		bjpSeats.add("19");
		bjpSeats.add("20");
		bjpSeats.add("21");
		bjpSeats.add("22");
		bjpSeats.add("23");
		bjpSeats.add("24");
		bjpSeats.add("25");
		bjpSeats.add("26");
		bjpSeats.add("27");
		bjpSeats.add("28");
		bjpSeats.add("29");
		bjpSeats.add("30");
		bjpSeats.add("31");
		return bjpSeats;
	}
	
	public List<String> getIncData(String year) {
		List<String> bjpSeats = new ArrayList<String>();
		bjpSeats.add("5");
		bjpSeats.add("1");
		bjpSeats.add("5");
		bjpSeats.add("7");
		bjpSeats.add("5");
		bjpSeats.add("6");
		bjpSeats.add("6");
		bjpSeats.add("8");
		bjpSeats.add("9");
		bjpSeats.add("2");
		bjpSeats.add("11");
		bjpSeats.add("12");
		bjpSeats.add("30");
		bjpSeats.add("14");
		bjpSeats.add("15");
		bjpSeats.add("16");
		bjpSeats.add("27");
		bjpSeats.add("15");
		bjpSeats.add("19");
		bjpSeats.add("65");
		bjpSeats.add("21");
		bjpSeats.add("22");
		bjpSeats.add("23");
		bjpSeats.add("24");
		bjpSeats.add("25");
		bjpSeats.add("26");
		bjpSeats.add("27");
		bjpSeats.add("28");
		bjpSeats.add("29");
		bjpSeats.add("30");
		bjpSeats.add("31");
		return bjpSeats;
	}
}
