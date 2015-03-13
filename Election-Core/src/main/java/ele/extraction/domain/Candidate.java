package ele.extraction.domain;

public class Candidate {
	private String name;
	private int age;
	private String category;
	private String Symbol;
	private int votes;
	private String party;
	private int postalVotes;
	private String sex;
	private Constituency constituency;
	private int generalVotes;

	public Candidate() {

	}

	public Candidate(String name, int votes, String party,
			Constituency constituency) {
		this.name = name;
		this.votes = votes;
		this.party = party;
		this.constituency = constituency;
	}

	public int getGeneralVotes() {
		return generalVotes;
	}

	public void setGeneralVotes(int generalVotes) {
		this.generalVotes = generalVotes;
	}

	public int getPostalVotes() {
		return postalVotes;
	}

	public void setPostalVotes(int postalVotes) {
		this.postalVotes = postalVotes;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSymbol() {
		return Symbol;
	}

	public void setSymbol(String symbol) {
		Symbol = symbol;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
}
