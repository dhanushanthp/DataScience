package ele.extraction.domain;

public class DataInput {
	private String constituencyName;
	private String[] parties;
	private String[] years;

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String[] getParties() {
		return parties;
	}

	public void setParties(String[] parties) {
		this.parties = parties;
	}

	public String[] getYears() {
		return years;
	}

	public void setYears(String[] years) {
		this.years = years;
	}
}
