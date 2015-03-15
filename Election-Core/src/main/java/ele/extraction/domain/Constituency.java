package ele.extraction.domain;

public class Constituency {
	private String name;
	private int totalElectors;
	private int validVotes;
	private int numberOfCandidates;

	public Constituency() {

	}

	public Constituency(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalElectors() {
		return totalElectors;
	}

	public void setTotalElectors(int totalElectors) {
		this.totalElectors = totalElectors;
	}

	public int getValidVotes() {
		return validVotes;
	}

	public void setValidVotes(int validVotes) {
		this.validVotes = validVotes;
	}

	public int getNumberOfCandidates() {
		return numberOfCandidates;
	}

	public void setNumberOfCandidates(int numberOfCandidates) {
		this.numberOfCandidates = numberOfCandidates;
	}
}
